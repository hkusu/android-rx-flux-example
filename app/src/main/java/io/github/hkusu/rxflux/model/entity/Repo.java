package io.github.hkusu.rxflux.model.entity;

import com.google.gson.annotations.SerializedName;

public class Repo {
    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;
}
