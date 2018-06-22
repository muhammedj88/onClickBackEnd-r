package model;

import java.io.Serializable;
import javax.persistence.*;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.util.List;


/**
 * The persistent class for the milestone database table.
 * 
 */
@Entity
@NamedQuery(name="Milestone.findAll", query="SELECT m FROM Milestone m")
public class Milestone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="milestone_id")
	private int milestoneId;

	private String name;

	private int percentage;

	//bi-directional many-to-one association to MilestoneProject
	@OneToMany(mappedBy="milestone")
	@XmlInverseReference(mappedBy="milestone")
	private List<MilestoneProject> milestoneProjects;

	//bi-directional many-to-one association to Task
	@OneToMany(mappedBy="milestone")
	@XmlInverseReference(mappedBy="milestone")
	private List<Task> tasks;

	public Milestone() {
	}

	public int getMilestoneId() {
		return this.milestoneId;
	}

	public void setMilestoneId(int milestoneId) {
		this.milestoneId = milestoneId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPercentage() {
		return this.percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public List<MilestoneProject> getMilestoneProjects() {
		return this.milestoneProjects;
	}

	public void setMilestoneProjects(List<MilestoneProject> milestoneProjects) {
		this.milestoneProjects = milestoneProjects;
	}

	public MilestoneProject addMilestoneProject(MilestoneProject milestoneProject) {
		getMilestoneProjects().add(milestoneProject);
		milestoneProject.setMilestone(this);

		return milestoneProject;
	}

	public MilestoneProject removeMilestoneProject(MilestoneProject milestoneProject) {
		getMilestoneProjects().remove(milestoneProject);
		milestoneProject.setMilestone(null);

		return milestoneProject;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task addTask(Task task) {
		getTasks().add(task);
		task.setMilestone(this);

		return task;
	}

	public Task removeTask(Task task) {
		getTasks().remove(task);
		task.setMilestone(null);

		return task;
	}

}