import requests

url = 'https://blog.csdn.net/'
r = requests.get(url)
if r.status_code == requests.codes.ok:
    # 响应码
    print('=== status_code === ', r.status_code)
    # 响应头
    print('=== headers === ', r.headers)
    # 获取响应头中的Content-Type字段
    print('=== Content-Type === ', r.headers.get('Content-Type'))
else:
    # 抛出异常
    r.raise_for_status()
