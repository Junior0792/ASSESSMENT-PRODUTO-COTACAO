package br.edu.infnet.ACME.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "produtos")

public class Produto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;
    @Column(name = "nome")
	private String nome;
    @Column(name = "categoria")
	private String categoria;
   // @Column(name = "valor")
    //private Integer valor;
    @OneToMany (cascade = {CascadeType.ALL}, mappedBy = "produto")
	private List<Cotacao>Cotacao;
	@Column(name = "nomeFoto")
	private String nomeFoto;
	

	public Produto() {
		
	}
	
	public Produto(Integer id, String nome, Integer valor) {
		this.id = id;
		this.nome = nome;
		//this.valor = valor;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNomeFoto() {
		return nomeFoto;
	}

	public void setNomeFoto(String nomeFoto) {
		this.nomeFoto = nomeFoto;
	}

	//public Integer getValor() {
	//	return valor;
//	}

//	public void setValor(Integer valor) {
		//this.valor = valor;
//	}

    public List<Cotacao> getCotacao() {
		return Cotacao;
	}

	public void setCotacao(List<Cotacao> cotacao) {
		Cotacao = cotacao;
	}

	//public void setValor(Integer valor) {
		//this.valor = valor;
	//}
	
	
	
}
