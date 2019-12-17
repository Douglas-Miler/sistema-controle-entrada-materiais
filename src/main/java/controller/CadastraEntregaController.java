package controller;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.EntregaDao;
import dao.ProdutoDao;
import javafx.collections.ObservableList;
import model.ClienteDTO;
import model.ItemTabela;

public class CadastraEntregaController {

	private String codeBar;
	private ProdutoDao produtoDao;
	private EntregaDao entregaDao;
	private BaseController baseController;

	public CadastraEntregaController() {
		produtoDao = new ProdutoDao();
		entregaDao = new EntregaDao();
		baseController = new BaseController();
	}

	public boolean inputCodeBarValidator(String codeBar) {

		boolean result = baseController.inputNumberValidator(codeBar);

		if(result)
			if(codeBar.startsWith("0"))
				this.codeBar = codeBar.substring(1);
			else
				this.codeBar = codeBar;
		
		return result;
	}

	public ItemTabela searchItemInDataBase() {
		return produtoDao.searchItem(this.codeBar);
	}

	public void createDeliveryInDatabase(ObservableList<ItemTabela> listOfTableItems, ClienteDTO clienteDTO,
			LocalDate deliveryDate) {
		deliveryDate = deliveryDate != null ? deliveryDate : LocalDate.now();
		entregaDao.createDelivery(listOfTableItems, clienteDTO, deliveryDate);
	}

	public boolean inputQuantityValidator(String quantity) {
		String nonNumbersCharacters = "[^0-9]";
		Pattern pattern = Pattern.compile(nonNumbersCharacters);
		Matcher matcher = pattern.matcher(quantity);

		if (!quantity.isEmpty())
			while (matcher.find())
				return false;
		else
			return false;

		return true;
	}

}
