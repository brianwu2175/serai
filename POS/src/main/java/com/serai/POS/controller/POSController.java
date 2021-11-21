package com.serai.POS.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.serai.POS.model.PizzaOrder;
import com.serai.POS.service.PizzaOrderServiceImpl;

/**
 * Controller class for POS
 * 
 * Used to direct and redirect traffic from one page to another page
 * as well as parse data from the service to the front-end of the application
 * 
 * @author Brian
 *
 */
@Controller
public class POSController {

	// Initializing logger for logging usage
	Logger logger = LoggerFactory.getLogger(POSController.class);

	@Autowired
	PizzaOrderServiceImpl pizzaOrderServiceImpl;

	/**
	 * Mapping method for the index page
	 * 
	 * @author Brian
	 * 
	 */
	@RequestMapping("/")
	public String indexPage() {

		logger.info("Traffic directed to homepage");

		return "index";
	}

	/**
	 * Mapping method for the add pizza order page
	 * Initializes model object for user to input data for a new pizza order
	 * 
	 * @author Brian
	 * 
	 */
	@RequestMapping("/addPizzaOrder")
	public String addPizzaOrderPage(Model model) {

		// Creating pizza order template in the model to parse to front-end for data collection
		PizzaOrder pizzaOrder = new PizzaOrder();
		model.addAttribute("pizzaOrder", pizzaOrder);

		logger.info("Traffic directed to add pizza order page");

		return "addPizzaOrder";
	}

	/**
	 * Servlet to save pizza order and redirect back to view pizza order page
	 * Calls service class to persist new pizza order
	 * 
	 * @author Brian
	 * 
	 */
	@RequestMapping("/savePizzaOrder")
	public String savePizzaOrderServlet(PizzaOrder pizzaOrder) {
		
		logger.info("Calling pizza order service to persist new pizza order with id:" + pizzaOrder.getPizzaOrderId());
		
		// Calling pizza order service implementation to persist pizza order into database
		pizzaOrderServiceImpl.addPizzaOrder(pizzaOrder);

		logger.info("Traffic redirected to view pizza orders page");

		return "redirect:/viewPizzaOrders";
	}

	/**
	 * Mapping method for the view pizza orders page
	 * Loads data for all pizza orders using a call from the service class
	 * Loads total price data for all pizza orders using a call from the service class
	 * Parses all loaded data into the view pizza orders page 
	 * 
	 * @author Brian
	 * 
	 */
	@RequestMapping("/viewPizzaOrders")
	public String viewPizzaOrderServlet(Model model) {

		logger.info("Calling pizza order service to fetch data for all pizza orders");

		// Fetching all pizza orders and saving it into a model to parse to the front-end view pizza orders page
		List<PizzaOrder> pizzaOrders = pizzaOrderServiceImpl.getAllPizzaOrders();
		model.addAttribute("pizzaOrders", pizzaOrders);

		logger.info("Calling pizza order service to fetch total price for all pizza orders");

		// Fetching total price of all pizza orders to parse to the front-end view pizza orders page
		double totalPrice = pizzaOrderServiceImpl.findTotalPrice(pizzaOrders);
		model.addAttribute("totalPrice", totalPrice);

		logger.info("Traffic directed to view pizza orders page");

		return "viewPizzaOrders";
	}

	/**
	 * Mapping method for the update pizza order page
	 * Loads data for pizza order into update pizza order page for user to perform updates 
	 * 
	 * @author Brian
	 * 
	 */
	@GetMapping("/updatePizzaOrder")
	public String updatePizzaOrder(@RequestParam("id") int pizzaOrderId, Model model) {
		
		logger.info("Calling pizza order service to fetch data for pizza order with id:" + pizzaOrderId);

		//Fetching pizza order and id for data updating in update pizza order page
		PizzaOrder pizzaOrder = pizzaOrderServiceImpl.getPizzaOrderById(pizzaOrderId);
		model.addAttribute("pizzaOrder", pizzaOrder);
		model.addAttribute("pizzaOrderId", pizzaOrderId);

		logger.info("Traffic directed to update pizza order page");

		return "updatePizzaOrder";
	}

	/**
	 * Servlet to update pizza order and redirect back to view pizza order page
	 * Calls service class to update new pizza order
	 * 
	 * @author Brian
	 * 
	 */
	@RequestMapping("/{id}updatePizzaOrder")
	public String updatePizzaOrder(@PathVariable("id") int pizzaOrderId, PizzaOrder pizzaOrder) {
		
		logger.info("Calling pizza order service to update pizza order with id:" + pizzaOrder.getPizzaOrderId());

		// Calling pizza order service implementation to update pizza order into database
		pizzaOrderServiceImpl.updatePizzaOrder(pizzaOrder, pizzaOrderId);
		
		logger.info("Traffic redirected to view pizza orders page");

		return "redirect:/viewPizzaOrders";
	}

	/**
	 * Servlet to delete pizza order and redirect back to view pizza order page
	 * Calls service class to delete new pizza order
	 * 
	 * @author Brian
	 * 
	 */
	@RequestMapping("/deletePizzaOrder")
	public String deletePizzaOrder(@RequestParam("id") int pizzaOrderId, Model model) {

		logger.info("Calling pizza order service to update pizza order with id:" + pizzaOrderId);

		// Calling pizza order service implementation to delete pizza order from database
		pizzaOrderServiceImpl.deletePizzaOrder(pizzaOrderId);
		
		logger.info("Traffic redirected to view pizza orders page");

		return "redirect:/viewPizzaOrders";
	}
}
