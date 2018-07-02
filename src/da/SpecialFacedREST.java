package da;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import model.Client;


@javax.inject.Singleton
@Path("Special")
@Produces({ "application/json" })
public class SpecialFacedREST {
	
	public SpecialFacedREST() {}
	
	@GET
	@Produces({ "application/json"})
	public String get() {
//		List<Actor> actors = new ArrayList<Actor>();
//      try {
//          Statement statement = conn.createStatement();
//          ResultSet resultSet = statement.executeQuery( "SELECT * FROM sakila.actor" );
//          while( resultSet.next() ) {
//          	   
//          	Actor actor = new Actor(
//              resultSet.getInt(      "actor_id"),
//              resultSet.getString(   "first_name"),
//              resultSet.getString(   "last_name"),
//              resultSet.getTimestamp("last_update" ) );
//              
//              actors.add(actor);
//          }
//          resultSet.close();
//          statement.close();
//      } catch (SQLException e) {
//      	System.out.println(e.getMessage());
//      }
//      return actors;
		return "terashrash";
//
	}

}
