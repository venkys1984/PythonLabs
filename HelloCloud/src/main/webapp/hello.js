function clicked(){
    alert("Clicked!");
    
   
}

function googleClientLoaded() {
    alert("Google client loaded!");
    
    // You need to pass the root path when you load your API
	// otherwise calls to execute the API run into a problem
	
	// rootpath will evaulate to either of these, depending on where the app is running:
	// //localhost:8080/_ah/api
	// //your-app-id/_ah/api

	var rootpath = "//" + window.location.host + "/_ah/api";
	
	// Load the helloworldendpoints API
	// If loading completes successfully, call loadCallback function
	gapi.client.load('yourFirstAPI', 'v1', googleAPILoaded, rootpath);
	
}

function googleAPILoaded(){
	btn = document.getElementById("greet");
	btn.onclick= function(){
        var request = gapi.client.yourFirstAPI.sayHello();    
        request.execute(function(response){
            console.log(response);
            alert(response.hello);
        });
    };
    
    nameBtn = document.getElementById("greetByName");
    nameBtn.onclick = function(){
        var request = gapi.client.yourFirstAPI.sayHelloByName({
            'yourname' : 'venky'
        });
         request.execute(function(response){
            console.log(response);
            alert(response.hello);
        });
    }
    
}

