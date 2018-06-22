package model;

import java.io.Serializable;
import javax.persistence.*;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="project_id")
	private int projectId;

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	private String type;

	//bi-directional many-to-one association to MilestoneProject
	@OneToMany(mappedBy="project")
	@XmlInverseReference(mappedBy="project")
	private List<MilestoneProject> milestoneProjects;

	//bi-directional many-to-one association to Client
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;

	//bi-directional many-to-one association to SystemProject
	@OneToMany(mappedBy="project")
	@XmlInverseReference(mappedBy="project")
	private List<SystemProject> systemProjects;

	//bi-directional many-to-one association to TaskProject
	@OneToMany(mappedBy="project")
	private List<TaskProject> taskProjects;

	public Project() {
	}

	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<MilestoneProject> getMilestoneProjects() {
		return this.milestoneProjects;
	}

	public void setMilestoneProjects(List<MilestoneProject> milestoneProjects) {
		this.milestoneProjects = milestoneProjects;
	}

	public MilestoneProject addMilestoneProject(MilestoneProject milestoneProject) {
		getMilestoneProjects().add(milestoneProject);
		milestoneProject.setProject(this);

		return milestoneProject;
	}

	public MilestoneProject removeMilestoneProject(MilestoneProject milestoneProject) {
		getMilestoneProjects().remove(milestoneProject);
		milestoneProject.setProject(null);

		return milestoneProject;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<SystemProject> getSystemProjects() {
		return this.systemProjects;
	}

	public void setSystemProjects(List<SystemProject> systemProjects) {
		this.systemProjects = systemProjects;
	}

	public SystemProject addSystemProject(SystemProject systemProject) {
		getSystemProjects().add(systemProject);
		systemProject.setProject(this);

		return systemProject;
	}

	public SystemProject removeSystemProject(SystemProject systemProject) {
		getSystemProjects().remove(systemProject);
		systemProject.setProject(null);

		return systemProject;
	}

	public List<TaskProject> getTaskProjects() {
		return this.taskProjects;
	}

	public void setTaskProjects(List<TaskProject> taskProjects) {
		this.taskProjects = taskProjects;
	}

	public TaskProject addTaskProject(TaskProject taskProject) {
		getTaskProjects().add(taskProject);
		taskProject.setProject(this);

		return taskProject;
	}

	public TaskProject removeTaskProject(TaskProject taskProject) {
		getTaskProjects().remove(taskProject);
		taskProject.setProject(null);

		return taskProject;
	}

}