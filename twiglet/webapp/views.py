from pygments import formatters, highlight, lexers
from django.conf import settings
from django.shortcuts import get_object_or_404, render_to_response
from django.http import HttpResponseRedirect, HttpResponse
from django.core.urlresolvers import reverse
from django.template import RequestContext
from django.core.mail import send_mail
from django.http import Http404
from webapp.forms import ContactForm, DownloadForm
from webapp.utils import get_trial_version, get_file_tree, get_file_tree_jstree
from django.core.servers.basehttp import FileWrapper

import os, mimetypes, zipfile
import simplejson
from cStringIO import StringIO

def home(request):
    return render_to_response('twiglet/home.html')

def how(request):
    return render_to_response('twiglet/how.html')

def features(request):
    return render_to_response('twiglet/features.html')

def cs2jdocumentation(request):
    return render_to_response('twiglet/cs2jdocumentation.html')

def showcase(request):
    return render_to_response('twiglet/showcase.html')

##
## Show cases
##

def showcase_helloworld(request):
    csharp_listing = get_file_tree_jstree(os.path.join(settings.TWIGLET_SHOWCASE_DIR, "generics", "csharp"), "generics", "csnode", "csharp")
    java_listing = get_file_tree_jstree(os.path.join(settings.TWIGLET_SHOWCASE_DIR, "generics", "java"), "generics", "jnode", "java")
    return render_to_response('twiglet/showcases/helloworld.html', {'csharp_tree_json': simplejson.dumps(csharp_listing), 'java_tree_json': simplejson.dumps(java_listing)})

def showcase_generics(request):
    return render_to_response('twiglet/showcases/generics.html')

def showcase_delegates(request):
    return render_to_response('twiglet/showcases/delegates.html')

def showcase_rustici(request):
    return render_to_response('twiglet/showcases/rustici.html')

def showcase_dirtree_help(request):
    return render_to_response('twiglet/showcases/dirtree_help.html')

def showcase_no_license(request):
    return render_to_response('twiglet/showcases/license_none.html')


##
## Contact Form
##
def contact(request):
    if request.method == 'POST':
        form = ContactForm(request.POST)
        if form.is_valid():
            form.save()
            cd = form.cleaned_data
            send_mail(
                "CS2J Online Contact from %s" % cd['name'],
                "Name: %(name)s\nEmail: %(email)s\nMessage: %(comment)s" % cd,
                cd.get('email'),
                ['info+test@twigletsoftware.com'],
            )
            return HttpResponseRedirect(reverse('webapp.views.contact_thanks'))
    else:
        form = ContactForm()
    return render_to_response('twiglet/contact_form.html', {'form': form}, context_instance=RequestContext(request))

def contact_thanks(request):
    return render_to_response('twiglet/contact_thanks.html')

##
##
##

##
## Download Form
##
def download(request):
    if request.method == 'POST':
##        form = DownloadForm(dict(download_version = get_trial_version(), **request.POST))
        form = DownloadForm(request.POST)
        if form.is_valid():
            download=form.save(commit=False)
            download.download_version = get_trial_version()
            download.save()
            cd = form.cleaned_data
            send_mail(
                "CS2J Trial download by %s" % cd['name'],
                "Name: %(name)s\nEmail: %(email)s\nVersion: %(download_version)s\nMessage: %(comment)s" % dict(download_version = get_trial_version(), **cd),
                cd.get('email'),
                ['info+test@twigletsoftware.com'],
            )
            request.session['allow_trial_download'] = True
            return HttpResponseRedirect(reverse('webapp.views.download_thanks'))
    else:
        form = DownloadForm()
    return render_to_response('twiglet/download_form.html', {'form': form}, context_instance=RequestContext(request))

def download_thanks(request):
    if request.session.get('allow_trial_download', False):
        return render_to_response('twiglet/download_thanks.html')
    else:
        return HttpResponseRedirect(reverse('webapp.views.download'))
        
