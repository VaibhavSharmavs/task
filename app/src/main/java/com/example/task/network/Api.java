package com.example.task.network;

import com.example.task.network.response.GetCategoryResponse;
import com.example.task.network.response.ItemListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    public static final String BASE_URL = "https://api.dotq.in/api/dotk/catalog/";


    @GET("getCategories/2018")
    Call<GetCategoryResponse> getCategory();


    @GET("getItemsBasicDetails/2018")
    Call<ItemListResponse> getItemList(@Query("category_id") String id);

}
