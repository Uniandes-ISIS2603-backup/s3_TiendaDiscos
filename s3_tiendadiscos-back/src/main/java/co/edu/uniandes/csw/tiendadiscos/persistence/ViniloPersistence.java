/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.persistence;

import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Andrés Hernández
 */
@Stateless
public class ViniloPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ViniloPersistence.class.getName());
    
    @PersistenceContext(unitName= "VinylAppPU")
    protected EntityManager em;
    
    
    public ViniloEntity create(ViniloEntity viniloEntity)
    {
        LOGGER.log(Level.INFO, "Creando un vinilo nuevo");
        em.persist(viniloEntity);
        LOGGER.log(Level.INFO, "Vinilo creado");
        return viniloEntity;
    }
    
    public List<ViniloEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Consultando todos los vinilos");
        Query q = em.createQuery("select u from ViniloEntity u");
        return q.getResultList();
    }
    
    public ViniloEntity find(Long viniloId)
    {
        LOGGER.log(Level.INFO, "Consultando el libro con id={0}", viniloId);
        return em.find(ViniloEntity.class, viniloId);
    }
    
    public ViniloEntity update(ViniloEntity viniloEntity)
    {
        LOGGER.log(Level.INFO, "Actualizando el vinilo con id={0}", viniloEntity.getId());
        return em.merge(viniloEntity);
    }
    
    public void delete(Long viniloId)
    {
        LOGGER.log(Level.INFO, "Borrando el libro con id={0}", viniloId);
        ViniloEntity bookEntity = em.find(ViniloEntity.class, viniloId);
        em.remove(bookEntity);
    }
}
