package com.goforer.clean_architecture.dependencyinjection.module.network;

import com.goforer.clean_architecture.dependencyinjection.annotation.scope.NetworkScope;
import com.goforer.clean_architecture.repository.netcarrier.CallMethod;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class GithubModule {
    private CallMethod mCallMethod;

    @Provides
    @NetworkScope
    CallMethod providesRequestMethod(Retrofit retrofit) {
        if (mCallMethod == null) {
            mCallMethod = retrofit.create(CallMethod.class);
        }

        return mCallMethod;
    }
}

