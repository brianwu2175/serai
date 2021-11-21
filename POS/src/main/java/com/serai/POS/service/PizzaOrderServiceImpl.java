package com.serai.POS.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serai.POS.model.PizzaOrder;
import com.serai.POS.repo.PizzaOrderRepo;

/**
 * Service class for POS
 * 
 * Used to call methods from pizza order repository to perform changes to the data
 * The controller class will call this class in order to persist, update or delete any pizza orders
 * 
 * @author Brian
 *
 */
@Service
public class PizzaOrderServiceImpl implements PizzaOrderInterface {

	// Initializing logger for logging usage
	Logger logger = LoggerFactory.getLogger(PizzaOrderServiceImpl.class);

	@Autowired
	PizzaOrderRepo pizzaOrderRepo;

	public List<PizzaOrder> getAllPizzaOrders() {
		
		//Initializing list of all pizza orders
		List<PizzaOrder> pizzaOrders = new ArrayList<>();
		
		// Try-catch statement to catch any errors while fetching all pizza orders
		try {

			logger.info("Fetching all pizza orders from the database");

			pizzaOrders = pizzaOrderRepo.findAll();

			logger.info("Fetched a total of " + pizzaOrders.size() + " pizza orders");

		} catch (Exception e) {

			logger.error("Could not fetch all pizza orders due to " + e);
		}
		
		return pizzaOrders;
	}

	public PizzaOrder getPizzaOrderById(int pizzaOrderId) {
		
		//Initializing pizza order object
		PizzaOrder pizzaOrder = new PizzaOrder();
				
		// Try-catch statement to catch any errors while fetching all pizza orders
		try {

			logger.info("Fetching pizza order with pizza order id:" + pizzaOrderId);

			pizzaOrder = pizzaOrderRepo.getById(pizzaOrderId);

			logger.info("Fetched pizza order with pizza order id:" + pizzaOrderId);			

		} catch (Exception e) {

			logger.error("Could not fetch pizza order with pizza order id:" + pizzaOrderId + " due to " + e);			
		}
		
		return pizzaOrder;
	}

	public void addPizzaOrder(PizzaOrder pizzaOrder) {

		// Try-catch statement to catch any errors while persisting new pizza order
		try {

			logger.info("Attemping to persist pizza order with id:" + pizzaOrder.getPizzaOrderId());

			pizzaOrderRepo.save(pizzaOrder);

			logger.info("New pizza order with id:" + pizzaOrder.getPizzaOrderId() + " has been persisted");

		} catch (Exception e) {

			logger.error("Could not persist new pizza order with id:" + pizzaOrder.getPizzaOrderId() + " due to " + e);
		}
	}

	public void updatePizzaOrder(PizzaOrder pizzaOrder, int pizzaOrderId) {

		// Try-catch statement to catch any errors while updating pizza order
		try {

			logger.info("Attemping to update pizza order with id:" + pizzaOrder.getPizzaOrderId());

			pizzaOrder.setPizzaOrderId(pizzaOrderId);
			pizzaOrderRepo.save(pizzaOrder);
			
			logger.info("Pizza order with id:" + pizzaOrder.getPizzaOrderId() + " has been updated");

		} catch (Exception e) {

			logger.error("Could not update pizza order with id:" + pizzaOrder.getPizzaOrderId() + " due to " + e);
		}
	}

	public void deletePizzaOrder(int pizzaOrderId) {
		
		// Try-catch statement to catch any errors while deleting pizza order
		try {

			logger.info("Attemping to delete pizza order with id:" + pizzaOrderId);

			pizzaOrderRepo.deleteById(pizzaOrderId);

			logger.info("Pizza order with id:" + pizzaOrderId + " has been deleted");

		} catch (Exception e) {

			logger.error("Could not delete pizza order with id:" + pizzaOrderId + " due to " + e);
		}
	}
	
	public double findTotalPrice(List<PizzaOrder> pizzaOrders) {
		
		//Initializing total price variable
		double totalPrice = 0.0;
		
		logger.info("Calculating total price for " + pizzaOrders.size() + " pizza orders");

		// For loop to calculate total price of all pizza orders
		for (int i = 0; i < pizzaOrders.size(); i++) {
			totalPrice = totalPrice + pizzaOrders.get(i).getPrice() * pizzaOrders.get(i).getQuantity();
		}
		
		logger.info("Calculated total price for " + pizzaOrders.size() + " pizza orders as $" + totalPrice);

		return totalPrice;
	}

}