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
import java.util.logging.Level;
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
    public UsuarioDTO createUsuario(UsuarioDTO usuario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource createUsuario: input: {0}", usuario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioLogic.createUsuario(usuario.toEntity()));
        LOGGER.log(Level.INFO, "UsuarioResource createUsuario: outPut: {0}", usuarioDTO);
        return usuarioDTO;
    }

    @GET
    public List<UsuarioDetailDTO> getUsuarios() {
        LOGGER.log(Level.INFO, "UsuarioResource getUsuarios: input: void");
        List<UsuarioDetailDTO> listaUsuarios = listEntity2DetailDTO(usuarioLogic.getUsuarios());
        LOGGER.log(Level.INFO, "UsuarioResource getUsuarios: output: {0}", listaUsuarios);
        return listaUsuarios;
    }

    @GET
    @Path("{usuariosId: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("usuariosId") Long usuarioId) {
        LOGGER.log(Level.INFO, "UsuarioResource getUsuario: input: {0}", usuarioId);
        UsuarioEntity usuarioEntity = usuarioLogic.getUsuario(usuarioId);
        if (usuarioEntity == null) {
            throw new WebApplicationException("El recurso /usuario/" + usuarioId + " no existe.", 404);
        }
        UsuarioDetailDTO usuarioDetailDTO = new UsuarioDetailDTO(usuarioEntity);
        LOGGER.log(Level.INFO, "UsuarioResource getUsuario: output: {0}", usuarioDetailDTO);
        return usuarioDetailDTO;
    }

    @PUT
    @Path("{usuariosId: \\d+}")
    public UsuarioDetailDTO updateUsuario(@PathParam("usuariosId") Long usuarioId, UsuarioDTO usuario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource updateUsuario: input: usuarioId: {0} , usuario:{1}", new Object[]{usuarioId, usuario});
        usuario.setId(usuarioId);
        if (usuarioLogic.getUsuario(usuarioId) == null) {
            throw new WebApplicationException("El recurso /usuario/" + usuarioId + " no existe.", 404);
        }
        UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(usuarioLogic.updateUsuario(usuarioId, usuario.toEntity()));
        return detailDTO;
    }

    @DELETE
    @Path("{usuariosId: \\d+}")
    public void deleteUsuarios(@PathParam("usuariosId") Long usuariosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource deleteUsuario: input: {0}", usuariosId);
        if (usuarioLogic.getUsuario(usuariosId) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + " no existe.", 404);
        }
        usuarioLogic.deleteUsuario(usuariosId);
        LOGGER.log(Level.INFO, "UsuarioResource deleteUsuario: output: void");
    }

    @Path("{usuariosId: \\d+}/billing")
    public Class<BillingInformationResource> getBillingResource(@PathParam("usuariosId") Long usuariosId) {
        if (usuarioLogic.getUsuario(usuariosId) == null) {
            throw new WebApplicationException("El recurso /usuario/" + usuariosId + " no existe.", 404);
        }
        return BillingInformationResource.class;
    }

    @Path("{usuariosId: \\d+}/wishlist")
    public Class<WishListResource> getWishListResource(@PathParam("usuariosId") Long usuariosId) {
        if (usuarioLogic.getUsuario(usuariosId) == null) {
            throw new WebApplicationException("El recurso /usuario/" + usuariosId + " no existe.", 404);
        }
        return WishListResource.class;
    }

    @Path("{usuariosId: \\d+}/carrito")
    public Class<CarritoDeComprasResource> getCarritoDeComprasResource(@PathParam("usuariosId") Long usuariosId) {
        if (usuarioLogic.getUsuario(usuariosId) == null) {
            throw new WebApplicationException("El recurso /usuario/" + usuariosId + " no existe.", 404);
        }
        return CarritoDeComprasResource.class;
    }

    @Path("{usuariosId: \\d+}/comentarios")
    public Class<ComentarioUsuarioResource> getComentariosResource(@PathParam("usuariosId") Long usuariosId) {
        if (usuarioLogic.getUsuario(usuariosId) == null) {
            throw new WebApplicationException("El recurso /usuario/" + usuariosId + " no existe.", 404);
        }
        return ComentarioUsuarioResource.class;
    }

    //----------------------------------------------------------------
    //METODOS
    //----------------------------------------------------------------   
    private List<UsuarioDetailDTO> listEntity2DetailDTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }
}
