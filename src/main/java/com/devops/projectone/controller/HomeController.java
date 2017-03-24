package com.devops.projectone.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.Category;
import com.niit.shoppingcart.domain.Product;
import com.niit.shoppingcart.domain.Supplier;
import com.niit.shoppingcart.domain.User;

@Controller
public class HomeController {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private User user;
	
	@Autowired
	private HttpSession session;
	

	//   http://localhost:8080/ShoppingCart/
	@RequestMapping("/")
	public ModelAndView showHomePage()
	{
		System.out.println("Starting the method showHomePage");
		//Specifying which page you have navigation
		ModelAndView mv = new ModelAndView("/Menu/home");
		
		//Specify what data you have to carry to home page
		
		mv.addObject("msg", "WELCOME TO SHOPPING CART");
		
		return mv;
	}
	
	@RequestMapping("/login")
	public ModelAndView showLoginPage()
	{
		System.out.println("Clicked on Login link");
		ModelAndView mv = new ModelAndView("/Menu/home");
		mv.addObject("isUserClickedLogin","true");
		
		return mv;
	}
	
	/*@RequestMapping("/register")
	public ModelAndView showRegistrationPage()
	{
		System.out.println("Clicked on Registraion link");
		ModelAndView mv = new ModelAndView("/Menu/home");
		mv.addObject("isUserClickedRegister","true");
		return mv;
	}*/
	
	
	@RequestMapping("/validate")
	public ModelAndView validateCredentials(@RequestParam("userID") String id, 
			@RequestParam("password") String pwd)
	{
		//Actually you have get the data from DB
		//Tempororily  -user->niit password =niit@123 // id and pwd is getting from db
		
		ModelAndView mv = new ModelAndView("/Menu/home");
		
		mv.addObject("isUserLoggedIn", "false");
		
		//if(id.equals("niit")  && pwd.equals("niit@123")){
		if( userDAO.validate(id, pwd)==true)
		
		{
		    user = userDAO.getUser(id);
			
			if(user.getRole().equals("ROLE_ADMIN"))
			{
				mv.addObject("isAdmin", true);
                
			}
			else
			{
				mv.addObject("isAdmin", false);
				
			}  
			
			mv.addObject("successMessage", "Valid Credentials");
			session.setAttribute("loginMessage", "Welcome :" +id);
		}
		else
		{
			mv.addObject("errorMessage", "InValid Credentials...please try again");
		}
		
		return mv;
		
	}
	
	@RequestMapping("/contact")
	public ModelAndView showContactPage()
	{
		System.out.println("Clicked on Contact link");
		ModelAndView mv = new ModelAndView("/Menu/home");
		mv.addObject("isUserClickedContact","true");
		return mv;
	}
	
	@RequestMapping("/aboutUs")
	public ModelAndView showAboutUsPage()
	{
		System.out.println("Clicked on About Us link");
		ModelAndView mv = new ModelAndView("/Menu/home");
		mv.addObject("isUserClickedAboutUs","true");
		return mv;
	}
	
	@RequestMapping("/admin")
	public ModelAndView showAdminPage()
	{
		System.out.println("Clicked on Admin link");
		ModelAndView mv = new ModelAndView("/Admin/AdminHome");
		mv.addObject("isAdmin","true");
		return mv;
	}
	
/*@RequestMapping("/logout")
	public ModelAndView logout()
	{
		ModelAndView mv =new ModelAndView("/Menu/home");
		//session.invalidate();
		session.removeAttribute("loginMessage");
		return mv;
		
	}
*/	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
