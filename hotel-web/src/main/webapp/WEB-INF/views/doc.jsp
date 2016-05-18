<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
<meta charset="utf-8">
<title>doc</title>
<style type="text/css">
body {
  font-family: Helvetica, arial, sans-serif;
  font-size: 14px;
  line-height: 1.6;
  padding-top: 10px;
  padding-bottom: 10px;
  background-color: white;
  padding: 30px; }

body > *:first-child {
  margin-top: 0 !important; }
body > *:last-child {
  margin-bottom: 0 !important; }

a {
  color: #4183C4; }
a.absent {
  color: #cc0000; }
a.anchor {
  display: block;
  padding-left: 30px;
  margin-left: -30px;
  cursor: pointer;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0; }

h1, h2, h3, h4, h5, h6 {
  margin: 20px 0 10px;
  padding: 0;
  font-weight: bold;
  -webkit-font-smoothing: antialiased;
  cursor: text;
  position: relative; }

h1:hover a.anchor, h2:hover a.anchor, h3:hover a.anchor, h4:hover a.anchor, h5:hover a.anchor, h6:hover a.anchor {
  background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA09pVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoMTMuMCAyMDEyMDMwNS5tLjQxNSAyMDEyLzAzLzA1OjIxOjAwOjAwKSAgKE1hY2ludG9zaCkiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6OUM2NjlDQjI4ODBGMTFFMTg1ODlEODNERDJBRjUwQTQiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6OUM2NjlDQjM4ODBGMTFFMTg1ODlEODNERDJBRjUwQTQiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo5QzY2OUNCMDg4MEYxMUUxODU4OUQ4M0REMkFGNTBBNCIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo5QzY2OUNCMTg4MEYxMUUxODU4OUQ4M0REMkFGNTBBNCIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PsQhXeAAAABfSURBVHjaYvz//z8DJYCRUgMYQAbAMBQIAvEqkBQWXI6sHqwHiwG70TTBxGaiWwjCTGgOUgJiF1J8wMRAIUA34B4Q76HUBelAfJYSA0CuMIEaRP8wGIkGMA54bgQIMACAmkXJi0hKJQAAAABJRU5ErkJggg==) no-repeat 10px center;
  text-decoration: none; }

h1 tt, h1 code {
  font-size: inherit; }

h2 tt, h2 code {
  font-size: inherit; }

h3 tt, h3 code {
  font-size: inherit; }

h4 tt, h4 code {
  font-size: inherit; }

h5 tt, h5 code {
  font-size: inherit; }

h6 tt, h6 code {
  font-size: inherit; }

h1 {
  font-size: 28px;
  color: black; }

h2 {
  font-size: 24px;
  border-bottom: 1px solid #cccccc;
  color: black; }

h3 {
  font-size: 18px; }

h4 {
  font-size: 16px; }

h5 {
  font-size: 14px; }

h6 {
  color: #777777;
  font-size: 14px; }

p, blockquote, ul, ol, dl, li, table, pre {
  margin: 15px 0; }

hr {
  background: transparent url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAYAAAAECAYAAACtBE5DAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBNYWNpbnRvc2giIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6OENDRjNBN0E2NTZBMTFFMEI3QjRBODM4NzJDMjlGNDgiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6OENDRjNBN0I2NTZBMTFFMEI3QjRBODM4NzJDMjlGNDgiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo4Q0NGM0E3ODY1NkExMUUwQjdCNEE4Mzg3MkMyOUY0OCIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo4Q0NGM0E3OTY1NkExMUUwQjdCNEE4Mzg3MkMyOUY0OCIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PqqezsUAAAAfSURBVHjaYmRABcYwBiM2QSA4y4hNEKYDQxAEAAIMAHNGAzhkPOlYAAAAAElFTkSuQmCC) repeat-x 0 0;
  border: 0 none;
  color: #cccccc;
  height: 4px;
  padding: 0;
}

body > h2:first-child {
  margin-top: 0;
  padding-top: 0; }
body > h1:first-child {
  margin-top: 0;
  padding-top: 0; }
  body > h1:first-child + h2 {
    margin-top: 0;
    padding-top: 0; }
body > h3:first-child, body > h4:first-child, body > h5:first-child, body > h6:first-child {
  margin-top: 0;
  padding-top: 0; }

a:first-child h1, a:first-child h2, a:first-child h3, a:first-child h4, a:first-child h5, a:first-child h6 {
  margin-top: 0;
  padding-top: 0; }

h1 p, h2 p, h3 p, h4 p, h5 p, h6 p {
  margin-top: 0; }

li p.first {
  display: inline-block; }
li {
  margin: 0; }
ul, ol {
  padding-left: 30px; }

ul :first-child, ol :first-child {
  margin-top: 0; }

dl {
  padding: 0; }
  dl dt {
    font-size: 14px;
    font-weight: bold;
    font-style: italic;
    padding: 0;
    margin: 15px 0 5px; }
    dl dt:first-child {
      padding: 0; }
    dl dt > :first-child {
      margin-top: 0; }
    dl dt > :last-child {
      margin-bottom: 0; }
  dl dd {
    margin: 0 0 15px;
    padding: 0 15px; }
    dl dd > :first-child {
      margin-top: 0; }
    dl dd > :last-child {
      margin-bottom: 0; }

blockquote {
  border-left: 4px solid #dddddd;
  padding: 0 15px;
  color: #777777; }
  blockquote > :first-child {
    margin-top: 0; }
  blockquote > :last-child {
    margin-bottom: 0; }

table {
  padding: 0;border-collapse: collapse; }
  table tr {
    border-top: 1px solid #cccccc;
    background-color: white;
    margin: 0;
    padding: 0; }
    table tr:nth-child(2n) {
      background-color: #f8f8f8; }
    table tr th {
      font-weight: bold;
      border: 1px solid #cccccc;
      margin: 0;
      padding: 6px 13px; }
    table tr td {
      border: 1px solid #cccccc;
      margin: 0;
      padding: 6px 13px; }
    table tr th :first-child, table tr td :first-child {
      margin-top: 0; }
    table tr th :last-child, table tr td :last-child {
      margin-bottom: 0; }

img {
  max-width: 100%; }

span.frame {
  display: block;
  overflow: hidden; }
  span.frame > span {
    border: 1px solid #dddddd;
    display: block;
    float: left;
    overflow: hidden;
    margin: 13px 0 0;
    padding: 7px;
    width: auto; }
  span.frame span img {
    display: block;
    float: left; }
  span.frame span span {
    clear: both;
    color: #333333;
    display: block;
    padding: 5px 0 0; }
span.align-center {
  display: block;
  overflow: hidden;
  clear: both; }
  span.align-center > span {
    display: block;
    overflow: hidden;
    margin: 13px auto 0;
    text-align: center; }
  span.align-center span img {
    margin: 0 auto;
    text-align: center; }
span.align-right {
  display: block;
  overflow: hidden;
  clear: both; }
  span.align-right > span {
    display: block;
    overflow: hidden;
    margin: 13px 0 0;
    text-align: right; }
  span.align-right span img {
    margin: 0;
    text-align: right; }
span.float-left {
  display: block;
  margin-right: 13px;
  overflow: hidden;
  float: left; }
  span.float-left span {
    margin: 13px 0 0; }
span.float-right {
  display: block;
  margin-left: 13px;
  overflow: hidden;
  float: right; }
  span.float-right > span {
    display: block;
    overflow: hidden;
    margin: 13px auto 0;
    text-align: right; }

code, tt {
  margin: 0 2px;
  padding: 0 5px;
  white-space: nowrap;
  border: 1px solid #eaeaea;
  background-color: #f8f8f8;
  border-radius: 3px; }

pre code {
  margin: 0;
  padding: 0;
  white-space: pre;
  border: none;
  background: transparent; }

.highlight pre {
  background-color: #f8f8f8;
  border: 1px solid #cccccc;
  font-size: 13px;
  line-height: 19px;
  overflow: auto;
  padding: 6px 10px;
  border-radius: 3px; }

pre {
  background-color: #f8f8f8;
  border: 1px solid #cccccc;
  font-size: 13px;
  line-height: 19px;
  overflow: auto;
  padding: 6px 10px;
  border-radius: 3px; }
  pre code, pre tt {
    background-color: transparent;
    border: none; }

sup {
    font-size: 0.83em;
    vertical-align: super;
    line-height: 0;
}
* {
	-webkit-print-color-adjust: exact;
}
@media screen and (min-width: 914px) {
    body {
        width: 854px;
        margin:0 auto;
    }
}
@media print {
	table, pre {
		page-break-inside: avoid;
	}
	pre {
		word-wrap: break-word;
	}
}
</style>
<style type="text/css">
/**
 * prism.js tomorrow night eighties for JavaScript, CoffeeScript, CSS and HTML
 * Based on https://github.com/chriskempson/tomorrow-theme
 * @author Rose Pritchard
 */

code[class*="language-"],
pre[class*="language-"] {
	color: #ccc;
	font-family: Consolas, Monaco, 'Andale Mono', monospace;
	direction: ltr;
	text-align: left;
	white-space: pre;
	word-spacing: normal;
	word-break: normal;
	line-height: 1.5;

	-moz-tab-size: 4;
	-o-tab-size: 4;
	tab-size: 4;

	-webkit-hyphens: none;
	-moz-hyphens: none;
	-ms-hyphens: none;
	hyphens: none;

}

/* Code blocks */
pre[class*="language-"] {
	padding: 1em;
	margin: .5em 0;
	overflow: auto;
}

:not(pre) > code[class*="language-"],
pre[class*="language-"] {
	background: #2d2d2d;
}

/* Inline code */
:not(pre) > code[class*="language-"] {
	padding: .1em;
	border-radius: .3em;
}

.token.comment,
.token.block-comment,
.token.prolog,
.token.doctype,
.token.cdata {
	color: #999;
}

.token.punctuation {
	color: #ccc;
}

.token.tag,
.token.attr-name,
.token.namespace,
.token.deleted {
	color: #e2777a;
}

.token.function-name {
	color: #6196cc;
}

.token.boolean,
.token.number,
.token.function {
	color: #f08d49;
}

.token.property,
.token.class-name,
.token.constant,
.token.symbol {
	color: #f8c555;
}

.token.selector,
.token.important,
.token.atrule,
.token.keyword,
.token.builtin {
	color: #cc99cd;
}

.token.string,
.token.char,
.token.attr-value,
.token.regex,
.token.variable {
	color: #7ec699;
}

.token.operator,
.token.entity,
.token.url {
	color: #67cdcc;
}

.token.important,
.token.bold {
	font-weight: bold;
}
.token.italic {
	font-style: italic;
}

.token.entity {
	cursor: help;
}

.token.inserted {
	color: green;
}
</style>
<style type="text/css">
pre.line-numbers {
	position: relative;
	padding-left: 3.8em;
	counter-reset: linenumber;
}

pre.line-numbers > code {
	position: relative;
}

.line-numbers .line-numbers-rows {
	position: absolute;
	pointer-events: none;
	top: 0;
	font-size: 100%;
	left: -3.8em;
	width: 3em; /* works for line-numbers below 1000 lines */
	letter-spacing: -1px;
	border-right: 1px solid #999;

	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;

}

	.line-numbers-rows > span {
		pointer-events: none;
		display: block;
		counter-increment: linenumber;
	}

		.line-numbers-rows > span:before {
			content: counter(linenumber);
			color: #999;
			display: block;
			padding-right: 0.8em;
			text-align: right;
		}
</style>
</head>
<body>
<div style='width:300px; magin-top:20px'>
    <ul id="tree" class="ztree" style='width:100%;float:right'>
    </ul>
</div>

<p><article class='md-body' style="margin-left: 15%;"></p>

<h1 id="toc_0">Hotel Api 文档</h1>

<h2 id="toc_1">版本</h2>

<table>
<thead>
<tr>
<th>版本</th>
<th>修改日期</th>
<th>修改人</th>
<th>备注</th>
</tr>
</thead>

<tbody>
<tr>
<td>1.0</td>
<td>|  |</td>
<td></td>
<td></td>
</tr>
</tbody>
</table>

<h2 id="toc_2">通用定义</h2>

<h3 id="toc_3">时间定义</h3>

<ul>
<li>除特殊说明之外，所有时间通信使用时间戳。格式为 14 位 yyyy-MM-dd hh:mm:ss ,例如20141225140000 。</li>
<li>注: 客户端需要自行处理时区问题，与服务器的通信均认为是北京时间。</li>
</ul>

<h3 id="toc_4">提交方式</h3>

<blockquote>
<ul>
<li>GET – 查询</li>
<li>DELETE – 删除</li>
<li>POST- 创建新的</li>
<li>PUT – 更新</li>
</ul>
</blockquote>

<h3 id="toc_5">session管理</h3>

<p>客户端自身需要维护 session 管理，即使用同一个 session 的客户端进行 http 链接。</p>

<h3 id="toc_6">错误信息</h3>

<p>除下载文件外，所有返回信息均为 <code>json</code>，必定包含 <code>success</code> 属性，有错误时必定包含 <code>errorcode</code>属性</p>

<pre class="line-numbers"><code class="language-javascript">{
&quot;success&quot;:&quot;F&quot;,
&quot;errcode&quot;:&#39;-1&#39;,
&quot;errmsg&quot;:&#39;错误信息&#39;
}</code></pre>

<p>errorcode 表示:</p>

<pre><code>1. `-701`  session 超时或者不存在该 session
2. `-702`  该 session 因为密码被修改而失效
3. `-703`  该 session 因用户被禁用而失效</code></pre>

<h3 id="toc_7">下载文件错误信息</h3>

<p>下载文件时，所有错误信息使用 http 错误状态码提示:</p>

<ul>
<li><code>404</code> 表示未找到文件</li>
<li><code>403</code> 表示无权限下载</li>
</ul>

<h3 id="toc_8">请求报文Url及参数格式</h3>

<blockquote>
<p><a href="http://ip:port/cube/%E4%B8%9A%E5%8A%A1%E7%B1%BB%E5%88%AB/%E4%B8%9A%E5%8A%A1%E5%9C%BA%E6%99%AF%EF%BC%88%E5%8A%A8%EF%BC%8F%E5%90%8D%E8%AF%8D:search%EF%BC%8Cdetail%EF%BC%8Ccancel%EF%BC%89">http://ip:port/cube/业务类别/业务场景（动／名词:search，detail，cancel）</a></p>
</blockquote>

<pre><code>所有 url 全部使用小写英文字母
所有参数驼峰</code></pre>

<p>事例：</p>

<pre><code>http://ip:port/cube/order/
http://ip:port/cube/order/cancel</code></pre>

<h2 id="toc_9">域名</h2>

<h3 id="toc_10">模拟环境</h3>

<blockquote>
<p><a href="http://api.imike.cn/cube">http://api.imike.cn/cube</a></p>
</blockquote>

<h3 id="toc_11">生产环境 (暂不开放)</h3>

<blockquote>
<p><a href="http://api.imike.com/cube">http://api.imike.com/cube</a></p>
</blockquote>

<h3 id="toc_12">报文中判断 true 和 false 的判断</h3>

<blockquote>
<p>报文中判断是否的字段，均使用（T/F），T 代表 true，F 代表 false</p>
</blockquote>

<h2 id="toc_13">fetch接口</h2>

<h3 id="toc_14">拉取标签信息</h3>

<hr>

<p><strong>业务说明：</strong>
拉取标签信息</p>

<p><strong>请求方式：</strong></p>

<blockquote>
<p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
<p><a href="http://ip:port/cube/hotel/mergeHotelFacility">http://ip:port/cube/hotel/mergeHotelFacility</a></p>
</blockquote>

<p><strong>请求参数：</strong></p>

<table>
<thead>
<tr>
<th>字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
<tr>
<td>﻿pageNo</td>
<td>页码</td>
<td>否</td>
<td>不填默认从第一页开始拉取</td>
</tr>
</tbody>
</table>

<blockquote>
<p>API返回json数据示例：</p>
</blockquote>

<ul>
<li>success为T时</li>
<li>http状态200</li>
</ul>

<pre class="line-numbers"><code class="language-javascript">
{
    &quot;success&quot;:&quot;T&quot;
}</code></pre>

<ul>
<li>success为F时</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errcode&quot;:,//错误码
    &quot;errmsg&quot;:
}</code></pre>

<h3 id="toc_15">拉取pms酒店信息</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
<p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
<p><a href="http://ip:port/cube/hotel/mergePmsHotel">http://ip:port/cube/hotel/mergePmsHotel</a></p>
</blockquote>

<p><strong>请求参数：</strong></p>

<table>
<thead>
<tr>
<th>字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
<tr>
<td>﻿pageNo</td>
<td>页码</td>
<td>否</td>
<td>不填默认从第一页开始拉取</td>
</tr>
</tbody>
</table>

<blockquote>
<p>API返回json数据示例：</p>
</blockquote>

<ul>
<li>success为T时</li>
<li>http状态200</li>
</ul>

<pre class="line-numbers"><code class="language-javascript">
{
    &quot;success&quot;:&quot;T&quot;
}</code></pre>

<ul>
<li>success为F时</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errcode&quot;:,//错误码
    &quot;errmsg&quot;:
}</code></pre>

<h3 id="toc_16">拉取房型息</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
<p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
<p><a href="http://ip:port/cube/roomtype/mergeRoomType">http://ip:port/cube/roomtype/mergeRoomType</a></p>
</blockquote>

<p><strong>请求参数：</strong></p>

<table>
<thead>
<tr>
<th>字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
<tr>
<td>﻿pageNo</td>
<td>页码</td>
<td>否</td>
<td>不填默认从第一页开始拉取</td>
</tr>
</tbody>
</table>

<blockquote>
<p>API返回json数据示例：</p>
</blockquote>

<ul>
<li>success为T时</li>
<li>http状态200</li>
</ul>

<pre class="line-numbers"><code class="language-javascript">
{
    &quot;success&quot;:&quot;T&quot;
}</code></pre>

<ul>
<li>success为F时</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errcode&quot;:,//错误码
    &quot;errmsg&quot;:
}</code></pre>

