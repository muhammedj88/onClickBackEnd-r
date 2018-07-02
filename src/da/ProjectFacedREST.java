
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import model.Milestone;
import model.MilestoneProject;
import model.Project;
import model.Task;
import model.TaskProject;

import org.joda.time.*;

@javax.inject.Singleton
@Path("project")
@Produces({ "application/json" })
public class ProjectFacedREST extends AbstractFacade<Project> {
	private EntityManager em;

	public ProjectFacedREST() {
		super(Project.class);
	}

	@PUT
	@Override
	@Consumes({ "application/xml", "application/json" })
	public Response edit(Project entity) {
		return super.edit(entity);
	}
	
	@PUT
	@Consumes({ "application/xml", "application/json" })
	@Path("add")
	public Project create2(Project project) {
		try {
			if (!getEntityManager().getTransaction().isActive()) {
				getEntityManager().getTransaction().begin();
			}
			getEntityManager().persist(project);
			getEntityManager().flush();
			
			EntityManagerHelper.commit();
			
			DateTime dateTime1 = new DateTime(project.getStartDate());
			DateTime dateTime2 = new DateTime(project.getEndDate());
			int weeksNumber = Weeks.weeksBetween(dateTime1,dateTime2).getWeeks();
			
			TaskFacedREST tasksRest = new TaskFacedREST();
			TaskProjectFacedREST taskProjrctRest = new TaskProjectFacedREST();
			List<Task> tasks = tasksRest.findAll();
			System.out.println(tasks.size());
			for (Task task : tasks) {
				if(task.getTaskType().equals(project.getType())) {
					TaskProject tp = new TaskProject();
					tp.setTask(task);
					tp.setProject(project);
					tp.setStatus(1);
					tp.setStakeholder(task.getStakeholder());
					tp.setWeek((int)Math.ceil(
							(((task.getMilestone().getStartPercentage()/100.0)) + 
							((task.getStagePercentage()/100.0)*(task.getMilestone().getPercentage()/100.0)))
							 * (double)weeksNumber));
					taskProjrctRest.create(tp);
				}
			}
			
			MilestoneFacedREST milestoneRest = new MilestoneFacedREST();
			List<Milestone> milestones = milestoneRest.findAll();
			MilestoneProjectFacedREST milestoneProjecrRest = new MilestoneProjectFacedREST();
			
			for (Milestone milestone : milestones) {
				MilestoneProject mp = new MilestoneProject();
				mp.setMilestone(milestone);
				mp.setProject(project);
				mp.setStartWeek((int)Math.ceil((milestone.getStartPercentage()/100.0) * (double)weeksNumber) + 1);
				mp.setEndWeek((int)Math.ceil(((milestone.getStartPercentage()/100.0) + (milestone.getPercentage()/100.0)) * (double)weeksNumber));
				milestoneProjecrRest.create(mp);
			}
			
			return project;
		} catch (Exception ex) {
			EntityManagerHelper.rollback();
			throw new WebApplicationException(ex, Response.Status.BAD_REQUEST);
		}
		
	}

	@DELETE
	@Path("remove/{id}")
	public Response remove(@PathParam("id") Integer id) {
		return super.remove(super.find(id));
	}

	@GET
	@Path("{id}")
	@Produces({ "application/json" })
	public Project find(@PathParam("id") Integer id) {
		return super.find(id);
	}

	@GET
	@Override
	@Produces({ "application/json" })
	public List<Project> findAll() {
		return super.findAll();
	}

	@GET
	@Path("{from}/{to}")
	@Produces({ "application/json" })
	public List<Project> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
