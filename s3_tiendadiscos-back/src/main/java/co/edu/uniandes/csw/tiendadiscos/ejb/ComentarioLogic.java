/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;

import co.edu.uniandes.csw.tiendadiscos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.ComentarioEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.CancionPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.ComentarioPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.TransaccionPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.ViniloPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Sebastian Martinez y Andres :)
 */
@Stateless
public class ComentarioLogic {

    private static final Logger LOGGER = Logger.getLogger(ComentarioLogic.class.getName());

    private static final String ERROR_VACIO = "No se puede crear un comentario vacio";
    
    @Inject
    private ComentarioPersistence persistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    @Inject
    private ViniloPersistence viniloPersistence;

    @Inject
    private CancionPersistence cancionPersistence;

    @Inject
    private TransaccionPersistence transaccionPersistence;

    //-------------------------------------------------------
    // Create para los cuatro tipos de comentarios.
    // Vinilo, Canción, Usuario, Transacción.
    //-------------------------------------------------------
    public ComentarioEntity createComentarioUsuario(Long usuarioIdDestino, Long usuarioIdi, ComentarioEntity comentarioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia el proceso de creación de un comentario a el usuario {0}", usuarioIdDestino);

        if (comentarioEntity.getContenido() == null || comentarioEntity.getContenido().isEmpty()) {
            throw new BusinessLogicException(ERROR_VACIO);
        }

        comentarioEntity.setUsuario(usuarioPersistence.find(usuarioIdDestino));
        comentarioEntity.setUsuarioI(usuarioPersistence.find(usuarioIdi));

        persistence.create(comentarioEntity);

        LOGGER.log(Level.INFO, "Termina el proceso de creación de un comentario a el usuario");
        return comentarioEntity;
    }

    public ComentarioEntity createComentarioTransaccion(Long transaccionId, Long usuarioId, ComentarioEntity comentarioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia el proceso de creación de un comentario a la transacción {0}", transaccionId);
        if (comentarioEntity.getContenido() == null || comentarioEntity.getContenido().isEmpty()) {
            throw new BusinessLogicException(ERROR_VACIO);
        }
        if (transaccionPersistence.find(transaccionId) == null) {
            throw new BusinessLogicException("La transacción destino no existe.");
        }
        if (usuarioPersistence.find(usuarioId) == null) {
            throw new BusinessLogicException("El usuario que comento no existe.");
        }

        comentarioEntity.setTransaccion(transaccionPersistence.find(transaccionId));
        comentarioEntity.setUsuarioI(usuarioPersistence.find(usuarioId));

        persistence.create(comentarioEntity);

        LOGGER.log(Level.INFO, "Termina el proceso de creación de un comentario a la transacción.");
        return comentarioEntity;
    }

    public ComentarioEntity createComentarioCancion(Long viniloId, Long cancionesId, Long usuarioId, ComentarioEntity comentarioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia el proceso de creación de un comentario a la canción {0}", cancionesId);
        if (comentarioEntity.getContenido() == null || comentarioEntity.getContenido().isEmpty()) {
            throw new BusinessLogicException(ERROR_VACIO);
        }
        UsuarioEntity usuario = usuarioPersistence.find(usuarioId);
        CancionEntity cancion = cancionPersistence.find(cancionesId, viniloId);
        if (cancion == null) {
            throw new WebApplicationException("La canción con el id " + cancionesId + " no existe.", 404);
        }
        if (usuario == null) {
            throw new WebApplicationException("El usuario con el id " + usuarioId + "que comento no existe.", 404);
        }

        comentarioEntity.setUsuarioI(usuario);
        comentarioEntity.setCancion(cancion);

        persistence.create(comentarioEntity);

        LOGGER.log(Level.INFO, "Termina el proceso de creación de un comentario a la canción.");
        return comentarioEntity;
    }

    public ComentarioEntity createComentarioVinilo(Long viniloId, Long usuarioId, ComentarioEntity comentarioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia el proceso de creación de un comentario al vinilo {0}", viniloId);

        if (comentarioEntity.getContenido() == null || comentarioEntity.getContenido().isEmpty()) {
            throw new BusinessLogicException(ERROR_VACIO);
        }

        if (comentarioEntity.getContenido().isEmpty()) {
            throw new BusinessLogicException("El comentario no puede estar vacio");
        }

        if (viniloPersistence.find(viniloId) == null) {
            throw new BusinessLogicException("El vinilo con el id: " + viniloId + " no existe.");
        }
        if (usuarioPersistence.find(usuarioId) == null) {
            throw new BusinessLogicException("El usuario que comento no existe.");
        }

        comentarioEntity.setVinilo(viniloPersistence.find(viniloId));
        comentarioEntity.setUsuarioI(usuarioPersistence.find(usuarioId));

        persistence.create(comentarioEntity);

        LOGGER.log(Level.INFO, "Termina el proceso de creación de un comentario al vinilo.");
        return comentarioEntity;
    }

    //-------------------------------------------------------
    // Getters de las listas de comentarios.
    //-------------------------------------------------------
    public List<ComentarioEntity> getComentariosToUsuarios(Long usuarioId) {
        return persistence.findAllToUsuario(usuarioId);
    }

    public List<ComentarioEntity> getComentariosToTransaccion(Long transaccionId) {
        return persistence.findAllToTransaccion(transaccionId);
    }

    public List<ComentarioEntity> getComentariosToCancion(Long cancionId) {
        return persistence.findAllToCancion(cancionId);
    }

    public List<ComentarioEntity> getComentariosToVinilo(Long viniloId) {
        return persistence.findAllToVinilo(viniloId);
    }

    public ComentarioEntity getComentario(long comentarioId) {
        LOGGER.log(Level.INFO, "Inicia el proceso de consultar el comentario con Id{0}", comentarioId);
        ComentarioEntity comentarioEntity = persistence.find(comentarioId);
        if (comentarioEntity == null) {
            LOGGER.log(Level.SEVERE, "El comentario con el id = {0} no existe", comentarioId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el comentario con id ={0}", comentarioId);
        return comentarioEntity;
    }

    public ComentarioEntity updateComentario(Long usuarioId, ComentarioEntity entity) 
    {
        UsuarioEntity usuario = usuarioPersistence.find(usuarioId);
        entity.setUsuarioI(usuario);
        persistence.update(entity);
        return entity;
    }

    public void deleteComentario(Long comentarioId) 
    {
        LOGGER.log(Level.INFO, "Iniciando borrar comentario con id{0}", comentarioId);
        persistence.delete(comentarioId);
        LOGGER.log(Level.INFO, "Se ha borrado el comentario con id{0}", comentarioId);
    }
    /**
     * linkhl was here :p
     */
}
