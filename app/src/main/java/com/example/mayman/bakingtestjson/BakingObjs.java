package com.example.mayman.bakingtestjson;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by MahmoudAyman on 8/12/2017.
 */

public class BakingObjs implements Parcelable {

    private String product_id;
    private String name;
    private String servings;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;
    private ArrayList<String> ingrediantList;

    ArrayList<StepObjs> getStepObjsList() {
        return stepObjsList;
    }

    void setStepObjsList(ArrayList<StepObjs> stepObjsList) {
        this.stepObjsList = stepObjsList;
    }

    private ArrayList<StepObjs> stepObjsList;


    BakingObjs(String product_id, String name, String servings, String image) {
        this.product_id = product_id;
        this.name = name;
        this.servings = servings;
        this.image = image;
    }

    private String quantity;
    private String measure;
    private String ingredient;


    private BakingObjs(Parcel in) {
        product_id = in.readString();
        name = in.readString();
        servings = in.readString();
        ingrediantList = in.createStringArrayList();
        quantity = in.readString();
        measure = in.readString();
        ingredient = in.readString();
    }

    public BakingObjs() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(product_id);
        dest.writeString(name);
        dest.writeString(servings);
        dest.writeStringList(ingrediantList);
        dest.writeString(quantity);
        dest.writeString(measure);
        dest.writeString(ingredient);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BakingObjs> CREATOR = new Creator<BakingObjs>() {
        @Override
        public BakingObjs createFromParcel(Parcel in) {
            return new BakingObjs(in);
        }

        @Override
        public BakingObjs[] newArray(int size) {
            return new BakingObjs[size];
        }
    };

    public String getProduct_id() {
        return product_id;
    }

    public String getName() {
        return name;
    }

    public String getServings() {
        return servings;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    ArrayList<String> getIngrediantList() {
        return ingrediantList;
    }

    void setIngrediantList(ArrayList<String> ingrediantList) {
        this.ingrediantList = ingrediantList;
    }
}