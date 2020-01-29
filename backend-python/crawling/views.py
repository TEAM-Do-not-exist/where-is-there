from rest_framework.decorators import api_view
from rest_framework.response import Response
from .models import Photo, PhotoCheck
from datetime import datetime, date
from bs4 import BeautifulSoup as bs
from urllib.parse import quote
from decouple import config
import requests
import json
import os

# Create your views here.
# set variables
mid_output = './crawling/instagram-crawler/output'
output = './crawling/output'


# crawling feat. travelholic
def crawling_info(info, idx, filename):
    url = info.get('key')
    datas = Photo.objects.filter(purl=url)
    unused = PhotoCheck.objects.filter(purl=url)

    # there is not duplicated tour info
    if len(datas) == 0 and len(unused) == 0:
        code = idx + 1
        source = info.get('img_urls')
        words = info.get('caption')

        hashtags = []
        if words != None:
            words = words.replace('\n', '')
            for i in range(len(words)):
                if words[i] == '#':
                    for j in range(i + 1, len(words)):
                        if words[j] in [' ', '#']:
                            word = words[i + 1:j]
                            if word != '':
                                hashtags.append(word)
                            break

        if hashtags != []:
            return code, url, source, hashtags

    # or return original info
    return [None] * 4


def crawling(target, length, filename):
    global mid_output, output

    # check crawling target(tour or resturant)
    if target == 'tour':
        account = 'travelholic_insta'
    elif target == 'eat':
        account = 'greedeat'

    # set crawler address and mid output address
    crawler = './crawling/instagram-crawler/crawler.py'
    address = f'{mid_output}/{filename}.json'

    os.system(
        f'python {crawler} posts_full -u {account} -n {length} -o {address} --fetch_details')

    with open(address, 'r', encoding='utf-8') as f:
        datas = json.load(f)

    # return results
    res = {}
    for data in datas:
        code, url, source, hashtags = crawling_info(data, len(res), filename)
        if hashtags != None:
            res[code] = {
                'pcode': code,
                'purl': url,
                'psource': source,
                'pplace_pname': hashtags
            }

    # save results
    if res != {}:
        with open(f'{output}/{filename}.json', 'w', encoding='utf-8') as f:
            json.dump(res, f)

    return res


@api_view(['GET', ])
def root(request):
    return Response(status=200)


@api_view(['GET', ])
def instagram(request):
    global mid_output, output

    # set target(tour or resturant) and length of resluts
    target = request.GET.get('target')
    length = int(request.GET.get('length'))

    # check output file is exist
    files = os.listdir(output)
    if target == 'tour':
        filename = 'travelholic'
    elif target == 'eat':
        filename = 'greedeat'
    else:
        return Response(status=400)

    # if there is not file, make file
    res = {}
    if f'{filename}.json' not in files:
        res = crawling(target=target, length=length, filename=filename)
    else:
        # or not, compare files
        with open(f'{mid_output}/{filename}.json', 'r', encoding='utf-8') as f:
            datas = json.load(f)

        end = datas[0].get('datetime')[:10]
        now = date.strftime(date.today(), '%Y-%m-%d')
        if len(datas) != length or now != end:
            res = crawling(target=target, length=length, filename=filename)
        else:
            with open(f'{output}/{filename}.json', 'r', encoding='utf-8') as f:
                json_datas = json.load(f)

            for key, val in json_datas.items():
                url = val['purl']
                checked = Photo.objects.filter(purl=url)
                unused = PhotoCheck.objects.filter(purl=url)
                if len(checked) < 1 and len(unused) < 1:
                    res[key] = val

    return Response(res, status=200)


@api_view(['GET', ])
def tour_api(request):
    keyword = quote(request.GET.get('keyword', '광주'))
    tour_url = f'http://api.visitkorea.or.kr/openapi/service/rest/PhotoGalleryService/gallerySearchList?MobileOS=ETC&MobileApp=AppTest&ServiceKey={config("TOUR_API_KEY")}&keyword={keyword}&_type=json'
    api_res = requests.get(tour_url).json()
    items = api_res.get('response').get('body').get('items').get('item')

    res = {}
    if items != None:
        for item in items:
            code = len(res) + 1
            place = item.get('galSearchKeyword').split(', ')
            url = item.get('galWebImageUrl')
            name = item.get('galTitle')
            source = 'http://api.visitkorea.or.kr/openapi/service/rest/PhotoGalleryService/galleryList'

            res[code] = {
                'pcode': code,
                'purl': url,
                'psource': source,
                'pplace_pname': [place, name]
            }

    return Response(res, status=200)


@api_view(['GET', ])
def mango(request):
    keyword = quote(request.GET.get('keyword', '광주'))
    mango_url = f'https://www.mangoplate.com/search/{keyword}'
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36',
        'Referer': 'https://www.mangoplate.com/',
    }
    html = requests.get(mango_url, headers=headers).text

    bs_object = bs(html, 'html.parser')
    titles = bs_object.find_all('h2', class_='title')
    images = bs_object.find_all('img', class_='center-croping lazy')

    res = {}
    for i in range(len(titles)):
        code = i + 1
        place = images[i]['alt'].split(' - ')[-1]
        url = images[i]['data-original']
        name = titles[i].get_text().replace('\n', '')
        source = mango_url

        res[code] = {
            'pcode': code,
            'purl': url,
            'psource': source,
            'pplace_pname': [place, name]
        }

    return Response(res, status=200)
