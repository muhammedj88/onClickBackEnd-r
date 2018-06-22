package model;

import java.io.Serializable;
import javax.persistence.*;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.util.List;


/**
 * The persistent class for the tasks database table.
 * 
 */
@Entity
@Table(name="tasks")
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="task_id")
	private int taskId;

	@Column(name="area_names")
	private String areaNames;

	@Column(name="area_subname")
	private String areaSubname;

	private String description;

	@Column(name="milestone_type")
	private String milestoneType;

	@Column(name="stage_percentage")
	private int stagePercentage;

	private String stakeholder1;

	@Column(name="task_type")
	private String taskType;

	//bi-directional many-to-one association to TaskProject
	@OneToMany(mappedBy="task")
	@XmlInverseReference(mappedBy="task")
	private List<TaskProject> taskProjects;

	//bi-directional many-to-one association to Milestone
	@ManyToOne
	@JoinColumn(name="milestone_id")
	private Milestone milestone;

	//bi-directional many-to-one association to Stakeholder
	@ManyToOne
	@JoinColumn(name="stakeholder_id")
	private Stakeholder stakeholder;

	public Task() {
	}

	public int getTaskId() {
		return this.taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getAreaNames() {
		return this.areaNames;
	}

	public void setAreaNames(String areaNames) {
		this.areaNames = areaNames;
	}

	public String getAreaSubname() {
		return this.areaSubname;
	}

	public void setAreaSubname(String areaSubname) {
		this.areaSubname = areaSubname;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMilestoneType() {
		return this.milestoneType;
	}

	public void setMilestoneType(String milestoneType) {
		this.milestoneType = milestoneType;
	}

	public int getStagePercentage() {
		return this.stagePercentage;
	}

	public void setStagePercentage(int stagePercentage) {
		this.stagePercentage = stagePercentage;
	}

	public String getStakeholder1() {
		return this.stakeholder1;
	}

	public void setStakeholder1(String stakeholder1) {
		this.stakeholder1 = stakeholder1;
	}

	public String getTaskType() {
		return this.taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public List<TaskProject> getTaskProjects() {
		return this.taskProjects;
	}

	public void setTaskProjects(List<TaskProject> taskProjects) {
		this.taskProjects = taskProjects;
	}

	public TaskProject addTaskProject(TaskProject taskProject) {
		getTaskProjects().add(taskProject);
		taskProject.setTask(this);

		return taskProject;
	}

	public TaskProject removeTaskProject(TaskProject taskProject) {
		getTaskProjects().remove(taskProject);
		taskProject.setTask(null);

		return taskProject;
	}

	public Milestone getMilestone() {
		return this.milestone;
	}

	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
	}

	public Stakeholder getStakeholder() {
		return this.stakeholder;
	}

	public void setStakeholder(Stakeholder stakeholder) {
		this.stakeholder = stakeholder;
	}

}