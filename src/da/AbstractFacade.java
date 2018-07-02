package da;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * @author MyEclipse Web Service Tools
 */
public abstract class AbstractFacade<T> {
	private Class<T> entityClass;

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected abstract EntityManager getEntityManager();

	public Response create(T entity) {
		try {
			if (!getEntityManager().getTransaction().isActive()) {
				getEntityManager().getTransaction().begin();
			}
			getEntityManager().persist(entity);
			EntityManagerHelper.commit();
			return Response.created(null).entity("Entity created successfully")
					.build();
		} catch (Exception ex) {
			EntityManagerHelper.rollback();
			throw new WebApplicationException(ex, Response.Status.BAD_REQUEST);
		}
	}

	public Response edit(T entity) {
		try {
			if (!getEntityManager().getTransaction().isActive()) {
				getEntityManager().getTransaction().begin();
			}
			getEntityManager().merge(entity);
			EntityManagerHelper.commit();
			return Response.ok().entity("Entity edited successfully").build();
		} catch (Exception ex) {
			EntityManagerHelper.rollback();
			throw new WebApplicationException(ex, Response.Status.NOT_FOUND);
		}
	}

	public Response remove(T entity) {
		try {
			if (!getEntityManager().getTransaction().isActive()) {
				getEntityManager().getTransaction().begin();
			}
			getEntityManager().remove(getEntityManager().merge(entity));
			EntityManagerHelper.commit();
			return Response.ok().entity("Entity deleted successfully").build();
		} catch (Exception ex) {
			EntityManagerHelper.rollback();
			throw new WebApplicationException(ex, Response.Status.NOT_FOUND);
		}
	}

	public T find(Object id) {
		T obj = getEntityManager().find(entityClass, id);
		EntityManagerHelper.closeEntityManager();
		return obj;
	}

	public List<T> findAll() {
		CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		List<T> list = (List<T>) getEntityManager().createQuery(cq).getResultList();
		//EntityManagerHelper.closeEntityManager();
		return list;
	}

	public List<T> findRange(int[] range) {
		CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		TypedQuery<Object> q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return (List<T>) q.getResultList();
	}

	public int count() {
		CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		javax.persistence.Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}

}
