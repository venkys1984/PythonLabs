package com.dummy.playground;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.users.User;
import com.google.appengine.repackaged.com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import static com.dummy.playground.OfyService.ofy;

/**
 * Defines v1 of a helloworld API, which provides simple "greeting" methods.
 */
@Api(
    name = "playground",
    version = "v1",
    scopes = {Constants.EMAIL_SCOPE},
    clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
    audiences = {Constants.ANDROID_AUDIENCE}
)
public class Playground {

  @ApiMethod(name="store", path="store", httpMethod="post")
  public JsonKeys store(JsonKeys s){
      System.out.println("name: " + s.name);
      Gson gson = new Gson();
      s.stringifiedKey = gson.toJson(s.keys);

      ofy().save().entity(s);
    return s;
  }

  @ApiMethod(name="getByName", path="getByName", httpMethod = "post")
  public List<JsonKeys> getByName(JsonIdentifier data){
      List<JsonKeys> jkeys = ofy().load().type(JsonKeys.class).filter("name =", data.name).list();
      return jkeys;
  }

    @ApiMethod(name="getByIdentifier", path="getByIdentifier", httpMethod = "post")
    public List<JsonKeys> getByIdentifier(JsonIdentifier data){
        //Gson gson = new Gson();
        //String stringifiedKey = gson.toJson(data.identifier);
        //System.out.println("Stringified key: " + stringifiedKey);

        Map<String,Object> criteria = new HashMap<>();
        criteria.put("product", "laptop");
        criteria.put("cost", "high");

        List<String> brands = new ArrayList<>();
        brands.add("dell");
        brands.add("lenovo");

        criteria.put("brands", brands);

        List<JsonKeys> jkeys = ofy().load().type(JsonKeys.class).filter("keys.product =", criteria.get("product"))
                                                                .filter("keys.cost =" , criteria.get("cost"))
                                                                //.filter("keys.brands",criteria.get("brands"))
                                                                .list();
        //List<JsonKeys> jkeys = ofy().load().type(JsonKeys.class).list();
        //System.out.println("Stringified key in entity: " + (jkeys.get(0)).stringifiedKey);
        return jkeys;
    }
}
