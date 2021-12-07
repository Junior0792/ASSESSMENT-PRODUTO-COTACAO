package br.edu.infnet.ACME.controller;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.ACME.model.Cotacao;
import br.edu.infnet.ACME.model.Produto;
import br.edu.infnet.ACME.repository.CotacaoRepository;
import br.edu.infnet.ACME.repository.ProdutoRepository;



@RestController
@RequestMapping("/cotacao")
public class CotacaoController {
	
	@Autowired
    private ProdutoRepository produtoRepository;
	
	@Autowired
    private CotacaoRepository cotacaoRepository;

//	@Autowired
//	private ProdutoService service;
	
    @RequestMapping(method = RequestMethod.GET)
	public Iterable<Cotacao> findAll() {
    	
    	return cotacaoRepository.findAll();
    }
	 
	@RequestMapping(method = RequestMethod.POST)
	public Cotacao save(@RequestBody Cotacao cotacao) {
		
		Cotacao c = cotacaoRepository.save(cotacao);
	     
	    return c;
	}
	  
    @RequestMapping(method = RequestMethod.PUT)
	public Cotacao update(@RequestBody Cotacao cotacao) {
    	
    	Cotacao c = cotacaoRepository.save(cotacao);

	    return c;

	    }
	    
	@RequestMapping(method = RequestMethod.DELETE)
	public void remove(@RequestBody Cotacao cotacao) {
		
		cotacaoRepository.delete(cotacao);

	    }
 
	    
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<Cotacao> findById(Integer id) {
		
		return cotacaoRepository.findById(id);
	    }
	 
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@RequestBody Integer id) {
		
		cotacaoRepository.deleteById(id);
	   }
	 
	@RequestMapping( method = RequestMethod.POST, value = "registrar")
	public Produto registrarCotacao(@RequestParam("idProduto") Integer idProduto,
	@RequestParam("precoCotacao") Double precoCotacao) {

	Produto produto = produtoRepository.findById(idProduto).get();
	Cotacao cotacao = new Cotacao();
	cotacao.setProduto(produto);
	cotacao.setPreco(precoCotacao);

	cotacaoRepository.save(cotacao);

	return produto;
	
	}

//	@RequestMapping(value = "exportar", method = RequestMethod.GET)
//	public HttpEntity<byte[]> exportar(@PathVariable("id") Integer id) throws IOException {
//		
//		Produto produto = produtoRepository.findById(id).orElse(null);
//		
//        List<Cotacao>Cotacao = service.obterLista(id);
//        
//        String txt = "produto;cotacao\n";
//        
//        txt = txt + produto + ";" + Cotacao;
//        
//	    byte[] bytes = txt.getBytes();
//		 
//	    HttpHeaders headers = new HttpHeaders();
//
//	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//
//	    headers.setContentLength(bytes.length);
//
//	    headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=produto"+ ".csv");
//
//	    return new HttpEntity<>(bytes, headers);
//   }	    
//	
	
}

    
	

