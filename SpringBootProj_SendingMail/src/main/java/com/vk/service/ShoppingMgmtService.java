package com.vk.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;


@Service("sms")
public class ShoppingMgmtService implements ishoppingservice{
	@Autowired
	private JavaMailSender sender;
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	@Override
	public String purchase(String[] items, double[] prices, String[] toMailids) throws Exception{
//.......................  calculting bill amount  .........................
		
		double billamt=0.0;
		for(double p:prices)
	     billamt=billamt+p;
		
//.......................   preparing message to send  .......................
		
		String msg=Arrays.toString(items)+"are purchases having prices::"+Arrays.toString(prices)+"with bill amount::"+billamt;	
				
//....................... triggering email message  ..........................
		
	String result=triggerEmail(msg,toMailids);
		return msg+"... "+result;
	}
private String triggerEmail(String msg,String[]toMailids) throws Exception{
	MimeMessage message=sender.createMimeMessage();
	MimeMessageHelper helper=new MimeMessageHelper(message,true);

//......................... setting header values  ..............................
	
	helper.setFrom(fromEmail);
	helper.setSubject("Open it to know it");
	helper.setCc(toMailids);
	helper.setSentDate(new Date());
	helper.setText(msg);
	helper.addAttachment("apple.jpg", new ClassPathResource("apple.jpg"));
	helper.addAttachment("charger.jpg", new ClassPathResource("charger.jpg"));
	helper.addAttachment("earbuds.jpeg", new ClassPathResource("earbuds.jpeg"));

//........................ sending  Email Message  ..........................
   sender.send(message);
   return "email message is sent";
	
	
	}
}
