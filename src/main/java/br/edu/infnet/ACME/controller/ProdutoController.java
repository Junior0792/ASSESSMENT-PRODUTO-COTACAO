package br.edu.infnet.ACME.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.edu.infnet.ACME.model.Produto;
import br.edu.infnet.ACME.repository.ProdutoRepository;
import br.edu.infnet.ACME.service.AmazonClientService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	 @Autowired
	 private ProdutoRepository produtoRepository;
	 @Autowired
	 private AmazonClientService amazonClientService;
	
	 
	 @RequestMapping(method = RequestMethod.GET)
	 public Iterable<Produto> findAll() {
		 
		 return produtoRepository.findAll();
	 }
	 
	 @RequestMapping(method = RequestMethod.POST)
	 public Produto save(@RequestBody Produto produto) {
		 
	     Produto p = produtoRepository.save(produto);
	     
	     return p;
	}
	 
	 @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	 public Optional<Produto> findById(@PathVariable Integer id) {
		 
		 return produtoRepository.findById(id);
	    } 
	 
	 @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	 public void delete(@PathVariable Integer id) {
		 
		 produtoRepository.deleteById(id);
	   }
	 
	 @RequestMapping(method = RequestMethod.PUT)
	 public Produto update(@RequestBody Produto produto) {

		 Produto p = produtoRepository.save(produto);

	     return p;
	    }
	    
	 @RequestMapping(method = RequestMethod.DELETE)
	 public void remove(@RequestBody Produto produto) {
		 
		 produtoRepository.delete(produto);

	    }

	 public List<String> listar(){
		 
		 return amazonClientService.listar();
		} 
	    
	 @PostMapping("upload/{id}")
     public String uploadFile(@RequestPart("file") MultipartFile file, @PathVariable("id") Integer id ) {
		 Produto produto = produtoRepository.findById(id).orElse(null);
		 String fileName = amazonClientService.save(file);
		 produto.setNomeFoto(fileName);
		 produtoRepository.save(produto);
			
		 return "Foto do produto" + file.getName() + "Salvo com sucesso!!!";
		}
	    
	    
	 @DeleteMapping("delete/{fileName}")
	 public String deleteFile(@PathVariable("fileName") String fileName) {
		 
		 amazonClientService.delete(fileName);
			
		 return "Foto do produto" + fileName + "Apagado com sucesso!!!";
		 
		}
	   
	 @RequestMapping(value = "baixar/{id}", method = RequestMethod.GET)
	 public HttpEntity<byte[]> downloadFile(@PathVariable("id") Integer id) throws IOException {
		 
		 Produto produto = produtoRepository.findById(id).orElse(null);
		 String fileName = produto.getNomeFoto();
		 
		 File arquivo = amazonClientService.download(fileName);
		 
		 byte[] bytes = Files.readAllBytes(arquivo.toPath());
		 
	     HttpHeaders headers = new HttpHeaders();

	     headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

	     headers.setContentLength(bytes.length);

	     headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+ fileName);

	     return new HttpEntity<>(bytes, headers);
    }	    
}