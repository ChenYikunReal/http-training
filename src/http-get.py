import requests

# 以下为GET请求
url = 'https://www.baidu.com/'
r = requests.get(url)
# 设置请求超时
# r = requests.get(url, timeout=20)

# 返回字节形式
print(r.content)
