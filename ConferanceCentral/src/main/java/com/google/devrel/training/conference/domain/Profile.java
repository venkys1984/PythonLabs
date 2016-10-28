package com.google.devrel.training.conference.domain;

import com.google.devrel.training.conference.form.ProfileForm.TeeShirtSize;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

// TODO indicate that this class is an Entity
@Entity
public class Profile {
	
	public String displayName;
	public String mainEmail;
	public @Id String userId;
	public TeeShirtSize teeSize;
	
	
    
    /**
     * Public constructor for Profile.
     * @param userId The user id, obtained from the email
     * @param displayName Any string user wants us to display him/her on this system.
     * @param mainEmail User's main e-mail address.
     * @param teeShirtSize The User's tee shirt size
     * 
     */
    public Profile (String userId, String displayName, String mainEmail,TeeShirtSize tsize) {
    	this.userId = userId;
    	this.displayName = displayName;
    	this.mainEmail = mainEmail;
    	this.teeSize = tsize;
    }
    
    public void update(String displayName,TeeShirtSize teeSize){
    	if(displayName != null)
    		this.displayName = displayName;
    	
    	if(teeSize != null)
    		this.teeSize = teeSize;
    }
    


	/**
     * Just making the default constructor private.
     */
    private Profile() {}

}
