package model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, nullable = false)
	private String cpf;

	@Column(nullable = false)
	private String nome;

	@Temporal(TemporalType.DATE)
	private Calendar dataNascimento;

	private String rg;

	private String endereco;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoHospedagem tipoHospedagem;

	@OneToMany(mappedBy = "cliente")
	private List<Entrega> entrega;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public TipoHospedagem getTipoHospedagem() {
		return tipoHospedagem;
	}

	public void setTipoHospedagem(TipoHospedagem tipoHospedagem) {
		this.tipoHospedagem = tipoHospedagem;
	}

	public List<Entrega> getEntrega() {
		return entrega;
	}

	public void setEntrega(List<Entrega> entrega) {
		this.entrega = entrega;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", rg="
				+ rg + ", endereco=" + endereco + ", tipoHospedagem=" + tipoHospedagem + ", entrega=" + entrega + "]";
	}

}
