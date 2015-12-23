package com.mk.taskfactory.biz.utils.email;
public class EmailSend {
	public static void emailSend(String[] attachFile,String content,String title,String toAddress) {
		MailInfo mailInfo = new MailInfo();
//		mailInfo.setMailServerHost("smtp.163.com");
//		mailInfo.setMailServerPort("25");
//		mailInfo.setValidate(true);
//		mailInfo.setUsername("bihaiyanyuxiaolong@163.com");
//		mailInfo.setPassword("654195681xl");// 您的邮箱密码
//		mailInfo.setFromAddress("bihaiyanyuxiaolong@163.com");
		mailInfo.setMailServerHost("mail.imike.com");
		mailInfo.setMailServerPort("587");
		mailInfo.setValidate(true);
		mailInfo.setUsername("pricecontrol@imike.com");
		mailInfo.setPassword("pricecontrol@imike.com");// 您的邮箱密码
		mailInfo.setFromAddress("pricecontrol@imike.com");
		mailInfo.setToAddress(toAddress);
		mailInfo.setSubject(title);
				
		//附件
		if (attachFile!=null&&attachFile.length>0) {
			mailInfo.setAttachFileNames(attachFile);
		}
		mailInfo.setContent(content);
		SimpleMail.sendHtmlMail(mailInfo);// 发送html格式
	}
}