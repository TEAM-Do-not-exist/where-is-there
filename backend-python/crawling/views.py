from rest_framework.decorators import api_view
from rest_framework.response import Response
from .models import Photo, PhotoCheck
from datetime import datetime, date
from bs4 import BeautifulSoup as bs
from selenium import webdriver
from urllib.parse import quote
from decouple import config
from time import sleep
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

        tags = []
        if filename == 'greedeat':
            source = [info.get('img_url')]

            # hashtag do cannot find in instagram crawlers, so find it one more
            options = webdriver.ChromeOptions()
            options.add_argument('headless')
            options.add_argument('disable-gpu')
            options.add_argument('--no-sandbox')
            options.add_argument('--disable-dev-shm-usage')

            # for local
            # driver = webdriver.Chrome(
            #     './crawling/instagram-crawler/inscrawler/bin/chromedriver.exe', options=options, port=8888)
            # for AWS
            driver = webdriver.Chrome(options=options, port=8888)
            driver.get(url)
            sleep(5)

            page_string = driver.page_source
            bs_obj = bs(page_string, 'lxml')

            org_tags_div = bs_obj.find_all(
                name='div', attrs={'class': 'C4VMK'})
            if len(org_tags_div) > 0:
                tags = org_tags_div[0].find_all(
                    name='a', attrs={'class': 'xil3i'})

            driver.quit()
        else:
            source = [info.get('img_url')]

            raw_tags = info.get('description')
            if raw_tags != None:
                raw_tags = raw_tags.replace('\n', ' ').split(' ')
                for raw_word in raw_tags:
                    if raw_word != '':
                        if raw_word[0] == '#' and len(raw_word) > 1:
                            word = ''
                            for char in raw_word[1:]:
                                if char != '~!@#$%^&*()_+`-={}[]:";\'<>?,./|\\�':
                                    word += char
                                else:
                                    break
                            tags.append(word)

        hashtags = []
        if filename == 'greedeat':
            for tag in tags:
                hashtags.append(tag.contents[0][1:].strip())
        else:
            hashtags = tags

        if hashtags != []:
            return code, url, source, hashtags

    # or return original info
    return [None] * 4


def crawling(target, length, filename):
    global mid_output, output

    # check crawling target(tour or resturant)
    if target == 'tour':
        tag = '국내여행'
    elif target == 'eat':
        account = 'greedeat'

    # set crawler address and mid output address
    crawler = './crawling/instagram-crawler/crawler.py'
    address = f'{mid_output}/{filename}.json'

    if target == 'tour':
        os.system(
            f'python {crawler} hashtag -t {tag} -n {length} -o {address} --fetch_details')
    else:
        os.system(
            f'python {crawler} posts -u {account} -n {length} -o {address}')

    with open(address, 'r', encoding='utf-8') as f:
        datas = json.load(f)

    # return results
    res, epoch = {}, 1
    for data in datas:
        print('\n' + f'===== {epoch} / {length} done =====' + '\n')
        code, url, source, hashtags = crawling_info(data, len(res), filename)
        if hashtags != None:
            res[code] = {
                'pcode': code,
                'purl': url,
                'psource': source,
                'pplace_pname': hashtags
            }
        epoch += 1

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
        filename = 'koreantour'
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

        end = datas[0].get('datetime')
        if end != None:
            end = end[:10]
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
        url = images[i]['data-original']
        source = mango_url
        place = images[i]['alt'].split(' - ')[-1]
        name = titles[i].get_text().replace('\n', '')

        res[code] = {
            'pcode': code,
            'purl': url,
            'psource': source,
            'pplace_pname': [place, name]
        }

    return Response(res, status=200)
