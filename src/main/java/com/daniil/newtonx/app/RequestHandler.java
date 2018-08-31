package com.daniil.newtonx.app;
import javax.json.*;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton "database" manager using in-memory data structures.
 */
public class RequestHandler {

    private static RequestHandler requestHandler = null;

    private Map<String, JsonObject> database;
    private int nextId;

    private RequestHandler(){
        database = new HashMap<String, JsonObject>();
        nextId = 0;
    }

    public static RequestHandler getInstance(){
        if(requestHandler == null){
            requestHandler = new RequestHandler();
        }
        return requestHandler;
    }

    public JsonObject getUserById(String id) {
        if(!this.database.containsKey(id)) return null;
        else return this.database.get(id);
    }

    public JsonArray fetchAllUsers(){
        JsonArrayBuilder response = Json.createArrayBuilder();
        for (String id : this.database.keySet()) {
            response.add(this.database.get(id));
        }
        return response.build();
    }

    public JsonObject addNewUser(UserInfo newUser){
        String id = this.getNextId();
        JsonObjectBuilder newUserEntry = Json.createObjectBuilder();
        newUserEntry.add("id", id);
        StringReader sr = new StringReader(newUser.toString());
        JsonObject inputData = Json.createReader(sr).readObject();
        inputData.entrySet().forEach(en ->
                        newUserEntry.add(en.getKey(), en.getValue())
                );
        this.database.put(id, newUserEntry.build());
        return this.database.get(id);
    }

    private String getNextId(){
        return Integer.toString(this.nextId++);
    }
}
