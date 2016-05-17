<div style='width:300px; magin-top:20px'>
	<ul id="tree" class="ztree" style='width:100%;float:right'>
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
> http://ip:port/ots/业务类别/业务场景（动／名词:search，detail，cancel）


```
所有 url 全部使用小写英文字母
所有参数驼峰
```
事例：

```
http://ip:port/ots/order/
http://ip:port/ots/order/cancel
```

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
> http://ip:port/ots/hotel/mergeHotelFacility

**请求参数：**

|    字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|﻿pageNo|页码|否|不填默认从第一页开始拉取



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


###拉取pms酒店信息

***
**业务说明：**


**请求方式：**
> POST

**接口url：**
> http://ip:port/ots/hotel/mergePmsHotel

**请求参数：**

|    字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|﻿pageNo|页码|否|不填默认从第一页开始拉取



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

###拉取房型息

***
**业务说明：**

**请求方式：**
> POST

**接口url：**
> http://ip:port/ots/roomtype/mergeRoomType

**请求参数：**

|    字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|﻿pageNo|页码|否|不填默认从第一页开始拉取



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

###拉取房型价格信息

***
**业务说明：**
拉取标签信息

**请求方式：**
> POST

**接口url：**
> http://ip:port/ots/roomtype/mergeRoomTypePrice

**请求参数：**

|    字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|﻿pageNo|页码|否|不填默认从第一页开始拉取



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

###拉取酒店库存信息

***
**业务说明：**


**请求方式：**
> POST

**接口url：**
> http://ip:port/ots/roomtype/mergeRoomTypeStock

**请求参数：**

|    字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|﻿pageNo|页码|否|不填默认从第一页开始拉取



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

##PUSH接口

###酒店信息全量push
***
**业务说明：**

**请求方式：**
> POST

**接口url：**
> http://ip:port/cube/hotel/hotelall

**head请求参数：**

|    字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|﻿token|token|是|


**body请求参数(json格式 以raw方式请求)：**

|    字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|id|酒店id|是|
|hotelname|酒店名称|是|
|detailaddr|酒店详情|是|
|hoteltype|酒店类型|是|
|longitude|经度|是|
|latitude|维度|是|
|retentiontime|保留时间|是|
|defaultleavetime|默认预离时间|是|
|provcode|省|是|
|citycode|市|是|
|discode|县区|是|
|provincename|省|是|
|cityname|市|是|
|districtname|县区|是|
|hotelpic|酒店图片|是|
|roomtypes|roomtypes子对象|是|

|    roomtypes字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|id|房型id|是|
|name|房型名称|是|
|area|房型面积|是|
|bedtype|床型|是|
|roomnum|房间数|是|   
                  
> API提交json数据示例：

```js
{
        "citycode": 310000,
        "cityname": "s 上海市",
        "defaultleavetime": "120000",
        "detailaddr": "上海市闸北区灵石路697-3号",
        "discode": 310108,
        "districtname": "z 闸北区",
        "hotelname": "上轩商务酒店111",
        "hotelpic": "https://dn-imke-pro.qbox.me/fuu6jue32lz7cvzfidyhfpmu-_z8",
        "id": 399,
        "introduction": "性价比高，住宿环境、通风采光较好。",
        "latitude": 31.283322,
        "longitude": 121.446101,
        "pmstype": null,
        "provcode": 310000,
        "provincename": "s 上海市",
        "repairtime": 1456588800000,
        "retentiontime": "180000",
        "roomtypes": [
            {
                "id": 175,
                "name": "234",
                "roomnum": 5,
"bedtype": "双床",
            	"area": "20.00"
            }
            {
                "id": 8765,
                "name": "sssss",
                "roomnum": 222,
"bedtype": "双床",
            	"area": "20.00"
            },
            {
                "id": 8766,
                "name": "xxx",
                "roomnum": 222,
"bedtype": "双床",
            	"area": "20.00"
            }
        ]
}
```

> API返回json数据示例：

* success为T时
* http状态200

```js
{
    "success":"T"
}
```

* success为F时
* http状态400


```
{
    "success":"F",
    "errorCode":,//错误码
    "errorMessage":
}
```

###酒店信息增量push
***
**业务说明：**

**请求方式：**
> POST

**接口url：**
> http://ip:port/cube/hotel/hotel

**head请求参数：**

|    字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|﻿token|token|是|


**body请求参数(json格式 以raw方式请求)：**

|    字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|id|酒店id|是|
|hotelname|酒店名称|是|
|detailaddr|酒店详情|是|
|hoteltype|酒店类型|是|
|longitude|经度|是|
|latitude|维度|是|
|retentiontime|保留时间|是|
|defaultleavetime|默认预离时间|是|
|provcode|省|是|
|citycode|市|是|
|discode|县区|是|
|provincename|省|是|
|cityname|市|是|
|districtname|县区|是|
|hotelpic|酒店图片|是|

> API提交json数据示例：

```js
{
	id:    111 ,                                  
	hotelname: 酒店名称,                                                               
	detailaddr: 酒店地址,                             
	longitude: 117.195908,                         
	latitude:  39.118328,                                                                                       
	roomnum:  10,      
	hotelphone: 13333333666,                            
	hoteltype:  精品酒店（参考枚举）                            
	discode: 10000001    ,                           
	citycode:   10000002,                          
	provcode:  10000003,
	hotelpic:“http://xxxxxx.com”
}
```

