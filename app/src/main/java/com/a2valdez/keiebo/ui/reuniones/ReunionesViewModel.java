package com.a2valdez.keiebo.ui.reuniones;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.a2valdez.keiebo.modelo.Reunion;
import com.a2valdez.keiebo.request.ApiClientRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReunionesViewModel extends ViewModel {
    private Context context;
    private MutableLiveData<List<Reunion>> mLista;

    public ReunionesViewModel(@NonNull Application application) {
        super();
        context = application.getApplicationContext();
    }

    public LiveData<List<Reunion>> getmLista() {
        if(mLista == null){
            mLista = new MutableLiveData<>();
        }
        return mLista;
    }

    public void obtenerReuniones(){
        String token = ApiClientRetrofit.leerToken(context);
        ApiClientRetrofit.ApiKeiebo apiKeiebo = ApiClientRetrofit.getApiKeiebo();
        Call<List<Reunion>> call = apiKeiebo.obtenerReuniones(token);
        call.enqueue(new Callback<List<Reunion>>() {
            @Override
            public void onResponse(Call<List<Reunion>> call, Response<List<Reunion>> response) {
                Log.d("salida", response.raw().toString());
                if(response.isSuccessful()){
                    mLista.postValue(response.body());
                } else{
                    Log.d("salida", response.message());
                }
            }
            @Override
            public void onFailure(Call<List<Reunion>> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }
}
