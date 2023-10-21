package br.com.gvp.bancoDigitalSpring.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gvp.bancoDigitalSpring.entity.Conta;
import br.com.gvp.bancoDigitalSpring.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {
	@Autowired
	private ContaService service;

	@PostMapping("/add")
	public ResponseEntity<Conta> addConta(@RequestBody Conta conta) {
		Conta contaCriada = service.addConta(conta);
		if (contaCriada != null) {
			return new ResponseEntity<Conta>(contaCriada, HttpStatus.CREATED);
		}
		return new ResponseEntity<Conta>(contaCriada, HttpStatus.NOT_ACCEPTABLE);
	}

	@GetMapping("/listar")
	public ResponseEntity<List<Conta>> listAll() {
		List<Conta> contas = service.listAll();
		return new ResponseEntity<List<Conta>>(contas, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteContaId(@PathVariable Long id) {
		service.deleteContaId(id);
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<Conta> buscarConta(@PathVariable Long id) {
		Conta conta = service.buscarContaId(id).get();
		if (conta != null) {
			return new ResponseEntity<Conta>(conta, HttpStatus.OK);
		}
		return new ResponseEntity<Conta>(conta, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/listar/{id}")
	public ResponseEntity<List<Conta>> listarContasCliente(@PathVariable Long id) {
		List<Conta> contasDoCliente = service.listarContasDoCliente(id);
		return new ResponseEntity<List<Conta>>(contasDoCliente, HttpStatus.OK);
	}

	@PutMapping("/depositar")
	public ResponseEntity<Conta> depositar(@RequestBody HashMap<String, Object> map) {
		int numeroConta = (int) map.get("numero");
		double valor = (double) map.get("valor");
		Conta conta = null;
		if (service.validaValorPositivo(valor)) {
			conta = service.depositar(numeroConta, valor);
			return new ResponseEntity<Conta>(conta, HttpStatus.OK);
		}
		return new ResponseEntity<Conta>(conta, HttpStatus.NOT_ACCEPTABLE);
	}

	@PutMapping("/sacar")
	public ResponseEntity<Conta> sacar(@RequestBody HashMap<String, Object> map) {
		int numeroConta = (int) map.get("numero");
		double valor = (double) map.get("valor");
		Conta conta = null;
		if (service.validaValorPositivo(valor)) {
			if (service.verificarSaldo(numeroConta, valor))
				conta = service.sacar(numeroConta, valor);
			return new ResponseEntity<Conta>(conta, HttpStatus.OK);
		}
		return new ResponseEntity<Conta>(conta, HttpStatus.NOT_ACCEPTABLE);
	}

	@PutMapping("/transferir")
	public ResponseEntity<Conta> transferir(@RequestBody HashMap<String, Object> map) {
		int numeroConta = (int) map.get("numeroConta");
		double valor = (double) map.get("valor");
		int numeroContaDestino = (int) map.get("numeroContaDestino");
		Conta conta = null;
		if (service.validaValorPositivo(valor)) {
			if (service.verificarSaldo(numeroConta, valor)) {
				conta = service.transferir(numeroConta, valor, numeroContaDestino);
				return new ResponseEntity<Conta>(conta, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Conta>(conta, HttpStatus.NOT_ACCEPTABLE);
	}

	@GetMapping("/extrato")
	public ResponseEntity<Conta> obterExtrato(@RequestBody HashMap<String, Object> map) {
		int numeroConta = (int) map.get("numero");
		Conta conta = service.getConta(numeroConta);
		if (conta == null) {
			return new ResponseEntity<Conta>(conta, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Conta>(conta, HttpStatus.OK);
	}

	@GetMapping("/extrato/{id}")
	public ResponseEntity<Conta> obterExtratoId(@PathVariable Long id) {
		return buscarConta(id);
	}
}