<h3 id="toc_17">拉取房型价格信息</h3>

<hr>

<p><strong>业务说明：</strong>
拉取标签信息</p>

<p><strong>请求方式：</strong></p>

<blockquote>
<p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
<p><a href="http://ip:port/cube/roomtype/mergeRoomTypePrice">http://ip:port/cube/roomtype/mergeRoomTypePrice</a></p>
</blockquote>

<p><strong>请求参数：</strong></p>

<table>
<thead>
<tr>
<th>字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
<tr>
<td>﻿pageNo</td>
<td>页码</td>
<td>否</td>
<td>不填默认从第一页开始拉取</td>
</tr>
</tbody>
</table>

<blockquote>
<p>API返回json数据示例：</p>
</blockquote>

<ul>
<li>success为T时</li>
<li>http状态200</li>
</ul>

<pre class="line-numbers"><code class="language-javascript">
{
    &quot;success&quot;:&quot;T&quot;
}</code></pre>

<ul>
<li>success为F时</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errcode&quot;:,//错误码
    &quot;errmsg&quot;:
}</code></pre>

<h3 id="toc_18">拉取酒店库存信息</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
<p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
<p><a href="http://ip:port/cube/roomtype/mergeRoomTypeStock">http://ip:port/cube/roomtype/mergeRoomTypeStock</a></p>
</blockquote>

