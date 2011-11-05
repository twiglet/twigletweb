from django.conf.urls.defaults import *

# Uncomment the next two lines to enable the admin:
from django.contrib import admin
admin.autodiscover()

urlpatterns = patterns('',

    # Uncomment the admin/doc line below and add 'django.contrib.admindocs' 
    # to INSTALLED_APPS to enable admin documentation:
    (r'^admin/doc/', include('django.contrib.admindocs.urls')),

    # Uncomment the next line to enable the admin:
    (r'^admin/', include(admin.site.urls)),

    (r'^contact/$', 'webapp.views.contact'),
    (r'^contact_thanks/$', 'webapp.views.contact_thanks'),
    (r'^download/$', 'webapp.views.download'),
    (r'^download_thanks/$', 'webapp.views.download_thanks'),
    (r'^download_trial/$', 'webapp.views.download_trial'),
    (r'^how/$', 'webapp.views.how'),
    (r'^features/$', 'webapp.views.features'),
    (r'^showcase/$', 'webapp.views.showcase'),
    (r'^showcase/helloworld/$', 'webapp.views.showcase_helloworld'),
    (r'^showcase/playground/$', 'webapp.views.showcase_showoff'),
    (r'^showcase/generics/$', 'webapp.views.showcase_generics'),
    (r'^showcase/delegates/$', 'webapp.views.showcase_delegates'),
    (r'^showcase/rustici/$', 'webapp.views.showcase_rustici'),
    (r'^showcase/rustici/dirtree_help$', 'webapp.views.showcase_dirtree_help'),
    (r'^showcase/code_license_none/$', 'webapp.views.showcase_license_none'),
    (r'^showcase/code_license_apache_20/$', 'webapp.views.showcase_license_apache_20'),
    (r'^showcase/code_license_sestoft/$', 'webapp.views.showcase_license_sestoft'),
    (r'^documentation/$', 'webapp.views.cs2jdocumentation'),
    (r'^$', 'webapp.views.home'),
    (r'^util/displaytree/(?P<project>\w+)/(?P<fname>(\w|\.)*)/?$', 'webapp.views.util_display'),
    (r'^util/displaycode/(?P<formatting>(pretty|plain))/(?P<project>\w+)/(?P<fname>(\w|\.)*)/?$', 'webapp.views.util_display_code'),
    (r'^util/download/(?P<project>\w+)/(?P<fname>(\w|\.)*)/?$', 'webapp.views.util_download'),


)
