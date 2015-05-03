package com.xnjd.hynm.util;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.*;

public class SendMailUtil {
	
	String SMTPHost=""; //SMTP服务器
	String user=""; //登录SMTP服务器的帐号
	String password=""; //登录SMTP服务器的密码
	String from =""; //发件人邮箱
	String to =""; //收件人邮箱
	String subject =""; //邮件标题
	String content =""; //邮件内容	
	String  recipients[]= {};//抄送人
	String recipientsArr[]={};//多个收件人
	

	public String[] getRecipientsArr() {
		return recipientsArr;
	}

	public void setRecipientsArr(String[] recipientsArr) {
		this.recipientsArr = recipientsArr;
	}

	//无参数构造方法
	public SendMailUtil() {}
	 
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		try{			
			//解决内容的中文问题
			//content = new String(content.getBytes("ISO8859-1"),"gb2312");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		this.content = content;
	}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	

	public String getSMTPHost() {
		return SMTPHost;
	}
	public void setSMTPHost(String host) {
		SMTPHost = host;
	}

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		try{			
			//解决标题的中文问�?
			//subject = new String(subject.getBytes("ISO8859-1"),"gb2312");
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		this.subject = subject;
	}

	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
	
   public String[] getCC(){
	   return recipients;
   }
   public String[] setCC(String[] recipients){
	   return this.recipients=recipients;
   }
   
   
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}	

	//发送邮件
	//发送邮件
		public boolean send(){
			//创建属性对象
			Properties props = new Properties();
			//指定SMTP服务�?
			props.put("mail.smtp.host", SMTPHost);
			props.put("mail.smtp.port",String.valueOf(25));  
			props.put("mail.smtp.localhost","localhost");
			//指定是否需要SMTP验证		
			props.put("mail.smtp.auth", "true");
			try{
				//创建授权验证对象
				SmtpAuth auth = new SmtpAuth();
				auth.setAccount(user,password);
				
				//创建Session对象
				Session mailSession = Session.getDefaultInstance(props,auth);
				mailSession.setDebug(true);
				
				//创建Message对象
				Message message=new MimeMessage(mailSession);

				//指定发件人邮箱
				message.setFrom(new InternetAddress(from));
				//指定收件人邮箱
				message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			
				//群发邮件的实现
		        final int num=recipients.length;
		        InternetAddress[] addresses = new InternetAddress[num];
		        for(int i=0;i<num;i++){
		        	addresses[i] = new InternetAddress(recipients[i]);
		        }
		         message.setRecipients(Message.RecipientType.CC,addresses);
				//指定邮件主题
				message.setSubject(subject);
				//指定邮件内容
				message.setContent(content,"text/html;charset=utf-8");
				//指定邮件发送日期
				message.setSentDate(new Date());
				
				//指定邮件优先级1：紧急3：普通5：缓慢
				//message.setHeader("X-Priority","3");
				//message.saveChanges();
							
				//创建Transport对象
				Transport transport = mailSession.getTransport("smtp");
				//连接SMTP服务器
				transport.connect(SMTPHost, user, password);
				//发送邮件
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
				return true;
			}catch(Exception ex){
				ex.printStackTrace();
				return false;
			}
		}
	//发送邮件
		public boolean sendEventMail() throws AddressException, MessagingException{
			//创建属性对象
			Properties props = new Properties();
			//指定SMTP服务�?
			props.put("mail.smtp.host", SMTPHost);
			props.put("mail.smtp.port",String.valueOf(25));  
			props.put("mail.smtp.localhost","localhost");
			//指定是否需要SMTP验证		
			props.put("mail.smtp.auth", "true");
				//创建授权验证对象
				SmtpAuth auth = new SmtpAuth();
				auth.setAccount(user,password);
				//创建Session对象
				Session mailSession = Session.getDefaultInstance(props,auth);
				mailSession.setDebug(true);
				//创建Message对象
				Message message=new MimeMessage(mailSession);
				//指定发件人邮箱
				message.setFrom(new InternetAddress(from));
				//群发邮件的实现
		        final int num=recipients.length;
		        InternetAddress[] addresses = new InternetAddress[num];
		        for(int i=0;i<num;i++){
		        	addresses[i] = new InternetAddress(recipients[i]);
		        }
				//指定收件人邮箱
		        if(!"".equals(to)&&to!=null){
		        		message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		        }else if(recipientsArr.length!=0){
		        	 final int recipientsNum=recipientsArr.length;
				        InternetAddress[] addressors = new InternetAddress[recipientsNum];
				        for(int i=0;i<recipientsNum;i++){ 	
				        	addressors[i] = new InternetAddress(recipientsArr[i].trim());
				        }
		        	message.setRecipients(Message.RecipientType.TO,addressors);
		        }
				
		        message.setRecipients(Message.RecipientType.CC,addresses);
				//指定邮件主题
				message.setSubject(subject);
				//指定邮件内容
				message.setContent(content,"text/html;charset=utf-8");
				//指定邮件发送日期
				message.setSentDate(new Date());
				
				//指定邮件优先级1：紧急3：普通5：缓慢
				//message.setHeader("X-Priority","3");
				//message.saveChanges();
							
				//创建Transport对象
				Transport transport = mailSession.getTransport("smtp");
				//连接SMTP服务器
				transport.connect(SMTPHost, user, password);
				//发送邮件
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
				return true;
			
		}
	
	//定义SMTP授权验证//
	public static class SmtpAuth extends Authenticator{
		String user,password;
		//设置帐号信息
		void setAccount(String user,String password){
			this.user = user;
			this.password = password;
		}
		//取得PasswordAuthentication对象
		protected PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(user,password);
		}
	}
	
}