<p><strong>请求参数：</strong></p>

<table>
<thead>
<tr>
<th>字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
<tr>
<td>﻿pageNo</td>
<td>页码</td>
<td>否</td>
<td>不填默认从第一页开始拉取</td>
</tr>
</tbody>
</table>

<blockquote>
<p>API返回json数据示例：</p>
</blockquote>

<ul>
<li>success为T时</li>
<li>http状态200</li>
</ul>

<pre class="line-numbers"><code class="language-javascript">
{
    &quot;success&quot;:&quot;T&quot;
}</code></pre>

<ul>
<li>success为F时</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errcode&quot;:,//错误码
    &quot;errmsg&quot;:
}</code></pre>

<h2 id="toc_19">PUSH接口</h2>

<h3 id="toc_20">酒店信息全量push</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
<p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
<p><a href="http://ip:port/cube/hotel/hotelall">http://ip:port/cube/hotel/hotelall</a></p>
</blockquote>

<p><strong>head请求参数：</strong></p>

<table>
<thead>
<tr>
<th>字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
<tr>
<td>﻿token</td>
<td>token</td>
<td>是</td>
<td></td>
</tr>
</tbody>
</table>

<p><strong>body请求参数(json格式 以raw方式请求)：</strong></p>

<table>
<thead>
<tr>
<th>字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
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
<td>roomtypes</td>
<td>roomtypes子对象</td>
<td>是</td>
<td></td>
</tr>
</tbody>
</table>

