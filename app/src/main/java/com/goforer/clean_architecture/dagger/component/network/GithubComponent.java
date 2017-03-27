package com.goforer.clean_architecture.dagger.component.network;

import com.goforer.clean_architecture.dagger.annotation.scope.NetworkScope;
import com.goforer.clean_architecture.dagger.module.network.GithubModule;
import com.goforer.clean_architecture.repository.request.BaseReqImpl;

import dagger.Component;

@NetworkScope
@Component(
        dependencies = NetworkComponent.class,
        modules = GithubModule.class
)

@SuppressWarnings("WeakerAccess")
public interface GithubComponent {
    void inject(BaseReqImpl baseReqImpl);
}

