package dummy.app.myapp;

/**
  * Add your first API methods in this class, or you may create another class. In that case, please
  * update your web.xml accordingly.
 **/

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;

import dummy.app.myapp.HelloMessage;
  
@Api(
    name = "yourFirstAPI",
    version = "v1",
    scopes = {Constants.EMAIL_SCOPE},
    clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
    audiences = {Constants.ANDROID_AUDIENCE}
)

public class YourFirstAPI {

	//REST : GET http://localhost:8080/_ah/api/yourFirstAPI/v1/sayHello
	@ApiMethod(name="sayHello", path="sayHello", httpMethod=HttpMethod.GET)
	public HelloMessage sayHello() {
        return new HelloMessage();
    }

    //REST : GET http://localhost:8080/_ah/api/yourFirstAPI/v1/sayHelloByName?name=xena
    @ApiMethod(name="sayHelloByName", path="sayHelloByName", httpMethod=HttpMethod.GET)
    public HelloMessage sayHelloByName(@Named("yourname") String name) {
    	return new HelloMessage(name);
    }
}
