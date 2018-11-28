/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.resources;

import co.edu.uniandes.csw.tiendadiscos.ejb.ComentarioLogic;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 * Recurso usado para tener solamente un delete para todos los cuatro tipos de comentarios.
 * 
 * @author Andrés Hernández
 */
@Path("comentarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ComentarioResource {
    
    private static final Logger LOGGER = Logger.getLogger(ComentarioResource.class.getName());
    
    @Inject
    private ComentarioLogic logic;
    
    @DELETE
    @Path("{comentariosId: \\d+}")
    public void deleteComentario(@PathParam("comentariosId") Long comentariosId)
    {
        LOGGER.log(Level.INFO, "ComentarioResource deleteComentario: input: {0}", comentariosId);
        if(logic.getComentario(comentariosId) == null)
            throw new WebApplicationException("El recurso /comentarios/"+ comentariosId+ " no existe", 404);
        logic.deleteComentario(comentariosId);
        LOGGER.log(Level.INFO, "ComentariosResource deleteComentario: output: void");                  
    }    
}
