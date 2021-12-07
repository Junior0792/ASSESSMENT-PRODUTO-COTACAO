package br.edu.infnet.ACME.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.ACME.model.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Integer>{
	

}
