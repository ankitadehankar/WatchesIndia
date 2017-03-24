package com.devops.projectone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;

public class CategoryController {

	// add category, delete category, update category, edit category, showCategoryList
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private Category category;
	
	@PostMapping("/manage_category_create")
	public ModelAndView createCategory(@RequestParam("id")String id, 
			@RequestParam("name")String name, @RequestParam("description")String description)
	{
		category.setId(id);
		category.setName(name);
		category.setDescription(description);
		
		ModelAndView mv = new ModelAndView("Redirect:/manage_categories");
		
		if (categoryDAO.save(category)) {
			mv.addObject("msg", "Successfully Created All Category");
		} else {
			mv.addObject("msg", "Not able to create, please contact Administrator");
		}
		return mv;
	}
}
