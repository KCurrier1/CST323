package com.gcu.model;

import javax.validation.constraints.NotNull;

/**
 *The model for color details
 */
public class ColorModel 
{
	public int id;
	@NotNull(message="Please pick a color")
	public String hex;
	
	/**
	 * Constructor with all parameters
	 * @param id (color ID #)
	 * @param hex (hex code)
	 */
	public ColorModel(int id, String hex) 
	{
		this.id = id;
		this.hex = hex;
	}
	/**
	 * Empty copy constructor
	 */
	public ColorModel() {
		
	}
	/** color id getter
	 * @return color id #
	 */
	public int getid() {
		return id;
	}
	/** color id setter
	 * @param id (color id #)
	 */
	public void setid(int id) {
		this.id = id;
	}
	/** color name getter
	 * @return color hex
	 */
	public String gethex() {
		return hex;
	}
	/** color name setter
	 * @param hex (color hex code)
	 */
	public void sethex(String hex) {
		this.hex = hex;
	}
	
	
}
