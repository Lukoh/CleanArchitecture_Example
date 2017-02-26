package com.goforer.github_clean_architecture_mvp.repository.communicator;

import com.goforer.github_clean_architecture_mvp.presentation.model.data.Repository;
import com.goforer.github_clean_architecture_mvp.presentation.model.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RequestMethod {
    @GET("/users/{name}")
    Call<User> getUser(
            @Path("name") String name
    );

    @GET("/users/{name}/repos")
    Call<List<Repository>> getRepository(
            @Path("name") String name
    );
}
