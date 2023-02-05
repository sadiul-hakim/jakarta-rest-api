package com.hakim.resources.client;

import com.hakim.entities.client.Client;
import com.hakim.entities.client.ClientBean;
import com.hakim.entities.client.LoginDTO;
import com.hakim.helper.Encryptor;
import com.hakim.resources.LoginResult;
import com.hakim.resources.RegisterResult;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Hakim
 */
@Path("/auth")
public class Authentication {

    @Inject
    private ClientBean clientBean;

    @Inject
    private Encryptor encryptor;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/register")
    public RegisterResult register(Client client) {
        String password = client.getPassword();
        String hash = encryptor.encypt(password);

        client.setPassword(hash);

        boolean isRegistered = clientBean.addClient(client);
        if (isRegistered) {
            return new RegisterResult(true, client.getEmail());
        } else {
            return new RegisterResult(false, null);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(LoginDTO dto) {
        System.out.println(dto);
        String password = dto.getPassword();
        String hash = encryptor.encypt(password);
        dto.setPassword(hash);

        Client client = clientBean.login(dto);

        if (client != null) {
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("id", client.getId() + "")
                    .add("loggedIn", "true")
                    .add("name", client.getFullname())
                    .add("email", client.getEmail()).build();
           
            
            return Response
                    .ok()
                    .entity(new LoginResult(true, client.getId(), client.getFullname(), client.getEmail(), client.getCountry()))
                    .cookie(new NewCookie("user", jsonObject.toString()))
                    .build();
            //return new LoginResult(true, client.getId(), client.getFullname(), client.getEmail(), client.getCountry());
        } else {
            return Response
                    .ok()
                    .entity(new LoginResult(false, null, null, null, null))
                    .build();
//            return new LoginResult(false, null, null, null, null);
        }
    }
}
