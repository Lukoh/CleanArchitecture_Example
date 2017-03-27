package com.goforer.clean_architecture.repository.request;

import com.goforer.clean_architecture.dagger.component.network.DaggerGithubComponent;
import com.goforer.clean_architecture.dagger.component.network.DaggerNetworkComponent;
import com.goforer.clean_architecture.dagger.component.network.NetworkComponent;
import com.goforer.clean_architecture.dagger.module.network.GithubModule;
import com.goforer.clean_architecture.dagger.module.network.NetworkModule;
import com.goforer.clean_architecture.repository.netcarrier.CallMethod;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class BaseReqImpl {
    public static final String BASE_URL = "https://api.github.com";

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
