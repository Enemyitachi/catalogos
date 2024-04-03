package org.acme;

import io.quarkus.logging.Log;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.acme.util.BodyRequestDosKeys;
import org.acme.util.BodyRequestTresKeys;
import org.acme.util.BodyRequestUnaKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("catalogos")
public class Obtener {
    String URL = "jdbc:sqlserver://localhost:1433;databaseName=Catalogos;"
            + "encrypt=true;trustServerCertificate=true";
    String USER = "sa";
    String PASS = "12345";


    public Response getDatos(String query) {
        List<Map<String, String>> resultados = new ArrayList<>();
        Log.info(query);

        try (Connection con = DriverManager.getConnection(URL,USER,PASS);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query)){

                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (rs.next()){
                    Map<String, String> fila = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        fila.put(metaData.getColumnName(i), rs.getString(i));
                    }
                    resultados.add(fila);
                }

        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error en la base de datos").build();
        }
        return Response.ok(resultados).build();
    }

    //GENERICOS
    //Obtener la tabla sin claves foraneas
    @GET
    @Path("obtener/{catalogo}")
    public Response obtener_Catalogo(@PathParam("catalogo") String catalogo){return getDatos("SELECT * FROM "+ catalogo);}

    //Obtener catalogo desde con una llave foranea
    @POST
    @Path("obtenerDesdeLlave/{catalogo}")
    public Response obtener_Catalogo_Desde_llave(@PathParam("catalogo") String catalogo, BodyRequestUnaKey body){
        return getDatos("SELECT * FROM "+ catalogo
                + "WHERE "+ body.getLlaveForanea()
                + " = " + body.getValorLLaveForanea());
    }

    @POST
    @Path("obtenerDesdeLlave2/{catalogo}")
    public Response obtener_Catalogo_Desde_llave(@PathParam("catalogo") String catalogo, BodyRequestDosKeys body){
        return getDatos("SELECT * FROM "+ catalogo
            + "WHERE "+ body.getLlaveForanea()
            + " = " + body.getValorLLaveForanea()
            + "AND " + body.getLlaveForanea2()
            + " = " + body.getValorLLaveForanea2());
    }
    @POST
    @Path("obtenerDesdeLlave3/{catalogo}")
    public Response obtener_Catalogo_Desde_llave(@PathParam("catalogo") String catalogo, BodyRequestTresKeys body){
        return getDatos("SELECT * FROM "+ catalogo
            + "WHERE "+ body.getLlaveForanea()
            + " = " + body.getValorLLaveForanea()
            + "AND " + body.getLlaveForanea2()
            + " = " + body.getValorLLaveForanea2()
            + "AND " +body.getLlaveForanea3()
            + " = " + body.getValorLLaveForanea3());
    }

    // OO metodo por metodo
    public Response obtener_Cat_Pais(){return getDatos("SELECT * FROM CAT_PAIS");}

    public Response obtener_Cat_Pais_Clave(){return getDatos("SELECT * FROM CAT_PAIS");}

    public Response obtener_Cat_Entidad_Nacimiento(){return getDatos("SELECT * FROM CAT_PAIS");}

    public Response obtener_Cat_Entidad_Domicilio(){return getDatos("SELECT * FROM CAT_PAIS");}

    public Response obtener_Cat_Municipio_Desde_Entidad(String Entidad){return getDatos("SELECT * FROM CAT_PAIS WHERE ID_ENTIDAD_FEDERATIVA ="+Entidad);}

    public Response obtener_Cat_Ciudad_Desde_Entidad(String Entidad){return getDatos("SELECT * FROM CAT_PAIS WHERE ID_ENTIDAD_FEDERATIVA ="+ Entidad);}

    public Response obtener_Cat_Codigo_Postal_Desde_Entidad(String Entidad){return getDatos("SELECT * FROM CAT_PAIS WHERE ID_ENTIDAD_FEDERATIVA ="+ Entidad);}

    public Response obtener_Cat_Codigo_Postal_Desde_Municipio(String Municipio){return getDatos("SELECT * FROM CAT_PAIS WHERE ID_MUNICIPIO ="+ Municipio);}

    public Response obtener_Cat_Colonia(String CP, String Ciudad, String Entidad){return getDatos("SELECT * FROM CAT_PAIS WHERE ID_ENTIDAD_FEDERATIVA ="+ Entidad
                                                                                                                +"AND ID_CODIGO_POSTAL ="+ CP
                                                                                                                +"AND ID_CIUDAD ="+Ciudad);}

    public Response obtener_Cat_Sector(){return getDatos("SELECT * FROM CAT_GIRO_SECTOR");}
    public Response obtener_Cat_Giro_Desde_Sector(String id_Sector){return getDatos("SELECT * FROM CAT_GIRO_SUBSECTOR WHERE ID_SECTOR = "+id_Sector);}
    public Response obtener_Cat_Giro_Rama_Desde_Subsector(String subSector){return getDatos("SELECT * FROM CAT_GIRO_RAMA WHERE ID_SUBSECTOR = "+subSector);}
    public Response obtener_Cat_Giro_Subrama_Desde_Rama(String subSector){return getDatos("SELECT * FROM CAT_GIRO_RAMA WHERE ID_SUBSECTOR = "+subSector);}
}
