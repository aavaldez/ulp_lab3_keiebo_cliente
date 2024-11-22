package com.a2valdez.keiebo.request;

import com.a2valdez.keiebo.modelo.*;

import java.util.ArrayList;


public class ApiClient {
    private ArrayList<Propietario> propietarios=new ArrayList<>();
    private ArrayList<Participante> Participantes=new ArrayList<>();
    private ArrayList<Participante> Participantes=new ArrayList<>();
    private ArrayList<Reunion> contratos=new ArrayList<>();
    private ArrayList<Pago> pagos=new ArrayList<>();
    private static Propietario usuarioActual=null;
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
    public Propietario login(String mail, final String password){
        for(Propietario propietario:propietarios){
            if(propietario.getEmail().equals(mail)&&propietario.getPassword().equals(password)){
                usuarioActual=propietario;
                return propietario;
            }
        }
        return null;
    }

    //Retorna el usuario que inició Sesión
    public Propietario obtenerUsuarioActual(){
        return usuarioActual;
    }

    //Retorna todas las propiedades del usuario propietario logueado
    public ArrayList<Participante> obtnerPropiedades(){
        ArrayList<Participante> temp=new ArrayList<>();
        for(Participante Participante:Participantes){
            if(Participante.getPropietario().equals(usuarioActual)){
                temp.add(Participante);
            }
        }
        return temp;
    }

    //Lista de Participantes alquilados actualmente del propietario logueado.
    public ArrayList<Participante> obtenerPropiedadesAlquiladas(){
        ArrayList<Participante> temp=new ArrayList<>();
        for(Reunion contrato:contratos){
            if(contrato.getParticipante().getPropietario().equals(usuarioActual)){
                temp.add(contrato.getParticipante());
            }
        }
        return temp;
    }

    //Dado un Participante retorna el contrato activo de dicho Participante
    public Reunion obtenerContratoVigente(Participante Participante){

        for(Reunion contrato:contratos){
            if(contrato.getParticipante().equals(Participante)){
                return contrato;
            }
        }
        return null;
    }

    //Dado un Participante, retorna el Participante del ultimo contrato activo de ese Participante.
    public Participante obtenerParticipante(Participante Participante){
        for(Reunion contrato:contratos){
            if(contrato.getParticipante().equals(Participante)){
                return contrato.getParticipante();
            }
        }
        return null;
    }

    //Dado un Contrato, retorna los pagos de dicho contrato
    public ArrayList<Pago> obtenerPagos(Reunion contratoVer){
        ArrayList<Pago> temp=new ArrayList<>();
        for(Reunion contrato:contratos){
            if(contrato.equals(contratoVer)){
                for(Pago pago:pagos){
                    if(pago.getContrato().equals(contrato)){
                        temp.add(pago);
                    }
                }
            }
            break;
        }
        return temp;
    }

    //Actualizar Perfil
    public void actualizarPerfil(Propietario propietario){
        int posición=propietarios.indexOf(propietario);
        if(posición!=-1){
            propietarios.set(posición,propietario);
        }
    }

    //ActualizarParticipante
    public void actualizarParticipante(Participante Participante){
        int posicion=Participantes.indexOf(Participante);
        if(posicion!=-1){
            Participantes.set(posicion,Participante);
        }
    }

    private void cargaDatos(){
        //Propietarios
        Propietario juan=new Propietario(1,23492012L,"Juan","Perez","juan@mail.com","123","2664553447", "juan.png");
        Propietario sonia=new Propietario(2,17495869L,"Sonia","Lucero","sonia@mail.com","123","266485417","sonia.png");
        propietarios.add(juan);
        propietarios.add(sonia);

        //Participantes
        Participante mario=new Participante(100,25340691L,"Mario","Luna","Aiello sup.","luna@mail.com","2664253411","Lucero Roberto","2664851422");
        Participantes.add(mario);

        //Participantes
        Participante salon=new Participante(501,"Colon 340","comercial","salon",2,20000,juan,true,"http://www.secsanluis.com.ar/servicios/salon1.jpg");
        Participante casa=new Participante(502,"Mitre 800","particular","casa",2,15000,juan,true,"http://www.secsanluis.com.ar/servicios/casa1.jpg");
        Participante otraCasa=new Participante(503,"Salta 325","particular","casa",3,17000,sonia,true,"http://www.secsanluis.com.ar/servicios/casa2.jpg");
        Participante dpto=new Participante(504,"Lavalle 450","particular","dpto",2,25000,sonia,true,"http://www.secsanluis.com.ar/servicios/departamento1.jpg");
        Participante casita=new Participante(505,"Belgrano 218","particular","casa",5,90000,sonia,true,"http://www.secsanluis.com.ar/servicios/casa3.jpg");

        Participantes.add(salon);
        Participantes.add(casa);
        Participantes.add(otraCasa);
        Participantes.add(dpto);
        Participantes.add(casita);

        //Contratos
        Reunion uno=new Reunion(701, "05/08/2020","05/08/2023",17000,mario,otraCasa);
        contratos.add(uno);
        //Pagos
        pagos.add(new Pago(900,1,uno,17000,"10/08/2020"));
        pagos.add(new Pago(901,2,uno,17000,"10/09/2020"));
        pagos.add(new Pago(902,3,uno,17000,"10/10/2020"));
    }
}
