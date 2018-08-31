package com.daniil.newtonx.rest;

import com.daniil.newtonx.app.RequestHandler;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/")
public class UserDatabaseService {

    RequestHandler requestHandler = RequestHandler.getInstance();


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
            return Response.status(400).entity("No user exits with that ID.").build();
        }
        return Response.status(200).entity(body.toString()).build();
    }
}
