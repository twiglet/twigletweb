from django.conf import settings
from django.core import serializers
from django.core.urlresolvers import reverse

import os
import re

def get_active_version():
    """
    Get the version of the curent trial.  If file trial_version.txt exists then use
    contents.
    Otherwise look for all files, find largest version number and use that.
    """
    version = 'unknown'
    if os.path.isfile(os.path.join(settings.TWIGLET_RELEASE_DIR, "version.txt")):
        version_file = open(os.path.join(settings.TWIGLET_RELEASE_DIR, "version.txt"), 'r')
        version = version_file.readline().strip()
    else:
        fnames = os.listdir(settings.TWIGLET_RELEASE_DIR)
        ## go through the list looking for trial archives, extract version number and remember biggest
        ## e.g. cs2j-110323-a.zip
        pat = re.compile('cs2j-(?:-)(.*).zip')
        for fname in fnames:
           m = pat.search(fname)
           if m and (version == 'unknown' or m.group(1) > version):
               version = m.group(1)
    return version

def get_file_tree(path, filter = ''):
    """
    Returns a dictionary containing all sub-directories and files. Filter files matching argument.
    """
    pat = re.compile(filter)
    cum_dirs = {}
    cum_files = []

    if os.path.isfile(path):
        fname = os.path.basename(path)
        if pat.search(fname):
            return ({},[fname])
        else:
            return ({},[])
    elif os.path.isdir(path):
        fnames = os.listdir(path)
        for fname in fnames:
            if os.path.isdir(os.path.join(path,fname)):
                cum_dirs[fname] = get_file_tree(os.path.join(path,fname), filter)
            elif os.path.isfile(os.path.join(path,fname)) and pat.search(fname):
                cum_files.append(fname)
    return (cum_dirs, cum_files)

def get_file_tree_jstree(path, project_token, node_id = 'node', fname_token = '', filter = ''):
    patmap = [(re.compile('.cs$'), "csharpfile"),(re.compile('.java$'), "javafile")]
    def get_iconclass(fname, defclass = ''):
        for pat,display_klass in patmap:
            if pat.search(fname):
                return display_klass
        return defclass
    def mk_fname_token(suffix):
        if not fname_token:
            return suffix
        else:
            return fname_token + '_' + suffix
    
    """
    Returns json to be consumed by jstree
    """
    pat = re.compile(filter)
    dirs = []
    files = []
    sibling = 0
    fnames = []

    if os.path.isfile(path):
        path,fname = os.path.split(path)
        fnames = [fname]
    elif os.path.isdir(path):
        fnames = os.listdir(path)
    for fname in fnames:
        sibling = sibling + 1
        this_id = '_'.join([node_id, str(sibling)])
        if os.path.isdir(os.path.join(path,fname)):
            dirs.append({'data' : {'title' : fname,
                                   'attr' : {'class' : 'folder',
                                             'project' : project_token,
                                             'fname' : mk_fname_token(fname),
                                             }
                                   },
                         'attr' : {'class' : 'folder', 'id' : this_id,},
                         'children': get_file_tree_jstree(os.path.join(path,fname), project_token, this_id, mk_fname_token(fname), filter)
                         })
        elif os.path.isfile(os.path.join(path,fname)) and pat.search(fname):
            files.append({'data' : {'title' : fname,
                                    'attr' : { 'project' : project_token,
                                               'fname' : mk_fname_token(fname),
                                               'href' : reverse('webapp.views.util_display_code', args=['pretty', project_token, mk_fname_token(fname)]),
                                               'target' : '_blank',
                                               },
                                    'icon' : get_iconclass(fname)},
                          'attr' : {'id' : this_id,},
                          })
    dirs.extend(files)
    return dirs