<table>
<thead>
<tr>
<th>roomtypes字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
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
</tbody>
</table>

<blockquote>
<p>API提交json数据示例：</p>
</blockquote>

<pre class="line-numbers"><code class="language-javascript">{
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
}</code></pre>

<blockquote>
<p>API返回json数据示例：</p>
</blockquote>

<ul>
<li>success为T时</li>
<li>http状态200</li>
</ul>

<pre class="line-numbers"><code class="language-javascript">{
    &quot;success&quot;:&quot;T&quot;
}</code></pre>

<ul>
<li>success为F时</li>
<li>http状态400</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errorCode&quot;:,//错误码
    &quot;errorMessage&quot;:
}</code></pre>

<h3 id="toc_21">酒店信息增量push</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
<p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
<p><a href="http://ip:port/cube/hotel/hotel">http://ip:port/cube/hotel/hotel</a></p>
</blockquote>

<p><strong>head请求参数：</strong></p>

<table>
<thead>
<tr>
<th>字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
<tr>
<td>﻿token</td>
<td>token</td>
<td>是</td>
<td></td>
</tr>
</tbody>
</table>

<p><strong>body请求参数(json格式 以raw方式请求)：</strong></p>

<table>
<thead>
<tr>
<th>字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
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
</tbody>
</table>

