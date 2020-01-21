from rest_framework.decorators import api_view
from rest_framework.response import Response
from .models import Tour
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

    tours = {}
    for data in datas:
        source = data.get('key')
        tour = Tour.objects.filter(psource=source)

        if len(tour) == 0:
            code = len(tours) + 1
            url = data.get('img_urls')

            hashtags = []
            words = data.get('caption')
            if words != None:
                words = words.replace('\n', '')
                for i in range(len(words)):
                    if words[i] == '#':
                        for j in range(i + 1, len(words)):
                            if words[j] in [' ', '#']:
                                hashtags.append(words[i + 1:j])
                                break

            tours[code] = {
                'pcode': code,
                'purl': url,
                'psource': source,
                'pplace_pname': hashtags
            }

    return tours


@api_view(['GET', ])
def root(request):
    return Response({'message': 'main page'}, 200)


@api_view(['GET', ])
def insta_tour(request):
    length = int(request.GET.get('length'))

    files = os.listdir('./crawling/output')
    if 'travelholic.json' not in files:
        tours = init_datas(length)
    else:
        with open('./crawling/output/travelholic.json', 'r', encoding='utf-8') as travelholic:
            datas = json.load(travelholic)

        end_date = datas[0].get('datetime')[:10]
        now_date = date.strftime(date.today(), '%Y-%m-%d')
        if len(datas) != length or now_date != end_date:
            tours = init_datas(length)
        else:
            return Response(status=200)

    return Response(tours, 200)
