package api; 

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response; 

@ApplicationPath("/") 
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON) 
public class RestApplication extends Application { 
	@GET  
    public Response sayHello() {     
        return Response.ok("Hello World desde el API REST",MediaType.APPLICATION_JSON).build();   
    } 
}
