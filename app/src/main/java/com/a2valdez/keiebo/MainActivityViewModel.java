package com.a2valdez.keiebo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.a2valdez.keiebo.modelo.Participante;
import com.a2valdez.keiebo.request.ApiClientRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Participante> mParticipante;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<Participante> getMPropietario() {
        if(mParticipante == null){
            mParticipante = new MutableLiveData<>();
        }
        return mParticipante;
    }

    public void LeerUsuario(){
        String token = ApiClientRetrofit.leerToken(context);
        ApiClientRetrofit.ApiKeiebo apiKeiebo = ApiClientRetrofit.getApiKeiebo();
        Call<Participante> p = apiKeiebo.obtenerPerfil(token);
        p.enqueue(new Callback<Participante>() {
            @Override
            public void onResponse(Call<Participante> call, Response<Participante> response) {
                if(response.isSuccessful()){
                    mParticipante.postValue(response.body());
                } else{
                    Log.d("salida", response.message());
                }
            }
            @Override
            public void onFailure(Call<Participante> call, Throwable t) {
                Log.d("salida", t.getMessage());
            }
        });
    }
}
