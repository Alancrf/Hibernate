package br.com.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import br.com.jpautil.JpaUtil;

public class GenericDAO<Entidade> {

	public void save(Entidade entidade) {
		//Usando o JpaUtils que foi criado em br.com.jpautil
		EntityManager entityManager = JpaUtil.getEntityManager();
		
		//Obtendo uma transa��o para o banco de dados (iniciando um processo para alguma opera��o (CRUD)
		EntityTransaction entityTransaction = entityManager.getTransaction();
		
		//Iniciando a transa��o (ativando)
		entityTransaction.begin();
		
		//Salvando no Banco de dados, (� preciso chamar o m�todo persist e passando a entidade recebida por par�metro)
		try {
			entityManager.persist(entidade);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entityTransaction.rollback();
		}
		
		//fazendo commit
		entityTransaction.commit();
		
		//fechando conex�o
		entityManager.close();
	}
	
	public List<Entidade> getListEntity(Class<Entidade> entidade){
	
		//Usando o JpaUtils que foi criado em br.com.jpautil
		EntityManager entityManager = JpaUtil.getEntityManager();
				
		//Obtendo uma transa��o para o banco de dados (iniciando um processo para alguma opera��o (CRUD)
		EntityTransaction entityTransaction = entityManager.getTransaction();
				
		//Iniciando a transa��o (ativando)
		entityTransaction.begin();
		
		@SuppressWarnings("unchecked")
		List<Entidade> listaEntidade = entityManager.createQuery("from "+entidade.getName()).getResultList();
				
		//fazendo commit
		entityTransaction.commit();
				
		//fechando conex�o
		entityManager.close();
		
		return listaEntidade;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void delete(Entidade entidade, long id) {
		
	
		//Usando o JpaUtils que foi criado em br.com.jpautil
		EntityManager entityManager = JpaUtil.getEntityManager();
						
		//Obtendo uma transa��o para o banco de dados (iniciando um processo para alguma opera��o (CRUD)
		EntityTransaction entityTransaction = entityManager.getTransaction();
						
		//Iniciando a transa��o (ativando)
		entityTransaction.begin();
		
		entidade = (Entidade) entityManager.find(entidade.getClass(), id);
		
		entityManager.remove(entidade);
		
		//fazendo commit
		entityTransaction.commit();
						
		//fechando conex�o
		entityManager.close();
		
	}
}
