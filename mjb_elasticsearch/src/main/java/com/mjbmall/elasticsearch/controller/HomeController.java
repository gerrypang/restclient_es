package com.mjbmall.elasticsearch.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mjbmall.elasticsearch.core.controller.BaseController;

@Controller
public class HomeController extends BaseController{
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/productpage", method = RequestMethod.GET)
	public String pageable(Locale locale, Model model, String query) {
		model.addAttribute("url", "./search/page");
		return "products";
	}

	@RequestMapping(value = "/productlist", method = RequestMethod.GET)
	public String requestparam(Locale locale, Model model, String query) {
		model.addAttribute("url", "./search/list");
		return "products";
	}
	
	@RequestMapping(value = "/bookpage", method = RequestMethod.GET)
	public String bookpage(Locale locale, Model model, String query) {
		model.addAttribute("url", "./search/book/page");
		return "books";
	}
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public String booklist(Locale locale, Model model, String query) {
		model.addAttribute("url", "./search/book/list");
		return "books";
	}
	
	@RequestMapping(value = "/demopage", method = RequestMethod.GET)
	public String demopage(Locale locale, Model model, String query) {
		model.addAttribute("url", "./search/demotable/page");
		return "demotable";
	}
	
	@RequestMapping(value = "/demolist", method = RequestMethod.GET)
	public String demolist(Locale locale, Model model, String query) {
		model.addAttribute("url", "./search/demotable/list");
		return "demotable";
	}
}
