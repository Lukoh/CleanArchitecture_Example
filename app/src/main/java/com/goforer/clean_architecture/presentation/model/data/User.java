package com.goforer.clean_architecture.presentation.model.data;


import android.os.Parcel;
import android.os.Parcelable;

import com.goforer.base.presentation.model.BaseModel;
import com.google.gson.annotations.SerializedName;

public class User extends BaseModel implements Parcelable {
    @SerializedName("id")
    private long mId;
    @SerializedName("avatar_url")
    private String mAvatarUrl;
    @SerializedName("gravatar_id")
    private String mAvatarId;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("name")
    private String mName;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("company")
    private String mCompany;
    @SerializedName("blog")
    private String mBlog;
    @SerializedName("location")
    private String mLocation;
    @SerializedName("public_repos")
    private int mReposCount;
    @SerializedName("followers")
    private int mFollowers;
    @SerializedName("following")
    private int mFollowing;

    public User() {
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getAvartarUrl() {
        return mAvatarUrl;
    }

    public void setAvartarUrl(String avartarUrl) {
        mAvatarUrl = avartarUrl;
    }

    public String getAvartarId() {
        return mAvatarId;
    }

    public void setAvartarId(String avartarId) {
        mAvatarId = avartarId;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String company) {
        mCompany = company;
    }

    public String getBlog() {
        return mBlog;
    }

    public void setBlog(String blog) {
        mBlog = blog;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public int getRepositoryCount() {
        return mReposCount;
    }

    public void setRepositoryCount(int reposCount) {
        mReposCount = reposCount;
    }

    public int getFollowing() {
        return mFollowing;
    }

    public void setFollowing(int following) {
        mFollowing = following;
    }

    public int getFollowers() {
        return mFollowers;
    }

    public void setFollowers(int followers) {
        mFollowers = followers;
    }

    protected User(Parcel in) {
        mId = in.readLong();
        mAvatarUrl = in.readString();
        mAvatarId = in.readString();
        mUrl = in.readString();
        mName = in.readString();
        mEmail = in.readString();
        mCompany = in.readString();
        mBlog = in.readString();
        mLocation = in.readString();
        mReposCount = in.readInt();
        mFollowers = in.readInt();
        mFollowing = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mAvatarUrl);
        dest.writeString(mAvatarId);
        dest.writeString(mUrl);
        dest.writeString(mName);
        dest.writeString(mEmail);
        dest.writeString(mCompany);
        dest.writeString(mBlog);
        dest.writeString(mLocation);
        dest.writeInt(mReposCount);
        dest.writeInt(mFollowers);
        dest.writeInt(mFollowing);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}