  package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the client database table.
 * 
 */
@Entity
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="client_id")
	private int clientId;

	private String address;

	private String name;

	//bi-directional many-to-one association to Portfolio
	@ManyToOne
	@JoinColumn(name="portfolio_id")
	private Portfolio portfolio;

	
	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="client")
	@XmlInverseReference(mappedBy="project")
	private List<Project> projects;
	
	public Client() {
	}

	public int getClientId() {
		return this.clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Portfolio getPortfolio() {
		return this.portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}
	

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setClient(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setClient(null);

		return project;
	}

}