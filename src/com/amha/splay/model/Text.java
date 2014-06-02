/*
 * Copyright (C) 2014 Amha Mogus amha.mogus@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
