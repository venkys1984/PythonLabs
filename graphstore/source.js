var request = require('request');
var async = require('async');

let sampleNodes = [
    
    //Node 1
    {
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
},

//Node 2
{
    "identifier" : {
         "WellId" : 561,
         "WellName" : "Well561"
    },

    "properties" : {
        "Country" : "Some Random country",
        "lat" : 23.5,
        "long" : 50,
        "quantity": {
            "oil" : 50,
            "gas" : 45.5,
            "water" : 4.5
        }
    }, 

    "parent" : {
        "CompanyId" : 35,
        "Name" : "SLB",
        "Revenue" : 55.5,  
    },

    "children" : [
        {
         "BoreHoleId" : 2001,
         "BoreHoleName" : "BoreHole2001"
       },

       {
         "BoreHoleId" : 2002,
         "BoreHoleName" : "BoreHole2002"
      },

      {
         "BoreHoleId" : 2003,
         "BoreHoleName" : "BoreHole2003"
      },
    ]

   
},

//Node 3
{
    "identifier" : {
         "WellId" : 562,
         "WellName" : "Well562"
    },

    "properties" : {
        "Country" : "Some Random country",
        "lat" : 23.5,
        "long" : 50,
        "quantity": {
            "oil" : 50,
            "gas" : 45.5,
            "water" : 4.5
        }
    }, 

    "parent" : {
        "CompanyId" : 35,
        "Name" : "SLB",
        "Revenue" : 55.5,  
    },

     "children" : [
        {
         "BoreHoleId" : 1001,
         "BoreHoleName" : "BoreHole1001"
        },
        {
         "BoreHoleId" : 1002,
         "BoreHoleName" : "BoreHole1002"
        },
        {
         "BoreHoleId" : 1003,
         "BoreHoleName" : "BoreHole1003"
       },
       {
         "BoreHoleId" : 1004,
         "BoreHoleName" : "BoreHole1004"
       },
    ]
},

//Node 4
{
      "identifier" : {
         "BoreHoleId" : 1001,
         "BoreHoleName" : "BoreHole1001"
    },

    "properties" : {
        "Country" : "Some Random country",
        "lat" : 23.5,
        "long" : 50,
        "quantity": {
            "oil" : 50,
            "gas" : 45.5,
            "water" : 4.5
        }
    }, 

    "parent" : {
       "WellId" : 562,
       "WellName" : "Well562"
    }
},

//Node 5
{
      "identifier" : {
         "BoreHoleId" : 1002,
         "BoreHoleName" : "BoreHole1002"
    },

    "properties" : {
        "Country" : "Some Random country",
        "lat" : 23.5,
        "long" : 50,
        "quantity": {
            "oil" : 50,
            "gas" : 45.5,
            "water" : 4.5
        }
    }, 

    "parent" : {
       "WellId" : 562,
       "WellName" : "Well562"
    }
},

//Node 6
{
      "identifier" : {
         "BoreHoleId" : 1003,
         "BoreHoleName" : "BoreHole1003"
    },

    "properties" : {
        "Country" : "Some Random country",
        "lat" : 23.5,
        "long" : 50,
        "quantity": {
            "oil" : 50,
            "gas" : 45.5,
            "water" : 4.5
        }
    }, 

    "parent" : {
       "WellId" : 562,
       "WellName" : "Well562"
    }
},

//Node 7
{
      "identifier" : {
         "BoreHoleId" : 1004,
         "BoreHoleName" : "BoreHole1004"
    },

    "properties" : {
        "Country" : "Some Random country",
        "lat" : 23.5,
        "long" : 50,
        "quantity": {
            "oil" : 50,
            "gas" : 45.5,
            "water" : 4.5
        }
    }, 

    "parent" : {
       "WellId" : 562,
       "WellName" : "Well562"
    }
},

//Node 8
{
      "identifier" : {
         "BoreHoleId" : 2001,
         "BoreHoleName" : "BoreHole2001"
    },

    "properties" : {
        "Country" : "Some Random country",
        "lat" : 23.5,
        "long" : 50,
        "quantity": {
            "oil" : 50,
            "gas" : 45.5,
            "water" : 4.5
        }
    }, 

    "parent" : {
       "WellId" : 561,
       "WellName" : "Well561"
    },

    "children" : [
        {
         "EmployeeId" : 1,
         "Name" : "A",
         "Income" : 3.5
       },

       {
         "EmployeeId" : 2,
         "Name" : "B",
         "Income" : 3.5
      },

      {
         "EmployeeId" : 3,
         "Name" : "C",
         "Income" : 3.5
     },

    ]
},

//Node 9
{
      "identifier" : {
         "BoreHoleId" : 2002,
         "BoreHoleName" : "BoreHole2002"
    },

    "properties" : {
        "Country" : "Some Random country",
        "lat" : 23.5,
        "long" : 50,
        "quantity": {
            "oil" : 50,
            "gas" : 45.5,
            "water" : 4.5
        }
    }, 

    "parent" : {
       "WellId" : 561,
       "WellName" : "Well561"
    },

     "children" : [
        {
         "EmployeeId" : 4,
         "Name" : "D",
         "Income" : 3.5
       },

       {
         "EmployeeId" : 5,
         "Name" : "E",
         "Income" : 3.5
      } 

    ]
},

//Node 10
{
      "identifier" : {
         "BoreHoleId" : 2003,
         "BoreHoleName" : "BoreHole2003"
    },

    "properties" : {
        "Country" : "Some Random country",
        "lat" : 23.5,
        "long" : 50,
        "quantity": {
            "oil" : 50,
            "gas" : 45.5,
            "water" : 4.5
        }
    }, 

    "parent" : {
       "WellId" : 561,
       "WellName" : "Well561"
    }
},

//Node 11
{
      "identifier" : {
         "EmployeeId" : 1,
         "Name" : "A",
         "Income" : 3.5
    },

    "properties" : {
        "Country" : "Some Random country",
        "lat" : 23.5,
        "long" : 50,
        "quantity": {
            "oil" : 50,
            "gas" : 45.5,
            "water" : 4.5
        }
    }, 

    "parent" : {
         "BoreHoleId" : 2001,
         "BoreHoleName" : "BoreHole2001"
    }
},

//Node 12
{
      "identifier" : {
         "EmployeeId" : 3,
         "Name" : "C",
         "Income" : 3.5
    },

    "properties" : {
        "Country" : "Some Random country",
        "lat" : 23.5,
        "long" : 50,
        "quantity": {
            "oil" : 50,
            "gas" : 45.5,
            "water" : 4.5
        }
    }, 

    "parent" : {
         "BoreHoleId" : 2001,
         "BoreHoleName" : "BoreHole2001"
    }
},

//Node 13
{
      "identifier" : {
         "EmployeeId" : 2,
         "Name" : "B",
         "Income" : 3.5
    },

    "properties" : {
        "Country" : "Some Random country",
        "lat" : 23.5,
        "long" : 50,
        "quantity": {
            "oil" : 50,
            "gas" : 45.5,
            "water" : 4.5
        }
    }, 

    "parent" : {
         "BoreHoleId" : 2001,
         "BoreHoleName" : "BoreHole2001"
    }
},

//Node 14
{
      "identifier" : {
         "EmployeeId" : 4,
         "Name" : "D",
         "Income" : 3.5
    },

    "properties" : {
        "Country" : "Some Random country",
        "lat" : 23.5,
        "long" : 50,
        "quantity": {
            "oil" : 50,
            "gas" : 45.5,
            "water" : 4.5
        }
    }, 

    "parent" : {
         "BoreHoleId" : 2002,
         "BoreHoleName" : "BoreHole2002"
    }
},

//Node 15
{
      "identifier" : {
         "EmployeeId" : 5,
         "Name" : "E",
         "Income" : 3.5
    },

    "properties" : {
        "Country" : "Some Random country",
        "lat" : 23.5,
        "long" : 50,
        "quantity": {
            "oil" : 50,
            "gas" : 45.5,
            "water" : 4.5
        }
    }, 

    "parent" : {
         "BoreHoleId" : 2002,
         "BoreHoleName" : "BoreHole2002"
    }
},






]


function simulateSource(){
    async.each(sampleNodes, function(node, done){
        //send Node and call done()
        let url = "http://localhost:8080/_ah/api/graphStoreAPI/v1/storeNode";
        request.post(url, 
                       {
                            json : node
                       },
                       function(error, response, body){
                            //Check for error
                            if(error){
                                console.log('Error:', error);
                                return done(new Error("Error while sending node"));
                            }

                            //Check for right status code
                            if(response.statusCode !== 200){
                                console.log('Invalid Status Code Returned:', response.statusCode);
                                return done(new Error("Error while sending node"));
                            }
                            console.log(body);
                            return done(null);
                        });
            
    }, 
    
    function(err){
        if(err){
            console.log(err);
        }
        else{
            console.log("All nodes sent");
        }
    });
}

simulateSource();
