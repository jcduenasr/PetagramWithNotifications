package com.jduenas.petagram.restApi.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.jduenas.petagram.restApi.JsonKeys;
import com.jduenas.petagram.pojo.User;
import com.jduenas.petagram.restApi.model.UserResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by jduenas on 13/12/2016.
 */

public class UserDeserializer implements JsonDeserializer<UserResponse> {
    @Override
    public UserResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        UserResponse userResponse = gson.fromJson(json, UserResponse.class);
        JsonArray userResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        userResponse.setUsers(deserializerUserFromJson(userResponseData));
        return userResponse;
    }
    private ArrayList<User> deserializerUserFromJson(JsonArray userResponseData){
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < userResponseData.size(); i++) {
            JsonObject userResponseDataObject = userResponseData.get(i).getAsJsonObject();

            String id               = userResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String fullname         = userResponseDataObject.get(JsonKeys.USER_FULL_NAME).getAsString();
            String profile_picture  = userResponseDataObject.get(JsonKeys.PROFILE_PICTURE).getAsString();
            String username         = userResponseDataObject.get(JsonKeys.USERNAME).getAsString();


            User currentUser = new User();
            currentUser.setId(id);
            currentUser.setFull_name(fullname);
            currentUser.setProfile_picture(profile_picture);
            currentUser.setUsername(username);

            users.add(currentUser);
        }
        return users;
    }
}
