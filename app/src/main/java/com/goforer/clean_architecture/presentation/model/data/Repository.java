package com.goforer.clean_architecture.presentation.model.data;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.goforer.base.presentation.model.BaseModel;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Repository extends BaseModel implements Parcelable {
    public abstract long id();

    @Nullable
    public abstract String name();
    @Nullable
    public abstract Owner owner();
    @Nullable
    public abstract String description();
    @Nullable
    public abstract String url();
    @Nullable
    public abstract String homepage();

    public abstract int stargazers_count();

    public static Repository create(long id, @Nullable String name, @Nullable Owner owner,
                                    @Nullable String description, @Nullable String url,
                                    @Nullable String homepage, int stargazers_count) {
        return builder()
                .id(id)
                .name(name)
                .owner(owner)
                .description(description)
                .url(url)
                .homepage(homepage)
                .stargazers_count(stargazers_count)
                .build();
    }

    @SuppressWarnings("WeakerAccess")
    public static TypeAdapter<Repository> typeAdapter(Gson gson) {
        return new AutoValue_Repository.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_Repository.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(long id);
        public abstract Builder name(@Nullable String name);
        public abstract Builder owner(@Nullable Owner owner);
        public abstract Builder description(@Nullable String description);
        public abstract Builder url(@Nullable String url);
        public abstract Builder homepage(@Nullable String homepage);
        public abstract Builder stargazers_count(int count);
        public abstract Repository build();
    }
}
