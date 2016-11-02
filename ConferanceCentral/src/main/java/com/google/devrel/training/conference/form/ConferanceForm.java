package com.google.devrel.training.conference.form;

import java.util.Date;
import java.util.List;

//the input structure for Conferance entity

public class ConferanceForm{
	 /**
     * The name of the conference.
     */
    public String name;

    /**
     * The description of the conference.
     */
    public String description;

    /**
     * Topics that are discussed in this conference.
     */
    public List<String> topics;

    /**
     * The city where the conference will take place.
     */
    public String city;

    /**
     * The start date of the conference.
     */
    public Date startDate;

    /**
     * The end date of the conference.
     */
    public Date endDate;

    /**
     * The capacity of the conference.
     */
    public int maxAttendees;
    
    public ConferanceForm() {}


}