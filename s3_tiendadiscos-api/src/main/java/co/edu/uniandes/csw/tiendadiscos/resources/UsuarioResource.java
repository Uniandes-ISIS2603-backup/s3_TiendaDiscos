/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;



import co.edu.uniandes.csw.tiendadiscos.dtos.UsuarioDTO;
import co.edu.uniandes.csw.tiendadiscos.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import javax.ws.rs.Consumes;

import javax.ws.rs.*;
/**
 *
 * @author Camilo Andres Salinas Martinez 
 */
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {
    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
    
    @Inject
    UsuarioLogic usuarioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @POST
    public UsuarioDTO createUsuario(UsuarioDTO usuario)throws BusinessLogicException{
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioLogic.createUsuario(usuario.toEntity()));
        
        
        return usuarioDTO;
    }
    
    @PUT
    @Path("{usuariosId: \\d+}")
    public UsuarioDTO updateUsuario(@PathParam("usuariosId") Long usuarioId,UsuarioDTO usuario){
        
        return usuario;
    }
    
    @GET
    @Path("{usuariosId: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("usuariosId") Long usuarioId){
        UsuarioEntity usuarioEntity = usuarioLogic.getUsuario(usuarioId);
        if (usuarioEntity ==null){
            throw new WebApplicationException("El recurso /usuario/" + usuarioId + " no existe.", 404);
        }
        UsuarioDetailDTO usuarioDetail = new UsuarioDetailDTO(usuarioEntity);
        
        return usuarioDetail;
    }
    
    @GET
    public List<UsuarioDetailDTO> getUsuarios(){
        List<UsuarioDetailDTO> listaUsuarios = listEntity2DetailDTO(usuarioLogic.getUsuarios());
        
        return listaUsuarios;
    }
    
    @DELETE
    @Path("{usuariosId: \\d+}")
    public void deleteUsuarios(@PathParam("usuariosId") Long usuarioId){
        
        
    } 
    
    @Path("{usuariosId: \\d+}/billing")
    public Class<BillingInformationResource> getBillingResource(@PathParam("usuariosId") Long usuariosId) {
        
        return BillingInformationResource.class;
    }
    
    @Path("{usuariosId: \\d+}/comentarios")
    public Class<ComentarioResource> getComentariosResource(@PathParam("usuariosId") Long usuariosId) {
        
        return ComentarioResource.class;
    }

    @Path("{usuariosId: \\d+}/wishlist")
    public Class<WishListResource> getWishListResource(@PathParam("usuariosId") Long usuariosId) {
        
        return WishListResource.class;
    }
    @Path("{usuariosId: \\d+}/carritoDeCompras")
    public Class<CarritoDeComprasResource> geCarritoDeComprasResource(@PathParam("usuariosId") Long usuariosId) {
        
        return CarritoDeComprasResource.class;
    }
    
    
    
    
    //METODOS
    
    
    
    
        private List<UsuarioDetailDTO> listEntity2DetailDTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }
}
