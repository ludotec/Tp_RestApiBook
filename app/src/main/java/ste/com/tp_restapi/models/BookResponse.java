package ste.com.tp_restapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookResponse{

    @SerializedName("kind")
    @Expose
    private String kind;

    @SerializedName("items")
    @Expose
    List<Volume> items = null;

    @SerializedName("totalItems")
    @Expose
    int totalItems;

    public String getKind() {
        return kind;
    }

    public List<Volume> getItems() {
        return items;
    }

    public int getTotalItems() {
        return totalItems;
    }}