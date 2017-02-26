package com.goforer.base.presentation.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseModel {
    public static GsonBuilder gsonBuilder() {
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();

        return builder;
    }

    public static Gson gson(){
        GsonBuilder builder = BaseModel.gsonBuilder();

        return builder.create();
    }
}
