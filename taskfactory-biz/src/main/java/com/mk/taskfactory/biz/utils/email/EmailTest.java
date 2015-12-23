package com.mk.taskfactory.biz.utils.email;
public class EmailTest {
	public static void main(String[] args) {
		//附件
		//String[] attachFileNames={"d:/Sunset.jpg"};
		// 这个类主要来发送邮件
		StringBuffer content = new StringBuffer();
		content.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">")
		.append("<html>")
		.append("<head>")
		.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">")
		.append("<title>测试邮件</title>")
		.append("<style type=\"text/css\">")
		.append(".test{font-family:\"Microsoft Yahei\";font-size: 18px;color: red;}")
		.append("</style>")
		.append("</head>")
		.append("<body>")
		.append("<span class=\"test\">大家好，这里是测试Demo</span>")
		.append("</body>")
		.append("</html>");
		try {
			EmailSend.emailSend(null, content.toString(),"测试邮件","654195681@qq.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}