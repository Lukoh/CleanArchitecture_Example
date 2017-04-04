package com.goforer.clean_architecture.dependencyinjection.component.network;

import com.goforer.clean_architecture.dependencyinjection.annotation.scope.NetworkScope;
import com.goforer.clean_architecture.dependencyinjection.module.network.GithubModule;
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

