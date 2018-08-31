package com.daniil.newtonx.app;
import javax.json.*;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {

    private static RequestHandler requestHandler = null;

    private Map<String, JsonObject> database;
    private int nextId;

    private RequestHandler(){
        database = new HashMap<String, JsonObject>();
        nextId = 0;
        this.addNewUser("{\"firstName\": \"Daniil\", \"lastName\": \"isAGod\"}");
    }

    public static RequestHandler getInstance(){
        if(requestHandler == null){
            requestHandler = new RequestHandler();
        }
        return requestHandler;
    }

    public JsonArray fetchAllUsers(){
        JsonArrayBuilder response = Json.createArrayBuilder();
        for (String id : this.database.keySet()) {
            response.add(this.database.get(id));
        }
        return response.build();
    }

    public boolean addNewUser(String newUser){
        String id = this.getNextId();
        JsonObject inputData = Json.createReader(new StringReader(newUser)).readObject();
        JsonObjectBuilder entry = Json.createObjectBuilder();
        entry.add("id", id);
        inputData.entrySet().forEach(en ->
                    entry.add(en.getKey(), en.getValue())
                );
//        data.put("id", Json.createReader(new StringReader(this.getNextId())).read());
        this.database.put(id, entry.build());
        return true;
    }

    private String getNextId(){
        return Integer.toString(this.nextId++);
    }

    public JsonObject getUserById(String id) {
        if(!this.database.containsKey(id)) return null;
        else return this.database.get(id);
    }
}
