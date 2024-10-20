package com.project.plywood.Serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.plywood.Service.MainService;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

//Rajendra

@Service
public class MainServiceImpl implements MainService{
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	private Environment env;

	@Override
	public void sendmail() {
		
//		Thread Thread = new Thread(new MyRunnable(name,mobile,email,messsage));
//		Thread.start();
		
		List<String> emailId=Arrays.asList(
				"rajendraprasadsahoo28@gmail.com"				
				);		
		
		for(String sendemail:emailId) {
			SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom(env.getProperty("spring.mail.username"));
	        message.setTo(sendemail);
	        message.setSubject("jgfgfgh");
	        message.setText("kgkujg");
	        mailSender.send(message);
		}
				
	}
	
	class MyRunnable implements Runnable {

		private String name;
		private String mobile;
		private String email;
		private String messsage;

		public MyRunnable(String name, String mobile, String email, String messsage) {
			this.name = name;
			this.mobile = mobile;
			this.email = email;
			this.messsage = messsage;
		}
		List<String> emailId=Arrays.asList(
				"rajendraprasadsahoo28@gmail.com"
//				"chiranjib.bablu@gmail.com"
				);	
		
		@Override
		public void run() {
			String subject="Saikrishna Traders || Message from User";
			String textmessage="Thank You .";
			SimpleMailMessage message =null;
			
			//send mail to Customer
			message = new SimpleMailMessage();
	        message.setFrom(env.getProperty("spring.mail.username"));
	        message.setTo(email.trim());
			textmessage = "Dear, "+name+"\r\n" 
					+ "\r\n" + "Thank you for reaching out to Sai Krishna Traders. "
					+ "\r\n We have received your message and will get back to you shortly to assist with your request.\r\n"					
					+ "\r\n Should you need any further assistance in the meantime, please feel free to contact us at the following :"+"\r\n"
					+ "\r\n Mobile No: "+"7008011757"
					+ "\r\n Email: "+"saikrishnapvc@gmail.com"
					+ "\r\n Address: "+"Saikrishna Traders, ByPass, Bhadrak\r\n"
							+ "          Odisha-756100 Near Reliance Trends \r\n"
					+ "\r\n We appreciate your patience and look forward to helping you.\r\n"
					+ "\r\n Best regards,"
					+ "\r\n Saikrishna Traders";
	        message.setSubject(subject);
	        message.setText(textmessage);
	        mailSender.send(message);
			
			//send mail to Owner
			for(String sendemail:emailId) {
				message = new SimpleMailMessage();
		        message.setFrom(env.getProperty("spring.mail.username"));
		        message.setTo(sendemail);
				textmessage = "Dear, Saikrishna Traders\r\n" + "\r\n" + "You have received a new message from a user. Please see the details below:\r\n"
						+ "\r\n Name: "+name
						+ "\r\n Mobile No: "+mobile
						+ "\r\n Email: "+email
						+ "\r\n Message: "+messsage+"\r\n"
						+ "\r\n Please reach out to the user and assist them as soon as possible."+"\r\n"
						+ "\r\n Best regards,"
						+ "\r\n Saikrishna Traders";
		        message.setSubject(subject);
		        message.setText(textmessage);
		        mailSender.send(message);
			}
		}		
	}

	@Override
	public Map<String, Object> rqstforcontact(Map<String, Object> mapobj) {		
		Thread Thread = new Thread(new MyRunnable(
				mapobj.get("name").toString(),
				mapobj.get("phone").toString(),
				mapobj.get("email").toString(),				
				mapobj.get("message").toString()));
		Thread.start();
		Map<String,Object> map=new HashMap<>();
		map.put("message","ThanYou ! Our Team Will Connect you Sortly .");
		map.put("status",200);
		return mapobj;
	}

}
