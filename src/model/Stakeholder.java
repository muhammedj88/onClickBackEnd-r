package model;

import java.io.Serializable;
import javax.persistence.*;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.util.List;


/**
 * The persistent class for the stakeholder database table.
 * 
 */
@Entity
@NamedQuery(name="Stakeholder.findAll", query="SELECT s FROM Stakeholder s")
public class Stakeholder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="stakeholder_id")
	private int stakeholderId;

	private String face;

	private String name;

	//bi-directional many-to-one association to TaskProject
	@OneToMany(mappedBy="stakeholder")
	@XmlInverseReference(mappedBy="stakeholder")
	private List<TaskProject> taskProjects;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="stakeholder")
	@XmlInverseReference(mappedBy="stakeholder")
	private List<Task> tasks;

	public Stakeholder() {
	}

	public int getStakeholderId() {
		return this.stakeholderId;
	}

	public void setStakeholderId(int stakeholderId) {
		this.stakeholderId = stakeholderId;
	}

	public String getFace() {
		return this.face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TaskProject> getTaskProjects() {
		return this.taskProjects;
	}

	public void setTaskProjects(List<TaskProject> taskProjects) {
		this.taskProjects = taskProjects;
	}

	public TaskProject addTaskProject(TaskProject taskProject) {
		getTaskProjects().add(taskProject);
		taskProject.setStakeholder(this);

		return taskProject;
	}

	public TaskProject removeTaskProject(TaskProject taskProject) {
		getTaskProjects().remove(taskProject);
		taskProject.setStakeholder(null);

		return taskProject;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setStakeholder(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setStakeholder(null);

		return task;
	}

}