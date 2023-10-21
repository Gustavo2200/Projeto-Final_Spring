package br.com.gvp.bancoDigitalSpring.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.gvp.bancoDigitalSpring.entity.Cliente;
import br.com.gvp.bancoDigitalSpring.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repositorio;

	public Cliente addCliente(Cliente cliente) {
		if(!validaNome(cliente.getNome())) {
			return null;
		}		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8081/validar/"+ cliente.getCpf();
		try {
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String>entity = new HttpEntity<>(null,headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity, String.class);
			 if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
	                return null;
	            } else {
	                return repositorio.save(cliente);
	            }
		}
		catch(Exception e) {
			return null;
		}
	}
	public boolean verificarIdClienteExiste(Long id) {
		for(Cliente c :repositorio.findAll()) {
			if (c.getId() == id) {
				return true;
			}
		}
		return false;
	}
	public List<Cliente> listAll() {
		return repositorio.findAll();
	}
	public boolean validaNome(String nome) {
		if (nome.equals("")) {
			return false;
		}
		for (int i = 0; i < nome.length(); i++) {
			if (!nome.substring(i).matches("[A-Z]*")) {
				return false;
			}
		}
		return true;
	}

	public boolean validarCPF(String cpf) {
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		if (cpf.length() != 11) {
			return false;
		}
		for (int i = 0; i < 11; i++) {
			if (!cpf.substring(i).matches("[0-9]*")) {
				return false;
			}
		}
		return true;
	}
	public Optional<Cliente> buscarClienteId (Long id) {
		return repositorio.findById(id);
	}
	public void deleteClienteById(Long id) {
		 repositorio.deleteById(id);
	}
	public Cliente atualizarClienteId(Long id,Cliente cliente) {
		Optional<Cliente> clienteOp = buscarClienteId(id);
		Cliente clienteExistente = clienteOp.get();
		if(cliente.getId()== clienteExistente.getId()) {
			if(validaNome(cliente.getNome())) {
				if(validarCPF(cliente.getCpf())) {
					clienteExistente.setNome(cliente.getNome());
					clienteExistente.setCpf(cliente.getCpf());
					clienteExistente.setIdade(cliente.getIdade());
					repositorio.save(clienteExistente);
					return clienteExistente;
				}
			}
		}
		return null;
		
	}
	
}
