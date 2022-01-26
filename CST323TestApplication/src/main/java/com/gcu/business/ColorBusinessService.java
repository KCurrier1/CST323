package com.gcu.business;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.IDataAccess;

import com.gcu.data.ColorService;
import com.gcu.model.ColorModel;

/**
 * Business logic for colors
 */
@Service
public class ColorBusinessService implements IDataAccess<ColorModel> 
{
	@Autowired
	ColorService DAO;
	
	/**
	 * Test method for debugging
	 */
	public void test() 
	{
		System.out.println("Hello");
	}
	/** Creates a color by calling data layer
	 * @param model (color model)
	 * @return true/false
	 */
	@Override
	public boolean create(ColorModel model) {
		//call DAO to create this model and insert into database, will return true or false
		return DAO.create(model);
	}


	/** finds all colors
	 * @return list of colors
	 */
	@Override
	public List<ColorModel> findAll() {
		//Instantiate new list of colors and call DAO to retrieve all colors
		List<ColorModel> colorsList = DAO.findAll();
		return colorsList;
	}


	/** finds a color model by its id
	 * @param id (color id)
	 * @return color model found
	 */
	@Override
	public ColorModel findById(int id) {
		//retrieve color from database with id passed as parameter, will return this color
		ColorModel color = DAO.findById(id);
		return color;
	}


	/** updates a color model in the database
	 * @param oldcolor (original)
	 * @param newcolor (updated)
	 * @return true/false
	 */
	@Override
	public boolean update(ColorModel oldcolor, ColorModel newcolor) {
		//update the database, replacing oldcolor with newcolor, will return true or false
		return DAO.update(oldcolor, newcolor);
	}


	/** deletes a color from the database
	 * @param colorModel (to be deleted)
	 * @return true/false
	 */
	@Override
	public boolean delete(ColorModel colorModel) 
	{
		//delete color passed as parameter from the database, will return true or false
		return DAO.delete(colorModel);
	}
	@Override
	public ColorModel findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
