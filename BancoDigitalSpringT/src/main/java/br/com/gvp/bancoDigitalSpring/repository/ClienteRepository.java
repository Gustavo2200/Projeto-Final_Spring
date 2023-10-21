package br.com.gvp.bancoDigitalSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gvp.bancoDigitalSpring.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
