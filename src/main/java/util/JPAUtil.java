package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sistema_controle_entregas");
	
	public EntityManager getEntityManagerInstance() {
		return entityManagerFactory.createEntityManager();
	}
	
}
