package br.com.gunthercloud.api.pix.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gunthercloud.api.pix.services.PixService;

@RestController
@RequestMapping(value = "/api/pix", produces = MediaType.APPLICATION_JSON_VALUE)
public record PixController(PixService service) {
	
	@GetMapping
	public ResponseEntity<String> listarChavesPix() {
		return ResponseEntity.ok(service.listarChavesPix().toString());
	}

}
