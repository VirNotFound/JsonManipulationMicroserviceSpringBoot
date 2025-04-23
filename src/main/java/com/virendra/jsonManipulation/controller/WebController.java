package com.virendra.jsonManipulation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.virendra.jsonManipulation.service.WebService;

@RestController
@RequestMapping("json")
public class WebController {
	
	@Autowired
	private WebService webService;
	
	// JsonNode Simple Input and Output
	@PostMapping("simpleInO")
	public ResponseEntity<JsonNode> simpleInO(@RequestBody JsonNode userData){
		return webService.simpleInO(userData);
	}
	
	// JsonNode Complex Output
	@PostMapping("complexOutput")
	public ResponseEntity<JsonNode> complexOutput(@RequestBody JsonNode userData){
		return webService.complexOutput(userData);
	}
	
	// JsonNode Complex Input
	@PostMapping("complexInput")
	public ResponseEntity<JsonNode> complexInput(@RequestBody JsonNode userData){
		return webService.complexInput(userData);
	}
	
}
