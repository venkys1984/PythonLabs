package com.google.devrel.training.conference.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.google.appengine.repackaged.com.google.common.collect.ImmutableList;
import com.google.devrel.training.conference.form.ConferanceForm;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.condition.IfNotDefault;

@Entity
public class Conferance {
	public static final String DEFAULT_CITY = "Default City";

	public static final List<String> DEFAULT_TOPICS = ImmutableList.of("Default", "Topic");

	    @Id
	    public Long id;

	   
	    @Index
	    public String name;

	  
	    public String description;

	    
	    @Parent
	    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	    public Key<Profile> profileKey;

	    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	    public String organizerUserId;

	   
	    @Index
	    public List<String> topics;

	
	    @Index(IfNotDefault.class) public String city;

	   
	    public Date startDate;

	    public Date endDate;

	    
	    @Index
	    public int month;

	   
	    @Index
	    public int maxAttendees;

	  
	    @Index
	    public int seatsAvailable;

	  
	    public Conferance() {}
	    
	    public void makeConf(ConferanceForm cf){
	    	this.city = cf.city;
	    	this.description = cf.description;
	    	this.endDate = cf.endDate;
	    	this.maxAttendees = cf.maxAttendees;
	    	this.startDate = cf.startDate;
	    	this.topics = cf.topics;
	    	this.name = cf.name;
	    		    	
	    	Calendar cal = Calendar.getInstance();
	    	cal.setTime(startDate);
	    	this.month = cal.get(Calendar.MONTH) + 1; //by default it returns 0 based index for month. So, adding 1
	    }
	
}