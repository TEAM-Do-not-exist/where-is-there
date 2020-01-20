from rest_framework.decorators import api_view
from rest_framework.response import Response
from .models import HashTag, Image, Tour
from .serializers import TourSerializer
from datetime import datetime, date
import subprocess
import json
import os

# Create your views here.


def init_datas(length):
    subprocess.call(
        f'python ./crawling/instagram-crawler/crawler.py posts_full -u travelholic_insta -n {length} -o ./crawling/output/travelholic.json --fetch_details', shell=True)

    with open('./crawling/output/travelholic.json', 'r', encoding='utf-8') as travelholic:
        datas = json.load(travelholic)

    tours = []
    for data in datas:
        key = data.get('key')
        tour = Tour.objects.get_or_create(link=key)[0]

        hashtags = []
        try:
            caption = data.get('caption').replace('\n', '')
            for i in range(len(caption)):
                if caption[i] == '#':
                    for j in range(i + 1, len(caption)):
                        if caption[j] in [' ', '#']:
                            tag = caption[i + 1:j]
                            break
                    hashtag = HashTag.objects.get_or_create(hashtag=tag)[0]
                    hashtags.append(hashtag)
        except:
            error = HashTag.objects.get_or_create(hashtag='NoHashTag')[0]
            hashtags.append(error)

        img_urls = data.get('img_urls')
        for img in img_urls:
            Image.objects.get_or_create(url=img, tour=tour)

        for hashtag in hashtags:
            tour.hashtags.add(hashtag)
        tours.append(tour)

    return TourSerializer(tours, many=True)


@api_view(['GET', ])
def root(request):
    return Response({'message': 'main page'}, 200)


@api_view(['GET', ])
def insta_tour(request):
    length = int(request.GET.get('length'))

    files = os.listdir('./crawling/output')
    if 'travelholic.json' not in files:
        tours_serializer = init_datas(length)
    else:
        with open('./crawling/output/travelholic.json', 'r', encoding='utf-8') as travelholic:
            datas = json.load(travelholic)

        end_date = datas[0].get('datetime')[:10]
        now_date = date.strftime(date.today(), '%Y-%m-%d')
        if len(datas) != length or now_date != end_date:
            tours_serializer = init_datas(length)
        else:
            tours_serializer = TourSerializer(
                Tour.objects.filter(id__lte=length), many=True)

    return Response(tours_serializer.data, 200)
