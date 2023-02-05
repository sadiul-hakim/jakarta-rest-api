package com.hakim.resources.client;

import com.hakim.entities.client.Client;
import com.hakim.entities.client.ClientBean;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author Hakim
 */
@Path("/clients")
public class AccessClient {
    @Inject
    private ClientBean bean;
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Client> getClients(){
        List<Client>list= bean.getAllClient();
        list.forEach(c->c.setPassword(""));
        return list;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Client getClient(@PathParam("id") long id){
        Client client= bean.getClient(id);
        client.setPassword("");
        return client;
    }
}
