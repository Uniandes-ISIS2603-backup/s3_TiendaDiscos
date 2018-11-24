/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.dtos.CancionDTO;
import co.edu.uniandes.csw.tiendadiscos.ejb.CancionLogic;
import co.edu.uniandes.csw.tiendadiscos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Andrés Hernández
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class CancionResource {

    private static final Logger LOGGER = Logger.getLogger(CancionResource.class.getName());

    @Inject
    private CancionLogic logic;

    @POST
    public CancionDTO createCancion(@PathParam("vinilosId") Long vinilosId, CancionDTO cancion) throws BusinessLogicException {
        if(cancion.getNombre().isEmpty() && cancion.getDescripcion().isEmpty()){
            throw new WebApplicationException("El vinilo no es valido, ingrese todos los valores");
        }
        LOGGER.log(Level.INFO, "CancionResource createCancion: input:{0}", cancion);
        CancionDTO nuevaCancion;
        nuevaCancion = new CancionDTO(logic.createCancion(cancion.toEntity(), vinilosId));
        LOGGER.log(Level.INFO, "CancionResource createCancion: output: {0}", nuevaCancion);
        return nuevaCancion;
    }

    @GET
    public List<CancionDTO> getCanciones(@PathParam("vinilosId") Long vinilosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CancionResource getCanciones de vinilo: input: void");
        List<CancionDTO> canciones = listEntity2DetailDTO(logic.getCancionesDeVinilo(vinilosId));
        LOGGER.log(Level.INFO, "CancionResource getCanciones: output: {0}", canciones);
        return canciones;
    }

    @GET
    @Path("{cancionesId: \\d+}")
    public CancionDTO getCancion(@PathParam("vinilosId") Long viniloId, @PathParam("cancionesId") Long cancionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CancionResource getCancion: input: {0}", cancionesId);
        CancionEntity laCancion = logic.getCancion(viniloId, cancionesId);
        if (null == laCancion) {
            throw new WebApplicationException("El recurso /canciones/" + cancionesId + " no existe.", 404);
        }
        CancionDTO cancion = new CancionDTO(laCancion);
        LOGGER.log(Level.INFO, "CancionResource getCancion: output: {0}", cancion);
        return cancion;
    }

    @PUT
    @Path("{cancionesId: \\d+}")
    public CancionDTO updateCancion(@PathParam("vinilosId") Long viniloId, @PathParam("cancionesId") Long cancionesId, CancionDTO cancion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CancionResource updateCancion : input: cancionesId: {0}, cancion: {1}", new Object[]{cancionesId, cancion});
        cancion.setId(cancionesId);
           if(cancion.getNombre().isEmpty() && cancion.getDescripcion().isEmpty()){
            throw new WebApplicationException("El vinilo no es valido, ingrese todos los valores");
        }
        CancionEntity laCancion = logic.getCancion(viniloId, cancionesId);
        if (null == laCancion) {
            throw new WebApplicationException("El recurso /canciones/" + cancionesId + " no existe.", 404);
        }

        CancionDTO cancionDTO = new CancionDTO(logic.updateCancion(viniloId, cancionesId, cancion.toEntity()));
        LOGGER.log(Level.INFO, "CancionResource updateCancion: output: {0}", cancionDTO);
        return cancionDTO;
    }

    @DELETE
    @Path("{cancionesId: \\d+}")
    public void deleteCancion(@PathParam("vinilosId") Long viniloId, @PathParam("cancionesId") Long cancionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CancionResource deleteCancion: input: {0}", cancionesId);
        if (logic.getCancion(viniloId, cancionesId) == null) {
            throw new WebApplicationException("El recurso /canciones/" + cancionesId + " no existe.", 404);
        }
        logic.deleteCancion(viniloId, cancionesId);
        LOGGER.log(Level.INFO, "CancionResource deleteCancion: output: void");
    }

    @Path("{cancionesId: \\d+}/comentarios")
    public Class<ComentarioCancionResource> getComentariosResource(@PathParam("vinilosId") Long viniloId, @PathParam("cancionesId") Long cancionesId) throws BusinessLogicException {
        if (logic.getCancion(viniloId, cancionesId) == null) {
            throw new WebApplicationException("El recurso /canciones/" + cancionesId + " no existe.", 404);
        }
        return ComentarioCancionResource.class;
    }

    //METODOS
    private List<CancionDTO> listEntity2DetailDTO(List<CancionEntity> entityList) {
        List<CancionDTO> list = new ArrayList<>();
        for (CancionEntity entity : entityList) {
            list.add(new CancionDTO(entity));
        }
        return list;
    }
}
