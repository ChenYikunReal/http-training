import requests

# 以下为POST请求
url = 'https://www.baidu.com/'
post_data = {'key': 'value'}
r = requests.post(url, data=post_data)

# 返回字节形式
print(r.content)
