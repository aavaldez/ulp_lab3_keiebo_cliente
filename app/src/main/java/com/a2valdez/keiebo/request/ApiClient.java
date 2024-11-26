package com.a2valdez.keiebo.request;

import com.a2valdez.keiebo.modelo.*;

import java.util.ArrayList;


public class ApiClient {
    private ArrayList<Participante> participantes=new ArrayList<>();
    private ArrayList<Reunion> reuniones=new ArrayList<>();
    private ArrayList<Tarea> tareas =new ArrayList<>();
    private static Participante usuarioActual=null;
    private static ApiClient api=null;

    private ApiClient(){
        //Nos conectamos a nuestra "Base de Datos"
        cargaDatos();
    }

    //Método para crear una instancia de ApiClient
    public static ApiClient getApi(){
        if (api==null){
            api=new ApiClient();
        }
        return api;
    }

    //Servicios
    //Para que pueda iniciar sesion
    public Participante login(String mail, final String password){
        for(Participante participante:participantes){
            if(participante.getEmail().equals(mail)&&participante.getPassword().equals(password)){
                usuarioActual=participante;
                return participante;
            }
        }
        return null;
    }

    //Retorna el usuario que inició Sesión
    public Participante obtenerUsuarioActual(){
        return usuarioActual;
    }

    //Retorna todas las propiedades del usuario participante logueado
    public ArrayList<Participante> obtnerPropiedades(){
        ArrayList<Participante> temp=new ArrayList<>();
        for(Participante Participante:participantes){
            if(Participante.equals(usuarioActual)){
                temp.add(Participante);
            }
        }
        return temp;
    }

    //Lista de Participantes alquilados actualmente del participante logueado.
    public ArrayList<Participante> obtenerPropiedadesAlquiladas(){
        ArrayList<Participante> temp=new ArrayList<>();
        for(Reunion reunion:reuniones){
            if(reunion.getParticipante().equals(usuarioActual)){
                temp.add(reunion.getParticipante());
            }
        }
        return temp;
    }

    //Dado un Participante retorna el reunion activo de dicho Participante
    public Reunion obtenerContratoVigente(Participante Participante){

        for(Reunion reunion:reuniones){
            if(reunion.getParticipante().equals(Participante)){
                return reunion;
            }
        }
        return null;
    }

    //Dado un Participante, retorna el Participante del ultimo reunion activo de ese Participante.
    public Participante obtenerParticipante(Participante Participante){
        for(Reunion reunion:reuniones){
            if(reunion.getParticipante().equals(Participante)){
                return reunion.getParticipante();
            }
        }
        return null;
    }

    //Dado un Contrato, retorna los pagos de dicho reunion
    public ArrayList<Tarea> obtenerPagos(Reunion contratoVer){
        ArrayList<Tarea> temp=new ArrayList<>();
        for(Reunion reunion:reuniones){
            if(reunion.equals(contratoVer)){
                for(Tarea tarea : tareas){
                    if(tarea.getContrato().equals(reunion)){
                        temp.add(tarea);
                    }
                }
            }
            break;
        }
        return temp;
    }

    //Actualizar Perfil
    public void actualizarPerfil(Participante participante){
        int posición=participantes.indexOf(participante);
        if(posición!=-1){
            participantes.set(posición,participante);
        }
    }

    //ActualizarParticipante
    public void actualizarParticipante(Participante Participante){
        int posicion=participantes.indexOf(Participante);
        if(posicion!=-1){
            participantes.set(posicion,Participante);
        }
    }

    private void cargaDatos(){
        //Usuarios
        Participante juan = new Participante(1,23492012L,"Juan","Perez","juan@mail.com","123","2664553447", "juan.png");
        Participante sonia = new Participante(2,17495869L,"Sonia","Lucero","sonia@mail.com","123","266485417","sonia.png");
        Participante mario = new Participante(100,25340691L,"Mario","Luna","luna@mail.com","2664253411","Lucero Roberto","2664851422");
        participantes.add(juan);
        participantes.add(sonia);
        participantes.add(mario);

        //Contratos
        Reunion uno=new Reunion(701, "05/08/2020","05/08/2023",17000, mario);

        reuniones.add(uno);
        //Pagos
        tareas.add(new Tarea(900,1,uno,17000,"10/08/2020"));
        tareas.add(new Tarea(901,2,uno,17000,"10/09/2020"));
        tareas.add(new Tarea(902,3,uno,17000,"10/10/2020"));
    }
}
