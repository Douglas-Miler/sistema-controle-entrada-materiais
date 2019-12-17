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
			if (codeBar.startsWith("0"))
				this.produto.setCodigoBarras(codeBar.substring(1));
			else
				this.produto.setCodigoBarras(codeBar);
		else
			return false;

		// NOME DO PRODUTO
		result = baseController.inputStringFieldHandler(productName);
		if (result)
			this.produto.setNome(this.baseController.name);
		else
			return false;

		// MARCA DO PRODUTO
		result = baseController.inputStringFieldHandler(productLabel);
		if (result)
			this.produto.setMarca(this.baseController.name);
		else
			return false;

		// DESCRICAO DO PRODUTO
		if (!cleanSpaces(weight).isEmpty()) {
			result = baseController.inputStringFieldHandler(description);
			if (result)
				this.produto.setDescricao(this.baseController.name);
			else
				return false;
		}

		// PESO DO PRODUTO
		if (!cleanSpaces(weight).isEmpty()) {
			result = baseController.inputNumberFloatingPointValidator(weight);
			if (result)
				this.produto.setPeso(Double.parseDouble(weight));
			else
				return false;
		}

		// VOLUME DO PRODUTO
		if (!cleanSpaces(volume).isEmpty()) {
			result = baseController.inputNumberFloatingPointValidator(volume);
			if (result)
				this.produto.setVolume(Double.parseDouble(volume));
			else
				return false;
		}

		// UNIDADES POR PACOTE
		if (!cleanSpaces(unity).isEmpty()) {
			result = baseController.inputNumberValidator(unity);
			if (result)
				this.produto.setUnidadesPorPacote(Integer.parseInt(unity));
			else
				return false;
		}

		return true;
	}

	private String cleanSpaces(String string) {
		return string.trim().replaceAll("\\s+", " ");
	}

	public void insertProductInDatabase() {
		produtoDao.createItem(this.produto);
	}
}
