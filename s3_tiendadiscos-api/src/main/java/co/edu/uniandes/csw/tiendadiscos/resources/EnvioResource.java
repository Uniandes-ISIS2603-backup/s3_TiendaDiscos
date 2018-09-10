/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;


import co.edu.uniandes.csw.tiendadiscos.dtos.EnvioDTO;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Camilo Andres Salinas Martinez
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class EnvioResource {
    @GET
    public EnvioDTO getEnvio(){
        
        return new EnvioDTO();
    }
    
    @POST 
    public EnvioDTO createEnvio (EnvioDTO envio) {
        return envio;
    }
    
    @PUT
    public EnvioDTO updateEnvio (EnvioDTO envio) {
    
    return envio ;
    }
    
    @DELETE
    public void deleteEnvio (){
        
    }
}
