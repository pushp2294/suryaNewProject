package com.example.suryaproject.util;
import com.example.suryaproject.model.ModelForApiRequest;
import com.example.suryaproject.model.ModelForUserEntityList;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServices {

    @POST("/list")
    Single<ModelForUserEntityList> fetchCeleberitiesDataList(@Body ModelForApiRequest modelForApiRequest);

}
