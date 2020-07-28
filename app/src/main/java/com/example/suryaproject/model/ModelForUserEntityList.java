package com.example.suryaproject.model;

import android.content.ClipData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ModelForUserEntityList {
    @SerializedName("items")
    @Expose
    private List<UserEntity> items = null;

    public List<UserEntity> getItems() {
        return items;
    }

    public void setItems(List<UserEntity> items) {
        this.items = items;
    }
}
