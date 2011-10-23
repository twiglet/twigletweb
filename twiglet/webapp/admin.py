from webapp.models import Contact
from webapp.models import Download
from django.contrib import admin

class ContactAdmin(admin.ModelAdmin):
    list_display = ('name', 'email', 'contact_date')
    readonly_fields = ('contact_date',)

admin.site.register(Contact, ContactAdmin)


class DownloadAdmin(admin.ModelAdmin):
    list_display = ('name', 'email', 'download_date')
    readonly_fields = ('download_date',)
    
admin.site.register(Download, DownloadAdmin)