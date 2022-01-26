package com.gcu.data;

import java.util.ArrayList;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.gcu.model.ColorModel;

/**
 * color data service
 */
@Service
public class ColorService implements IDataAccess<ColorModel> 
{
	//Autowired datasource injections
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	/** sets the datasource
	 * @param datasource (datasource)
	 */
	public ColorService(DataSource datasource) {
		//set data source and template object
				this.dataSource = datasource;
				this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	/**
	 * calls the database to get all colors and returns them in a list
	 * @return List of colors
	 */
	@Override
	public List<ColorModel> findAll() {
		//SQL query to select all from colors
		String sql = "SELECT * FROM colors";
		//create structure to hold colors
		List<ColorModel> colors = new ArrayList<ColorModel>();
		try 
		{
			//Execute SQL query and loop over result set, adding to colors
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);	
			while(srs.next())
			{
				colors.add(new ColorModel(srs.getInt("id"),
										srs.getString("hex")));
			}
		}
		//catch exceptions and print stack trace
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		//return colors list
		return colors;
	}

	/** creates a color by inserting the model into the database
	 * @param model (color model)
	 * @return true/false
	 */
	@Override
	public boolean create(ColorModel model) {
		//sql statement for inserting values
				String sql = "INSERT INTO colors(id, hex) VALUES(?,?)";
				try 
				{
					//execute SQL insert to update values with following model attributes
					int rows = jdbcTemplateObject.update(sql,
														model.getid(),
														model.gethex());
					//return result of insertion
					return rows == 1 ? true : false;
							
				}
				//catch and print stack trace
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				return false;
	}
	/** finds a model by id
	 * @param id (color id we are looking for)
	 * @return color model 
	 */
	@Override
	public ColorModel findById(int id) 
	{
		//SQL query for selecting all from colors with ID passed as parameter
		String sql = "SELECT * FROM colors WHERE id = '" + id + "'";
		//create structure to hold users
		List<ColorModel> colors = new ArrayList<ColorModel>();
		try {
			//Execute SQL query and loop over result set
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);	
			while(srs.next())
			{
				colors.add(new ColorModel(srs.getInt("id"),
						srs.getString("hex")));
			}
			//There should only be one color with unique ID, if ID is found return colors[0] with the result
			if (colors.size() == 1) {
				return colors.get(0);
			}
			//else if there's multiple colors with unique ID, return null for error handling
			else if (colors.size() > 1) {
				return null;
			}
			//Else no object was found, return color with negative ID for error handling
			return new ColorModel(-1,
					"NULL");
		}
		//catch and print stack trace
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/** updates a model
	 * @param oldcolor (original model)
	 * @param newcolor (updated model)
	 * @return true/false
	 */
	@Override
	public boolean update(ColorModel oldcolor, ColorModel newcolor) {
		//sql statement for updating values
		String sql = "UPDATE colors SET hex = '" + newcolor.gethex() + "' WHERE colors.id = " + oldcolor.getid() + "";
		try 
		{
			//execute SQL update
			int rows = jdbcTemplateObject.update(sql);
			//return result of insertion
			return rows == 1 ? true : false;
					
		}
		//catch and print stack trace
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	/** deletes color
	 * @param model (color model to be deleted)
	 * @return true/false
	 */
	@Override
	public boolean delete(ColorModel model) 
	{
		//get the ID of the color that's being deleted
		int id = model.getid();
		//sql statement for deleting a value by id
		String sql = "DELETE FROM colors WHERE id = '" + id + "'";
		try 
		{
			int rows = jdbcTemplateObject.update(sql);
			
			return rows == 1 ? true : false;
		}
		//catch and print stack trace
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public ColorModel findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
