/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;



import co.edu.uniandes.csw.tiendadiscos.dtos.UsuarioDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import static javax.ws.rs.HttpMethod.POST;
import static javax.ws.rs.HttpMethod.DELETE;
import javax.ws.rs.*;
/**
 *
 * @author Camilo Andres Salinas Martinez 
 */
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
public class UsuarioResource {
    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
    
    //@Inject
    //EditorialLogic editorialLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @POST
    public UsuarioDTO createUsuario(UsuarioDTO usuario){
        return usuario;
    }
    
    @PUT
    @Path("{usuariosId: \\d+}")
    public UsuarioDTO updateUsuario(@PathParam("usuariosId") Long usuarioId,UsuarioDTO usuario){
        
        return usuario;
    }
    
    @GET
    @Path("{usuariosId: \\d+}")
    public UsuarioDTO getUsuario(@PathParam("usuariosId") Long usuarioId){
        
        return null;
    }
    
    @GET
    public UsuarioDTO getUsuarios(){
        
        return null;
    }
    
    @DELETE
    @Path("{usuariosId: \\d}")
    public void deleteUsuarios(@PathParam("usuariosId") Long usuarioId){
        
        
    } 
    
     
    @Path("{usuariosId: \\d+}/billingInformation")
    public Class<BillingInformationResource> getBillingInformationResource(@PathParam("usuariosId") Long usuariosId) {
 
        return BillingInformationResource.class;
    }
    
}