<blockquote>
<p>API提交json数据示例：</p>
</blockquote>

<pre class="line-numbers"><code class="language-javascript">{
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
      &quot;retentiontime&quot;: &quot;180000&quot;
    }
  }
}</code></pre>

<blockquote>
<p>API返回json数据示例：</p>
</blockquote>

<ul>
<li>success为T时</li>
<li>http状态200</li>
</ul>

<pre class="line-numbers"><code class="language-javascript">{
    &quot;success&quot;:&quot;T&quot;
}</code></pre>

<ul>
<li>success为F时</li>
<li>http状态400</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errorCode&quot;:,//错误码
    &quot;errorMessage&quot;:
}</code></pre>

<h3 id="toc_22">酒店标签信息增量push</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
<p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
<p><a href="http://ip:port/cube/hotel/hotelfacility">http://ip:port/cube/hotel/hotelfacility</a></p>
</blockquote>

<p><strong>head请求参数：</strong></p>

<table>
<thead>
<tr>
<th>字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
<tr>
<td>﻿token</td>
<td>token</td>
<td>是</td>
<td></td>
</tr>
</tbody>
</table>

<p><strong>body请求参数(json格式 以raw方式请求)：</strong></p>

<table>
<thead>
<tr>
<th>字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
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
</tbody>
</table>

<table>
<thead>
<tr>
<th>tags对象字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
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
</tbody>
</table>

<blockquote>
<p>API提交json数据示例：</p>
</blockquote>

<pre class="line-numbers"><code class="language-javascript">{
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
}</code></pre>

