package main;

import javafx.application.Application;
import util.JPAUtil;
import view.ProcuraCliente;

public class Main {

	public static void main(String[] args) {
		new JPAUtil().getEntityManagerInstance().close();
		Application.launch(ProcuraCliente.class, args);
	}

}
