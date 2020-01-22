from rest_framework.decorators import api_view
from rest_framework.response import Response
from .models import Photo
from datetime import datetime, date
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
    source = info.get('key')
    datas = Photo.objects.filter(psource=source)

    # there is not duplicated tour info
    if len(datas) == 0:
        code = idx + 1
        url = info.get('img_urls')
        words = info.get('caption')

        hashtags = []
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
        # or return original info
        return [False] * 4


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

    # check crawling is needed
    get_data = 0
    files = os.listdir(mid_output)
    if f'{filename}.json' not in files:
        get_data = 1

    # if crawling is needed, do it
    if get_data == 1:
        os.system(
            f'python {crawler} posts_full -u {account} -n {length} -o {address} --fetch_details')

    with open(address, 'r', encoding='utf-8') as f:
        datas = json.load(f)

    # return results
    res = {}
    for data in datas:
        code, url, source, hashtags = crawling_info(data, len(res), filename)
        if code != False:
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
            # just unpack this line for test
            with open(f'{output}/{filename}.json', 'r', encoding='utf-8') as f:
                res = json.load(f)
            # # just unpack this line for service
            # return Response(res, status=200)

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
