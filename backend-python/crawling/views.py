from rest_framework.decorators import api_view
from rest_framework.response import Response
from .models import Crawling
from datetime import datetime, date
import subprocess
import json
import os

# Create your views here.


def get_tour_info(info, idx):
    source = [info.get('key'), 'instagram', 'travelholic_insta']
    tour = Crawling.objects.filter(psource=source)

    if len(tour) == 0:
        code = idx + 1
        url = info.get('img_urls')

        hashtags = []
        words = info.get('caption')
        if words != None:
            words = words.replace('\n', '')
            for i in range(len(words)):
                if words[i] == '#':
                    for j in range(i + 1, len(words)):
                        if words[j] in [' ', '#']:
                            hashtags.append(words[i + 1:j])
                            break
        return code, url, source, hashtags
    else:
        return tour.pcode, tour.pplace, tour.purl, tour.pname, tour.psource


def crawling(target, length):
    if target == 'tour':
        account = 'travelholic_insta'
        filename = 'travelholic'
    else:
        account = 'greedeat'
        filename = account

    get_data = 0
    files = os.listdir('./crawling/instagram-crawler/output')
    if target == 'tour':
        mid_ouput = f'./crawling/instagram-crawler/output/{filename}.json'
        if 'travelholic.json' not in files:
            crawler = './crawling/instagram-crawler/crawler.py'
            get_data = 1
    else:
        pass

    if get_data == 1:
        subprocess.call(
            f'python {crawler} posts_full -u {account} -n {length} -o {mid_ouput} --fetch_details', shell=True)

    with open(mid_ouput, 'r', encoding='utf-8') as travelholic:
        datas = json.load(travelholic)

    res = {}
    for data in datas:
        if target == 'tour':
            code, url, source, hashtags = get_tour_info(data, len(res))
        else:
            pass

        res[code] = {
            'pcode': code,
            'purl': url,
            'psource': source,
            'pplace_pname': hashtags
        }

    if target == 'tour':
        with open('./crawling/output/travelholic.json', 'w', encoding='utf-8') as f:
            json.dump(res, f)
    else:
        pass

    return res


@api_view(['GET', ])
def root(request):
    return Response({'message': 'main page'}, 200)


@api_view(['GET', ])
def instagram(request):
    length = int(request.GET.get('length'))
    target = request.GET.get('target')

    files = os.listdir('./crawling/output')
    if target == 'tour':
        if 'travelholic.json' not in files:
            res = crawling(target='tour', length=length)
        else:
            with open('./crawling/instagram-crawler/output/travelholic.json', 'r', encoding='utf-8') as travelholic:
                datas = json.load(travelholic)

            end = datas[0].get('datetime')[:10]
            now = date.strftime(date.today(), '%Y-%m-%d')
            if len(datas) != length or now != end:
                res = crawling(target='tour', length=length)
            else:
                with open('./crawling/output/travelholic.json', 'r', encoding='utf-8') as f:
                    res = json.load(f)
    else:
        pass

    return Response(res, 200)
