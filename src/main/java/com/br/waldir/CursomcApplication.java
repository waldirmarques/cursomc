package com.br.waldir;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.waldir.domain.Categoria;
import com.br.waldir.domain.Cidade;
import com.br.waldir.domain.Estado;
import com.br.waldir.domain.Produto;
import com.br.waldir.repositories.CategoriaRepository;
import com.br.waldir.repositories.CidadeRepository;
import com.br.waldir.repositories.EstadoRepository;
import com.br.waldir.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired //Alto instancia a classe sem precisar do new.
	private CategoriaRepository categoriaRepository;
	@Autowired 
	private ProdutoRepository produtoRepository;
	@Autowired 
	private EstadoRepository estadoRepository;
	@Autowired 
	private CidadeRepository cidadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritorio");
		
		Produto pro1 = new Produto (null,"Computador",2200.000);
		Produto pro2 = new Produto (null,"Impressora",800.00);
		Produto pro3 = new Produto (null,"Mause",15.0);		
		
		cat1.getProtudos().addAll(Arrays.asList(pro1,pro2,pro3));
		cat2.getProtudos().addAll(Arrays.asList(pro2));
		
		pro1.getCategorias().addAll(Arrays.asList(cat1));
		pro2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		pro3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(pro1,pro2,pro3));
		
		Estado s1 = new Estado(null,"Minas Gerais");
		Estado s2 = new Estado(null, "São Paula");
		
		
		Cidade c1 = new Cidade(null,"Uberlândia",s1);
		Cidade c2 = new Cidade(null,"São Paulo",s2);
		Cidade c3 = new Cidade(null,"Campinas",s2);
		
		s1.getCidades().addAll(Arrays.asList(c1));
		s2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(s1,s2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
	}

}
