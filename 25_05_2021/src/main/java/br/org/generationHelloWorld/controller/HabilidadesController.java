package br.org.generationHelloWorld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HabilidadesController {
	
	@GetMapping("/habilidades")
	public String Habilidades() {
		
		
		return "Persistência, orientação ao detalhe";
		
	}
	
	
	

}
