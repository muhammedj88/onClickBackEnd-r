package config;

import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {
		register(da.ClientFacedREST.class);
		register(da.ProjectFacedREST.class);
		register(da.TaskFacedREST.class);
		register(da.TaskProjectFacedREST.class);
		register(da.MilestoneFacedREST.class);
		//register(config.CorsConfig.class);
		//register(da.SpecialFacedREST.class);
	}
}
