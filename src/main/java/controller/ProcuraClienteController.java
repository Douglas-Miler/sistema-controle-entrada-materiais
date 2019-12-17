package controller;

import java.util.List;

import dao.ClienteDao;
import model.ClienteDTO;

public class ProcuraClienteController {

	private String name;
	private ClienteDao clienteDao;
	private BaseController baseController;

	public ProcuraClienteController() {
		clienteDao = new ClienteDao();
		baseController = new BaseController();
	}
	
	public boolean inputStringFieldHandler(String name) {

		boolean result = baseController.inputStringFieldHandler(name);
		
		if(result)
			this.name = this.baseController.name;
		
		return result;
	}

	public List<ClienteDTO> getSearchResult() {
		return clienteDao.getSearchResult(this.name);
	}

}
