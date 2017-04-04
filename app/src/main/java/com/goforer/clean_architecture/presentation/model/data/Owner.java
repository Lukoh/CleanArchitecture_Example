package com.goforer.clean_architecture.presentation.model.data;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
@SuppressWarnings("WeakerAccess")
public abstract class Owner implements Parcelable {
    public abstract long id();

    @Nullable
    public abstract String avatar_url();
    @Nullable
    public abstract String repos_url();

    @SuppressWarnings("WeakerAccess")
    public static TypeAdapter<Owner> typeAdapter(Gson gson) {
        return new AutoValue_Owner.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_Owner.Builder();
    }

    public static Owner create(long id, @Nullable String avatar_url, @Nullable String repos_url) {
        return builder()
                .id(id)
                .avatar_url(avatar_url)
                .repos_url(repos_url)
                .build();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(long id);
        public abstract Builder avatar_url(@Nullable String url);
        public abstract Builder repos_url(@Nullable String url);
        public abstract Owner build();
    }
}
