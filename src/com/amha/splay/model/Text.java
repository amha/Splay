package com.amha.splay.model;

public class Text {

	private String message;
	private int bgColor;
	
	public Text(){
		//Temp hack, revisit this later
	}
	public Text(String text, int hexColor){
		this.message = text;
		this.bgColor = hexColor;
	}
	public String getMessage(){
		return message;
	}
	public int getBgColor(){
		return bgColor;
	}
	public void setMessage(String text){
		this.message = text;
	}
	public void setBgColor(int newColor){
		this.bgColor = newColor;
	}
}
