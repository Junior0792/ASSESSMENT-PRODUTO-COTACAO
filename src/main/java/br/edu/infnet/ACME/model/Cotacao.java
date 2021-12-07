package br.edu.infnet.ACME.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name ="cotacao")

public class Cotacao {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;
    @Column(name = "preço")
    private double preco;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProduto")
    private Produto produto;
    
    
    public Cotacao() {
    	
    }
    
    public Cotacao(Integer id, double preco) {
    	this.id = id;
    	this.preco = preco;
    }
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
    public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	
	
    
}
