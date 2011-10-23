from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
from django.contrib import admin
admin.autodiscover()

urlpatterns = patterns('',
    # Example:
    # (r'^twiglet/', include('twiglet.foo.urls')),

    # Uncomment the admin/doc line below and add 'django.contrib.admindocs' 
    # to INSTALLED_APPS to enable admin documentation:
    (r'^admin/doc/', include('django.contrib.admindocs.urls')),

    # Uncomment the next line to enable the admin:
    (r'^admin/', include(admin.site.urls)),

    (r'^contact/$', 'twiglet.views.contact'),
    (r'^contact_thanks/$', 'twiglet.views.contact_thanks'),
    (r'^download/$', 'twiglet.views.download'),
    (r'^download_thanks/$', 'twiglet.views.download_thanks'),
    (r'^download_trial/$', 'twiglet.views.download_trial'),
    (r'^how/$', 'twiglet.views.how'),
    (r'^features/$', 'twiglet.views.features'),
    (r'^showcase/$', 'twiglet.views.showcase'),
    (r'^showcase/helloworld/$', 'twiglet.views.showcase_helloworld'),
    (r'^showcase/generics/$', 'twiglet.views.showcase_generics'),
    (r'^showcase/delegates/$', 'twiglet.views.showcase_delegates'),
    (r'^showcase/rustici/$', 'twiglet.views.showcase_rustici'),
    (r'^showcase/rustici/dirtree_help$', 'twiglet.views.showcase_dirtree_help'),
    (r'^showcase/code_no_license/$', 'twiglet.views.showcase_no_license'),
    (r'^documentation/$', 'twiglet.views.cs2jdocumentation'),
    (r'^$', 'twiglet.views.home'),
    (r'^util/displaytree/(?P<project>\w+)/(?P<fname>(\w|\.)*)/?$', 'twiglet.views.util_display'),
    (r'^util/displaycode/(?P<formatting>(pretty|plain))/(?P<project>\w+)/(?P<fname>(\w|\.)*)/?$', 'twiglet.views.util_display_code'),
    (r'^util/download/(?P<project>\w+)/(?P<fname>(\w|\.)*)/?$', 'twiglet.views.util_download'),


)
