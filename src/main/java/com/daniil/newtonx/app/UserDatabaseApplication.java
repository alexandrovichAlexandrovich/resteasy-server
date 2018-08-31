package com.daniil.newtonx.app;

import com.daniil.newtonx.rest.UserDatabaseService;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class UserDatabaseApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();

    public UserDatabaseApplication() {
        singletons.add(new UserDatabaseService());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
