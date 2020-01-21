from django.db import models

# Create your models here.


class Crawling(models.Model):
    pcode = models.AutoField(primary_key=True)
    pplace = models.CharField(max_length=50)
    purl = models.TextField()
    pname = models.CharField(max_length=50)
    psource = models.CharField(max_length=25)
