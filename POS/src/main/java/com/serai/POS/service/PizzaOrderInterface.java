package com.serai.POS.service;

import java.util.List;

import com.serai.POS.model.PizzaOrder;

/**
 * Pizza order interface
 * 
 * Used to predefine needed methods and their respective input variables for POS
 * 
 * @author Brian
 *
 */
public interface PizzaOrderInterface {

	List<PizzaOrder> getAllPizzaOrders();

	PizzaOrder getPizzaOrderById(int pizzaOrderId);

	void addPizzaOrder(PizzaOrder pizzaOrder);

	void updatePizzaOrder(PizzaOrder pizzaOrder, int pizzaOrderId);

	void deletePizzaOrder(int pizzaOrderId);
	
	double findTotalPrice(List<PizzaOrder> pizzaOrders);
}