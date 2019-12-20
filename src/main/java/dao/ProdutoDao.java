package dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.ItemTabela;
import model.Produto;
import util.JPAUtil;

public class ProdutoDao {

	public ItemTabela searchItem(String codeBar) {

		ItemTabela itemTabela = new ItemTabela(-1, "Vazio", "Vazia");

		String jpql = "SELECT new model.ItemTabela(p.id, p.nome, p.marca) FROM Produto p where p.codigoBarras like :pCodigoBarras";

		EntityManager manager = new JPAUtil().getEntityManagerInstance();
		manager.getTransaction().begin();

		TypedQuery<ItemTabela> query = manager.createQuery(jpql, ItemTabela.class);
		query.setParameter("pCodigoBarras", codeBar);

		try {
			itemTabela = query.getSingleResult();
			itemTabela.setQuantidade(1);
		} catch (NoResultException nre) {
			nre.printStackTrace();
		}

		manager.getTransaction().commit();
		manager.close();

		return itemTabela;
	}

	public boolean createItem(Produto produto) {

		boolean result = true;

		try {
			EntityManager manager = new JPAUtil().getEntityManagerInstance();
			manager.getTransaction().begin();

			manager.persist(produto);

			manager.getTransaction().commit();
			manager.close();
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}

		return result;
	}

}
