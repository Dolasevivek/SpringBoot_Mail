package com.vk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.vk.service.ShoppingMgmtService;

@SpringBootApplication
public class Boot102CommonSendingMainApplication {

	public static void main(String[] args) {
		// getting Ioc container
		try(ConfigurableApplicationContext ctx=SpringApplication.run(Boot102CommonSendingMainApplication.class, args);)
		{
				// get Spring bean class obj ref
				ShoppingMgmtService service = ctx.getBean("sms", ShoppingMgmtService.class);
				// invoke the buisness method
				try {
					String resultMsg=service.purchase(new String[] {"Mobile","charger","Earbuds"},
	    			 new double[] {90000,2500,3600},
	    			 new String[] {"dolasevivek27@gmail.com","vkditofficial@gmail.com"});
					System.out.println(resultMsg);
					}catch(Exception e)
					{
						e.printStackTrace();
					}
	   
		}catch(Exception e)
		{
			e.printStackTrace();
		}
}// main end

}// class end
