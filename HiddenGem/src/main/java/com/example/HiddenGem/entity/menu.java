package com.example.HiddenGem.entity;

public class menu {
	private int mid;
	private String food;
	private int price;
	private String foodTitle;
	public menu() {
	}
	public menu(int mid, String food, int price, String foodTitle) {
		this.mid = mid;
		this.food = food;
		this.price = price;
		this.foodTitle = foodTitle;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getFoodTitle() {
		return foodTitle;
	}
	public void setFoodTitle(String foodTitle) {
		this.foodTitle = foodTitle;
	}
	
}
