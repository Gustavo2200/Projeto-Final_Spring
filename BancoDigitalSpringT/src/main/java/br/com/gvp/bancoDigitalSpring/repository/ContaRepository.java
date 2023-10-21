package br.com.gvp.bancoDigitalSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gvp.bancoDigitalSpring.entity.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
