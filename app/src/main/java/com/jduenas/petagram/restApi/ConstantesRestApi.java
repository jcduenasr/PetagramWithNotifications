package com.jduenas.petagram.restApi;

/**
 * Created by jduenas on 12/12/2016.
 */

public final class ConstantesRestApi {

    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "4247312755.f8d586e.a82ea0e2fdb342c3b8ebbc2df86c37c2";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String KEY_GET_RECENT_MEDIA_USER_ID = "users/{user-id}/media/recent/";
    public static final String KEY_GET_FOLLOWEDS_SELF = "users/self/followed-by";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER+KEY_ACCESS_TOKEN+ACCESS_TOKEN;
    public static final String URL_GET_RECENT_MEDIA_USER_ID = KEY_GET_RECENT_MEDIA_USER_ID+KEY_ACCESS_TOKEN+ACCESS_TOKEN;

    public static final String URL_GET_FOLOWEDS_SELF = KEY_GET_FOLLOWEDS_SELF+KEY_ACCESS_TOKEN+ACCESS_TOKEN;

    public static final String URL_SEARCH_USER = "users/search?";
}
