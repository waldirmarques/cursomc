package com.br.waldir;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.waldir.domain.Categoria;
import com.br.waldir.domain.Cidade;
import com.br.waldir.domain.Cliente;
import com.br.waldir.domain.Endereco;
import com.br.waldir.domain.Estado;
import com.br.waldir.domain.Pagamento;
import com.br.waldir.domain.PagamentoComBoleto;
import com.br.waldir.domain.PagamentoComCartao;
import com.br.waldir.domain.Pedido;
import com.br.waldir.domain.Produto;
import com.br.waldir.domain.enums.EstadoPagamento;
import com.br.waldir.domain.enums.TipoCliente;
import com.br.waldir.repositories.CategoriaRepository;
import com.br.waldir.repositories.CidadeRepository;
import com.br.waldir.repositories.ClienteRepository;
import com.br.waldir.repositories.EnderecoRepository;
import com.br.waldir.repositories.EstadoRepository;
import com.br.waldir.repositories.PagamentoRepository;
import com.br.waldir.repositories.PedidoRepository;
import com.br.waldir.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired // Alto instancia a classe sem precisar do new.
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	PagamentoRepository pagamentoRepository;

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
		
		Cliente cli1 = new Cliente(null,"Waldir Marques","waldir.marques@dce.ufpb.br","4143532532",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("634634414","674735634"));
		
		//Parou na aula 22
		//Cliente cli2 = new Cliente(null,"Gustavo","gustavo.venezes@dce.ufpb.br","351423545",TipoCliente.PESSOAFISICA);
		
		Endereco e1 = new Endereco(null, "sertão", "313", "centro", "Perto a faculdade", "580000000",cli1,c1);
		Endereco e2 = new Endereco(null, "vida loka", "754", "são josé", "Perto a escola", "580573457",cli1,c2);
		
		cli1.getEndereco().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1)); 
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	}

}
