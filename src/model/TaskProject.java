package model;

import java.io.Serializable;
import javax.persistence.*;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the task_project database table.
 * 
 */
@Entity
@Table(name="task_project")
@NamedQuery(name="TaskProject.findAll", query="SELECT t FROM TaskProject t")
public class TaskProject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="task_project_id")
	private int taskProjectId;

	private int status;

	private int week;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="project_id")
	@XmlInverseReference(mappedBy="projectid")
	private Project project;

	//bi-directional many-to-one association to Stakeholder
	@ManyToOne
	@JoinColumn(name="stakeholder_id")
	private Stakeholder stakeholder;

	//bi-directional many-to-one association to Task
	@ManyToOne
	@JoinColumn(name="task_id")
	private Task task;

	public TaskProject() {
	}

	public int getTaskProjectId() {
		return this.taskProjectId;
	}

	public void setTaskProjectId(int taskProjectId) {
		this.taskProjectId = taskProjectId;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getWeek() {
		return this.week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Stakeholder getStakeholder() {
		return this.stakeholder;
	}

	public void setStakeholder(Stakeholder stakeholder) {
		this.stakeholder = stakeholder;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}