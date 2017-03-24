package com.devops.projectone.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.MyCartDAO;
import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Category;
import com.niit.shoppingcart.domain.MyCart;
import com.niit.shoppingcart.domain.Product;
import com.niit.shoppingcart.domain.Supplier;

@Controller
public class SpringSecurityController {

	public static Logger log = LoggerFactory.getLogger(SpringSecurityController.class);

	@Autowired
	private MyCartDAO cartDAO;

	@Autowired
	private MyCart myCart;

	@Autowired
	private HttpSession session;
	
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private Category category;

	@Autowired
	private SupplierDAO supplierDAO;

	@Autowired
	private Supplier supplier;

	@Autowired
	private Product product;
	
	@Autowired
	private ProductDAO productDAO;
	

	// authentication-failure-forward-url="/loginError"
	@RequestMapping(value = "/loginError", method = RequestMethod.GET)
	public String loginError(Model model) {
		log.debug("Starting of the method loginError");
		model.addAttribute("errorMessage", "Invalid Credentials.  Please try again.");
		model.addAttribute("invalidCredentials", "true");
		log.debug("Ending of the method loginError");
		return "/Menu/home";

	}

	// <security:access-denied-handler error-page="/accessDenied" />
	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDenied(Model model) {
		log.debug("Starting of the method accessDenied");
		model.addAttribute("errorMessage", "You are not authorized to access this page");

		log.debug("Ending of the method accessDenied");
		return "/Menu/home";

	}
	// <security:form-login authentication-success-forward-url="/success"/>
	/*
	 * @RequestMapping("/success") public ModelAndView successForwardURL() {
	 * log.debug("Starting of the method successForwardURL"); ModelAndView mv =
	 * new ModelAndView("home");
	 * 
	 * Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); String
	 * loggedInUserid = auth.getName(); Collection<GrantedAuthority> authorities
	 * = (Collection<GrantedAuthority>) auth.getAuthorities(); if
	 * (authorities.contains("ROLE_ADMIN")) { mv.addObject("isAdmin", "true");
	 * log.debug("You are Admin"); } else { log.debug("You are not  Admin");
	 * mv.addObject("isAdmin", "false"); // myCart = cartDAO.list(userID);
	 * mv.addObject("myCart", myCart); // Fetch the myCart list based on user ID
	 * List<MyCart> cartList = cartDAO.list(loggedInUserid);
	 * mv.addObject("cartList", cartList); mv.addObject("cartSize",
	 * cartList.size()); }
	 * 
	 * log.debug("Ending of the method successForwardURL"); return mv;
	 * 
	 * }
	 */
/**
 * If we are using spring-security, this method is going to execute after login
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public ModelAndView validate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("starting of the method validate");
		ModelAndView mv = new ModelAndView("/Admin/AdminHome");
		// session = request.getSession(true);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userID = auth.getName();
		session.setAttribute("loggedInUser", userID);

		if (request.isUserInRole("ROLE_ADMIN")) {

			session.setAttribute("isAdmin", true);

		} else {

			session.setAttribute("isAdmin", false);
			
			mv.addObject("isUserClickedMyCart", "true");
			
			mv.addObject("myCart", myCart);
			// Fetch the myCart list based on user ID
			List<MyCart> cartList = cartDAO.list(userID);
			mv.addObject("cartList", cartList);
			mv.addObject("cartSize", cartList.size());
			mv.addObject("totalAmount", cartDAO.getTotalAmount(userID));

		}
		log.debug("Ending of the method validate");
		return mv;
	}
	
	
	@RequestMapping("/secure_logout")
	public ModelAndView secureLogout()
	{
		//what you attach to session at the time login need to remove.
		
		
		session.invalidate();
		
		ModelAndView mv = new ModelAndView("Home");
		
		//After logout also use should able to browse the categories and products
		//as we invalidated the session, need to load these data again.
		
		session.setAttribute("category", category); // domain object names
		session.setAttribute("product", product);
		session.setAttribute("supplier", supplier);
		
		
		session.setAttribute("categoryList", categoryDAO.list());
		
		session.setAttribute("supplierList", supplierDAO.list());
		
		session.setAttribute("productList", productDAO.list());
		
		
		//OR Simpley remove only one attribute from the session.
		
		//session.removeAttribute("loggedInUser"); // you no need to load categoriees,suppliers and products
	
		return mv;
		
	}
}