> API返回json数据示例：

* success为T时
* http状态200

```js
{
    "success":"T"
}
```

* success为F时
* http状态400


```
{
    "success":"F",
    "errorCode":,//错误码
    "errorMessage":
}
```

###酒店标签信息增量push
***
**业务说明：**

**请求方式：**
> POST

**接口url：**
> http://ip:port/cube/hotel/hotelfacility

**head请求参数：**

|    字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|﻿token|token|是|


**body请求参数(json格式 以raw方式请求)：**

|    字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|﻿hotelid|酒店id|是|
|﻿tagid|标签id,逗号分隔|是|
|﻿roomtype|房型标签对象数组|是|

|    房型标签对象字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|﻿roomtypeid|房型id|是|
|﻿tagid|标签id,逗号分隔|是|

> API提交json数据示例：

```js
{
	"hotelid": 33670,
	"tagid": 1,2,3,4,5，
	"roomtype":[
		{
		"roomtypeid":1111
		"roomtypetagid":1,2,3,4，
	}，{
		"roomtypeid":1111
		"roomtypetagid":1,2,3,4，
	}
]
}
```

> API返回json数据示例：

* success为T时
* http状态200

```js
{
    "success":"T"
}
```

* success为F时
* http状态400


```
{
    "success":"F",
    "errorCode":,//错误码
    "errorMessage":
}
```

###房型信息增量push
***
**业务说明：**

**请求方式：**
> POST

**接口url：**
> http://ip:port/cube/roomtype/roomtype

**head请求参数：**

|    字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|﻿token|token|是|


**body请求参数(json格式 以raw方式请求)：**

|    字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|id|房型id|是|
|hotelid|酒店id|是|
|name|房型名称|是|
|area|房型面积|是|
|bedtype|床型|是|1、大床 2、双床 3、三床 4、单人床 5、一单一双 6、上下铺 7、通铺 8、榻榻米 9、水床 10、圆床 11、拼床 12、其他
|bedsize|床尺寸|是|
|prepay|支付类型|是|0、预付
|breakfast|是否含早餐|是|0、无早；1、含早
|status|是否可定（关房）|是|0、可定；1、不可订
|refund|是否可退款|是|0、不可退
|maxroomnum|单个订单最大房量|是|默认为8 可设置值为[1,8]；
|roomnum|房间数|是|最大预定数，不是可用的

> API提交json数据示例：

```js
 { 
	“hotelid”:9999,
	“roomtypeid”:444,
	“name”:”大床房”,
	“area”:25,
	“bedtype“:1
	“roomnum”:78,
	“prepay“：0
}
```

> API返回json数据示例：

* success为T时
* http状态200

```js
{
    "success":"T"
}
```

* success为F时
* http状态400


```
{
    "success":"F",
    "errorCode":,//错误码
    "errorMessage":
}
```

###房型价格信息增量push
***
**业务说明：**

**请求方式：**
> POST

**接口url：**
> http://ip:port/cube/roomtype/roomtypeprice

**head请求参数：**

|    字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|﻿token|token|是|


**body请求参数(json格式 以raw方式请求)：**

|    字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|﻿roomtypeid|房型id|是|
|﻿roomtypes|房型集合|是|

|    roomtypes字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|﻿roomtypeid|房型id|是|
|﻿priceinfo|价格集合|是|

|    priceinfo字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|﻿day|日期|是|
|﻿price|价格|是|

> API提交json数据示例：

```js
{
    "hotelid": "2013",
    "roomtypes": [
        {
            "roomtypeid": "20122",
            "priceinfo": [
                {
                    "day": "20160328",
                    "price": "218"
                },
                {
                    "day": "20160329",
                    "price": "300"
                }
            ]
        },
        {
            "roomtypeid": "20122",
            "priceinfo": [
                {
                    "day": "20160328",
                    "price": "218"
                },
                {
                    "day": "20160329",
                    "price": "300"
                }
            ]
        }
    ]
}
```

> API返回json数据示例：

* success为T时
* http状态200

```js
{
    "success":"T"
}
```

* success为F时
* http状态400


```
{
    "success":"F",
    "errorCode":,//错误码
    "errorMessage":
}
```

###订单状态增量push
***
**业务说明：**

**请求方式：**
> POST

**接口url：**
> http://ip:port/cube/order/orderstatus

**head请求参数：**

|    字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|﻿token|token|是|


**body请求参数(json格式 以raw方式请求)：**

|    字段        |         名称        | 是否必须 | 说明|
--------------- | ------------------- | -------| ----------
|﻿orderid|订单id|是|
|﻿orderstatus|订单状态|是|
                   
> API提交json数据示例：

```js
{
	"orderid": 1234567,
	"orderstatus": 1
}
```

> API返回json数据示例：

* success为T时
* http状态200

```js
{
    "success":"T"
}
```

* success为F时
* http状态400


```
{
    "success":"F",
    "errorCode":,//错误码
    "errorMessage":
}
```

</article>

<link href="asset/css/zTreeStyle.css" media="all" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="asset/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="asset/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="asset/js/ztree_toc.js"></script>
<script type="text/javascript" src="asset/js/mdtree.js"></script>

