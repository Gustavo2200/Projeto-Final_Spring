package br.com.gvp.bancoDigitalSpring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Conta {
	@Id
	@Column(name = "Id")
	private Long idConta;
	@Column(name = "Saldo")
	private double saldo;
	@Column(name = "Senha")
	private String senha;
	@Column(name = "Numero")
	private int numero;
	@Column(name = "IdCliente")
	private Long idCliente;
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Long getIdConta() {
		return idConta;
	}
	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}
	public void depositar(double valor) {
	    saldo += valor;
	}
	public void sacar(double valor) {
		saldo -= valor;
	}

}
