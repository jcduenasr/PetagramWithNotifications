package com.jduenas.petagram.restApi.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.jduenas.petagram.pojo.Mascota;
import com.jduenas.petagram.restApi.JsonKeys;
import com.jduenas.petagram.pojo.User;
import com.jduenas.petagram.restApi.model.MascotaResponse;
import com.jduenas.petagram.restApi.model.UserResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by jduenas on 13/12/2016.
 */

public class UserDeserializer implements JsonDeserializer<MascotaResponse> {
    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse userResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray userResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        userResponse.setMascotas(deserializerUserFromJson(userResponseData));
        return userResponse;
    }
    private ArrayList<Mascota> deserializerUserFromJson(JsonArray userResponseData){
        ArrayList<Mascota> users = new ArrayList<>();
        for (int i = 0; i < userResponseData.size(); i++) {
            JsonObject userResponseDataObject = userResponseData.get(i).getAsJsonObject();

            String id               = userResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String fullname         = userResponseDataObject.get(JsonKeys.USER_FULL_NAME).getAsString();
            String profile_picture  = userResponseDataObject.get(JsonKeys.PROFILE_PICTURE).getAsString();
            String username         = userResponseDataObject.get(JsonKeys.USERNAME).getAsString();


            Mascota currentUser = new Mascota();
            currentUser.setId(id);
            currentUser.setNombreCompleto(fullname);
            currentUser.setProfile_picture(profile_picture);
            currentUser.setUsername(username);

            users.add(currentUser);
        }
        return users;
    }
}
