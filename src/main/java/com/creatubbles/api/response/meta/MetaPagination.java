package com.creatubbles.api.response.meta;

import lombok.Value;

import com.google.gson.annotations.SerializedName;

@Value
public class MetaPagination implements Metadata {

    @SerializedName("total_count")
    int objectCount;

    @SerializedName("total_pages")
    int pageCount;

    @SerializedName("per_page")
    int perPage;

    @SerializedName("current_page")
    int page;
}
