package com.example.HiddenGem.entity;

public class Menu {
	private int mid;
	private String food;
	private String price;
	private String foodTitle;
	public Menu() {
	}
	public Menu(int mid, String food, String price, String foodTitle) {
		this.mid = mid;
		this.food = food;
		this.price = price;
		this.foodTitle = foodTitle;
	}
	
	
	public Menu(String food, String price) {
		this.food = food;
		this.price = price;
	}
	@Override
	public String toString() {
		return "menu [mid=" + mid + ", food=" + food + ", price=" + price + ", foodTitle=" + foodTitle + "]";
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getFoodTitle() {
		return foodTitle;
	}
	public void setFoodTitle(String foodTitle) {
		this.foodTitle = foodTitle;
	}
	
}
