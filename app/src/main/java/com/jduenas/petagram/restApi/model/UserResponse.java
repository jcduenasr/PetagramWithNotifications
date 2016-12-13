package com.jduenas.petagram.restApi.model;

import com.jduenas.petagram.pojo.User;

import java.util.ArrayList;

/**
 * Created by jduenas on 13/12/2016.
 */

public class UserResponse {

    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
