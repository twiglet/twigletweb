from django.forms import ModelForm, Textarea, TextInput
from webapp.models import Contact
from webapp.models import Download

class ContactForm(ModelForm):
    class Meta:
        model = Contact
        exclude = ('contact_date',)
        widgets = {
            'name': TextInput(attrs={'size': 30, 'minlength' : 3, 'class' : 'entrybox round required'}),
            'email': TextInput(attrs={'size': 30, 'class' : 'entrybox round required email'}),
            'phone': TextInput(attrs={'size': 30, 'class' : 'entrybox round'}),
            'comment': Textarea(attrs={'cols': 50, 'rows': 10, 'class' : 'entrybox round required'}),
        }
        

class DownloadForm(ModelForm):
    class Meta:
        model = Download
        exclude = ('download_date', 'download_version')
        widgets = {
            'name': TextInput(attrs={'size': 30, 'minlength' : 3, 'class' : 'entrybox round required'}),
            'email': TextInput(attrs={'size': 30, 'class' : 'entrybox round required email'}),
            'phone': TextInput(attrs={'size': 30, 'class' : 'entrybox round'}),
            'comment': Textarea(attrs={'cols': 50, 'rows': 10, 'class' : 'entrybox round'}),
        }
