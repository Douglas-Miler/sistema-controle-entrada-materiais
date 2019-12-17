package model;

public class ItemTabela {

	private Integer idProduto;
	private Integer indiceLista;
	private String nomeProduto;
	private String marcaProduto;
	private Integer quantidade;

	public ItemTabela(Integer idProduto, String nomeProduto, String marcaProduto) {
		this.idProduto = idProduto;
		this.nomeProduto = nomeProduto;
		this.marcaProduto = marcaProduto;
	}

	public Integer getIndiceLista() {
		return indiceLista;
	}

	public void setIndiceLista(Integer indiceLista) {
		this.indiceLista = indiceLista;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getMarcaProduto() {
		return marcaProduto;
	}

	public void setMarcaProduto(String marcaProduto) {
		this.marcaProduto = marcaProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

}
