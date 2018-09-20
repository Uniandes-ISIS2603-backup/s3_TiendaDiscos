/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;

import co.edu.uniandes.csw.tiendadiscos.entities.EnvioEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.EnvioPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Camilo Andres Salinas Martinez
 */
@Stateless
public class EnvioLogic {
    private static final Logger LOGGER = Logger.getLogger(EnvioLogic.class.getName());

    @Inject
    private EnvioPersistence persistence;

    /**
     * Se encarga de crear un Envio en la base de datos.
     *
     * @param envioEntity Objeto de EnvioEntity con los datos nuevos
     * @return Objeto de EnvioEntity con los datos nuevos y su ID.
     */
    public EnvioEntity createAuthor(EnvioEntity envioEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del envio");
        EnvioEntity newEnvioEntity = persistence.create(envioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del envio");
        return newEnvioEntity;
    }
    /**
     * Obtiene los datos de una instancia de Envio a partir de su ID.
     *
     * @param usuarioId Identificador de la instancia a consultar
     * @return Instancia de AuthorEntity con los datos del Author consultado.
     */
    public EnvioEntity getEnvio(Long usuarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el envio del usuario con id = {0}", usuarioId);
        EnvioEntity envioEntity = persistence.find(usuarioId);
        if (envioEntity == null) {
            LOGGER.log(Level.SEVERE, "El envio del usuario con el id = {0} no existe", usuarioId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el el envio del usuario con id = {0}", usuarioId);
        return envioEntity;
    }

    /**
     * Actualiza la información de una instancia de Envio.
     *
     * @param usuarioId Identificador de la instancia a actualizar
     * @param authorEntity Instancia de EnvioEntity con los nuevos datos.
     * @return Instancia de EnvioEntity con los datos actualizados.
     */
    public EnvioEntity updateEnvio(Long usuarioId, EnvioEntity envioEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el envio del usuario con id = {0}", usuarioId);
        EnvioEntity newEnvioEntity = persistence.update(envioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el envio del usuario con id = {0}", usuarioId);
        return newEnvioEntity;
    }

    /**
     * Elimina una instancia de Envio de la base de datos.
     *
     * @param usuarioId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el autor tiene libros asociados.
     */
    public void deleteAuthor(Long usuarioId) throws BusinessLogicException {

        persistence.delete(usuarioId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el autor con id = {0}", usuarioId);
    }
}
