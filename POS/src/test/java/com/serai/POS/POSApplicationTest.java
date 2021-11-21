package com.serai.POS;

import static org.junit.jupiter.api.Assertions.*;

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

	// Setup a predefined sample pizza order object and add it into database
	@BeforeEach
	public void setup() {
		pizzaOrder = new PizzaOrder();
		pizzaOrder.setPizzaName("Pepperoni");
		pizzaOrder.setPrice(99.0);
		pizzaOrder.setQuantity(1);
		pizzaOrderServiceImpl.addPizzaOrder(pizzaOrder);
	}

	// Test to test add pizza order method in implementation class
	@Test
	public void addPizzaOrders_PizzaOrderPersistsIntoDatabase() {

		// Sample pizza to add into database
		PizzaOrder pizzaOrderSample = new PizzaOrder();
		pizzaOrderSample.setPizzaName("Mushroom");
		pizzaOrderSample.setPrice(95.0);
		pizzaOrderSample.setQuantity(1);

		// Call the code under test
		pizzaOrderServiceImpl.addPizzaOrder(pizzaOrderSample);

		// Save result in database into a pizza order object
		PizzaOrder pizzaOrderResult = pizzaOrderServiceImpl.getPizzaOrderById(pizzaOrderSample.getPizzaOrderId());

		// Check if the test is a pass or fail by comparing Id
		assertEquals(pizzaOrderSample.getPizzaOrderId(), pizzaOrderResult.getPizzaOrderId());
	}

	// Test to test update pizza name with update pizza order method in implementation class
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

	// Test to test update pizza price with update pizza order method in implementation class
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
	
	// Test to test update pizza quantity with update pizza order method in implementation class
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

	// Test to test delete pizza order method in implementation class
	@Test
	public void deletePizzaOrders_RemoveRecordsOfPizzaOrder() {

		// Call the code under test
		pizzaOrderServiceImpl.deletePizzaOrder(pizzaOrder.getPizzaOrderId());

		// Check if the test is a pass or fail by comparing number of pizza orders in db
		assertEquals(0, pizzaOrderServiceImpl.getAllPizzaOrders().size());
	}

	// Test to test get all pizza order method in implementation class
	@Test
	public void getAllPizzaOrders_FindsFullListOfPizzaOrders() {

		// Check if the test is a pass or fail by comparing number of pizza orders in db
		assertEquals(2, pizzaOrderServiceImpl.getAllPizzaOrders().size());
	}

}
