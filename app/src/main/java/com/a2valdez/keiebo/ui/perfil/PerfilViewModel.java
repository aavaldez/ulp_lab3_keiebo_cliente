package com.a2valdez.keiebo.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.a2valdez.keiebo.LoginActivity;
import com.a2valdez.keiebo.modelo.Participante;
import com.a2valdez.keiebo.request.ApiClientRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Participante> mParticipante;
    private MutableLiveData<Boolean> esEditable;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Participante> getMPropietario() {
        if(mParticipante == null){
            mParticipante = new MutableLiveData<>();
        }
        return mParticipante;
    }

    public LiveData<Boolean> getMEsEditable() {
        if (esEditable == null) {
            esEditable = new MutableLiveData<>();
            esEditable.setValue(false);
        }
        return esEditable;
    }

    public void LeerUsuario(){
        String token = ApiClientRetrofit.leerToken(context);
        ApiClientRetrofit.ApiKeiebo apiKeiebo = ApiClientRetrofit.getApiKeiebo();
        Call<Participante> p = apiKeiebo.obtenerPerfil(token);
        p.enqueue(new Callback<Participante>() {
            @Override
            public void onResponse(Call<Participante> call, Response<Participante> response) {
                if(response.isSuccessful()){
                    //Log.d("salida", response.body().getNombre());
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

    public void CambiarEstadoEdicion(){
        esEditable.setValue(!esEditable.getValue());
    }

    public void GuardarParticipante(Participante p){
        /*
        if (dni.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }
        */

        String token = ApiClientRetrofit.leerToken(context);
        if (token.isEmpty()) {
            Log.d("salida", "Token inexistente");
            Intent intent = new Intent(context, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
            return;
        }

        ApiClientRetrofit.ApiKeiebo apiKeiebo = ApiClientRetrofit.getApiKeiebo();
        Call<Participante> call = apiKeiebo.editarPerfil(token, p);
        call.enqueue(new Callback<Participante>() {
            @Override
            public void onResponse(@NonNull Call<Participante> call, @NonNull Response<Participante> response) {
                Log.d("salida", response.raw().toString());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        mParticipante.setValue(p);
                        Toast.makeText(context, "Editado correctamente", Toast.LENGTH_SHORT).show();
                        CambiarEstadoEdicion();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Participante> call, @NonNull Throwable t) {
                Toast.makeText(context, "Error al obtener usuario", Toast.LENGTH_SHORT).show();
            }
        });

    }

}