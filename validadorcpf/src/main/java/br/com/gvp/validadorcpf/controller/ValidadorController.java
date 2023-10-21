package br.com.gvp.validadorcpf.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validar")
public class ValidadorController {
	@GetMapping("/{cpf}")
	public ResponseEntity<Object> validarCPF(@PathVariable String cpf){
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		long cpfLong=0;
		if (cpf.length() != 11) {
			return ResponseEntity.badRequest().body("CPF invalido");
		}
		for (int i = 0; i < 11; i++) {
			if (!cpf.substring(i).matches("[0-9]*")) {
				return ResponseEntity.badRequest().body("CPF invalido");
			}
		}
		try {
			cpfLong = Long.parseLong(cpf);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("CPF invalido");
		}
		if(cpfLong%2 ==0 ) {
		return ResponseEntity.ok(cpf);
		}
		else {
			return ResponseEntity.badRequest().body("CPF invalido");
		}
	}

}
