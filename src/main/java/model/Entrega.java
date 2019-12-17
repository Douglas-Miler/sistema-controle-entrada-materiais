package model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Entrega {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Calendar dataEntrega;
	
	@Column(nullable=false)
	private Integer quantidade;
	
	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Cliente cliente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Calendar dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Entrega [id=" + id + ", dataEntrega=" + dataEntrega + ", quantidade=" + quantidade + ", produto="
				+ produto + ", cliente=" + cliente + "]";
	}

}