<blockquote>
<p>API返回json数据示例：</p>
</blockquote>

<ul>
<li>success为T时</li>
<li>http状态200</li>
</ul>

<pre class="line-numbers"><code class="language-javascript">{
    &quot;success&quot;:&quot;T&quot;
}</code></pre>

<ul>
<li>success为F时</li>
<li>http状态400</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errorCode&quot;:,//错误码
    &quot;errorMessage&quot;:
}</code></pre>

<h3 id="toc_23">房型信息增量push</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
<p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
<p><a href="http://ip:port/cube/roomtype/roomtype">http://ip:port/cube/roomtype/roomtype</a></p>
</blockquote>

<p><strong>head请求参数：</strong></p>

<table>
<thead>
<tr>
<th>字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
<tr>
<td>﻿token</td>
<td>token</td>
<td>是</td>
<td></td>
</tr>
</tbody>
</table>

<p><strong>body请求参数(json格式 以raw方式请求)：</strong></p>

<table>
<thead>
<tr>
<th>字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
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
</tbody>
</table>

<blockquote>
<p>API提交json数据示例：</p>
</blockquote>

<pre class="line-numbers"><code class="language-javascript">{
    data:{
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
    }
}</code></pre>

<blockquote>
<p>API返回json数据示例：</p>
</blockquote>

<ul>
<li>success为T时</li>
<li>http状态200</li>
</ul>

<pre class="line-numbers"><code class="language-javascript">{
    &quot;success&quot;:&quot;T&quot;
}</code></pre>

<ul>
<li>success为F时</li>
<li>http状态400</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errorCode&quot;:,//错误码
    &quot;errorMessage&quot;:
}</code></pre>

<h3 id="toc_24">房型价格信息增量push</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
<p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
<p><a href="http://ip:port/cube/roomtype/roomtypeprice">http://ip:port/cube/roomtype/roomtypeprice</a></p>
</blockquote>

<p><strong>head请求参数：</strong></p>

<table>
<thead>
<tr>
<th>字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
<tr>
<td>﻿token</td>
<td>token</td>
<td>是</td>
<td></td>
</tr>
</tbody>
</table>

<p><strong>body请求参数(json格式 以raw方式请求)：</strong></p>

<table>
<thead>
<tr>
<th>字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
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
</tbody>
</table>

<table>
<thead>
<tr>
<th>roomtypes字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
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
</tbody>
</table>

<table>
<thead>
<tr>
<th>priceinfo字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
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
</tbody>
</table>

<blockquote>
<p>API提交json数据示例：</p>
</blockquote>

<pre class="line-numbers"><code class="language-javascript">{
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
}</code></pre>

<blockquote>
<p>API返回json数据示例：</p>
</blockquote>

<ul>
<li>success为T时</li>
<li>http状态200</li>
</ul>

<pre class="line-numbers"><code class="language-javascript">{
    &quot;success&quot;:&quot;T&quot;
}</code></pre>

<ul>
<li>success为F时</li>
<li>http状态400</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errorCode&quot;:,//错误码
    &quot;errorMessage&quot;:
}</code></pre>

<h3 id="toc_25">订单状态增量push</h3>

<hr>

<p><strong>业务说明：</strong></p>

<p><strong>请求方式：</strong></p>

<blockquote>
<p>POST</p>
</blockquote>

<p><strong>接口url：</strong></p>

<blockquote>
<p><a href="http://ip:port/cube/order/orderstatus">http://ip:port/cube/order/orderstatus</a></p>
</blockquote>

<p><strong>head请求参数：</strong></p>

<table>
<thead>
<tr>
<th>字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
<tr>
<td>﻿token</td>
<td>token</td>
<td>是</td>
<td></td>
</tr>
</tbody>
</table>

<p><strong>body请求参数(json格式 以raw方式请求)：</strong></p>

<table>
<thead>
<tr>
<th>字段</th>
<th>名称</th>
<th>是否必须</th>
<th>说明</th>
</tr>
</thead>

<tbody>
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
<td></td>
</tr>
</tbody>
</table>

<blockquote>
<p>API提交json数据示例：</p>
</blockquote>

<pre class="line-numbers"><code class="language-javascript">{
    &quot;orderid&quot;: 1234567,
    &quot;orderstatus&quot;: 1
}</code></pre>

<blockquote>
<p>API返回json数据示例：</p>
</blockquote>

<ul>
<li>success为T时</li>
<li>http状态200</li>
</ul>

<pre class="line-numbers"><code class="language-javascript">{
    &quot;success&quot;:&quot;T&quot;
}</code></pre>

<ul>
<li>success为F时</li>
<li>http状态400</li>
</ul>

<pre><code>{
    &quot;success&quot;:&quot;F&quot;,
    &quot;errorCode&quot;:,//错误码
    &quot;errorMessage&quot;:
}</code></pre>

