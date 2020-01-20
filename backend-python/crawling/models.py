from django.db import models

# Create your models here.


class HashTag(models.Model):
    hashtag = models.CharField(max_length=100)


class Tour(models.Model):
    link = models.TextField()
    hashtags = models.ManyToManyField(HashTag, related_name='tours')


class Image(models.Model):
    url = models.TextField()
    tour = models.ForeignKey(
        Tour, on_delete=models.CASCADE, related_name='images')
