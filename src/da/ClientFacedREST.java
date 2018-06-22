
package da;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import model.Client;

@javax.inject.Singleton
@Path("client")
@Produces({ "application/json" })
public class ClientFacedREST extends AbstractFacade<Client> {
	private EntityManager em;
	public ClientFacedREST() {
		super(Client.class);
	}	
	@PUT
	@Override
	@Consumes({ "application/xml", "application/json" })
	public Response edit(Client entity) {
		return super.edit(entity);
	}
	@DELETE
	@Path("remove/{id}")
	public Response remove(@PathParam("id") Integer id) {
		return super.remove(super.find(id));
	}
	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	public Client find(@PathParam("id") Integer id) {
		return super.find(id);
	}
	

	@GET
	@Override
	@Produces({ "application/json"})
	public List<Client> findAll() {
		return super.findAll();
	}

	@GET
	@Path("{from}/{to}")
	@Produces({ "application/json" })
	public List<Client> findRange(@PathParam("from") Integer from,
			@PathParam("to") Integer to) {
		return super.findRange(new int[] { from, to });
	}

	@GET
	@Path("count")
	@Produces("text/plain")
	public String countREST() {
		return String.valueOf(super.count());
	}

	@Override
	protected EntityManager getEntityManager() {
		em = EntityManagerHelper.getEntityManager();
		return em;
	}
	
}

