package br.edu.infnet.ACME.repository;





import org.springframework.data.repository.CrudRepository;

import br.edu.infnet.ACME.model.Cotacao;


public interface CotacaoRepository extends CrudRepository<Cotacao, Integer>{
	

  // public List<Cotacao> listAllbyProdutoId(Integer produtoId);
	
	
}
