package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, nullable = false)
	private String codigoBarras;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String marca;

	@Column(nullable = true)
	private String descricao;

	@Column(nullable = true)
	private Double peso;

	@Column(nullable = true)
	private Double volume;

	@Column(nullable = true)
	private Integer unidadesPorPacote;

	@OneToMany(mappedBy = "produto")
	private List<Entrega> entrega;

	public Produto() {

	}

	public Produto(String codigoBarras, String nome, String marca, String descricao, Double peso, Double volume,
			Integer unidadesPorPacote) {
		this.codigoBarras = codigoBarras;
		this.nome = nome;
		this.marca = marca;
		this.descricao = descricao;
		this.peso = peso;
		this.volume = volume;
		this.unidadesPorPacote = unidadesPorPacote;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Integer getUnidadesPorPacote() {
		return unidadesPorPacote;
	}

	public void setUnidadesPorPacote(Integer unidadesPorPacote) {
		this.unidadesPorPacote = unidadesPorPacote;
	}

	public List<Entrega> getEntrega() {
		return entrega;
	}

	public void setEntrega(List<Entrega> entrega) {
		this.entrega = entrega;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", codigoBarras=" + codigoBarras + ", nome=" + nome + ", marca=" + marca
				+ ", descricao=" + descricao + ", peso=" + peso + ", volume=" + volume + ", unidadesPorPacote="
				+ unidadesPorPacote + ", entrega=" + entrega + "]";
	}

}
