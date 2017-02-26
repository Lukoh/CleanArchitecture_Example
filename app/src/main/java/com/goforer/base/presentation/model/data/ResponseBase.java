package com.goforer.base.presentation.model.data;

public class ResponseBase {
    // HTTP Code : 200
    public static final String OK = "OK";
    // HTTP Code : 200
    public static final String NO_CONTENT = "NO_CONTENT";
    // HTTP Code : 400
    public static final String ERROR_INVALID_PAGE = "ERROR_INVALID_PAGE";
    // HTTP Code : 400
    public static final String ERROR_INVALID_APPID = "ERROR_INVALID_APPID";
    // HTTP Code : 400
    public static final String ERROR_INVALID_UID = "ERROR_INVALID_UID";
    // HTTP Code : 400
    public static final String ERROR_INVALID_DEVICE_ID = "ERROR_INVALID_DEVICE_ID";
    // HTTP Code : 400
    public static final String ERROR_INVALID_IP = "ERROR_INVALID_IP";
    // HTTP Code : 400
    public static final String ERROR_INVALID_TIMESTAMP = "ERROR_INVALID_TIMESTAMP";
    // HTTP Code : 400
    public static final String ERROR_INVALID_LOCALE = "ERROR_INVALID_LOCALE";
    // HTTP Code : 400
    public static final String ERROR_INVALID_ANDROID_ID = "ERROR_INVALID_ANDROID_ID";
    // HTTP Code : 400
    public static final String ERROR_INVALID_CATEGORY = "ERROR_INVALID_CATEGORY";
    // HTTP Code : 401
    public static final String ERROR_INVALID_HASHKEY = "ERROR_INVALID_HASHKEY";
    // HTTP Code : 404
    public static final String NOT_FOUND = "NOT_FOUND";
    // HTTP Code : 500
    public static final String ERROR_INTERNAL_SERVER = "ERROR_INTERNAL_SERVER_ERROR";
    // HTTP Code : 502
    public static final String BAD_GATEWAY = "BAD_GATEWAY";

    public static final int GENERAL_ERROR = -1;
    public static final int RESPONSE_SIGNATURE_NOT_MATCH = -2;
    public static final int NETWORK_ERROR = -3;
    public static final int SUCCESSFUL = 1;
}
