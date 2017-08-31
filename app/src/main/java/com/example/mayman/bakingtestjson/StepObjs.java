package com.example.mayman.bakingtestjson;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MahmoudAyman on 8/19/2017.
 */

public class StepObjs implements Parcelable {

    private String stepId;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    protected StepObjs(Parcel in) {
        stepId = in.readString();
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnailURL = in.readString();
    }

    public static final Creator<StepObjs> CREATOR = new Creator<StepObjs>() {
        @Override
        public StepObjs createFromParcel(Parcel in) {
            return new StepObjs(in);
        }

        @Override
        public StepObjs[] newArray(int size) {
            return new StepObjs[size];
        }
    };

    public StepObjs() {

    }

    String getStepId() {
        return stepId;
    }

    void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
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



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(stepId);
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeString(thumbnailURL);
    }
}