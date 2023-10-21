package br.com.gvp.bancoDigitalSpring.controller;

import java.util.List;
import java.util.Optional;

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

import br.com.gvp.bancoDigitalSpring.entity.Cliente;
import br.com.gvp.bancoDigitalSpring.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	private ClienteService service;

	@PostMapping("/add")
	public ResponseEntity<Cliente> addCliente(@RequestBody Cliente cliente) {
		Cliente clienteCriado = service.addCliente(cliente);
		if (clienteCriado != null) {
			return new ResponseEntity<Cliente>(clienteCriado, HttpStatus.CREATED);
		}
		return new ResponseEntity<Cliente>(clienteCriado, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/listar")
	public ResponseEntity<List<Cliente>> listAll() {
		List<Cliente> clientes = service.listAll();
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}

	@GetMapping("/buscar/{id}")
	public ResponseEntity<Cliente> buscarClienteId(@PathVariable Long id) {
		Optional<Cliente> clienteOp = service.buscarClienteId(id);
		Cliente cliente = clienteOp.get();
		if (cliente != null) {
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteClienteById(@PathVariable Long id) {
		service.deleteClienteById(id);
	}

	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
		Cliente clienteAtualizado = service.atualizarClienteId(id, cliente);
		if (clienteAtualizado != null) {
			return new ResponseEntity<Cliente>(clienteAtualizado, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Cliente>(clienteAtualizado, HttpStatus.BAD_REQUEST);
	}

}
