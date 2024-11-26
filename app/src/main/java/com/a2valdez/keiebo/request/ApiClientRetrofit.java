package com.a2valdez.keiebo.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.a2valdez.keiebo.modelo.Reunion;
import com.a2valdez.keiebo.modelo.Participante;
import com.a2valdez.keiebo.modelo.Tarea;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public class ApiClientRetrofit {

    public static final String URLBASE = "http://192.168.100.2:5000/";
    //public static final String URLBASE = "http://192.168.1.191:5000/";
    private static ApiKeiebo apiKeiebo;

    public static ApiKeiebo getApiKeiebo(){
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLBASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiKeiebo = retrofit.create(ApiKeiebo.class);
        return apiKeiebo;
    }

    public interface ApiKeiebo{
        @FormUrlEncoded
        @POST("Participantes/Login")
        Call<String> login(@Field("Email") String usuario, @Field("Password") String password);

        @GET("Participantes/Perfil")
        Call<Participante> obtenerPerfil(@Header("Authorization") String token);

        @PUT("Participantes/Editar")
        Call<Participante> editarPerfil(@Header("Authorization") String token, @Body Participante participante);

        @GET("Participantes/Todos")
        Call<List<Participante>> obtenerParticipantes(@Header("Authorization") String token, @Path("id") int id);

        @Multipart
        @POST("Participantes/Crear")
        Call<Participante> crearReunion(@Header("Authorization") String token,
                                     @Part("Direccion") RequestBody direccion,
                                     @Part("Ambientes") RequestBody ambientes,
                                     @Part("Tipo") RequestBody tipo,
                                     @Part("Uso") RequestBody uso,
                                     @Part("Precio") RequestBody precio,
                                     @Part MultipartBody.Part imagen
        );
        //Nuevo
        @GET("Reuniones/Obtener/{id}")
        Call<List<Reunion>> obtenerReuniones(@Header("Authorization") String token);

        @GET("Participantes/Obtener/{id}")
        Call<Participante> obtenerParticipante(@Header("Authorization") String token,  @Path("id") int id);
    }

    public static void guardarToken(Context context, String token){
        SharedPreferences sp=context.getSharedPreferences("token.xml",0);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("token", token);
        editor.commit();
    }

    public static String leerToken(Context context){
        SharedPreferences sp=context.getSharedPreferences("token.xml",0);
        return sp.getString("token","");
    }
}
