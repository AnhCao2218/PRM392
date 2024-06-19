package com.caodnhe150776.myapplication.slot11;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceUpd {
    @FormUrlEncoded
    @POST("update_product.php")
    Call<SvrReponseUpd> UpdateExe(@Field("pid") String pid,
                                     @Field("name") String name,
                                     @Field("price") String price,
                                     @Field("description") String description);

}
