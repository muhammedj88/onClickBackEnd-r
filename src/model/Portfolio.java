 package model;

import java.io.Serializable;
import javax.persistence.*;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.util.List;


/**
 * The persistent class for the portfolio database table.
 * 
 */
@Entity
@NamedQuery(name="Portfolio.findAll", query="SELECT p FROM Portfolio p")
public class Portfolio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="portfolio_id") 
	private int portfolioId;

	private String name;

	//bi-directional many-to-one association to Client
	@OneToMany(mappedBy="portfolio")
	@XmlInverseReference(mappedBy="client")
	private List<Client> clients;

	public Portfolio() {
	}

	public int getPortfolioId() {
		return this.portfolioId;
	}

	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Client> getClients() {
		return this.clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Client addClient(Client client) {
		getClients().add(client);
		client.setPortfolio(this);

		return client;
	}

	public Client removeClient(Client client) {
		getClients().remove(client);
		client.setPortfolio(null);

		return client;
	}

}