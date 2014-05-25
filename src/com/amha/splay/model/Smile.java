package com.amha.splay.model;

public class Smile {
	
	private int imageReference;
	private int backgroundColor;

	public Smile(){
		
	}
	
	public Smile(int img, int color){
		this.imageReference = img;
		this.backgroundColor = color;
	}
	//Getter methods.
	public int getImageReference(){
		return this.imageReference;
	}
	
	public int getBackgroundColor(){
		return this.backgroundColor;
	}
	
	//Setter method. We can only update background colors.
	public void setBackgroundColor(int newBackroundColor){
		this.backgroundColor = newBackroundColor;
	}
}
