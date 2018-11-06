/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;

import co.edu.uniandes.csw.tiendadiscos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendadiscos.persistence.CancionPersistence;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 *  Clase que implementa la conexión con la persistencia para la entidad de la canción.
 * @author Andrés Hernández
 */
@Stateless
public class CancionLogic 
{
    private static final Logger LOGGER = Logger.getLogger(CancionLogic.class.getName());
    
    @Inject
    private CancionPersistence persistence;
    
    /**
     * Crea una canción en la persitence.
     * No hay restricciones para registrarlo.
     * 
     * @param cancionEntity Canción que se desea registrar.
     * @return canción que se agrego a persistence.
     */
    public CancionEntity createCancion(CancionEntity cancionEntity)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la canción.");
        
        persistence.create(cancionEntity);
        LOGGER.log(Level.INFO, "Termina el proceso de creación de la canción.");
        return cancionEntity;
    }
    
    /**
     * OBtiene todas las canciones en la persistencia.
     * @return Lista de canciones.
     */
    public List<CancionEntity> getCanciones()
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de consulta de todos las canciones");
        List<CancionEntity> canciones = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina el proceso de consultar todos los libros.");
        return canciones;
    }
    
    /**
     * Obtiene una canción por medio de su id.
     * @param cancionId Id de la canción a ser buscada.
     * @return La canción asociada al id.
     */
    public CancionEntity getCancion(Long cancionId)
    {
        LOGGER.log(Level.INFO, "Incia el proceso de consulta de la cancion con el id = {0}", cancionId);
        CancionEntity cancionEntity = persistence.find(cancionId);
        if(cancionEntity == null)
            LOGGER.log(Level.INFO, "La canción con el id = {0} no existe", cancionId);
        LOGGER.log(Level.INFO, "Termina el proceso de consulta de la canción con el id = {0}");
        return cancionEntity;
    }
    
    /**
     * Actualizar una canción.
     * 
     * @param cancionId Id de la canción que se desea actualizar.
     * @param cancionEntity Objeto entity con la nueva información de la canción.
     * @return Canción actualizada.
     */
    public CancionEntity updateCancion(Long cancionId, CancionEntity cancionEntity)
    {
        LOGGER.log(Level.INFO, "Inicia el proceso para actualizar a la canción con el id = {0}", cancionId);
        CancionEntity newEntity = persistence.update(cancionEntity);
        LOGGER.log(Level.INFO, "Termina el proceso de actualizar a la canción con el id = {0}", cancionId);
        return newEntity;
    }
    
    /**
     * Elimina una canción por el id.
     * @param cancionId Id de la canción a borrar.
     */
    public void deleteCancion(Long cancionId)
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de borrar la cancion con el id = {0}", cancionId);
        persistence.delete(cancionId);
        LOGGER.log(Level.INFO, "Termina el proceso de borrar la cancion con el id = {0}", cancionId);
    }
}