package com.tcc.lojavirtual.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ItemPedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ItemPedidoPK codigoItemPedido = new ItemPedidoPK();
	
	private Integer quantidade;
	private Double preco;
	
	public ItemPedido() {
	}

	public ItemPedido(Pedido pedido, Produto produto,Integer quantidade, Double preco) {
		codigoItemPedido.setPedido(pedido);
		codigoItemPedido.setProduto(produto);
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public Pedido getPedido() {
		return codigoItemPedido.getPedido();
	}

	public void setCodigoPedido(Pedido pedido) {
		codigoItemPedido.setPedido(pedido);
	}

	public Produto getProduto() {
		return codigoItemPedido.getProduto();
	}

	public void setCodigoProduto(Produto produto) {
		codigoItemPedido.setProduto(produto);
	}
	
	public ItemPedidoPK getCodigoItemPedido() {
		return codigoItemPedido;
	}

	public void setCodigoItemPedido(ItemPedidoPK codigoItemPedido) {
		this.codigoItemPedido = codigoItemPedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
	
	
	
}
