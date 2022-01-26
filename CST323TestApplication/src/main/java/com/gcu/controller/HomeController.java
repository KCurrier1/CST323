package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * simple welcome controller that returns a view name
 *
 */
@Controller
@RequestMapping("/")
public class HomeController 
{
	/**
	 * A route to home that sets the title and welcome message
	 * @param model (page model)
	 * @return the home page
	 */
	@GetMapping("/")
	public String home(Model model)
	{
		//Simply return a Model w/an attribute named 
		//message and return a view named home using a string
		model.addAttribute("title", "CST-323 Home");
		model.addAttribute("message", "Welcome to Kayla's CST-323 Test Application");
		return "index";
	}
}
