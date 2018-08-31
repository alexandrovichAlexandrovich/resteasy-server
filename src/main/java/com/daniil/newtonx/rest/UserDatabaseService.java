package com.daniil.newtonx.rest;

import com.daniil.newtonx.app.RequestHandler;
import com.daniil.newtonx.app.UserInfo;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class UserDatabaseService {

    private RequestHandler requestHandler = RequestHandler.getInstance();

    @GET
    @Path("/all")
    @Produces("application/json")
    public Response fetchAllUsers(){
        JsonArray body = requestHandler.fetchAllUsers();
        return Response.status(200).entity(body.toString()).build();
    }

    @GET
    @Path("/id/{id}")
    @Produces("application/json")
    public Response getUserById(@PathParam("id") String id){
        JsonObject body = requestHandler.getUserById(id);
        if (body == null) {
            return Response.status(400).build();
        }
        return Response.status(200).entity(body.toString()).build();
    }

    @POST
    @Path("/post")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createNewUser(UserInfo info) {
        if(info.isInvalidEntry()){
            return Response.status(400).build();
        }
        JsonObject response = requestHandler.addNewUser(info);
        return Response.status(200).header("Accept","application/json").entity(response.toString()).build();

    }
}
