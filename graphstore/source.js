var request = require('request');
var async = require('async');

let sampleNode = {
    "identifier" : {
        "CompanyId" : 35,
        "Name" : "SLB",
        "Revenue" : 55.5,        
    },

    "properties" : {
        "CompanyId" : 35,
        "Name" : "SLB",
        "Branches" : ["Houston", "India", "Gulf"],
        "Client" : {
            "Name" : "Chevron",
            "Country" : "US",
            "Revenue" : 44.5,
            "Size" : 5000
        }
    },

    "parent" : {
        "CompanyId" : 56,
        "Name" : "Alphabet Inc"
    },

    "children" : [
        {
            "WellId" : 561,
            "WellName" : "Well561"
        },
        {
            "WellId" : 562,
            "WellName" : "Well562"
        }

    ]
}

function sendNode(){
    let url = "http://localhost:8080/_ah/api/graphStoreAPI/v1/storeNode";
    request.post(url, 
                       {
                            json : sampleNode
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

                            console.log(body);
                            
                        });
            
}

sendNode();

let sampleKey = {
    "identifier" : {
        "CompanyId" : 35,
        "Name" : "SLB",
        "Revenue" : 55.5
    }
}

function sendIdentifier(){
    let url = "http://localhost:8080/_ah/api/graphStoreAPI/v1/getNode";
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