def download_trial(request):
    if request.session.get('allow_trial_download', False):
        ## Send a file through Django without loading the whole file into              
        ## memory at once. The FileWrapper will turn the file object into an           
        ## iterator for chunks of 8KB.                                                 
        filename = settings.TWIGLET_TRIAL_DIR + '/cs2j-trial-latest.zip' # Select your file here.                                
        wrapper = FileWrapper(file(filename))
        response = HttpResponse(wrapper, content_type='application/zip')
        response['Content-Disposition'] = 'attachment; filename=cs2j-trial-latest.zip'
        response['Content-Length'] = os.path.getsize(filename)
        return response
    else:
        return HttpResponseRedirect(reverse('webapp.views.download'))

##
##
##

##
## Utils (ajax etc.)
##

def util_display(request, project, fname):
    try:
        ## underscores are directory characters
        my_project = os.path.join(*project.split('_'))
        my_dirname = os.path.join(*fname.split('_'))
    
        listing = get_file_tree_jstree(os.path.join(settings.TWIGLET_SHOWCASE_DIR, my_project, my_dirname), project, fname)
        ##listing_ul = tree_to_ul(listing)
        return render_to_response('twiglet/display_tree.html', {'tree_json': simplejson.dumps(listing)})
    except Exception:
        ## todo
        raise


def util_display_code(request, project, fname, formatting='pretty'):
    try:
        ## in project underscores are directory characters
        my_project = os.path.join(*project.split('_'))
        my_fname = os.path.join(*fname.split('_'))

        code_file = os.path.join(settings.TWIGLET_SHOWCASE_DIR, my_project, my_fname)
        if not(os.path.isfile(code_file)):
            raise Http404
        source = open(code_file, 'r')
        if formatting == 'pretty':
            formatted_display = highlight(source.read(),
                                          lexers.get_lexer_for_filename(os.path.basename(my_fname)),
                                          formatters.HtmlFormatter(linenos=True))
        else:
            formatted_display = '<pre>' + source.read().replace('<','&lt;') + '</pre>'

        return render_to_response('twiglet/display_code.html', {'project': my_project, 'fname' : my_fname, 'display' : formatted_display, 'formatting' : formatting })
    except Exception:
        ## todo
        raise

def util_download(request, project, fname, mimetype = None):
    try:
        ## in project underscores are directory characters
        my_project = os.path.join(*project.split('_'))
        my_fname = os.path.join(*fname.split('_'))
    
        target = os.path.join(settings.TWIGLET_SHOWCASE_DIR, my_project, my_fname)
        if os.path.isfile(target):
            filename = os.path.basename(target)
            if mimetype is None:
                mimetype, encoding = mimetypes.guess_type(filename)
            wrapper = FileWrapper(file(target))
            response = HttpResponse(wrapper, mimetype=mimetype)
            response['Content-Disposition'] = 'attachment; filename=%s' % filename
            response['Content-Length'] = os.path.getsize(target)
            return response
        elif os.path.isdir(target):
            archive_name = os.path.basename(target)
            if target.endswith(os.sep):
                archive_name = os.path.basename(target[:len(target) - len(os.sep)])
            temp = StringIO()
            archive = zipfile.ZipFile(temp, 'w', zipfile.ZIP_DEFLATED)
            for root, dirs, files in os.walk(target):
                #NOTE: ignore empty directories
                for fn in files:
                    absfn = os.path.join(root, fn)
                    zfn = os.path.join(archive_name, absfn[len(target):]) #XXX: relative path
                    archive.write(absfn, zfn.encode("latin-1"))
            archive.close()
            size = temp.tell()
            temp.seek(0)
            response = HttpResponse(temp.getvalue(), content_type='application/zip')
            response['Content-Disposition'] = 'attachment; filename=%s.zip' % archive_name
            response['Content-Length'] = size
            temp.close()
            return response
        else:
            raise Http404
    except Exception:
        ## todo
        raise
##
##
##