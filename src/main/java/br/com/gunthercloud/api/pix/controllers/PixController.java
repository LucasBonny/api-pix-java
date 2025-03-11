package br.com.gunthercloud.api.pix.controllers;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gunthercloud.api.pix.dto.PixRequestParamsDTO;
import br.com.gunthercloud.api.pix.services.PixService;

@RestController
@RequestMapping(value = "/api/pix", produces = MediaType.APPLICATION_JSON_VALUE)
public record PixController(PixService service) {

	@GetMapping
	public ResponseEntity<String> listarChavesPix() {
		return ResponseEntity.ok(service.listarChavesPix().toString());
	}
	@PostMapping
	public ResponseEntity<String> criarChavePix() {
		var result = service.criarChavePix().toString();
		return ResponseEntity.status(201).body(result);
	}
	@DeleteMapping("/{chave}")
	public ResponseEntity<String> removerChavePix(@PathVariable String chave) {
		service.removerChavePix(chave).toString();
		return ResponseEntity.status(204).build();
	}
	
	@PostMapping("/criar")
	public ResponseEntity<String> gerarPixComQrCode(@RequestBody PixRequestParamsDTO body) {
		var response = service.gerarPixComQrCode(body);
        System.out.println(response.get("pixCopiaECola"));
		return ResponseEntity.status(201).body(response.toString());
	}

}
