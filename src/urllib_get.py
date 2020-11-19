import urllib.request

# get请求
r = urllib.request.urlopen('https://baidu.com', data=None, timeout=10)
data = r.read().decode()

# 打开文件 这里网络数据流的编码需要和写入的文件编码一致
fo = open('urllib_get.txt', 'a+', encoding='utf-8')
# 写入文件
fo.write(data)
# 关闭文件
fo.close()
