package com.example.suryaproject.model;
import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.example.suryaproject.BaseApplication;
import com.example.suryaproject.repository.ApiClient;
import com.example.suryaproject.roomdatabase.RoomDatabaseForCountries;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
public class ApiCallBusinessLogic {
public  String ApiResponseStatus="error";
    public MutableLiveData<String> apiResponseLivedataGlobal;
    public String getCeleberitiesDetails(String email,MutableLiveData<String> apiResponseLivedata) {
        apiResponseLivedataGlobal=apiResponseLivedata;
        ModelForApiRequest modelForApiRequest=new ModelForApiRequest();
        modelForApiRequest.setEmailId(email);
        ApiClient.getInstance().getApi().fetchCeleberitiesDataList(modelForApiRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ModelForUserEntityList>() {
                    @Override
                    public void onSuccess(ModelForUserEntityList modelForUserEntityList) {
                        deletAndUpdateData(modelForUserEntityList.getItems());
                    }
                    @Override
                    public void onError(Throwable e) {
                        apiResponseLivedataGlobal.setValue("error");
                    }
                });
        return ApiResponseStatus;
    }


    public void deletAndUpdateData(final List<UserEntity> userEntities) {
        Single<Integer> insertObservable = RoomDatabaseForCountries.providesRoomDatabase(BaseApplication.getAppContext()).getPromptReminderDaoInstance().deleteAll();
        insertObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(Integer id) {
                        insertData(userEntities);
                    }
                    @Override
                    public void onError(Throwable e)
                    {
                        apiResponseLivedataGlobal.setValue("error");
                    }
                });
    }

    public void insertData(final List<UserEntity> userEntities) {
        Single<List<Long>> insertObservable = RoomDatabaseForCountries.providesRoomDatabase(BaseApplication.getAppContext()).getPromptReminderDaoInstance().insertanewData(userEntities);
        insertObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Long>>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void onSuccess(List<Long> data) {
                        apiResponseLivedataGlobal.setValue("success");
                    }
                    @Override
                    public void onError(Throwable e) {
                        apiResponseLivedataGlobal.setValue("success");
                    }
                });
    }
}
