package com.a2valdez.keiebo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.a2valdez.keiebo.request.ApiClientRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> mMensaje;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<String> getmMensaje() {
        if(mMensaje == null){
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }

    public void Login(String email, String password){
        ApiClientRetrofit.ApiKeiebo apiKeiebo = ApiClientRetrofit.getApiKeiebo();
        Call<String> token = apiKeiebo.login(email, password);
        token.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    ApiClientRetrofit.guardarToken(context, "Bearer "+response.body());
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else{
                    //Log.d("salida", response.message());
                    Toast.makeText(context, "Error al intentar autenticar", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //Log.d("salida", t.getMessage());
                Toast.makeText(context, "Error al intentar autenticar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
