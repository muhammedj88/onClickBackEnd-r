package model;

import java.io.Serializable;
import javax.persistence.*;


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

	@Column(name="start_percentage")
	private int startPercentage;

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

	public int getStartPercentage() {
		return this.startPercentage;
	}

	public void setStartPercentage(int startPercentage) {
		this.startPercentage = startPercentage;
	}

}