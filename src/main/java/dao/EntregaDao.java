package dao;

import java.time.LocalDate;
import java.util.Calendar;

import javax.persistence.EntityManager;

import javafx.collections.ObservableList;
import model.Cliente;
import model.ClienteDTO;
import model.Entrega;
import model.ItemTabela;
import model.Produto;
import util.JPAUtil;

public class EntregaDao {

	public void createDelivery(ObservableList<ItemTabela> listOfTableItems, ClienteDTO clienteDTO, LocalDate deliveryDate) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(deliveryDate.getYear(), deliveryDate.getMonthValue() - 1, deliveryDate.getDayOfMonth());
		
		for (ItemTabela itemTabela : listOfTableItems) {
			Entrega entrega = new Entrega();
			EntityManager manager = new JPAUtil().getEntityManagerInstance();
			
			manager.getTransaction().begin();
			
			entrega.setCliente(manager.find(Cliente.class, clienteDTO.getId()));
			entrega.setProduto(manager.find(Produto.class, itemTabela.getIdProduto()));
			entrega.setQuantidade(itemTabela.getQuantidade());
			entrega.setDataEntrega(calendar);

			manager.persist(entrega);
			manager.getTransaction().commit();

			manager.close();
		}

	}

}
