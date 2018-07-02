package model;

import java.io.Serializable;
import javax.persistence.*;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;


/**
 * The persistent class for the system_project database table.
 * 
 */
@Entity
@Table(name="system_project")
@NamedQuery(name="SystemProject.findAll", query="SELECT s FROM SystemProject s")
public class SystemProject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="system_id")
	private int systemId;

	@Column(name="system_name")
	private String systemName;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="projects_id")
	@XmlInverseReference(mappedBy="projects_id")
	private Project project;

	public SystemProject() {
	}

	public int getSystemId() {
		return this.systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}