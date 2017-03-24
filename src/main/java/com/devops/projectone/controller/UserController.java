package com.devops.projectone.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.MyCartDAO;
import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.Category;
import com.niit.shoppingcart.domain.MyCart;
import com.niit.shoppingcart.domain.Product;
import com.niit.shoppingcart.domain.Supplier;
import com.niit.shoppingcart.domain.User;

@Controller
public class UserController {

	public static Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDAO userDAO;
	

	@Autowired
	private User user;

	@Autowired
	private MyCartDAO cartDAO;

	@Autowired
	private MyCart myCart;

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private Category category;

	@Autowired
	private SupplierDAO supplierDAO;

	@Autowired
	private Supplier supplier;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private Product product;

	
	@Autowired
	private HttpSession session;

	/**
	 * if invalid credentials -> Home page , login , error message if valid
	 * credentials && he is admin -> AdminHome page ,logout link if valid
	 * credentials && he is end user -> Home page, myCart, logout link
	 * 
	 * @param userID
	 * @param password
	 * @return it will return data and page name where to return
	 */
	/*@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public ModelAndView validate(@RequestParam(value = "username") String userID,
			@RequestParam(value = "password") String password) {
		log.debug("Starting of the method validate");

		// ModelAndView mv = new ModelAndView("/home");
		ModelAndView mv = new ModelAndView("/home");
		user = userDAO.validate(id, password); //you no need to do this step 
											//if you are using spring security
		// if the record exist with this userID and password it will return user
		// details else will return null

		if (user != null) {
			log.debug("Valid Credentials");
			
			session.setAttribute("loggedInUser", user.getName());
			session.setAttribute("loggedInUserID", user.getId());

			session.setAttribute("user", user); //
			session.setAttribute("supplier", supplier);
			session.setAttribute("supplierList", supplierDAO.list());
			
			session.setAttribute("productList", productDAO.list());
			session.setAttribute("product", product);

			session.setAttribute("category", category);
			session.setAttribute("categoryList", categoryDAO.list());

			if (user.getRole().equals("ROLE_ADMIN")) {
				log.debug("Logged in as Admin");
				mv.addObject("isAdmin", "true");
				

			} else {
				log.debug("Logged in as User");
				mv.addObject("isAdmin", "false");
				//myCart = cartDAO.list(userID);
				mv.addObject("myCart", myCart);
				// Fetch the myCart list based on user ID
				List<MyCart> cartList = cartDAO.list(userID);
				mv.addObject("cartList", cartList);
				mv.addObject("cartSize", cartList.size());
			}

		} else {
			log.debug("Invalid Credentials");

			mv.addObject("invalidCredentials", "true");
			mv.addObject("errorMessage", "Invalid Credentials");
			
			// ${errorMessage}

		}
		log.debug("Ending of the method validate");
		return mv;
	}*/

	@RequestMapping("/logout")
	public ModelAndView logout() {
		log.debug("Starting of the method logout");
		ModelAndView mv = new ModelAndView("/home");
		session.invalidate(); // will remove the attributes which are added
								// session
		session.setAttribute("category", category);
		session.setAttribute("categoryList", categoryDAO.list());

		mv.addObject("logoutMessage", "You successfully logged out");
		mv.addObject("loggedOut", "true");
		
	    log.debug("Ending of the method logout");
		return mv;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute User user) {
		log.debug("Starting of the method registerUser");
		ModelAndView mv = new ModelAndView("/Menu/home");
		if (userDAO.getUser(user.getId()) == null) {
			user.setRole("ROLE_USER"); // all the users are end users by default
			userDAO.save(user);
			log.debug("You are successfully register");
			mv.addObject("successMessage", "You are successfully registered");
		} else {
			log.debug("User exist with this id");
			mv.addObject("errorMessage", "User exist with this id");
		}
		log.debug("Ending of the method registerUser");
		return mv;
	}

	/*//authentication-failure-forward-url="/loginError"
	@RequestMapping(value = "/loginError", method = RequestMethod.GET)
	public String loginError(Model model) {
		log.debug("Starting of the method loginError");
		model.addAttribute("errorMessage", "Invalid Credentials.  Please try again.");
		log.debug("Ending of the method loginError");
		return "home";

	}
//<security:access-denied-handler error-page="/accessDenied" />
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDenied(Model model) {
		log.debug("Starting of the method accessDenied");
		model.addAttribute("errorMessage", "You are not authorized to access this page");
		log.debug("Ending of the method accessDenied");
		return "home";

	}*/

}
