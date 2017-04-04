package com.goforer.clean_architecture.presentation.model.data;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.goforer.base.presentation.model.BaseModel;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class User extends BaseModel implements Parcelable {
    public abstract long id();

    @Nullable
    public abstract String avatar_url();
    @Nullable
    public abstract String gravatar_id();
    @Nullable
    public abstract String url();
    @Nullable
    public abstract String name();
    @Nullable
    public abstract String email();
    @Nullable
    public abstract String company();
    @Nullable
    public abstract String blog();
    @Nullable
    public abstract String location();

    public abstract int public_repos();
    public abstract int followers();
    public abstract int following();

    public static User create(long id, @Nullable String avatar_url, @Nullable String gravatar_id,
                              @Nullable String url, @Nullable String name, @Nullable String email,
                              @Nullable String company, @Nullable String blog, @Nullable String location,
                              int public_repos, int followers, int following) {
        return builder()
                .id(id)
                .avatar_url(avatar_url)
                .gravatar_id(gravatar_id)
                .url(url)
                .name(name)
                .email(email)
                .company(company)
                .blog(blog)
                .location(location)
                .public_repos(public_repos)
                .followers(followers)
                .following(following)
                .build();
    }

    @SuppressWarnings("WeakerAccess")
    public static TypeAdapter<User> typeAdapter(Gson gson) {
        return new AutoValue_User.GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new AutoValue_User.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(long id);
        public abstract Builder avatar_url(@Nullable String url);
        public abstract Builder gravatar_id(@Nullable String id);
        public abstract Builder url(@Nullable String url);
        public abstract Builder name(@Nullable String name);
        public abstract Builder email(@Nullable String email);
        public abstract Builder company(@Nullable String company);
        public abstract Builder blog(@Nullable String blog);
        public abstract Builder location(@Nullable String location);
        public abstract Builder public_repos(int count);
        public abstract Builder followers(int followers);
        public abstract Builder following(int following);
        public abstract User build();
    }
}