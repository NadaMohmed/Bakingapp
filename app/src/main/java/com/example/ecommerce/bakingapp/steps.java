package com.example.ecommerce.bakingapp;

import android.os.Parcel;
import android.os.Parcelable;

public class steps implements Parcelable {
    int id ;
  private String shortDescription ;
   private String  description ;
  private String videoURL ;
    private String thumbnailURL ;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }


public steps (int id , String shortDescription, String description,String videoURL, String thumbnailURL)
{
    this.id = id ;
    this.shortDescription = shortDescription ;
    this.description = description ;
    this.videoURL = videoURL ;
    this.thumbnailURL = thumbnailURL ;

}


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.shortDescription);
        dest.writeString(this.description);
        dest.writeString(this.videoURL);
        dest.writeString(this.thumbnailURL);
    }

    protected steps(Parcel in) {
        this.id = in.readInt();
        this.shortDescription = in.readString();
        this.description = in.readString();
        this.videoURL = in.readString();
        this.thumbnailURL = in.readString();
    }

    public static final Parcelable.Creator<steps> CREATOR = new Parcelable.Creator<steps>() {
        @Override
        public steps createFromParcel(Parcel source) {
            return new steps(source);
        }

        @Override
        public steps[] newArray(int size) {
            return new steps[size];
        }
    };
}
