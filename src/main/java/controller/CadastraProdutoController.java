package controller;

import dao.ProdutoDao;
import model.Produto;

public class CadastraProdutoController {

	private ProdutoDao produtoDao;
	private BaseController baseController;
	private Produto produto;

	public CadastraProdutoController() {
		produtoDao = new ProdutoDao();
		baseController = new BaseController();
	}

	public boolean inputProductValidator(String codeBar, String productName, String productLabel, String description,
			String weight, String volume, String unity) {

		this.produto = new Produto();

		// CODIGO DE BARRAS
		boolean result = baseController.inputNumberValidator(codeBar);
		if (result)
			this.produto.setCodigoBarras(baseController.number);
		else
			return false;

		// NOME DO PRODUTO
		result = baseController.inputStringFieldHandler(productName);
		if (result)
			this.produto.setNome(this.baseController.name);
		else
			return false;

		// MARCA DO PRODUTO
		productLabel = productLabel.trim();
		if (!productLabel.isEmpty())
			this.produto.setMarca(productLabel.replaceAll("\\s+", " "));

		// DESCRICAO DO PRODUTO
		description = description.trim();
		if (!description.isEmpty())
			this.produto.setDescricao(description.replaceAll("\\s+", " "));

		// PESO DO PRODUTO
		result = baseController.inputNumberFloatingPointValidator(weight);
		if (result)
			this.produto.setPeso(Double.parseDouble(baseController.number));

		// VOLUME DO PRODUTO
		result = baseController.inputNumberFloatingPointValidator(volume);
		if (result)
			this.produto.setVolume(Double.parseDouble(baseController.number));
		
		// UNIDADES POR PACOTE
		result = baseController.inputNumberValidator(unity);
		if (result)
			this.produto.setUnidadesPorPacote(Integer.parseInt(baseController.number));

		return true;
	}

	public boolean insertProductInDatabase() {
		return produtoDao.createItem(this.produto);
	}
}
