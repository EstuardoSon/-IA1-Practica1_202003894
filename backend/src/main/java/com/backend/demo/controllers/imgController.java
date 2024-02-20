package com.backend.demo.controllers;

import com.backend.demo.models.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class imgController {

	@CrossOrigin(origins = "*")
	@GetMapping("/")
	public String home() {
		return "Estuardo Gabriel Son Mux - 202003894";
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/scan")
	public Response scan(@RequestParam("file") MultipartFile file) {
		String answer;
		try { 
            
            // Creating an object of FileOutputStream class   
            
            //file.getBytes() 
            answer = file.getName();
            return new Response(200,answer);
        }  
        
        // Catch block to handle exceptions 
        catch (Exception e) { 
            e.printStackTrace();
            answer = "Error";
            
            return new Response(500,answer);
        } 
	}
}
