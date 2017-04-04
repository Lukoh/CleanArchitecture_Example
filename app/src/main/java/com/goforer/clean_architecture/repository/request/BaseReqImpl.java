package com.goforer.clean_architecture.repository.request;

import com.goforer.clean_architecture.dependencyinjection.component.network.DaggerGithubComponent;
import com.goforer.clean_architecture.dependencyinjection.component.network.DaggerNetworkComponent;
import com.goforer.clean_architecture.dependencyinjection.component.network.NetworkComponent;
import com.goforer.clean_architecture.dependencyinjection.module.network.GithubModule;
import com.goforer.clean_architecture.dependencyinjection.module.network.NetworkModule;
import com.goforer.clean_architecture.repository.netcarrier.CallMethod;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class BaseReqImpl {
    private static final String BASE_URL = "https://api.github.com";

    public static final String SUCCESS = "SUCCESS";

    @Inject
    Retrofit mRetrofit;

    @Inject
    CallMethod mCallMethod;

    BaseReqImpl() {
        NetworkComponent netComponent = DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(BASE_URL))
                .build();

        DaggerGithubComponent.builder().networkComponent(netComponent)
                .githubModule(new GithubModule())
                .build()
                .inject(this);
    }
}
