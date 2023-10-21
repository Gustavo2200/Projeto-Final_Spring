package br.com.gvp.bancoDigitalSpring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gvp.bancoDigitalSpring.entity.Conta;
import br.com.gvp.bancoDigitalSpring.repository.ContaRepository;

@Service
public class ContaService {
	@Autowired
	private ContaRepository repositorio;

	public Conta addConta(Conta conta) {
		if (validarNumeroConta(conta.getNumero())) {
			if (validarSenha(conta.getSenha())) {
				if (!verificarContaExiste(conta.getNumero())) {
					if (!veriicarExisteIdConta(conta.getIdConta())) {
						return repositorio.save(conta);
					}
				}
			}
		}
		return null;
	}

	public boolean veriicarExisteIdConta(Long id) {
		for (Conta c : repositorio.findAll()) {
			if (c.getIdConta() == id) {
				return true;
			}
		}
		return false;
	}

	public List<Conta> listAll() {
		return repositorio.findAll();
	}

	public Optional<Conta> buscarContaId(Long id) {
		return repositorio.findById(id);
	}

	public void deleteContaId(Long id) {
		Conta conta = buscarContaId(id).get();
		if (conta.getSaldo() == 0) {
			repositorio.deleteById(id);
		}
	}

	public boolean verificarContaExiste(int numeroConta) {
		List<Conta> contas = listAll();
		for (int i = 0; i < contas.size(); i++) {
			if (contas.get(i).getNumero() == numeroConta) {
				return true;
			}
		}
		return false;

	}

	public boolean verificarSaldo(int numeroConta, double valor) {
		for (Conta c : repositorio.findAll()) {
			if (c.getNumero() == numeroConta) {
				if (c.getSaldo() < valor) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean validaValorPositivo(double valor) {
		if (valor < 0) {
			return false;
		}
		return true;
	}

	public boolean validarNumeroConta(int numero) {
		if (numero < 10000 && numero > 99999) {
			return false;
		}
		return true;
	}

	public boolean validarSenha(String senha) {
		if (senha.length() != 4) {
			return false;
		}
		for (int i = 0; i < senha.length(); i++) {
			if (!senha.matches("[0-9]*")) {
				return false;
			}
		}
		return true;
	}

	public List<Conta> listarContasDoCliente(Long id) {
		List<Conta> contas = listAll();
		for (Conta c : repositorio.findAll()) {
			if (c.getIdCliente() != id) {
				contas.remove(c);
			}
		}
		return contas;
	}

	public Conta depositar(int numeroConta, double valor) {
		for (Conta c : repositorio.findAll()) {
			if (c.getNumero() == numeroConta) {
				c.depositar(valor);
				repositorio.save(c);
				return c;
			}
		}
		return null;

	}

	public Conta sacar(int numeroConta, double valor) {
		for (Conta c : repositorio.findAll()) {
			if (c.getNumero() == numeroConta) {
				c.sacar(valor);
				repositorio.save(c);
				return c;
			}
		}
		return null;
	}

	public Conta transferir(int numeroConta, double valor, int numeroContaDestino) {
		Conta conta = null;
		Conta contaDestino = null;
		for (Conta c : repositorio.findAll()) {
			if (c.getNumero() == numeroConta) {
				conta = c;
			} else if (c.getNumero() == numeroContaDestino) {
				contaDestino = c;
			}
		}
		conta.sacar(valor);
		repositorio.save(conta);
		contaDestino.depositar(valor);
		repositorio.save(contaDestino);
		return conta;
	}

	public Conta getConta(int numeroConta) {
		for (Conta c : repositorio.findAll()) {
			if (c.getNumero() == numeroConta) {
				return c;
			}
		}
		return null;
	}
}
