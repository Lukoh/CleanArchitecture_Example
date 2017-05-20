package com.goforer.clean_architecture.repository.netcarrier;

import com.goforer.clean_architecture.repository.model.data.Repository;
import com.goforer.clean_architecture.repository.model.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CallMethod {
    @GET("/users/{name}")
    Call<User> getUser(
            @Path("name") String name
    );

    @GET("/users/{name}/repos")
    Call<List<Repository>> getRepository(
            @Path("name") String name
    );
}
