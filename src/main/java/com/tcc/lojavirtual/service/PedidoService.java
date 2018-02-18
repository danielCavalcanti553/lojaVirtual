package com.tcc.lojavirtual.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tcc.lojavirtual.domain.Cliente;
import com.tcc.lojavirtual.domain.ItemPedido;
import com.tcc.lojavirtual.domain.Pedido;
import com.tcc.lojavirtual.repository.ClienteRepository;
import com.tcc.lojavirtual.repository.ItemPedidoRepository;
import com.tcc.lojavirtual.repository.PagamentoRepository;
import com.tcc.lojavirtual.repository.PedidoRepository;
import com.tcc.lojavirtual.repository.ProdutoRepository;
import com.tcc.lojavirtual.security.UserSecurity;
import com.tcc.lojavirtual.service.exception.AuthorizationException;
import com.tcc.lojavirtual.service.exception.NotFoundObjectException;;
@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired // Configurado com @Bean em ProfileTestConfig
	private EmailService emailService; 
	
	public Pedido find(Integer id) {
		Pedido obj = pedidoRepository.findOne(id);
		
		if(obj==null) {
			throw new NotFoundObjectException("Pedido Não encontrado! " +
					id);
			}
		
		return obj;
	}
	
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	public Pedido insert(Pedido obj) {
		
		obj.setCodigoPedido(null);
		obj.setDataPedido(new Date());
		obj.getPagamento().setDataPagamento(new Date());		
		obj.getPagamento().setPedido(obj);
		obj.setCliente(clienteRepository.findOne(obj.getCliente().getCodigoCliente()));
		obj = pedidoRepository.save(obj);

		for(ItemPedido ip : obj.getItens()) {
			ip.setProduto(produtoRepository.findOne(ip.getProduto().getCodigoProduto()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setQuantidade(ip.getQuantidade());
			ip.setPedido(obj);
		}
		
		obj.getPagamento().setValor(obj.getValorTotal());
		
		pagamentoRepository.save(obj.getPagamento());
		itemPedidoRepository.save(obj.getItens());
		
		emailService.sendConfirmationPedidoEmail(obj);
		
		return obj;
	}
	
	// Obtem pedidos paginados do usuário logado
	public Page<Pedido> findPage(Integer page, Integer linesPage, String order, String direction){
		
		UserSecurity user = UserService.authenticated(); 
		if(user==null) throw new AuthorizationException("Acesso Negado");
		
		PageRequest pageRequest = new PageRequest(page, linesPage, Direction.valueOf(direction),order);
		
		Cliente cliente = clienteRepository.findOne(user.getId());
		
		return pedidoRepository.findByCliente(cliente, pageRequest);
		
	}
	
}
