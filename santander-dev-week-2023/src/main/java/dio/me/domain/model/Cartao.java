package dio.me.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "tabela_cartao")
public class Cartao {
	 public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	 
	 @Column(unique = true)
	 private String numero;
	 
	 @Column(name = "limite_disponivel", precision = 13, scale = 2)
	    private BigDecimal limite;


}
