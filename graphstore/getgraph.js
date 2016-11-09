var request = require('request');
var async = require('async');

let sampleKey = {
    "identifier" : {
        "CompanyId" : 35,
        "Name" : "SLB",
        "Revenue" : 55.5
    }
}

function sendIdentifier(){
    let url = "http://localhost:8080/_ah/api/graphStoreAPI/v1/getTopDownTree";
     request.post(url, 
                       {
                            json : sampleKey
                       },
                       function(error, response, body){
                            //Check for error
                            if(error){
                                return console.log('Error:', error);
                            }

                            //Check for right status code
                            if(response.statusCode !== 200){
                                return console.log('Invalid Status Code Returned:', response.statusCode);
                            }
                            console.log("The received response:");
                            console.log(body);
                            
                        });
}

sendIdentifier();