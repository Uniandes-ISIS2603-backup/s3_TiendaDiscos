/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.persistance;

import co.edu.uniandes.csw.tiendadiscos.entities.CancionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andrés Hernández 
 */
@Stateless
public class CancionPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(CancionPersistence.class.getName());
    
    @PersistenceContext(unitName= "VinylAppPU")
    protected EntityManager em;
    
    /**
     * Método para persistir una canción en la base de datos.
     * @param cancionEntity Objeto cancion que se creará en la base de datos.
     * @return  devuelve la entidad creada con un id dado por la base de datos.
     */
    public CancionEntity create(CancionEntity cancionEntity)
    {
        LOGGER.log(Level.INFO , "Creando una nueva cancion");
        em.persist(cancionEntity);
        LOGGER.log(Level.INFO, "Cancion creada");
        return cancionEntity;
    }
    
    public List<CancionEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Consultando todas las canciones.");
        Query q = em.createQuery("select u from CancionEntity u");
        return q.getResultList();
    }
    
    public CancionEntity find(Long cancionId)
    {
        LOGGER.log(Level.INFO, "Consultando la canción con el id={0}" , cancionId);
        return em.find(CancionEntity.class, cancionId);
    }
    
    public CancionEntity update(CancionEntity cancionEntity)
    {
        LOGGER.log(Level.INFO, "Actualizando la canción con el id={0}", cancionEntity.getId());
        return em.merge(cancionEntity);
    }
    
    public void delete(Long cancionId)
    {
        LOGGER.log(Level.INFO, "Borrando la canción con el id={0}", cancionId);
        CancionEntity cancionEntity = em.find(CancionEntity.class, cancionId);
        em.remove(cancionEntity);
    }    
}
