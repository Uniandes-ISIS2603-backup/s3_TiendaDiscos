/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.EnvioDTO;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Camilo Andres Salinas Martinez
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransaccionEnvioResource {
    private static final Logger LOGGER = Logger.getLogger(TransaccionEnvioResource.class.getName());
    
    
    
    @POST
    public EnvioDTO crearEnvio(EnvioDTO envio){
        
        return envio;
    }
    
    @GET
    public EnvioDTO obtenerEnvio(){
        return null;
    }
    
    @PUT
    public EnvioDTO actualizarEnvio(EnvioDTO envio){
        return envio;
    }
    @DELETE
    public void eliminarEnvio(){
    }
    
    
}
