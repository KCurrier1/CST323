package com.gcu.controller;

import java.util.List;




import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.business.ColorBusinessService;
import com.gcu.model.ColorModel;
/**
 * The Controller that handles all routing for color pages
 *
 */
@Controller
@RequestMapping("/colors")
public class ColorController 
{
	@Autowired
	private ColorBusinessService service;
	/**
	 * displays the color page that shows all the colors in the database
	 * @param model (page model)
	 * @return colors page
	 */
	@GetMapping("/")
	public String display(Model model) 
	{
		//display method for landing on the all colors page, passes services get all colors in as 'colors' attribute
		model.addAttribute("title", "All colors");
		model.addAttribute("colors", service.findAll());
		//return colors view
		return "colors";
	}
	/**
	 * displays the create color page and form
	 * @param model (page model)
	 * @return create colors page
	 */
	@GetMapping("/addColor")
	public String displayCreate(Model model) 
	{
		//display for landing on the create page, loads a new color as 'colorModel' attribute
	    model.addAttribute("title", "Add color");
	    model.addAttribute("colorModel", new ColorModel());
	    //return createcolor view
	    return "createColor";
	}
	/**
	 * Handles the creating of the color and if valid routes back to the 
	 * colors page - if errors, routes back to the create color form
	 * and displays validation errors
	 * @param colorModel (color information)
	 * @param bindingResult (error logging)
	 * @param model (page model)
	 * @return either create color or colors page based on if the data was valid
	 */
	@PostMapping("/addColor/createColor")
	public String createcolor(@Valid ColorModel colorModel, BindingResult bindingResult, Model model) 
	{
		//createcolor method for displayCreate submission, calls service's create method
		if (bindingResult.hasErrors()) 
		{
			model.addAttribute("title", "Create colors");
			return "createcolor";
		}
		//Prints the created color's name for debugging
		System.out.println(colorModel.gethex());
		//calling service to create the color and insert into database
		boolean result = service.create(colorModel);
		//add success or failure attribute depending on the result of creation
		if(result == true) 
		{
			model.addAttribute("resultMessage", "color Created Successfully");
		}
		else
		{
			model.addAttribute("resultMessage", "Something went wrong...");
		}
		//Create list of all retrieved colors from service method
		List<ColorModel> colorsList = service.findAll();	
		//Return to all colors page after creation, with new updated list and success or failure pages
		model.addAttribute("title", "All colors");
		model.addAttribute("colors", colorsList);
		return "redirect:/colors/";
		
	}
	/** deletes a color
	 * @param id (color id)
	 * @param colorModel (color model)
	 * @param bindingResult (error log)
	 * @param model (page model)
	 * @return colors with a success/fail message
	 */
	@PostMapping("/doDelete")
	public String deletecolor(@RequestParam(name="id") int id, @Valid ColorModel colorModel, BindingResult bindingResult, Model model) 
	{
		//deletecolor method for button call in colors page, first find the color with the ID passed in from the view
		colorModel = service.findById(id);
		//once color is obtained, delete the color from the database by calling the service method
		//add success or failure attribute depending on result of deletion
		if(service.delete(colorModel) != true || colorModel.getid() == -1) 
		{
			//if service failed to delete object or objectID was not found in database, failure
			model.addAttribute("resultMessage", "Something went wrong...");
		}
		else
		{
			//else success
			model.addAttribute("resultMessage", "color Deleted Successfully");
		}
		//retrieve updated list and return colors page with updated list
		List<ColorModel> colorsList = service.findAll();
		model.addAttribute("title", "All colors");
		model.addAttribute("colors", colorsList);
		return "colors";
		
	}
	/** update color page routing
	 * @param id (color id)
	 * @param model (page model)
	 * @return update color page
	 */
	@PostMapping("/editView")
	public String editView(@RequestParam(name="id") int id, Model model) 
	{
		//editView method for landing on the edit page, requestparam ID for finding which user was clicked on
		//Add attributes and set attribute 'colorModel' as the object that was clicked on
	    model.addAttribute("title", "Edit color");
	    model.addAttribute("colorModel", service.findById(id));
	    //return updatecolor view
	    return "updateColor";
	}
	/** edit color in database
	 * @param id (color id)
	 * @param colorModel (updated color model)
	 * @param bindingResult (error log)
	 * @param model (page model)
	 * @return colors page with a success/fail message
	 */
	@PostMapping("/doEdit")
	public String editcolor(@RequestParam(name="id") int id, @Valid ColorModel colorModel, BindingResult bindingResult, Model model) 
	{	
		//doEdit method for updating the object in the database
		//Find oldcolor by retrieving object with ID passed as requested parameter
		ColorModel oldcolor = service.findById(id);
		//colorModel passed in as parameter is the new color, replace oldcolor with new color by calling service
		boolean result  = service.update(oldcolor, colorModel);
		//add success or failure attribute depending on result of replacement/update
		if(result == true) 
		{
			model.addAttribute("resultMessage", "Color Updated Successfully");
		}
		else
		{
			model.addAttribute("resultMessage", "Something went wrong...");
		}
		//retrieve updated list and return all colors page with updated list
		List<ColorModel> colorsList = service.findAll();
		model.addAttribute("title", "All colors");
		model.addAttribute("colors", colorsList);
		return "redirect:/colors/";
	}
	
	
}
