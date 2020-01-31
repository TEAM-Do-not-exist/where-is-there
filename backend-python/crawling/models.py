from django.db import models

# Create your models here.


class Photo(models.Model):
    class Meta:
        db_table = 'photo'

    pcode = models.AutoField(primary_key=True)
    pplace = models.CharField(max_length=300)
    purl = models.CharField(max_length=500)
    pname = models.CharField(max_length=200)
    psource = models.CharField(max_length=500)


class PhotoCheck(models.Model):
    class Meta:
        db_table = 'photo_check'

    pcode = models.AutoField(primary_key=True)
    purl = models.CharField(max_length=500)
