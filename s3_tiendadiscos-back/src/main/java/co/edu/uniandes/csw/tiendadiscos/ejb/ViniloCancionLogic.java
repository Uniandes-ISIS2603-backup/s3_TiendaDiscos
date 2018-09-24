/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;


import co.edu.uniandes.csw.tiendadiscos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendadiscos.persistence.CancionPersistence;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendadiscos.persistence.ViniloPersistence;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 *
 * @author Andrés Hernández
 */
@Stateless
public class ViniloCancionLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ViniloCancionLogic.class.getName());
    
    @Inject
    private ViniloPersistence viniloPersistence;
    
    @Inject
    private CancionPersistence cancionPersistence;
    
    /**
     * Asocia una canción existente a un Vinilo.
     * @param cancionId Identificador de la instancia de Canción.
     * @param viniloId Identificador de la instancia de Vinilo.
     * @return  Instancia de Cancionentity que fue asociada al vinilo.
     */
    public CancionEntity addCancion(Long cancionId, Long viniloId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una cancion al vinilo con id = {0}", viniloId);
        CancionEntity cancionEntity = cancionPersistence.find(cancionId);
        ViniloEntity viniloEntity = viniloPersistence.find(viniloId);
        viniloEntity.getCanciones().add(cancionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una cancion al vinilo con id = {0}", viniloId);
        return cancionEntity;
    }
    
    /**
     * Obtiene una colección de instancias de CancionEntity asociadas a una instancia de vinilo.
     * @param vinilosId Identificador de la instancia de Vinilo.
     * @return Colección de instancias de CancionEntity asociadas a una instancia de Vinilo.
     */
    public List<CancionEntity> getCanciones(Long vinilosId)
    {
        return viniloPersistence.find(vinilosId).getCanciones();
    }

    /**
     * Obtiene una instancia de CancionEntity asociada a una instancia de Vinilo
     * @param cancionId Identificador de una instancia de Cancion.
     * @param vinilosId Identificador de la instancia de Vinilo.
     * @return La entidad de la canción asociada con el Vinilo.
     */
    public CancionEntity getCancion(Long cancionId, Long vinilosId)
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de consulta de una canción con el id = {0}", cancionId);
        List<CancionEntity> canciones = viniloPersistence.find(vinilosId).getCanciones();
        CancionEntity cancionEntity = cancionPersistence.find(cancionId);
        int position = canciones.indexOf(cancionEntity);
        if(position >= 0)
            return canciones.get(position);
        return null;
    }
    
    /**
     * Reemplaza las instancias de Cancion asociadas a una instancia de Vinilo.
     * @param vinilosId Identificador de la instancia de Vinilo.
     * @param list Nueva colección de CancionEntity asociada a la instancia de Vinilo.
     * @return 
     */
    public List<CancionEntity> replaceCanciones(Long vinilosId, List<CancionEntity> list)
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de reemplazar las canciones del vinilo con id = {0}", vinilosId);
        ViniloEntity viniloEntity = viniloPersistence.find(vinilosId);
        viniloEntity.setCanciones(list);
        LOGGER.log(Level.INFO, "Termina el proceso de reemplazar las canciones del vinilo con el id = {0}", vinilosId);
        return viniloPersistence.find(vinilosId).getCanciones();
    }
    
    /**
     * Desasocia una cancion existente de un Vinilo existente.
     * 
     * @param vinilosId Identificador de la instancia del vinilo.
     * @param cancionId Identificador de la instancia de la canción.
     */
    public void removeCancion(Long vinilosId, Long cancionId)
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de borrar una canción del vinilo con id = {0}", vinilosId);
        ViniloEntity viniloEntity = viniloPersistence.find(vinilosId);
        CancionEntity cancionEntity = cancionPersistence.find(cancionId);
        viniloEntity.getCanciones().remove(cancionEntity);
        LOGGER.log(Level.INFO, "Termina el proceso de borrar una cancion del vinilo con id = {0}", vinilosId);
    }
}