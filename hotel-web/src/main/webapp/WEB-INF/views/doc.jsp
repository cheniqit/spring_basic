<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="generated-by" content="Markdown PRO, http://markdownpro.com"/>
    <title></title>
    <style type="text/css">
        html,body{margin:0;padding:0;}
        body {padding: 20px}
        h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,acronym,address,cite,code,del,dfn,em,img,q,s,samp,small,strike,strong,sub,sup,tt,var,dd,dl,dt,li,ol,ul,fieldset,form,label,legend,button,table,caption,tbody,tfoot,thead,tr,th,td{margin:0;padding:0;border:0;font-weight:normal;font-style:normal;font-size:100%;line-height:1;font-family:inherit;}
        table{border-collapse:collapse;border-spacing:0;}
        ol,ul{list-style:none;}
        q:before,q:after,blockquote:before,blockquote:after{content:"";}
        html{overflow-y:scroll;font-size:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;}
        a:focus{outline:thin dotted;}
        a:hover,a:active{outline:0;}
        article,aside,details,figcaption,figure,footer,header,hgroup,nav,section{display:block;}
        audio,canvas,video{display:inline-block;*display:inline;*zoom:1;}
        audio:not([controls]){display:none;}
        sub,sup{font-size:75%;line-height:0;position:relative;vertical-align:baseline;}
        sup{top:-0.5em;}
        sub{bottom:-0.25em;}
        img{border:0;-ms-interpolation-mode:bicubic;}
        button,input,select,textarea{font-size:100%;margin:0;vertical-align:baseline;*vertical-align:middle;}
        button,input{line-height:normal;*overflow:visible;}
        button::-moz-focus-inner,input::-moz-focus-inner{border:0;padding:0;}
        button,input[type="button"],input[type="reset"],input[type="submit"]{cursor:pointer;-webkit-appearance:button;}
        input[type="search"]{-webkit-appearance:textfield;-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box;}
        input[type="search"]::-webkit-search-decoration{-webkit-appearance:none;}
        textarea{overflow:auto;vertical-align:top;}
        html,body{background-color:#ffffff;}
        body{margin:0;font-family:"Helvetica Neue",Helvetica,Arial,sans-serif;font-size:13px;font-weight:normal;line-height:18px;color:#404040;}
        .container{width:940px;margin-left:auto;margin-right:auto;zoom:1;}.container:before,.container:after{display:table;content:"";zoom:1;*display:inline;}
        .container:after{clear:both;}
        .container-fluid{position:relative;min-width:940px;padding-left:20px;padding-right:20px;zoom:1;}.container-fluid:before,.container-fluid:after{display:table;content:"";zoom:1;*display:inline;}
        .container-fluid:after{clear:both;}
        .container-fluid>.sidebar{float:left;width:220px;}
        .container-fluid>.content{margin-left:240px;}
        a{color:#0069d6;text-decoration:none;line-height:inherit;font-weight:inherit;}a:hover{color:#00438a;text-decoration:underline;}
        .pull-right{float:right;}
        .pull-left{float:left;}
        .hide{display:none;}
        .show{display:block;}
        .row{zoom:1;margin-left:-20px;}.row:before,.row:after{display:table;content:"";zoom:1;*display:inline;}
        .row:after{clear:both;}
        p{font-size:13px;font-weight:normal;line-height:18px;margin-bottom:9px;}p small{font-size:11px;color:#bfbfbf;}
        h1,h2,h3,h4,h5,h6{font-weight:bold;color:#404040;}h1 small,h2 small,h3 small,h4 small,h5 small,h6 small{color:#bfbfbf;}
        h1{margin-bottom:18px;font-size:30px;line-height:36px;}h1 small{font-size:18px;}
        h2{font-size:24px;line-height:36px;}h2 small{font-size:14px;}
        h3,h4,h5,h6{line-height:36px;}
        h3{font-size:18px;}h3 small{font-size:14px;}
        h4{font-size:16px;}h4 small{font-size:12px;}
        h5{font-size:14px;}
        h6{font-size:13px;color:#bfbfbf;text-transform:uppercase;}
        ul,ol{margin:0 0 18px 25px;}
        ul ul,ul ol,ol ol,ol ul{margin-bottom:0;}
        ul{list-style:disc;}
        ol{list-style:decimal;}
        li{line-height:18px;color:#808080;}
        ul.unstyled{list-style:none;margin-left:0;}
        dl{margin-bottom:18px;}dl dt,dl dd{line-height:18px;}
        dl dt{font-weight:bold;}
        dl dd{margin-left:9px;}
        hr{margin:20px 0 19px;border:0;border-bottom:1px solid #eee;}
        strong{font-style:inherit;font-weight:bold;}
        em{font-style:italic;font-weight:inherit;line-height:inherit;}
        .muted{color:#bfbfbf;}
        blockquote{margin-bottom:18px;border-left:5px solid #eee;padding-left:15px;}blockquote p{font-size:14px;font-weight:300;line-height:18px;margin-bottom:0;}
        blockquote small{display:block;font-size:12px;font-weight:300;line-height:18px;color:#bfbfbf;}blockquote small:before{content:'\2014 \00A0';}
        address{display:block;line-height:18px;margin-bottom:18px;}
        code,pre{padding:0 3px 2px;font-family:Monaco, Andale Mono, Courier New, monospace;font-size:12px;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px;}
        code{padding:1px 3px;}
        pre{background-color:#f5f5f5;display:block;padding:8.5px;margin:0 0 18px;line-height:18px;font-size:12px;border:1px solid #ccc;border:1px solid rgba(0, 0, 0, 0.15);-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px;white-space:pre;white-space:pre-wrap;word-wrap:break-word;}
        form{margin-bottom:18px;}
        fieldset{margin-bottom:18px;padding-top:18px;}fieldset legend{display:block;padding-left:150px;font-size:19.5px;line-height:1;color:#404040;*padding:0 0 5px 145px;*line-height:1.5;}
        form .clearfix{margin-bottom:18px;zoom:1;}form .clearfix:before,form .clearfix:after{display:table;content:"";zoom:1;*display:inline;}
        form .clearfix:after{clear:both;}
        label,input,select,textarea{font-family:"Helvetica Neue",Helvetica,Arial,sans-serif;font-size:13px;font-weight:normal;line-height:normal;}
        label{padding-top:6px;font-size:13px;line-height:18px;float:left;width:130px;text-align:right;color:#404040;}
        form .input{margin-left:150px;}
        input[type=checkbox],input[type=radio]{cursor:pointer;}
        input,textarea,select,.uneditable-input{display:inline-block;width:210px;height:18px;padding:4px;font-size:13px;line-height:18px;color:#808080;border:1px solid #ccc;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px;}
        input[type=checkbox],input[type=radio]{width:auto;height:auto;padding:0;margin:3px 0;*margin-top:0;line-height:normal;border:none;}
        input[type=file]{background-color:#ffffff;padding:initial;border:initial;line-height:initial;-webkit-box-shadow:none;-moz-box-shadow:none;box-shadow:none;}
        input[type=button],input[type=reset],input[type=submit]{width:auto;height:auto;}
        select,input[type=file]{height:27px;line-height:27px;*margin-top:4px;}
        select[multiple]{height:inherit;}
        textarea{height:auto;}
        .uneditable-input{background-color:#ffffff;display:block;border-color:#eee;-webkit-box-shadow:inset 0 1px 2px rgba(0, 0, 0, 0.025);-moz-box-shadow:inset 0 1px 2px rgba(0, 0, 0, 0.025);box-shadow:inset 0 1px 2px rgba(0, 0, 0, 0.025);cursor:not-allowed;}
        :-moz-placeholder{color:#bfbfbf;}
        ::-webkit-input-placeholder{color:#bfbfbf;}
        input,textarea{-webkit-transition:border linear 0.2s,box-shadow linear 0.2s;-moz-transition:border linear 0.2s,box-shadow linear 0.2s;-ms-transition:border linear 0.2s,box-shadow linear 0.2s;-o-transition:border linear 0.2s,box-shadow linear 0.2s;transition:border linear 0.2s,box-shadow linear 0.2s;-webkit-box-shadow:inset 0 1px 3px rgba(0, 0, 0, 0.1);-moz-box-shadow:inset 0 1px 3px rgba(0, 0, 0, 0.1);box-shadow:inset 0 1px 3px rgba(0, 0, 0, 0.1);}
        input:focus,textarea:focus{outline:0;border-color:rgba(82, 168, 236, 0.8);-webkit-box-shadow:inset 0 1px 3px rgba(0, 0, 0, 0.1),0 0 8px rgba(82, 168, 236, 0.6);-moz-box-shadow:inset 0 1px 3px rgba(0, 0, 0, 0.1),0 0 8px rgba(82, 168, 236, 0.6);box-shadow:inset 0 1px 3px rgba(0, 0, 0, 0.1),0 0 8px rgba(82, 168, 236, 0.6);}
        input[type=file]:focus,input[type=checkbox]:focus,select:focus{-webkit-box-shadow:none;-moz-box-shadow:none;box-shadow:none;outline:1px dotted #666;}
        form div.clearfix.error{background:#fae5e3;padding:10px 0;margin:-10px 0 10px;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;}form div.clearfix.error>label,form div.clearfix.error span.help-inline,form div.clearfix.error span.help-block{color:#9d261d;}
        form div.clearfix.error input,form div.clearfix.error textarea{border-color:#c87872;-webkit-box-shadow:0 0 3px rgba(171, 41, 32, 0.25);-moz-box-shadow:0 0 3px rgba(171, 41, 32, 0.25);box-shadow:0 0 3px rgba(171, 41, 32, 0.25);}form div.clearfix.error input:focus,form div.clearfix.error textarea:focus{border-color:#b9554d;-webkit-box-shadow:0 0 6px rgba(171, 41, 32, 0.5);-moz-box-shadow:0 0 6px rgba(171, 41, 32, 0.5);box-shadow:0 0 6px rgba(171, 41, 32, 0.5);}
        form div.clearfix.error .input-prepend span.add-on,form div.clearfix.error .input-append span.add-on{background:#f4c8c5;border-color:#c87872;color:#b9554d;}
        table{width:100%;margin-bottom:18px;padding:0;border-collapse:separate;*border-collapse:collapse;font-size:13px;border:1px solid #ddd;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;}table th,table td{padding:10px 10px 9px;line-height:18px;text-align:left;}
        table th{padding-top:9px;font-weight:bold;vertical-align:middle;border-bottom:1px solid #ddd;}
        table td{vertical-align:top;}
        table th+th,table td+td{border-left:1px solid #ddd;}
        table tr+tr td{border-top:1px solid #ddd;}
        table tbody tr:first-child td:first-child{-webkit-border-radius:4px 0 0 0;-moz-border-radius:4px 0 0 0;border-radius:4px 0 0 0;}
        table tbody tr:first-child td:last-child{-webkit-border-radius:0 4px 0 0;-moz-border-radius:0 4px 0 0;border-radius:0 4px 0 0;}
        table tbody tr:last-child td:first-child{-webkit-border-radius:0 0 0 4px;-moz-border-radius:0 0 0 4px;border-radius:0 0 0 4px;}
        table tbody tr:last-child td:last-child{-webkit-border-radius:0 0 4px 0;-moz-border-radius:0 0 4px 0;border-radius:0 0 4px 0;}
        .zebra-striped tbody tr:nth-child(odd) td{background-color:#f9f9f9;}
        .zebra-striped tbody tr:hover td{background-color:#f5f5f5;}
        .zebra-striped .header{cursor:pointer;}.zebra-striped .header:after{content:"";float:right;margin-top:7px;border-width:0 4px 4px;border-style:solid;border-color:#000 transparent;visibility:hidden;}
        .zebra-striped .header:hover:after{visibility:visible;}
        footer{margin-top:17px;padding-top:17px;border-top:1px solid #eee;}
        .page-header{margin-bottom:17px;border-bottom:1px solid #ddd;-webkit-box-shadow:0 1px 0 rgba(255, 255, 255, 0.5);-moz-box-shadow:0 1px 0 rgba(255, 255, 255, 0.5);box-shadow:0 1px 0 rgba(255, 255, 255, 0.5);}.page-header h1{margin-bottom:8px;}
        .close{float:right;color:#000000;font-size:20px;font-weight:bold;line-height:13.5px;text-shadow:0 1px 0 #ffffff;filter:alpha(opacity=20);-khtml-opacity:0.2;-moz-opacity:0.2;opacity:0.2;}.close:hover{color:#000000;text-decoration:none;filter:alpha(opacity=40);-khtml-opacity:0.4;-moz-opacity:0.4;opacity:0.4;}

        pre {
            padding: 0;
            margin: 10px 0px 10px;
            overflow: auto; /*--If the Code exceeds the width, a scrolling is available--*/
            overflow-Y: hidden;  /*--Hides vertical scroll created by IE--*/
        }
        pre code {
            margin: 5px;  /*--Left Margin--*/
            padding: 0px;
            display: block;
            line-height: 18px;
        }
        .center { text-align:center}
        .left {text-align:left}
        .right {text-align:right}

    </style><style type="text/css">
    body {
        font-family: "Geneva", Arial, sans-serif;
        font-size: 13px;
        margin: 10px;
    }

    a, a:visited {
        color: #09c;
    }

    a:hover {
        color: #336699;
        text-decoration: none;
    }

    h1 {
        margin: 0px 0px 10px;
        font-weight: bold;
    }

    h2 {
        border-bottom: 2px dotted #ccc;
        margin: 5px 0px 15px;
    }

    h6 {
        color: #09c;
    }

    blockquote {
        font-family: "Georgia", Courier New, courier, sans-serif;
        background: #efefef;
        padding: 5px 10px;
        border: solid 1px #ddd;
        margin: 15px;
        -webkit-border-radius:6px;
        -moz-border-radius:6px;
        border-radius:6px;
        color:  #333;

    }

    ul, ol {
        margin-bottom: 15px;
    }

    li {
        padding: 3px;
    }



    code {
        background-color: #f1f1f1;
        color: #336699;
    }

    pre {
        background-color: #f1f1f1;
    }

    pre > code {
        margin: 0px;
        padding: 5px;
        border: 0px;
        background-color: #f1f1f1;
    }


</style></head>
<body>
<div style='width:300px; magin-top:20px'>
    <ul id="tree" class="ztree" style='width:100%;float:right'>
    </ul>
</div>

<p><article class='md-body' style="margin-left: 15%;"></p>

<h1>Hotel Api 文档</h1>

<h2>版本</h2>

<table><thead>
<tr>
    <th>版本</th>
    <th>修改日期</th>
    <th>修改人</th>
    <th>备注</th>
</tr>
</thead><tbody>
<tr>
    <td>1.0</td>
    <td></td>
    <td></td>
    <td></td>
</tr>
</tbody></table>

<h2>通用定义</h2>

<h3>时间定义</h3>

<ul>
    <li>除特殊说明之外，所有时间通信使用时间戳。格式为 14 位 yyyy-MM-dd hh:mm:ss ,例如20141225140000 。</li>
    <li>注: 客户端需要自行处理时区问题，与服务器的通信均认为是北京时间。</li>
</ul>

<h3>提交方式</h3>

<blockquote>
    <ul>
        <li>GET – 查询</li>
        <li>DELETE – 删除</li>
        <li>POST- 创建新的</li>
        <li>PUT – 更新</li>
    </ul>
</blockquote>

<h3>session管理</h3>

<p>客户端自身需要维护 session 管理，即使用同一个 session 的客户端进行 http 链接。</p>

<h3>错误信息</h3>

<p>除下载文件外，所有返回信息均为 <code>json</code>，必定包含 <code>success</code> 属性，有错误时必定包含 <code>errorcode</code>属性</p>

<pre><code class="js">{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errcode&quot;:&#39;-1&#39;,
    &quot;errmsg&quot;:&#39;错误信息&#39;
    }
</code></pre>

<p>errorcode 表示:</p>

<pre><code>1. `-701`  session 超时或者不存在该 session
    2. `-702`  该 session 因为密码被修改而失效
    3. `-703`  该 session 因用户被禁用而失效
</code></pre>

<h3>下载文件错误信息</h3>

<p>下载文件时，所有错误信息使用 http 错误状态码提示:</p>

<ul>
    <li><code>404</code> 表示未找到文件</li>
    <li><code>403</code> 表示无权限下载</li>
</ul>

<h3>请求报文Url及参数格式</h3>

<blockquote>
    <p>http://ip:port/ots/业务类别/业务场景（动／名词:search，detail，cancel）</p>
</blockquote>

<pre><code>所有 url 全部使用小写英文字母
    所有参数驼峰
</code></pre>

<p>事例：</p>

<pre><code>http://ip:port/ots/order/
    http://ip:port/ots/order/cancel
</code></pre>

<h3>报文中判断 true 和 false 的判断</h3>

<blockquote>
    <p>报文中判断是否的字段，均使用（T/F），T 代表 true，F 代表 false</p>
</blockquote>

<h2>fetch接口</h2>

<h3>拉取标签信息</h3>

<hr>

<p><strong>业务说明：</strong>
    拉取标签信息</p>

<p><strong>请求方式：</strong></p>

<blockquote>
    <p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
    <p>http://ip:port/cube/hotel/mergeHotelFacility</p>
</blockquote>

<p><strong>请求参数：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>﻿pageNo</td>
    <td>页码</td>
    <td>否</td>
    <td>不填默认从第一页开始拉取</td>
</tr>
</tbody></table>

<blockquote>
    <p>API返回json数据示例：</p>
</blockquote>

<ul>
    <li>success为T时</li>
    <li>http状态200</li>
</ul>

<pre><code class="js">
    {
    &quot;success&quot;:&quot;T&quot;
    }
</code></pre>

<ul>
    <li>success为F时</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errcode&quot;:,//错误码
    &quot;errmsg&quot;:
    }
</code></pre>

<h3>拉取pms酒店信息</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
    <p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
    <p>http://ip:port/cube/hotel/mergePmsHotel</p>
</blockquote>

<p><strong>请求参数：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>﻿pageNo</td>
    <td>页码</td>
    <td>否</td>
    <td>不填默认从第一页开始拉取</td>
</tr>
</tbody></table>

<blockquote>
    <p>API返回json数据示例：</p>
</blockquote>

<ul>
    <li>success为T时</li>
    <li>http状态200</li>
</ul>

<pre><code class="js">
    {
    &quot;success&quot;:&quot;T&quot;
    }
</code></pre>

<ul>
    <li>success为F时</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errcode&quot;:,//错误码
    &quot;errmsg&quot;:
    }
</code></pre>

<h3>拉取房型息</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
    <p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
    <p>http://ip:port/cube/roomtype/mergeRoomType</p>
</blockquote>

<p><strong>请求参数：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>﻿pageNo</td>
    <td>页码</td>
    <td>否</td>
    <td>不填默认从第一页开始拉取</td>
</tr>
</tbody></table>

<blockquote>
    <p>API返回json数据示例：</p>
</blockquote>

<ul>
    <li>success为T时</li>
    <li>http状态200</li>
</ul>

<pre><code class="js">
    {
    &quot;success&quot;:&quot;T&quot;
    }
</code></pre>

<ul>
    <li>success为F时</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errcode&quot;:,//错误码
    &quot;errmsg&quot;:
    }
</code></pre>

<h3>拉取房型价格信息</h3>

<hr>

<p><strong>业务说明：</strong>
    拉取标签信息</p>

<p><strong>请求方式：</strong></p>

<blockquote>
    <p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
    <p>http://ip:port/cube/roomtype/mergeRoomTypePrice</p>
</blockquote>

<p><strong>请求参数：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>﻿pageNo</td>
    <td>页码</td>
    <td>否</td>
    <td>不填默认从第一页开始拉取</td>
</tr>
</tbody></table>

<blockquote>
    <p>API返回json数据示例：</p>
</blockquote>

<ul>
    <li>success为T时</li>
    <li>http状态200</li>
</ul>

<pre><code class="js">
    {
    &quot;success&quot;:&quot;T&quot;
    }
</code></pre>

<ul>
    <li>success为F时</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errcode&quot;:,//错误码
    &quot;errmsg&quot;:
    }
</code></pre>

<h3>拉取酒店库存信息</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
    <p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
    <p>http://ip:port/cube/roomtype/mergeRoomTypeStock</p>
</blockquote>

<p><strong>请求参数：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>﻿pageNo</td>
    <td>页码</td>
    <td>否</td>
    <td>不填默认从第一页开始拉取</td>
</tr>
</tbody></table>

<blockquote>
    <p>API返回json数据示例：</p>
</blockquote>

<ul>
    <li>success为T时</li>
    <li>http状态200</li>
</ul>

<pre><code class="js">
    {
    &quot;success&quot;:&quot;T&quot;
    }
</code></pre>

<ul>
    <li>success为F时</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errcode&quot;:,//错误码
    &quot;errmsg&quot;:
    }
</code></pre>

<h2>PUSH接口</h2>

<h3>酒店信息全量push</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
    <p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
    <p>http://ip:port/cube/hotel/hotelall</p>
</blockquote>

<p><strong>head请求参数：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>﻿token</td>
    <td>token</td>
    <td>是</td>
    <td></td>
</tr>
</tbody></table>

<p><strong>body请求参数(json格式 以raw方式请求)：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>id</td>
    <td>酒店id</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>hotelname</td>
    <td>酒店名称</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>hotelphone</td>
    <td>酒店电话</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>detailaddr</td>
    <td>酒店详情</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>hoteltype</td>
    <td>酒店类型</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>longitude</td>
    <td>经度</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>latitude</td>
    <td>维度</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>retentiontime</td>
    <td>保留时间</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>defaultleavetime</td>
    <td>默认预离时间</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>provcode</td>
    <td>省</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>citycode</td>
    <td>市</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>discode</td>
    <td>县区</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>provincename</td>
    <td>省</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>cityname</td>
    <td>市</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>districtname</td>
    <td>县区</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>hotelpic</td>
    <td>酒店图片</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>hotelpics</td>
    <td>酒店图片json</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>introduction</td>
    <td>酒店介绍</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>opentime</td>
    <td>开业时间</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>repairtime</td>
    <td>装修时间</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>roomtypes</td>
    <td>roomtypes子对象</td>
    <td>是</td>
    <td></td>
</tr>
</tbody></table>

<table><thead>
<tr>
    <th>roomtypes字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>id</td>
    <td>房型id</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>hotelid</td>
    <td>酒店id</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>name</td>
    <td>房型名称</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>area</td>
    <td>房型面积</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>bedtype</td>
    <td>床型</td>
    <td>是</td>
    <td>1、大床 2、双床 3、三床 4、单人床 5、一单一双 6、上下铺 7、通铺 8、榻榻米 9、水床 10、圆床 11、拼床 12、其他</td>
</tr>
<tr>
    <td>bedsize</td>
    <td>床尺寸</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>prepay</td>
    <td>支付类型</td>
    <td>是</td>
    <td>0、预付</td>
</tr>
<tr>
    <td>breakfast</td>
    <td>是否含早餐</td>
    <td>是</td>
    <td>0、无早；1、含早</td>
</tr>
<tr>
    <td>status</td>
    <td>是否可定（关房）</td>
    <td>是</td>
    <td>0、可定；1、不可订</td>
</tr>
<tr>
    <td>refund</td>
    <td>是否可退款</td>
    <td>是</td>
    <td>0、不可退</td>
</tr>
<tr>
    <td>maxroomnum</td>
    <td>单个订单最大房量</td>
    <td>是</td>
    <td>默认为8 可设置值为[1,8]；</td>
</tr>
<tr>
    <td>roomnum</td>
    <td>房间数</td>
    <td>是</td>
    <td>最大预定数，不是可用的</td>
</tr>
<tr>
    <td>roomtypepics</td>
    <td>图片</td>
    <td>是</td>
    <td>json</td>
</tr>
</tbody></table>

<blockquote>
    <p>API提交json数据示例：</p>
</blockquote>

<pre><code class="js">{
    &quot;data&quot;: {
    &quot;hotel&quot;: {
    &quot;citycode&quot;: 310000,
    &quot;cityname&quot;: &quot;S 上海市&quot;,
    &quot;defaultleavetime&quot;: &quot;120000&quot;,
    &quot;detailaddr&quot;: &quot;灵石路679-3号&quot;,
    &quot;discode&quot;: 310108,
    &quot;districtname&quot;: &quot;Z 闸北区&quot;,
    &quot;hotelname&quot;: &quot;上轩商务酒店&quot;,
    &quot;hotelphone&quot;: &quot;13810711699&quot;,
    &quot;hotelpic&quot;: &quot;https://dn-imke-pro.qbox.me/FsldGWiFERrp0uuCSaeG4CB-4EH2&quot;,
    &quot;hotelpics&quot;: &quot;[{\&quot;name\&quot;:\&quot;def\&quot;,\&quot;pic\&quot;:[{\&quot;url\&quot;:\&quot;https://dn-imke-pro.qbox.me/FsldGWiFERrp0uuCSaeG4CB-4EH2\&quot;}]},{\&quot;name\&quot;:\&quot;lobby\&quot;,\&quot;pic\&quot;:[{\&quot;url\&quot;:\&quot;https://dn-imke-pro.qbox.me/FqScMeNfEkN68Zw3Yi4711FdascG\&quot;}]},{\&quot;name\&quot;:\&quot;mainHousing\&quot;,\&quot;pic\&quot;:[{\&quot;url\&quot;:\&quot;https://dn-imke-pro.qbox.me/FseQy0avIf9k4Gq5uCcWi_9rpG0U\&quot;}]}]&quot;,
    &quot;hoteltype&quot;: 3,
    &quot;id&quot;: 2813,
    &quot;introduction&quot;: &quot;测试酒店&quot;,
    &quot;latitude&quot;: 29.58339,
    &quot;longitude&quot;: 106.497452,
    &quot;opentime&quot;: &quot;2014-02-01&quot;,
    &quot;provcode&quot;: 310000,
    &quot;provincename&quot;: &quot;S 上海市&quot;,
    &quot;repairtime&quot;: &quot;2014-02-01&quot;,
    &quot;retentiontime&quot;: &quot;180000&quot;,
    &quot;roomtypes&quot;: [
    {
    &quot;area&quot;: &quot;20.00&quot;,
    &quot;bedsize&quot;: &quot;2.20&quot;,
    &quot;bedtype&quot;: &quot;1&quot;,
    &quot;breakfast&quot;: &quot;0&quot;,
    &quot;id&quot;: 29995,
    &quot;name&quot;: &quot;大床房&quot;,
    &quot;prepay&quot;: &quot;1&quot;,
    &quot;roomnum&quot;: 20,
    &quot;status&quot;:0,
    &quot;refund&quot;:0,
    &quot;roomtypepics&quot;: &quot;[{\&quot;name\&quot;:\&quot;def\&quot;,\&quot;pic\&quot;:[{\&quot;url\&quot;:\&quot;https://dn-imke-pro.qbox.me/Fpy2bsNNUSdyyx4jKkG89FpgzPj2\&quot;}]},{\&quot;name\&quot;:\&quot;bed\&quot;,\&quot;pic\&quot;:[]},{\&quot;name\&quot;:\&quot;toilet\&quot;,\&quot;pic\&quot;:[]}]&quot;
    },
    {
    &quot;area&quot;: &quot;25.00&quot;,
    &quot;bedsize&quot;: &quot;2.20&quot;,
    &quot;bedtype&quot;: &quot;1&quot;,
    &quot;breakfast&quot;: &quot;0&quot;,
    &quot;id&quot;: 29996,
    &quot;name&quot;: &quot;海景房&quot;,
    &quot;prepay&quot;: &quot;1&quot;,
    &quot;roomnum&quot;: 4,
    &quot;status&quot;:0,
    &quot;refund&quot;:0,
    &quot;roomtypepics&quot;: &quot;[{\&quot;name\&quot;:\&quot;def\&quot;,\&quot;pic\&quot;:[{\&quot;url\&quot;:\&quot;https://dn-imke-pro.qbox.me/Fpy2bsNNUSdyyx4jKkG89FpgzPj2\&quot;}]},{\&quot;name\&quot;:\&quot;bed\&quot;,\&quot;pic\&quot;:[]},{\&quot;name\&quot;:\&quot;toilet\&quot;,\&quot;pic\&quot;:[]}]&quot;
    },
    {
    &quot;area&quot;: &quot;34.00&quot;,
    &quot;bedsize&quot;: &quot;2.20,2.20&quot;,
    &quot;bedtype&quot;: &quot;2&quot;,
    &quot;breakfast&quot;: &quot;0&quot;,
    &quot;id&quot;: 29997,
    &quot;name&quot;: &quot;大床房A&quot;,
    &quot;prepay&quot;: &quot;1&quot;,
    &quot;roomnum&quot;: 20,
    &quot;status&quot;:0,
    &quot;refund&quot;:0,
    &quot;roomtypepics&quot;: &quot;[{\&quot;name\&quot;:\&quot;def\&quot;,\&quot;pic\&quot;:[{\&quot;url\&quot;:\&quot;https://dn-imke-pro.qbox.me/FipvpKpZ2oJL8vS624ugGQm-aDDi\&quot;}]},{\&quot;name\&quot;:\&quot;bed\&quot;,\&quot;pic\&quot;:[]},{\&quot;name\&quot;:\&quot;toilet\&quot;,\&quot;pic\&quot;:[]}]&quot;
    },
    {
    &quot;area&quot;: &quot;34.00&quot;,
    &quot;bedsize&quot;: &quot;2.20&quot;,
    &quot;bedtype&quot;: &quot;1&quot;,
    &quot;breakfast&quot;: &quot;0&quot;,
    &quot;id&quot;: 30025,
    &quot;name&quot;: &quot;特价房&quot;,
    &quot;prepay&quot;: &quot;1&quot;,
    &quot;roomnum&quot;: 10,
    &quot;status&quot;:0,
    &quot;refund&quot;:0,
    &quot;roomtypepics&quot;: &quot;[{\&quot;name\&quot;:\&quot;def\&quot;,\&quot;pic\&quot;:[{\&quot;url\&quot;:\&quot;https://dn-imke-pro.qbox.me/FoLA7XtPcrBjjqp0rfwSkK-CJSX1\&quot;}]},{\&quot;name\&quot;:\&quot;bed\&quot;,\&quot;pic\&quot;:[]},{\&quot;name\&quot;:\&quot;toilet\&quot;,\&quot;pic\&quot;:[]}]&quot;
    },
    {
    &quot;area&quot;: &quot;30.00&quot;,
    &quot;bedsize&quot;: &quot;2.00,1.80&quot;,
    &quot;bedtype&quot;: &quot;2&quot;,
    &quot;breakfast&quot;: &quot;0&quot;,
    &quot;id&quot;: 30046,
    &quot;name&quot;: &quot;压测房型&quot;,
    &quot;prepay&quot;: &quot;1&quot;,
    &quot;roomnum&quot;: 200,
    &quot;status&quot;:0,
    &quot;refund&quot;:0,
    &quot;roomtypepics&quot;: &quot;[{\&quot;name\&quot;:\&quot;def\&quot;,\&quot;pic\&quot;:[{\&quot;url\&quot;:\&quot;https://dn-imke-pro.qbox.me/FqScMeNfEkN68Zw3Yi4711FdascG\&quot;}]},{\&quot;name\&quot;:\&quot;bed\&quot;,\&quot;pic\&quot;:[]},{\&quot;name\&quot;:\&quot;toilet\&quot;,\&quot;pic\&quot;:[]}]&quot;
    }
    ]
    }
    }
    }
</code></pre>

<blockquote>
    <p>API返回json数据示例：</p>
</blockquote>

<ul>
    <li>success为T时</li>
    <li>http状态200</li>
</ul>

<pre><code class="js">{
    &quot;success&quot;:&quot;T&quot;
    }
</code></pre>

<ul>
    <li>success为F时</li>
    <li>http状态400</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errorCode&quot;:,//错误码
    &quot;errorMessage&quot;:
    }
</code></pre>

<h3>酒店信息删除push</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
    <p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
    <p>http://ip:port/cube/hotel/delete</p>
</blockquote>

<p><strong>head请求参数：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>﻿token</td>
    <td>token</td>
    <td>是</td>
    <td></td>
</tr>
</tbody></table>

<p><strong>body请求参数(json格式 以raw方式请求)：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>id</td>
    <td>酒店id</td>
    <td>是</td>
    <td></td>
</tr>
</tbody></table>

<blockquote>
    <p>API提交json数据示例：</p>
</blockquote>

<pre><code class="js">{
    &quot;data&quot;: {
    &quot;hotelid&quot;: 2813
    }
    }
</code></pre>

<blockquote>
    <p>API返回json数据示例：</p>
</blockquote>

<ul>
    <li>success为T时</li>
    <li>http状态200</li>
</ul>

<pre><code class="js">{
    &quot;success&quot;:&quot;T&quot;
    }
</code></pre>

<ul>
    <li>success为F时</li>
    <li>http状态400</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errorCode&quot;:,//错误码
    &quot;errorMessage&quot;:
    }
</code></pre>

<h3>酒店标签信息增量push</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
    <p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
    <p>http://ip:port/cube/hotel/hotelfacility</p>
</blockquote>

<p><strong>head请求参数：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>﻿token</td>
    <td>token</td>
    <td>是</td>
    <td></td>
</tr>
</tbody></table>

<p><strong>body请求参数(json格式 以raw方式请求)：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>﻿hotelid</td>
    <td>酒店id</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>﻿tags</td>
    <td>标签id,逗号分隔</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>﻿roomtypeTags</td>
    <td>房型标签对象数组</td>
    <td>是</td>
    <td></td>
</tr>
</tbody></table>

<table><thead>
<tr>
    <th>tags对象字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>﻿id</td>
    <td>标签id</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>﻿tagname</td>
    <td>标签name</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>﻿taggroupid</td>
    <td>标签分类id</td>
    <td>是</td>
    <td>1、商圈位置(5公里内) 2、类型特色 3、设施服务</td>
</tr>
</tbody></table>

<blockquote>
    <p>API提交json数据示例：</p>
</blockquote>

<pre><code class="js">{
    &quot;data&quot;: {
    &quot;hotelid&quot;: 2813,
    &quot;roomtypeTags&quot;: null,
    &quot;tags&quot;: [
    {
    &quot;id&quot;: 2,
    &quot;taggroupid&quot;: 2,
    &quot;tagname&quot;: &quot;温泉度假&quot;
    },
    {
    &quot;id&quot;: 24,
    &quot;taggroupid&quot;: 3,
    &quot;tagname&quot;: &quot;免费无线&quot;
    },
    {
    &quot;id&quot;: 32,
    &quot;taggroupid&quot;: 3,
    &quot;tagname&quot;: &quot;独立卫浴&quot;
    },
    {
    &quot;id&quot;: 36,
    &quot;taggroupid&quot;: 3,
    &quot;tagname&quot;: &quot;旅游票务服务&quot;
    },
    {
    &quot;id&quot;: 37,
    &quot;taggroupid&quot;: 1,
    &quot;tagname&quot;: &quot;旅游景区&quot;
    }
    ]
    }
    }
</code></pre>

<blockquote>
    <p>API返回json数据示例：</p>
</blockquote>

<ul>
    <li>success为T时</li>
    <li>http状态200</li>
</ul>

<pre><code class="js">{
    &quot;success&quot;:&quot;T&quot;
    }
</code></pre>

<ul>
    <li>success为F时</li>
    <li>http状态400</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errorCode&quot;:,//错误码
    &quot;errorMessage&quot;:
    }
</code></pre>

<h3>房型信息增量push</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
    <p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
    <p>http://ip:port/cube/roomtype/roomtype</p>
</blockquote>

<p><strong>head请求参数：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>﻿token</td>
    <td>token</td>
    <td>是</td>
    <td></td>
</tr>
</tbody></table>

<p><strong>body请求参数(json格式 以raw方式请求)：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>id</td>
    <td>房型id</td>
    <td>是</td>
    <td></td>
</tr>
</tbody></table>

<p>hotelid
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
    |roomtypepics|图片|是|json</p>

<blockquote>
    <p>API提交json数据示例：</p>
</blockquote>

<pre><code class="js">[
    {
    &quot;area&quot;: &quot;20.00&quot;,
    &quot;bedsize&quot;: &quot;2.20&quot;,
    &quot;bedtype&quot;: &quot;1&quot;,
    &quot;breakfast&quot;: &quot;0&quot;,
    &quot;id&quot;: 29967,
    &quot;hotelid&quot;:2807,
    &quot;name&quot;: &quot;大床房&quot;,
    &quot;prepay&quot;: &quot;1&quot;,
    &quot;roomnum&quot;: 20,
    &quot;status&quot;:0,
    &quot;refund&quot;:0,
    &quot;maxroomnum&quot;:8,
    &quot;roomtypepics&quot;: &quot;[{\&quot;name\&quot;:\&quot;def\&quot;,\&quot;pic\&quot;:[{\&quot;url\&quot;:\&quot;https://dn-imke-pro.qbox.me/Fpy2bsNNUSdyyx4jKkG89FpgzPj2\&quot;}]},{\&quot;name\&quot;:\&quot;bed\&quot;,\&quot;pic\&quot;:[]},{\&quot;name\&quot;:\&quot;toilet\&quot;,\&quot;pic\&quot;:[]}]&quot;
    },{
    &quot;area&quot;: &quot;20.00&quot;,
    &quot;bedsize&quot;: &quot;2.20&quot;,
    &quot;bedtype&quot;: &quot;1&quot;,
    &quot;breakfast&quot;: &quot;0&quot;,
    &quot;id&quot;: 29967,
    &quot;hotelid&quot;:2807,
    &quot;name&quot;: &quot;大床房&quot;,
    &quot;prepay&quot;: &quot;1&quot;,
    &quot;roomnum&quot;: 20,
    &quot;status&quot;:0,
    &quot;refund&quot;:0,
    &quot;maxroomnum&quot;:8,
    &quot;roomtypepics&quot;: &quot;[{\&quot;name\&quot;:\&quot;def\&quot;,\&quot;pic\&quot;:[{\&quot;url\&quot;:\&quot;https://dn-imke-pro.qbox.me/Fpy2bsNNUSdyyx4jKkG89FpgzPj2\&quot;}]},{\&quot;name\&quot;:\&quot;bed\&quot;,\&quot;pic\&quot;:[]},{\&quot;name\&quot;:\&quot;toilet\&quot;,\&quot;pic\&quot;:[]}]&quot;
    }
    ]
</code></pre>

<blockquote>
    <p>API返回json数据示例：</p>
</blockquote>

<ul>
    <li>success为T时</li>
    <li>http状态200</li>
</ul>

<pre><code class="js">{
    &quot;success&quot;:&quot;T&quot;
    }
</code></pre>

<ul>
    <li>success为F时</li>
    <li>http状态400</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errorCode&quot;:,//错误码
    &quot;errorMessage&quot;:
    }
</code></pre>

<h3>房型信息删除push</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
    <p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
    <p>http://ip:port/cube/roomtype/delete</p>
</blockquote>

<p><strong>head请求参数：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>﻿token</td>
    <td>token</td>
    <td>是</td>
    <td></td>
</tr>
</tbody></table>

<p><strong>body请求参数(json格式 以raw方式请求)：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>id</td>
    <td>房型id</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>hotelid</td>
    <td>酒店id</td>
    <td>是</td>
    <td></td>
</tr>
</tbody></table>

<blockquote>
    <p>API提交json数据示例：</p>
</blockquote>

<pre><code class="js">{
    data:{
    &quot;id&quot;: 29995,
    &quot;hotelid&quot;: 243
    }
    }
</code></pre>

<blockquote>
    <p>API返回json数据示例：</p>
</blockquote>

<ul>
    <li>success为T时</li>
    <li>http状态200</li>
</ul>

<pre><code class="js">{
    &quot;success&quot;:&quot;T&quot;
    }
</code></pre>

<ul>
    <li>success为F时</li>
    <li>http状态400</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errorCode&quot;:,//错误码
    &quot;errorMessage&quot;:
    }
</code></pre>

<h3>房型价格信息增量push</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
    <p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
    <p>http://ip:port/cube/roomtype/roomtypeprice</p>
</blockquote>

<p><strong>head请求参数：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>﻿token</td>
    <td>token</td>
    <td>是</td>
    <td></td>
</tr>
</tbody></table>

<p><strong>body请求参数(json格式 以raw方式请求)：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>﻿hotelid</td>
    <td>酒店id</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>﻿roomtypes</td>
    <td>房型集合</td>
    <td>是</td>
    <td></td>
</tr>
</tbody></table>

<table><thead>
<tr>
    <th>roomtypes字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>﻿roomtypeid</td>
    <td>房型id</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>﻿priceinfo</td>
    <td>价格集合</td>
    <td>是</td>
    <td></td>
</tr>
</tbody></table>

<table><thead>
<tr>
    <th>priceinfo字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>﻿date</td>
    <td>日期</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>﻿cost</td>
    <td>价格</td>
    <td>是</td>
    <td></td>
</tr>
</tbody></table>

<blockquote>
    <p>API提交json数据示例：</p>
</blockquote>

<pre><code class="js">{
    &quot;data&quot;: {
    &quot;hotelid&quot;:2813,
    &quot;roomtypeprices&quot;: [
    {
    &quot;priceinfos&quot;: [
    {
    &quot;cost&quot;: &quot;10.00&quot;,
    &quot;date&quot;: &quot;2016-05-11&quot;
    }
    ],
    &quot;roomtypeid&quot;: 29995
    }
    ]
    }
    }
</code></pre>

<blockquote>
    <p>API返回json数据示例：</p>
</blockquote>

<ul>
    <li>success为T时</li>
    <li>http状态200</li>
</ul>

<pre><code class="js">{
    &quot;success&quot;:&quot;T&quot;
    }
</code></pre>

<ul>
    <li>success为F时</li>
    <li>http状态400</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errorCode&quot;:,//错误码
    &quot;errorMessage&quot;:
    }
</code></pre>

<h3>订单状态增量push</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
    <p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
    <p>http://ip:port/cube/order/orderstatus</p>
</blockquote>

<p><strong>head请求参数：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>﻿token</td>
    <td>token</td>
    <td>是</td>
    <td></td>
</tr>
</tbody></table>

<p><strong>body请求参数(json格式 以raw方式请求)：</strong></p>

<table><thead>
<tr>
    <th>字段</th>
    <th>名称</th>
    <th>是否必须</th>
    <th>说明</th>
</tr>
</thead><tbody>
<tr>
    <td>﻿orderid</td>
    <td>订单id</td>
    <td>是</td>
    <td></td>
</tr>
<tr>
    <td>﻿orderstatus</td>
    <td>订单状态</td>
    <td>是</td>
    <td>0:待确认,5:已经客服确认等待老板确认,10:确认中,20:已确认,40:入住,100:完成,200:订单取消,210:pms取消,220:客服取消,230:系统取消</td>
</tr>
</tbody></table>

<blockquote>
    <p>API提交json数据示例：</p>
</blockquote>

<pre><code class="js">{
    &quot;orderid&quot;: 1234567,
    &quot;orderstatus&quot;: 1
    }
</code></pre>

<blockquote>
    <p>API返回json数据示例：</p>
</blockquote>

<ul>
    <li>success为T时</li>
    <li>http状态200</li>
</ul>

<pre><code class="js">{
    &quot;success&quot;:&quot;T&quot;
    }
</code></pre>

<ul>
    <li>success为F时</li>
    <li>http状态400</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errorCode&quot;:,//错误码
    &quot;errorMessage&quot;:
    }
</code></pre>

<p></article></p>

<p><link href="asset/css/zTreeStyle.css" media="all" rel="stylesheet" type="text/css"/></p>

<script type="text/javascript" src="asset/js/jquery-1.4.4.min.js"></script>

<script type="text/javascript" src="asset/js/jquery.ztree.all-3.5.min.js"></script>

<script type="text/javascript" src="asset/js/ztree_toc.js"></script>

<script type="text/javascript" src="asset/js/mdtree.js"></script>

</body>
</html>