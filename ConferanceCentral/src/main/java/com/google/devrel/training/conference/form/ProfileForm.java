package com.google.devrel.training.conference.form;

public class ProfileForm{
	
	public String displayName;
	public TeeShirtSize teeShirtSize;
	
	public static enum TeeShirtSize {
	    	NOT_SPECIFIED,
	        XS,
	        S,
	        M,
	        L, 
	        XL, 
	        XXL,
	        XXXL
	 };
	 
	
	public ProfileForm(String name, TeeShirtSize teeSize){
		this.displayName = name;
		this.teeShirtSize = teeSize;
	}
	
	public ProfileForm(){}
}