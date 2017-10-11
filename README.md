# DomainSearch
软件功能：<br>
1.单个域名查询，返回结果为是否可以注册。<br>
original=210 : Domain name is available     表示域名可以注册<br>
original=211 : Domain name is not available 表示域名已经注册<br>
original=212 : Domain name is invalid   表示域名参数传输错误<br>
original=213 : Time out 查询超时<br>
2.批量域名查询，可以通过问号代替字母进行模糊查询。<br>
例如：??.com可以查询aa.com到zz.com<br>
3.域名信息查询，返回注册人，注册时间，到期时间等等。<br>
<br>
此项目学习到的知识：<br>
1.熟悉MVP的用法和实现方式。<br>
2.GreenDao的使用方法。（Android数据库协助项目）<br>
3.应用pathData来画图标。<br>
4.XStream来解析XML数据。<br>
5.Volley的基本使用。<br>
6.阿里云API的认证的生成算法。<br>
7.Android项目架构时间练习以及思考。<br>
<br>
遇到的问题：<br>
1.list的数据更新一定要在同一个线程里，否则报错<br>
The content of the adapter has changed but ListView did not receive a notification. <br>
Make sure the content of your adapter is not modified from a background thread, <br>
but only from the UI thread. <br>
Make sure your adapter calls notifyDataSetChanged() when its content changes.<br>
就是data list和notifyDataSetChanged要在同一个线程里。<br>
2.线程的停止，不要用全局变量去控制线程。为Runnable里加控制变量。<br>
<br>
目录结构中DomainSearchGreenDao为生成Dao的JAVA项目。<br>
<br>
注意：<br>
如果要查询域名信息，需要在阿里云上申请的访问控制RAM，<br>
https://ram.console.aliyun.com/#/overview<br>
替换DomainInfo文件中的<br>
private static final String AccessKey = "xxxxxx";<br>
private static final String Signature = "xxxxxx";<br>
<br>
打包好的APP在根目录下app-release.apk。<br>
APP会发布到Google Play上，但现在还访问不了，稍后再做尝试。<br>