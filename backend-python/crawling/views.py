from rest_framework.decorators import api_view
from rest_framework.response import Response
from .models import Crawling
from datetime import datetime, date
import subprocess
import json
import os

# Create your views here.
# set variables
mid_output = './crawling/instagram-crawler/output'
output = './crawling/output'


# crawling feat. travelholic
def get_tour_info(info, idx):
    source = [info.get('key'), 'instagram', 'travelholic_insta']
    tour = Crawling.objects.filter(psource=source)

    # there is not duplicated tour info
    if len(tour) == 0:
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


def crawling(target, length):
    global mid_output, output

    # check crawling target(tour or resturant)
    if target == 'tour':
        account = 'travelholic_insta'
        filename = 'travelholic'
    else:
        account = 'greedeat'
        filename = account

    # set crawler address and mid output address
    crawler = './crawling/instagram-crawler/crawler.py'
    address = f'{mid_output}/{filename}.json'

    # check crawling is needed
    get_data = 0
    files = os.listdir(mid_output)
    if target == 'tour':
        if 'travelholic.json' not in files:
            get_data = 1
    else:
        pass

    # if crawling is needed, do it
    if get_data == 1:
        subprocess.call(
            f'python {crawler} posts_full -u {account} -n {length} -o {address} --fetch_details', shell=True)

    with open(address, 'r', encoding='utf-8') as f:
        datas = json.load(f)

    # return results
    res = {}
    for data in datas:
        if target == 'tour':
            code, url, source, hashtags = get_tour_info(data, len(res))
        else:
            pass

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
    return Response({'message': 'main page'}, 200)


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
    else:
        pass

    # if there is not file, make file
    if f'{filename}.json' not in files:
        res = crawling(target=target, length=length)
    else:
        # or not, compare files
        with open(f'{mid_output}/{filename}.json', 'r', encoding='utf-8') as f:
            datas = json.load(f)

        end = datas[0].get('datetime')[:10]
        now = date.strftime(date.today(), '%Y-%m-%d')
        if len(datas) != length or now != end:
            res = crawling(target='tour', length=length)
        else:
            # just unpack this line for test
            with open(f'{output}/{filename}.json', 'r', encoding='utf-8') as f:
                res = json.load(f)
            # # just unpack this line for service
            # res = {'1': {
            #     'pcode': 'there is no more data',
            #     'purl': ['https://t1.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/13ur/image/dMMFg4Edthw4Bh0uohu3VjISNCE.jpeg'],
            #     'psource': [],
            #     'pplace_pname': []
            # }}

    return Response(res, 200)
