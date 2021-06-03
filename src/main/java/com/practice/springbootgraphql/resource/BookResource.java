package com.practice.springbootgraphql.resource;

import com.practice.springbootgraphql.service.GraphqlService;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookResource {
	
	@Autowired
	private GraphqlService graphqlService;
	
	@PostMapping
	public ResponseEntity<Object> getAllBooks(@RequestBody String query){
		ExecutionResult executionResult= graphqlService.getGraphQL().execute(query);
		
		return new ResponseEntity<>(executionResult, HttpStatus.ACCEPTED);
	}

}
