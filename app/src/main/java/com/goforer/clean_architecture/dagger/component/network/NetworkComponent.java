package com.goforer.clean_architecture.dagger.component.network;

import com.goforer.clean_architecture.dagger.module.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(
        modules = NetworkModule.class
)

public interface NetworkComponent {
    // downstream components need these exposed
    // method name does not really matter
    Retrofit getInstance();
}
