package com.a2valdez.keiebo.ui.reuniones;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.a2valdez.keiebo.modelo.Participante;
import com.a2valdez.keiebo.modelo.Reunion;
import com.a2valdez.keiebo.request.ApiClientRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReunionViewModel extends ViewModel {
    private Context context;
    private MutableLiveData<List<Participante>> mLista;

    public ReunionViewModel(@NonNull Application application) {
        super();
        context = application.getApplicationContext();
    }

    public LiveData<List<Participante>> getmLista() {
        if(mLista == null){
            mLista = new MutableLiveData<>();
        }
        return mLista;
    }

    public void obtenerParticipantes(Bundle bundle){
        String token = ApiClientRetrofit.leerToken(context);
        ApiClientRetrofit.ApiKeiebo apiKeiebo = ApiClientRetrofit.getApiKeiebo();
        Call<List<Participante>> call = apiKeiebo.obtenerParticipantes(token, bundle.getInt("id"));
        call.enqueue(new Callback<List<Participante>>() {
            @Override
            public void onResponse(Call<List<Participante>> call, Response<List<Participante>> response) {
                Log.d("salida", response.raw().toString());
                if(response.isSuccessful()){
                    mLista.postValue(response.body());
                } else{
                    Log.d("salida", response.message());
                }
            }
            @Override
            public void onFailure(Call<List<Participante>> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }
}
