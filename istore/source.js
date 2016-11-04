var request = require('request');
var async = require('async');

let sampleNodes = [
    {
        "source" : "avocet",
        "identifier" : "otherProperties.CompanyId",
        "otherProperties" : {
          "CompanyId" : "56",
          "name" : "SLB",
          "Size" : "L"  ,
          "CEO" : "ABC version 111"
        },
        "parent" :{},
        "children" : [
            {
                "ref" : "23",
                "source" : "avocet",
                "type" : "otherProperties.WellId"
            },
            {
                "ref" : "24",
                "source" : "avocet",
                "type" : "otherProperties.WellId"
            }
        ]
    },
    
    {
        "source" : "avocet",
        "identifier" : "otherProperties.WellId",
        "otherProperties" : {
          "WellId" : "23",
          "name" : "Well23",
          "Size" : "L"    
        },
        "parent" : {
            "ref" : "56",
            "source" : "avocet",
            "type" : "otherProperties.CompanyId"
        },
        "children" : [
            {
                "ref" : "105",
                "source" : "avocet",
                "type" : "otherProperties.BoreHoleId"
            },
            {
              "ref" : "106",
              "source" : "avocet",
              "type" : "otherProperties.BoreHoleId"
            }
        ]
        
    },
    
     {
        "source" : "avocet",
        "identifier" : "otherProperties.WellId",
        "otherProperties" : {
          "WellId" : "24",
          "name" : "Well24",
          "Size" : "L"    
        },
        "parent" : {
            "ref" : "56",
            "source" : "avocet",
            "type" : "otherProperties.CompanyId"
        },
        "children" : [     
        ]
        
    },
    
    {
        "source" : "avocet",
        "identifier" : "otherProperties.BoreHoleId",
        "otherProperties" : {
            "BoreHoleId" : "105",
            "name" : "BoreHole105",
            "capacity" : "L"
        },
        "parent" : {
            "ref" : "23",
            "source" : "avocet",
            "type" : "otherProperties.WellId"
        },
         "children" : []
    },
    
    {
       "source" : "avocet",
       "identifier" : "otherProperties.BoreHoleId",
       "otherProperties" : {
           "BoreHoleId" : "106",
           "name" : "BoreHole106",
           "capacity" : "L"
       },
       "parent" : {
           "ref" : "23",
           "source" : "avocet",
           "type" : "otherProperties.WellId"
       },
       "children" : []
    },
    
 
    
    
    //next
]

let sampleData2 = [
       
     {
        "source" : "avocet",
        "identifier" : "otherProperties.WellId",
        "otherProperties" : {
          "WellId" : "24",
          "name" : "Well24",
          "Size" : "L"    
        },
        "parent" : {
            "ref" : "56",
            "source" : "avocet",
            "type" : "otherProperties.CompanyId"
        },
        "children" : [
            {
              "ref" : "206",
              "source" : "avocet",
              "type" : "otherProperties.BoreHoleId"
            }
        ]
        
    },
    
     {
       "source" : "avocet",
       "identifier" : "otherProperties.BoreHoleId",
       "otherProperties" : {
           "BoreHoleId" : "206",
           "name" : "BoreHole206",
           "capacity" : "L"
       },
       "parent" : {
           "ref" : "24",
           "source" : "avocet",
           "type" : "otherProperties.WellId"
       },
       "children" : []
    }
    
    
]

let sampleData = {
 "parent": {
  "ref": "1",
  "source": "avocet",
  "type": "id"
 },
 "children": [
  {
   "ref": "23",
   "source": "avocet",
   "type": "id"
  },
  {
   "ref": "24",
   "source": "avocet",
   "type": "id"
  }
 ],
 "identifier": "id",
 "source": "avocet",
 "otherProperties": {
     "lat" : "45",
     "long" : "50",
     "country" : "india"
 }
} ; 



function main(){
    let url = "http://localhost:8080/_ah/api/istore/v1/storeNode";
   //let url = "https://scratch-147506.appspot.com/_ah/api/istore/v1/storeNode"
    async.each(sampleNodes, function(item, doneCallback){
            //process each node
          request.post(url, 
                       {
                            json : item
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
                            doneCallback(null);
                        });
        
            }, 
               function(err){
                    //the finally method
                    console.log("Finished sending all nodes"); 
                });
    
    
 /*   request.post(url, {
        json : sampleData
    }, function(error, response, body){
        if (!error && response.statusCode == 200) {
            console.log(body)
        }
    });*/
}

main();