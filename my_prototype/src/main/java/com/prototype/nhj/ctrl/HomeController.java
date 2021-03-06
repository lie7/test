package com.prototype.nhj.ctrl;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prototype.nhj.svc.HomeService;

import dbio.DBIO_Cubrid_Test;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Resource(name="HomeService")
	private HomeService homeService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/","/home"})
	public String home(Locale locale, Model model, @RequestParam Map ioMap, ServletRequest req) {
		logger.info("11 Welcome home! The client locale is  {}.{}", locale, "test");
		         
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		homeService.printTmp(model);
		
		System.out.println("paramMap : "+ioMap);
		System.out.println("paramMap : "+ioMap.getClass());
		
		System.out.println("req111 : "+req.getClass());
		
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("ioMap", ioMap );
		
		try {
			System.out.println("log");
			
			DBIO_Cubrid_Test.main(null);
			
			System.out.println("loglog");
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return "home";
	}
	
}
