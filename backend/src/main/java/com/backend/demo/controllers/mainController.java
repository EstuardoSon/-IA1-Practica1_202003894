package com.backend.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.demo.models.*;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.FaceAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.SafeSearchAnnotation;
import com.google.protobuf.ByteString;

@RestController
public class mainController {
	
	@CrossOrigin(origins = "*")
	@GetMapping("/")
	public String hello() {
		return "Estuardo Gabriel Son Mux - 202003894";
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/scan")
	public reponse scan(@RequestParam("file") MultipartFile file) throws IOException {
		try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {
			
			//Crear lista de solicitudes
			List<AnnotateImageRequest> requests = new ArrayList<>();
			
			//Crear caracteristicas a buscar
			Feature feat = Feature.newBuilder().setType(Type.SAFE_SEARCH_DETECTION).build();
			Feature featFace = Feature.newBuilder().setType(Type.FACE_DETECTION).build();
			
			//Parsear la imagen
			ByteString imgBytes = ByteString.copyFrom(file.getBytes());
			Image img = Image.newBuilder().setContent(imgBytes).build();
			
			//Unir las configuraciones de busqueda para crear las solicitudes
			AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
			AnnotateImageRequest requestFace = AnnotateImageRequest.newBuilder().addFeatures(featFace).setImage(img).build();
			
			//Agregar las solicitudes a la lista
			requests.add(request);
			requests.add(requestFace);
			
			//Almacenar las respuestas
			BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
		    List<AnnotateImageResponse> responses = response.getResponsesList();

		    AnnotateImageResponse AIRtags = responses.get(0);
		    AnnotateImageResponse AIRfaces = responses.get(1);
		    
		    List<tag> tags = new ArrayList<>();
		    
		    if (AIRtags.hasError()) {
		        System.out.format("Error: %s%n", AIRtags.getError().getMessage());
		    }else {
		    	SafeSearchAnnotation annotation = AIRtags.getSafeSearchAnnotation();
				
		    	tags.add(new tag("Adulto", annotation.getAdultValue()));
				tags.add(new tag("Parodia", annotation.getSpoofValue()));
				tags.add(new tag("Medico", annotation.getMedicalValue()));
				tags.add(new tag("Caliente", annotation.getRacyValue()));
				tags.add(new tag("Violencia", annotation.getViolenceValue()));
		    }
		       
		    int rostros = 0;
		    
		    if (AIRfaces.hasError()) {
		    	System.out.format("Error: %s%n", AIRfaces.getError().getMessage());
		    }else {
		    	List<FaceAnnotation> annotation = AIRfaces.getFaceAnnotationsList();
		    	
		    	rostros = annotation.size();
		    }
		    
		    return new reponse(200, tags, rostros);
		}
	}
}
