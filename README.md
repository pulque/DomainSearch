# DomainSearch
# DomainSearch 软件功能：&lt;br> 1.单个域名查询，返回结果为是否可以注册。&lt;br> original=210 : Domain name is available     表示域名可以注册&lt;br> original=211 : Domain name is not available 表示域名已经注册&lt;br> original=212 : Domain name is invalid   表示域名参数传输错误&lt;br> original=213 : Time out 查询超时&lt;br> 2.批量域名查询，可以通过问号代替字母进行模糊查询。&lt;br> 例如：??.com可以查询aa.com到zz.com&lt;br> 3.域名信息查询，返回注册人，注册时间，到期时间等等。&lt;br> &lt;br> 此项目学习到的知识：&lt;br> 1.熟悉MVP的用法和实现方式。&lt;br> 2.GreenDao的使用方法。（Android数据库协助项目）&lt;br> 3.应用pathData来画图标。&lt;br> 4.XStream来解析XML数据。&lt;br> 5.Volley的基本使用。&lt;br> 6.阿里云API的认证的生成算法。&lt;br> 7.Android项目架构时间练习以及思考。&lt;br> &lt;br> 目录结构中DomainSearchGreenDao为生成Dao的JAVA项目。&lt;br> &lt;br> 注意：&lt;br> 如果要查询域名信息，需要在阿里云上申请的访问控制RAM，&lt;br> https://ram.console.aliyun.com/#/overview&lt;br> 替换DomainInfo文件中的&lt;br> private static final String AccessKey = "xxxxxx";&lt;br> private static final String Signature = "xxxxxx";&lt;br> &lt;br> 生成的APP会发布到Google Play上，但现在还访问不了，稍后再做尝试。&lt;br>
