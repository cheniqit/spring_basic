<div style='width:300px; magin-top:20px'>
	<ul /Users/kirinli/Library/Containers/com.tencent.xinWeChat/Data/Library/Application Support/Wechat/1.2/e82cf06e3c1f11cd04f609cca7859bcd/Message/MessageTemp/6fc68ac07f701bf73757ce1dbe01fd4f/Image/WeChat_1463981469.jpegid="tree" class="ztree" style='width:100%;float:right'>
	</ul>
</div>
<article class='md-body' style="margin-left: 15%;">
# Hotel Api 文档
## 版本

版本 |  修改日期  | 修改人 | 备注
--- | --------- | ----- | -----------
1.0 |  |  |

## 通用定义
### 时间定义
* 除特殊说明之外，所有时间通信使用时间戳。格式为 14 位 yyyy-MM-dd hh:mm:ss ,例如20141225140000 。
* 注: 客户端需要自行处理时区问题，与服务器的通信均认为是北京时间。

### 提交方式
>
* GET – 查询
* DELETE – 删除
* POST- 创建新的
* PUT – 更新

### session管理
客户端自身需要维护 session 管理，即使用同一个 session 的客户端进行 http 链接。

### 错误信息
除下载文件外，所有返回信息均为 `json`，必定包含 `success` 属性，有错误时必定包含 `errorcode`属性

```js
{
"success":"F",
"errcode":'-1',
"errmsg":'错误信息'
}
```
errorcode 表示:

	1. `-701`  session 超时或者不存在该 session
	2. `-702`  该 session 因为密码被修改而失效
	3. `-703`  该 session 因用户被禁用而失效

### 下载文件错误信息
下载文件时，所有错误信息使用 http 错误状态码提示:

* `404` 表示未找到文件
* `403` 表示无权限下载

### 请求报文Url及参数格式
> http://ip:port/cube/业务类别/业务场景（动／名词:search，detail，cancel）


```
所有 url 全部使用小写英文字母
所有参数驼峰
```
事例：

```
http://ip:port/cube/order/
http://ip:port/cube/order/cancel
```

##域名
### 模拟环境
> http://api.imike.cn/cube

### 生产环境 (暂不开放)
> http://api.imike.com/cube



### 报文中判断 true 和 false 的判断
> 报文中判断是否的字段，均使用（T/F），T 代表 true，F 代表 false


##fetch接口

###拉取标签信息

***
**业务说明：**
拉取标签信息

**请求方式：**
> POST

**接口url：**
> http://ip:port/cube/file/upload

**请求参数：**

|    字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|﻿hotelId|酒店 ID|是|
|roomTypeId| 房型 ID| 否， 不跳默认酒店主展
|picType| 图片类型| 1-门头及招牌； 2-大堂； 3-主力房源； 4-房型图片



> API返回json数据示例：

* success为T时
* http状态200

```js

{
    "success":"T"
}
```
* success为F时


```
{
    "success":"F",
    "errcode":,//错误码
    "errmsg":
}
```




</article>

<link href="asset/css/zTreeStyle.css" media="all" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="asset/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="asset/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="asset/js/ztree_toc.js"></script>
<script type="text/javascript" src="asset/js/mdtree.js"></script>

