package com.tcc.lojavirtual.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ItemPedidoPk codigoItem = new ItemPedidoPk();

	private Integer quantidade;
	
	public ItemPedido(Pedido pedido, Produto produto, Integer quantidade) {
		codigoItem.setPedido(pedido);
		codigoItem.setProduto(produto);
		
		this.quantidade = quantidade;
	}
	public ItemPedido() {
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	@JsonIgnore
	public Pedido getPedido() {
		return codigoItem.getPedido();
	}
	
	public void setPedido(Pedido pedido) {
		codigoItem.setPedido(pedido);
	}
	
	public Produto getProduto() {
		return codigoItem.getProduto();
	}
	
	public void setProduto(Produto produto) {
		codigoItem.setProduto(produto);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoItem == null) ? 0 : codigoItem.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (codigoItem == null) {
			if (other.codigoItem != null)
				return false;
		} else if (!codigoItem.equals(other.codigoItem))
			return false;
		return true;
	}
	
	
	


	
	

	
}
