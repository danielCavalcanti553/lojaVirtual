package com.tcc.lojavirtual.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcc.lojavirtual.domain.Categoria;
import com.tcc.lojavirtual.domain.Cliente;
import com.tcc.lojavirtual.domain.Historico;
import com.tcc.lojavirtual.domain.ItemPedido;
import com.tcc.lojavirtual.domain.Pagamento;
import com.tcc.lojavirtual.domain.Pedido;
import com.tcc.lojavirtual.domain.Produto;
import com.tcc.lojavirtual.domain.enums.Perfil;
import com.tcc.lojavirtual.domain.enums.TipoHistorico;
import com.tcc.lojavirtual.repository.CategoriaRepository;
import com.tcc.lojavirtual.repository.ClienteRepository;
import com.tcc.lojavirtual.repository.HistoricoRepository;
import com.tcc.lojavirtual.repository.ItemPedidoRepository;
import com.tcc.lojavirtual.repository.PagamentoRepository;
import com.tcc.lojavirtual.repository.PedidoRepository;
import com.tcc.lojavirtual.repository.ProdutoRepository;

@Service
public class ProfileTestDados{
	
	@Autowired
	private BCryptPasswordEncoder passCrypt;	
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private HistoricoRepository historicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;	
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	

	public void insereDados() throws Exception {
		
		Categoria cat1 = new Categoria(null,"Eletrodoméstivos");
		Categoria cat2 = new Categoria(null,"Informática");
		Categoria cat3 = new Categoria(null,"Móveis");
		Categoria cat4 = new Categoria(null,"Came, mesa e banho");
		Categoria cat5 = new Categoria(null,"Celulares");
		Categoria cat6 = new Categoria(null,"Eletrônicos");
		Categoria cat7 = new Categoria(null,"Escritório");
		
		Produto pro1 = new Produto(null,"Computador",3,200.00);
		Produto pro2 = new Produto(null,"Liquidificador",3,200.00);
		Produto pro3 = new Produto(null,"Celular Samsung",3,200.00);
		Produto pro4 = new Produto(null,"SmartTV",3,200.00);
		Produto pro5 = new Produto(null,"Ventilador",3,200.00);
		Produto pro6 = new Produto(null,"Guarda Roupa",3,200.00);
		Produto pro7 = new Produto(null,"Travesseiro",3,200.00);
		Produto pro8 = new Produto(null,"Cadeira Giratória",3,200.00);
		Produto pro9 = new Produto(null,"Celular LG",3,200.00);
		Produto pro10 = new Produto(null,"Poltrona",3,200.00);
		
		cat1.getProdutos().addAll(Arrays.asList(pro1));
		cat2.getProdutos().addAll(Arrays.asList(pro2));
		cat3.getProdutos().addAll(Arrays.asList(pro2,pro6,pro10));
		cat4.getProdutos().addAll(Arrays.asList(pro7));
		cat5.getProdutos().addAll(Arrays.asList(pro9,pro3));
		cat6.getProdutos().addAll(Arrays.asList(pro4,pro5));
		cat7.getProdutos().addAll(Arrays.asList(pro8));
		
		pro1.getCategorias().addAll(Arrays.asList(cat1));
		pro2.getCategorias().addAll(Arrays.asList(cat2,cat3));
		pro3.getCategorias().addAll(Arrays.asList(cat5));
		pro4.getCategorias().addAll(Arrays.asList(cat6));
		pro5.getCategorias().addAll(Arrays.asList(cat6));
		pro6.getCategorias().addAll(Arrays.asList(cat3));
		pro7.getCategorias().addAll(Arrays.asList(cat4));
		pro9.getCategorias().addAll(Arrays.asList(cat5));
		pro8.getCategorias().addAll(Arrays.asList(cat7));
		pro10.getCategorias().addAll(Arrays.asList(cat3));
		
		categoriaRepository.save(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		produtoRepository.save(Arrays.asList(pro1,pro2,pro3,pro4,pro5,pro6,pro7,pro8,pro9,pro10));
		
		Historico his1 = new Historico(null,new Date(),4,TipoHistorico.ENTRADA,pro1);
		Historico his2 = new Historico(null,new Date(),4,TipoHistorico.ENTRADA,pro2);
		Historico his3 = new Historico(null,new Date(),4,TipoHistorico.SAIDA,pro3);
		Historico his4 = new Historico(null,new Date(),4,TipoHistorico.ENTRADA,pro4);
		Historico his5 = new Historico(null,new Date(),4,TipoHistorico.ENTRADA,pro5);
		Historico his6 = new Historico(null,new Date(),4,TipoHistorico.SAIDA,pro6);
		Historico his7 = new Historico(null,new Date(),4,TipoHistorico.ENTRADA,pro7);
		Historico his8 = new Historico(null,new Date(),4,TipoHistorico.ENTRADA,pro8);
		historicoRepository.save(Arrays.asList(his1,his2,his3,his4,his5,his6,his7,his8));
		
		Cliente cli1 = new Cliente(null,"35314131390","Daniel Souza","Rua x", "Rio de Janeiro","RJ","(21)9821-0192","daniel@daniel.com",passCrypt.encode("123456"));
		cli1.setPerfis(Perfil.ADMIN);
		Cliente cli2 = new Cliente(null,"25093295884","Marcia Gomes","Rua y", "São Paulo","SP","(11)2133-2333","marcia@marcia.com",passCrypt.encode("123456"));
		clienteRepository.save(Arrays.asList(cli1,cli2));
		
		Pedido ped1 = new Pedido(null,new Date(),cli1);
		Pedido ped2 = new Pedido(null,new Date(),cli2);
		
		
		ItemPedido item1 = new ItemPedido(ped1,pro1,1,pro1.getPreco());
		ItemPedido item2 = new ItemPedido(ped1,pro2,1,pro2.getPreco());
		ItemPedido item3 = new ItemPedido(ped1,pro3,1,pro3.getPreco());
		ItemPedido item4 = new ItemPedido(ped2,pro1,1,pro1.getPreco());
		
		
		Pagamento pag1 = new Pagamento(null, new Date(), "1234123412341234", 10000, ped1);
		ped1.setPagamento(pag1);
		Pagamento pag2 = new Pagamento(null, new Date(), "1234123412341234", 500, ped2);
		ped2.setPagamento(pag2);		
		pedidoRepository.save(Arrays.asList(ped1,ped2));
		pagamentoRepository.save(Arrays.asList(pag1,pag2));
		
		
		
		ped1.getItens().addAll(Arrays.asList(item1,item2,item3));
		ped2.getItens().addAll(Arrays.asList(item4));
		//
		pro1.getItens().addAll(Arrays.asList(item1,item4));
		pro2.getItens().addAll(Arrays.asList(item2));
		pro3.getItens().addAll(Arrays.asList(item3));
		
		itemPedidoRepository.save(Arrays.asList(item1,item2,item3,item4));
		
		
	}
}
