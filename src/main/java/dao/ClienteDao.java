package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.ClienteDTO;
import util.JPAUtil;

public class ClienteDao {

	public List<ClienteDTO> getSearchResult(String name) {
		
		name = "%"+name+"%";
		
		List<ClienteDTO> names = new ArrayList<>();
		names.add(new ClienteDTO(-1, "Vazio"));
		
		EntityManager manager = new JPAUtil().getEntityManagerInstance();
		
		String jpql = "select new model.ClienteDTO(c.id, c.nome) from Cliente c where c.nome like :pName";
		
		TypedQuery<ClienteDTO> query = manager.createQuery(jpql, ClienteDTO.class);
		query.setParameter("pName", name);
		
		if(query.getResultList().size() != 0)
			names = query.getResultList();
		
		manager.close();
		
		return names;
	}

}
