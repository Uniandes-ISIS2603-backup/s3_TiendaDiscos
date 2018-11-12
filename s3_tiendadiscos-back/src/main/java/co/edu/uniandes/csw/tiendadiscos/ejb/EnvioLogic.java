/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;

import co.edu.uniandes.csw.tiendadiscos.entities.EnvioEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.EnvioPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.TransaccionPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Camilo Andres Salinas Martinez
 */
@Stateless
public class EnvioLogic 
{
    private static final Logger LOGGER = Logger.getLogger(EnvioLogic.class.getName());

    @Inject
    private TransaccionPersistence transaccionPersistence;
    
    @Inject
    private EnvioPersistence persistence;

    /**
     * Se encarga de crear un Envio en la base de datos.
     *
     * @param envioEntity Objeto de EnvioEntity con los datos nuevos
     * @return Objeto de EnvioEntity con los datos nuevos y su ID.
     */
    public EnvioEntity create(EnvioEntity envioEntity, long transaccionId)throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del envio");
        
        TransaccionEntity transaccion = transaccionPersistence.find(transaccionId);
        if (transaccion == null)
            throw new BusinessLogicException("No existe la transacción asociada a este envio.");
        if(persistence.find(transaccionId)!=null)
            throw new BusinessLogicException("La transacción ya tiene un envio asociado.");
        envioEntity.setTransaccion(transaccion);
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
    public EnvioEntity getEnvio(Long transaccionId) throws BusinessLogicException, WebApplicationException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el envio de la transaccion con id = {0}", transaccionId);
        
        TransaccionEntity transaccion = transaccionPersistence.find(transaccionId);
        if(transaccion == null)
            throw new WebApplicationException("La transacción con el id "+transaccionId+" no existe.", 404);
        
        EnvioEntity envioEntity = persistence.find(transaccionId);
        if (envioEntity == null)
            throw new WebApplicationException("La transaccion con el id " + transaccionId + " no tiene un Envio asociado", 404);
        
        LOGGER.log(Level.INFO, "Termina proceso de consultar el el envio de la transaccion con id = {0}", transaccionId);
        return envioEntity;
    }

    /**
     * Actualiza la información de una instancia de Envio.
     *
     * @param transaccionId Identificador de la instancia a actualizar
     * @param envioEntity Instancia de EnvioEntity con los nuevos datos.
     * @return Instancia de EnvioEntity con los datos actualizados.
     * @throws BusinessLogicException si la transaccion no tiene asociado un envio.
     */
    public EnvioEntity updateEnvio(Long transaccionId, EnvioEntity envioEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el envio asociado a la transaccion con id = {0}", transaccionId);
        if(transaccionPersistence.find(transaccionId).getEnvio() == null)
            throw new BusinessLogicException("La transaccion no tiene asociado un envio.");
        
        EnvioEntity newEnvioEntity = persistence.update(envioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el envio a la transacción con id = {0}", transaccionId);
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