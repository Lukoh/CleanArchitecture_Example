package com.goforer.base.presentation.model;

import com.google.gson.JsonElement;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ListModel <T extends BaseModel> implements ParameterizedType {
    private Class<T> mWrapped;

    public ListModel(Class<T> wrapped) {
        mWrapped = wrapped;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return new Type[] { mWrapped};
    }

    @Override
    public Type getOwnerType() {
        return null;
    }

    @Override
    public Type getRawType() {
        return List.class;
    }

    public List<T> fromJson(JsonElement json) {
        return T.gson().fromJson(json, this);
    }

    @Deprecated
    public List<T> fromString(String jsonString) {
        return T.gson().fromJson(jsonString, this);
    }
}
