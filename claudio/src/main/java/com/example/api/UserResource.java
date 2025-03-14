package TestJava.claudio.src.main.java.com.example.api;

import TestJava.claudio.src.main.java.com.example.dao.UserDAO;
import TestJava.claudio.src.main.java.com.example.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/users")
public class UserResource {
    
    private UserDAO userDAO = new UserDAO();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> users = userDAO.getAllUsers();
        return Response.ok(users).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id) {
        Optional<User> user = userDAO.getUserById(id);
        
        if (user.isPresent()) {
            return Response.ok(user.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(
            @FormParam("name") String name,
            @FormParam("email") String email) {
        
        if (name == null || email == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        boolean success = userDAO.createUser(name, email);
        
        if (success) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}