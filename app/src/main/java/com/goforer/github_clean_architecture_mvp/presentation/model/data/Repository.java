package com.goforer.github_clean_architecture_mvp.presentation.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.goforer.base.presentation.model.BaseModel;
import com.google.gson.annotations.SerializedName;

public class Repository extends BaseModel implements Parcelable {
    public static final int LIST_TITLE_TYPE = 0;
    public static final int LIST_ITEM_TYPE = 1;

    @SerializedName("id")
    private long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("owner")
    private Owner mOwner;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("url")
    private String mUrl;
    @SerializedName("homepage")
    private String mHomepage;
    @SerializedName("stargazers_count")
    private int mStarCount;

    public Repository() {
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Owner get0wner() {
        return mOwner;
    }

    public void setOwner(Owner owner) {
        mOwner = owner;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getHomepage() {
        return mHomepage;
    }

    public void setHomepage(String homepage) {
        mHomepage = homepage;
    }

    public int getStarCount() {
        return mStarCount;
    }

    public void setStarCount(int starCount) {
        mStarCount = starCount;
    }

    protected Repository(Parcel in) {
        mId = in.readLong();
        mName = in.readString();
        mOwner = in.readParcelable(Owner.class.getClassLoader());
        mDescription = in.readString();
        mUrl = in.readString();
        mHomepage = in.readString();
        mStarCount = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mName);
        dest.writeParcelable(mOwner, flags);
        dest.writeString(mDescription);
        dest.writeString(mUrl);
        dest.writeString(mHomepage);
        dest.writeInt(mStarCount);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Repository> CREATOR
            = new Parcelable.Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };

    public final static class Owner implements Parcelable {
        @SerializedName("id")
        private long mId;
        @SerializedName("avatar_url")
        private String mAvartarUrl;
        @SerializedName("repos_url")
        private String mReposUrl;

        public long getId() {
            return mId;
        }

        public String getAvartarUrl() {
            return mAvartarUrl;
        }

        public String getReposUrl() {
            return mReposUrl;
        }

        protected Owner(Parcel in) {
            mId = in.readLong();
            mAvartarUrl = in.readString();
            mReposUrl = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(mId);
            dest.writeString(mAvartarUrl);
            dest.writeString(mReposUrl);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<Owner> CREATOR
                = new Parcelable.Creator<Owner>() {
            @Override
            public Owner createFromParcel(Parcel in) {
                return new Owner(in);
            }

            @Override
            public Owner[] newArray(int size) {
                return new Owner[size];
            }
        };
    }
}
