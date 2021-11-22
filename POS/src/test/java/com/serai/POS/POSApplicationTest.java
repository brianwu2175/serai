package com.serai.POS;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.serai.POS.model.PizzaOrder;
import com.serai.POS.service.PizzaOrderServiceImpl;

@SpringBootTest
class POSApplicationTest {

	@Autowired
	private PizzaOrderServiceImpl pizzaOrderServiceImpl;

	private PizzaOrder pizzaOrder;

	// Clear database and setup a predefined sample pizza order object to add into database
	@BeforeEach
	public void setup() {
		List<PizzaOrder> pizzaOrders = pizzaOrderServiceImpl.getAllPizzaOrders();
		for (PizzaOrder p : pizzaOrders) {pizzaOrderServiceImpl.deletePizzaOrder(p.getPizzaOrderId());}
		pizzaOrder = new PizzaOrder();
		pizzaOrder.setPizzaName("Pepperoni");
		pizzaOrder.setPrice(99.0);
		pizzaOrder.setQuantity(1);
		pizzaOrderServiceImpl.addPizzaOrder(pizzaOrder);
	}

	// Test add pizza order method in implementation class
	@Test
	public void addPizzaOrders_PizzaOrderPersistsIntoDatabase() {
		
		// Sample pizza to add into database
		PizzaOrder pizzaOrderSample = new PizzaOrder();
		pizzaOrderSample.setPizzaName("Seafood");
		pizzaOrderSample.setPrice(100.0);
		pizzaOrderSample.setQuantity(1);

		// Call the code under test
		pizzaOrderServiceImpl.addPizzaOrder(pizzaOrderSample);

		// Save result in database into a pizza order object
		PizzaOrder pizzaOrderResult = pizzaOrderServiceImpl.getPizzaOrderById(pizzaOrderSample.getPizzaOrderId());

		// Check if the test is a pass or fail by comparing to string
		assertEquals(pizzaOrderSample.toString(), pizzaOrderResult.toString());
	}

	// Test update pizza name with update pizza order method in implementation class
	@Test
	public void updatePizzaName_NewPizzaOrderPersistsIntoDatabase() {

		// Setup new variables for pizza order object
		pizzaOrder.setPizzaName("Salami");

		// Call the code under test
		pizzaOrderServiceImpl.updatePizzaOrder(pizzaOrder, pizzaOrder.getPizzaOrderId());

		// Check if the test is a pass or fail by comparing pizza name
		PizzaOrder pizzaOrderResult = pizzaOrderServiceImpl.getPizzaOrderById(pizzaOrder.getPizzaOrderId());
		assertEquals("Salami", pizzaOrderResult.getPizzaName());
	}

	// Test update pizza price with update pizza order method in implementation class
	@Test
	public void updatePizzaPrice_NewPizzaOrderPersistsIntoDatabase() {

		// Setup new variables for pizza order object
		pizzaOrder.setPrice(100.0);

		// Call the code under test
		pizzaOrderServiceImpl.updatePizzaOrder(pizzaOrder, pizzaOrder.getPizzaOrderId());

		// Check if the test is a pass or fail by comparing pizza name
		PizzaOrder pizzaOrderResult = pizzaOrderServiceImpl.getPizzaOrderById(pizzaOrder.getPizzaOrderId());
		assertEquals(100.0, pizzaOrderResult.getPrice());
	}
	
	// Test update pizza quantity with update pizza order method in implementation class
	@Test
	public void updatePizzaQuantity_NewPizzaOrderPersistsIntoDatabase() {

		// Setup new variables for pizza order object
		pizzaOrder.setQuantity(5);

		// Call the code under test
		pizzaOrderServiceImpl.updatePizzaOrder(pizzaOrder, pizzaOrder.getPizzaOrderId());

		// Check if the test is a pass or fail by comparing pizza name
		PizzaOrder pizzaOrderResult = pizzaOrderServiceImpl.getPizzaOrderById(pizzaOrder.getPizzaOrderId());
		assertEquals(5, pizzaOrderResult.getQuantity());
	}

	// Test delete pizza order method in implementation class
	@Test
	public void deletePizzaOrders_RemoveRecordsOfPizzaOrder() {

		// Call the code under test
		pizzaOrderServiceImpl.deletePizzaOrder(pizzaOrder.getPizzaOrderId());

		// Check if the test is a pass or fail by comparing number of pizza orders in db
		assertEquals(0, pizzaOrderServiceImpl.getAllPizzaOrders().size());
	}

	// Test get all pizza order method in implementation class
	@Test
	public void getAllPizzaOrders_ReturnsFullListOfPizzaOrders() {
		
		// Check if the test is a pass or fail by comparing number of pizza orders in db
		assertEquals(1, pizzaOrderServiceImpl.getAllPizzaOrders().size());
	}
	
	// Test find total price method in implementation class
	@Test
	public void findTotalPrice_ReturnsTotalPriceOfPizzaOrders() {
		
		// Sample pizza to add into database
		PizzaOrder pizzaOrderSample = new PizzaOrder();
		pizzaOrderSample.setPizzaName("Mushroom");
		pizzaOrderSample.setPrice(95.0);
		pizzaOrderSample.setQuantity(3);
		pizzaOrderServiceImpl.addPizzaOrder(pizzaOrderSample);
		
		// Calculate total price manually
		double expectedPrice = pizzaOrder.getPrice()*pizzaOrder.getQuantity() + pizzaOrderSample.getPrice()*pizzaOrderSample.getQuantity();
		
		// Retrieve list of pizza orders to use find total price to calculate total price of pizza orders
		List<PizzaOrder> pizzaOrders = pizzaOrderServiceImpl.getAllPizzaOrders();
		double totalPrice = pizzaOrderServiceImpl.findTotalPrice(pizzaOrders);
		
		// Check if the test is a pass or fail by comparing number of pizza orders in db
		assertEquals(expectedPrice, totalPrice);
	}
}
