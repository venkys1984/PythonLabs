package dummy.app.myapp;

public class HelloMessage {
	public String hello;
	
	public HelloMessage(){
		hello = "Hello message";
	}

	public HelloMessage(String name){
		hello = "Hello " + name;
	}
}