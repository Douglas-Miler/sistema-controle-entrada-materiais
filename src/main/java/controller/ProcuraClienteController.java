package controller;

import java.util.List;

import dao.ClienteDao;
import model.ClienteDTO;

public class ProcuraClienteController {

	private ClienteDao clienteDao;
	private BaseController baseController;

	public ProcuraClienteController() {
		clienteDao = new ClienteDao();
		baseController = new BaseController();
	}

	public boolean inputStringFieldHandler(String name) {
		return baseController.inputStringFieldHandler(name);
	}

	public List<ClienteDTO> getSearchResult() {
		return clienteDao.getSearchResult(this.baseController.name);
	}

}