<p></article></p>

<p><link href="asset/css/zTreeStyle.css" media="all" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="asset/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="asset/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="asset/js/ztree_toc.js"></script>
<script type="text/javascript" src="asset/js/mdtree.js"></script></p>

<script type="text/javascript">
self="undefined"!=typeof window?window:"undefined"!=typeof WorkerGlobalScope&&self instanceof WorkerGlobalScope?self:{};var Prism=function(){var e=/\blang(?:uage)?-(?!\*)(\w+)\b/i,t=self.Prism={util:{encode:function(e){return e instanceof n?new n(e.type,t.util.encode(e.content),e.alias):"Array"===t.util.type(e)?e.map(t.util.encode):e.replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/\u00a0/g," ")},type:function(e){return Object.prototype.toString.call(e).match(/\[object (\w+)\]/)[1]},clone:function(e){var n=t.util.type(e);switch(n){case"Object":var a={};for(var r in e)e.hasOwnProperty(r)&&(a[r]=t.util.clone(e[r]));return a;case"Array":return e.map(function(e){return t.util.clone(e)})}return e}},languages:{extend:function(e,n){var a=t.util.clone(t.languages[e]);for(var r in n)a[r]=n[r];return a},insertBefore:function(e,n,a,r){r=r||t.languages;var i=r[e];if(2==arguments.length){a=arguments[1];for(var l in a)a.hasOwnProperty(l)&&(i[l]=a[l]);return i}var o={};for(var s in i)if(i.hasOwnProperty(s)){if(s==n)for(var l in a)a.hasOwnProperty(l)&&(o[l]=a[l]);o[s]=i[s]}return t.languages.DFS(t.languages,function(t,n){n===r[e]&&t!=e&&(this[t]=o)}),r[e]=o},DFS:function(e,n,a){for(var r in e)e.hasOwnProperty(r)&&(n.call(e,r,e[r],a||r),"Object"===t.util.type(e[r])?t.languages.DFS(e[r],n):"Array"===t.util.type(e[r])&&t.languages.DFS(e[r],n,r))}},highlightAll:function(e,n){for(var a,r=document.querySelectorAll('code[class*="language-"], [class*="language-"] code, code[class*="lang-"], [class*="lang-"] code'),i=0;a=r[i++];)t.highlightElement(a,e===!0,n)},highlightElement:function(a,r,i){for(var l,o,s=a;s&&!e.test(s.className);)s=s.parentNode;if(s&&(l=(s.className.match(e)||[,""])[1],o=t.languages[l]),o){a.className=a.className.replace(e,"").replace(/\s+/g," ")+" language-"+l,s=a.parentNode,/pre/i.test(s.nodeName)&&(s.className=s.className.replace(e,"").replace(/\s+/g," ")+" language-"+l);var g=a.textContent;if(g){g=g.replace(/^(?:\r?\n|\r)/,"");var u={element:a,language:l,grammar:o,code:g};if(t.hooks.run("before-highlight",u),r&&self.Worker){var c=new Worker(t.filename);c.onmessage=function(e){u.highlightedCode=n.stringify(JSON.parse(e.data),l),t.hooks.run("before-insert",u),u.element.innerHTML=u.highlightedCode,i&&i.call(u.element),t.hooks.run("after-highlight",u)},c.postMessage(JSON.stringify({language:u.language,code:u.code}))}else u.highlightedCode=t.highlight(u.code,u.grammar,u.language),t.hooks.run("before-insert",u),u.element.innerHTML=u.highlightedCode,i&&i.call(a),t.hooks.run("after-highlight",u)}}},highlight:function(e,a,r){var i=t.tokenize(e,a);return n.stringify(t.util.encode(i),r)},tokenize:function(e,n){var a=t.Token,r=[e],i=n.rest;if(i){for(var l in i)n[l]=i[l];delete n.rest}e:for(var l in n)if(n.hasOwnProperty(l)&&n[l]){var o=n[l];o="Array"===t.util.type(o)?o:[o];for(var s=0;s<o.length;++s){var g=o[s],u=g.inside,c=!!g.lookbehind,f=0,h=g.alias;g=g.pattern||g;for(var p=0;p<r.length;p++){var d=r[p];if(r.length>e.length)break e;if(!(d instanceof a)){g.lastIndex=0;var m=g.exec(d);if(m){c&&(f=m[1].length);var y=m.index-1+f,m=m[0].slice(f),v=m.length,k=y+v,b=d.slice(0,y+1),w=d.slice(k+1),O=[p,1];b&&O.push(b);var N=new a(l,u?t.tokenize(m,u):m,h);O.push(N),w&&O.push(w),Array.prototype.splice.apply(r,O)}}}}}return r},hooks:{all:{},add:function(e,n){var a=t.hooks.all;a[e]=a[e]||[],a[e].push(n)},run:function(e,n){var a=t.hooks.all[e];if(a&&a.length)for(var r,i=0;r=a[i++];)r(n)}}},n=t.Token=function(e,t,n){this.type=e,this.content=t,this.alias=n};if(n.stringify=function(e,a,r){if("string"==typeof e)return e;if("[object Array]"==Object.prototype.toString.call(e))return e.map(function(t){return n.stringify(t,a,e)}).join("");var i={type:e.type,content:n.stringify(e.content,a,r),tag:"span",classes:["token",e.type],attributes:{},language:a,parent:r};if("comment"==i.type&&(i.attributes.spellcheck="true"),e.alias){var l="Array"===t.util.type(e.alias)?e.alias:[e.alias];Array.prototype.push.apply(i.classes,l)}t.hooks.run("wrap",i);var o="";for(var s in i.attributes)o+=s+'="'+(i.attributes[s]||"")+'"';return"<"+i.tag+' class="'+i.classes.join(" ")+'" '+o+">"+i.content+"</"+i.tag+">"},!self.document)return self.addEventListener?(self.addEventListener("message",function(e){var n=JSON.parse(e.data),a=n.language,r=n.code;self.postMessage(JSON.stringify(t.util.encode(t.tokenize(r,t.languages[a])))),self.close()},!1),self.Prism):self.Prism;var a=document.getElementsByTagName("script");return a=a[a.length-1],a&&(t.filename=a.src,document.addEventListener&&!a.hasAttribute("data-manual")&&document.addEventListener("DOMContentLoaded",t.highlightAll)),self.Prism}();"undefined"!=typeof module&&module.exports&&(module.exports=Prism);
</script>
<script type="text/javascript">
Prism.languages.clike={comment:[{pattern:/(^|[^\\])\/\*[\w\W]*?\*\//g,lookbehind:!0},{pattern:/(^|[^\\:])\/\/.*?(\r?\n|$)/g,lookbehind:!0}],string:/("|')(\\\n|\\?.)*?\1/g,"class-name":{pattern:/((?:(?:class|interface|extends|implements|trait|instanceof|new)\s+)|(?:catch\s+\())[a-z0-9_\.\\]+/gi,lookbehind:!0,inside:{punctuation:/(\.|\\)/}},keyword:/\b(if|else|while|do|for|return|in|instanceof|function|new|try|throw|catch|finally|null|break|continue)\b/g,"boolean":/\b(true|false)\b/g,"function":{pattern:/[a-z0-9_]+\(/gi,inside:{punctuation:/\(/}},number:/\b-?(0x[\dA-Fa-f]+|\d*\.?\d+([Ee]-?\d+)?)\b/g,operator:/[-+]{1,2}|!|<=?|>=?|={1,3}|&{1,2}|\|?\||\?|\*|\/|~|\^|%/g,ignore:/&(lt|gt|amp);/gi,punctuation:/[{}[\];(),.:]/g};
</script>
<script type="text/javascript">
Prism.languages.javascript=Prism.languages.extend("clike",{keyword:/\b(break|case|catch|class|const|continue|debugger|default|delete|do|else|enum|export|extends|false|finally|for|function|get|if|implements|import|in|instanceof|interface|let|new|null|package|private|protected|public|return|set|static|super|switch|this|throw|true|try|typeof|var|void|while|with|yield)\b/g,number:/\b-?(0x[\dA-Fa-f]+|\d*\.?\d+([Ee][+-]?\d+)?|NaN|-?Infinity)\b/g,"function":/(?!\d)[a-z0-9_$]+(?=\()/gi}),Prism.languages.insertBefore("javascript","keyword",{regex:{pattern:/(^|[^/])\/(?!\/)(\[.+?]|\\.|[^/\r\n])+\/[gim]{0,3}(?=\s*($|[\r\n,.;})]))/g,lookbehind:!0}}),Prism.languages.markup&&Prism.languages.insertBefore("markup","tag",{script:{pattern:/<script[\w\W]*?>[\w\W]*?<\/script>/gi,inside:{tag:{pattern:/<script[\w\W]*?>|<\/script>/gi,inside:Prism.languages.markup.tag.inside},rest:Prism.languages.javascript},alias:"language-javascript"}});
</script>
<script type="text/javascript">
Prism.hooks.add("after-highlight",function(e){var n=e.element.parentNode;if(n&&/pre/i.test(n.nodeName)&&-1!==n.className.indexOf("line-numbers")){var t,a=1+e.code.split("\n").length;lines=new Array(a),lines=lines.join("<span></span>"),t=document.createElement("span"),t.className="line-numbers-rows",t.innerHTML=lines,n.hasAttribute("data-start")&&(n.style.counterReset="linenumber "+(parseInt(n.getAttribute("data-start"),10)-1)),e.element.appendChild(t)}});
</script>
</body>

</html>
