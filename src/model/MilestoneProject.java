package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the milestone_project database table.
 * 
 */
@Entity
@Table(name="milestone_project")
@NamedQuery(name="MilestoneProject.findAll", query="SELECT m FROM MilestoneProject m")
public class MilestoneProject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="milestone_project_id")
	private int milestoneProjectId;

	@Column(name="end_week")
	private int endWeek;

	@Column(name="start_week")
	private int startWeek;

	//bi-directional many-to-one association to Milestone
	@ManyToOne
	@JoinColumn(name="milestone_id")
	private Milestone milestone;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;

	public MilestoneProject() {
	}

	public int getMilestoneProjectId() {
		return this.milestoneProjectId;
	}

	public void setMilestoneProjectId(int milestoneProjectId) {
		this.milestoneProjectId = milestoneProjectId;
	}

	public int getEndWeek() {
		return this.endWeek;
	}

	public void setEndWeek(int endWeek) {
		this.endWeek = endWeek;
	}

	public int getStartWeek() {
		return this.startWeek;
	}

	public void setStartWeek(int startWeek) {
		this.startWeek = startWeek;
	}

	public Milestone getMilestone() {
		return this.milestone;
	}

	public void setMilestone(Milestone milestone) {
		this.milestone = milestone;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}