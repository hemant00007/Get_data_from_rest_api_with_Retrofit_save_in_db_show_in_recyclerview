package com.example.save_data_in_db_show_in_recyclerview_retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface Restinteface {

    @GET("menu.php")
    Call<List<Repo>> savedata();
}
