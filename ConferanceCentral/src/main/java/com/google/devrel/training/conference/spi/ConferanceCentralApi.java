package com.google.devrel.training.conference.spi;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.devrel.training.conference.Constants;
import com.google.devrel.training.conference.domain.Profile;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

//import com.google.api.server.spi.config.Named;
import com.google.devrel.training.conference.form.ProfileForm;
import com.google.devrel.training.conference.service.OfyService;

import javax.inject.Named;
import com.google.appengine.api.users.User;

/**
  * Add your first API methods in this class, or you may create another class. In that case, please
  * update your web.xml accordingly.
 **/

@Api(name = "conference",
     version = "v1",
     scopes = { Constants.EMAIL_SCOPE },
     clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID },
     description = "API for the Conference Central Backend application.")

public class ConferanceCentralApi {
	
	@ApiMethod(name = "saveProfile", path = "profile", httpMethod = HttpMethod.POST)
	public Profile saveProfile(final User user,ProfileForm pf) throws UnauthorizedException{
		if(user == null){
			throw new UnauthorizedException("You are not signed in!");
		}
		Profile entity = new Profile(user.getUserId(),pf.displayName,user.getEmail(),pf.teeShirtSize);
		Objectify ofy = OfyService.ofy();
		ofy.save().entity(entity).now();
		return entity;
	}
}
