package com.serai.POS.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Model class for POS
 * 
 * Used to define the variables for each individual pizza order object
 * An auto generated value is used for pizza order id
 * 
 * @author Brian
 *
 */
@Entity
public class PizzaOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pizzaOrderId;
	private String pizzaName;
	private double price;
	private int quantity;

	public int getPizzaOrderId() {
		return pizzaOrderId;
	}

	public void setPizzaOrderId(int pizzaOrderId) {
		this.pizzaOrderId = pizzaOrderId;
	}

	public String getPizzaName() {
		return pizzaName;
	}

	public void setPizzaName(String pizzaName) {
		this.pizzaName = pizzaName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}