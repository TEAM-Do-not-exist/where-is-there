from rest_framework import serializers
from .models import HashTag, Image, Tour


class HashTagSerializer(serializers.ModelSerializer):
    class Meta:
        model = HashTag
        fields = '__all__'


class ImageSerializer(serializers.ModelSerializer):
    class Meta:
        model = Image
        fields = '__all__'


class TourSerializer(serializers.ModelSerializer):
    hashtags = HashTagSerializer(many=True)
    images = ImageSerializer(many=True)

    class Meta:
        model = Tour
        fields = '__all__'
