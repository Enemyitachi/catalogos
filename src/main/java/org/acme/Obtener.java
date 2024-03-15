package org.acme;

import io.quarkus.logging.Log;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.sql.*;
import java.text.SimpleDateFormat;
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

    @GET
    @Path("obtener/{tabla}")
    public Response getDatos(@PathParam("tabla") String tabla) {
        String query = "SELECT * FROM "+ tabla;
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
}
