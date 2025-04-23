package com.virendra.jsonManipulation.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class WebService {
	
	// Simple Input and Output
	
//	--------------- Input ---------------
//	 {
//	 		"name" : "John",
//	 		"age" : 20
//	 }
	
//	--------------- Output ---------------
//	{
//	    "name": "John",
//	    "age": 20,
//	    "message": "You are John, and you are 20 years old."
//	}
	 
	public ResponseEntity<JsonNode> simpleInO(JsonNode userData){
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode response = mapper.createObjectNode();
		
		try {
		String name = userData.get("name").asText();
		int age = userData.get("age").asInt();
		
		// Custom Message Using input data
		String message = "You are " + name + ", and you are " + age + " years old.";
		
		// Display Json Output
		response.put("name", name);
		response.put("age", age);
		response.put("message", message);
		
		return ResponseEntity.ok(response);
		
		}catch(Exception e) {
    	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(mapper.createObjectNode().put("error", "Invalid JSON structure"));
    	}
	
	}
	
	// ********************************************************************************************* //
	
	// JsonNode Complex Output

//	--------------- Input ---------------
//		{
//		  "name": "john",
//		  "age": 30,
//		  "hobby1": "Riding Bike",
//		  "hobby2": "Reading",
//		  "hobby3": "Playing Guitar"
//		}
	
//	--------------- Output ---------------
//	{
//	    "status": "success",
//	    "data": {
//	        "name": "john",
//	        "age": 30,
//	        "hobbies": [
//	            "Riding Bike",
//	            "Reading",
//	            "Playing Guitar"
//	        ]
//	    }
//	}
	
    public ResponseEntity<JsonNode> complexOutput(JsonNode userData) {
        ObjectMapper mapper = new ObjectMapper();
        try {

        String name = userData.get("name").asText();
        int age = userData.get("age").asInt();
        String hobby1 = userData.get("hobby1").asText();
        String hobby2 = userData.get("hobby2").asText();
        String hobby3 = userData.get("hobby3").asText();

        // Prepare hobbies array
        ArrayNode hobbiesArray = mapper.createArrayNode();
        hobbiesArray.add(hobby1);
        hobbiesArray.add(hobby2);
        hobbiesArray.add(hobby3);

        // Prepare "data" object
        ObjectNode dataNode = mapper.createObjectNode();
        dataNode.put("name", name);
        dataNode.put("age", age);
        dataNode.set("hobbies", hobbiesArray);

        // Prepare final response
        ObjectNode response = mapper.createObjectNode();
        response.put("status", "success");
        response.set("data", dataNode);

        return ResponseEntity.ok(response);
        
        }catch(Exception e) {
    	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(mapper.createObjectNode().put("error", "Invalid JSON structure"));
    	}
    }
    
    
    // ********************************************************************************************* //

    // JsonNode Complex Input
	
//    --------------- Input ---------------
//    {
//        "user": {
//            "name": "Jane Doe",
//            "contact": {
//                "email": "jane.doe@example.com",
//                "phone": "123-456-7890"
//            },
//            "activities" : ["Logging", "Development", "Finance"]
//        },
//        "transaction": {
//            "id": "abc123",
//            "amount": 250.75
//        }
//    }
    
//    --------------- Output ---------------
//    {
//        "fullName": "Jane Doe",
//        "email": "jane.doe@example.com",
//        "transactionId": "abc123",
//        "amount": 250.75,
//        "activities": [
//            "Logging",
//            "Development",
//            "Finance"
//        ]
//    }
    
    public ResponseEntity<JsonNode> complexInput(JsonNode userData){
    	ObjectMapper mapper = new ObjectMapper();
    	ArrayNode activitiesArray = mapper.createArrayNode();
    	try {
    		// Prepare Data for Output
    		String fullName = userData.path("user").path("name").asText();
    		String email = userData.path("user").path("contact").path("email").asText();
    		String tranId = userData.path("transaction").path("id").asText();
    		Double amount = userData.path("transaction").path("amount").asDouble();
    		
    		// Fetch activities and store them in activitiesArray
    		JsonNode activitiesNode = userData.path("user").path("activities");
    		if(activitiesNode.isArray()) {
    			for(JsonNode activity : activitiesNode) {
    				activitiesArray.add(activity);
    			}
    		}
    		
    		// Final Response
    		ObjectNode response = mapper.createObjectNode();
    		response.put("fullName", fullName);
    		response.put("email", email);
    		response.put("transactionId", tranId);
    		response.put("amount", amount);
    		response.set("activities", activitiesArray);
    		
    		return ResponseEntity.ok(response);
    		
    	}catch(Exception e) {
    	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(mapper.createObjectNode().put("error", "Invalid JSON structure"));
    	}

    }	

	
}
