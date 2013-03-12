from django.db import models

# Create your models here.

class Contact(models.Model):
    name = models.CharField(max_length=50)
    email = models.EmailField()
    phone = models.CharField(max_length=50, blank=True)
    comment = models.TextField()
    contact_date = models.DateTimeField('contact date', auto_now_add=True)
    contact_version = models.CharField(max_length=50, default="unknown")
    def __unicode__(self):
        return "Contact: " + self.name

class Download(models.Model):
    name = models.CharField(max_length=50)
    email = models.EmailField()
    phone = models.CharField(max_length=50, blank=True)
    comment = models.TextField(blank=True)
    download_version = models.CharField(max_length=50, default="unknown")
    download_date = models.DateTimeField('download date', auto_now_add=True)
    def __unicode__(self):
        return "Download: " + self.name


