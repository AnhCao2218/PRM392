package com.caodnhe150776.myapplication.slot11;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface InterfaceSelect {

    @GET("get_all_product.php")
    Call<SvrReponseSelect> getPrd();
}
