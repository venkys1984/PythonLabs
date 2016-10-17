package com.google.devrel.training.conference.service;

import com.google.devrel.training.conference.domain.Profile;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;


public class OfyService{
	
	//register entity class
	static{
		ObjectifyService.factory().register(Profile.class);
	}
	
	public static Objectify ofy(){
		return ObjectifyService.ofy();
	}
}