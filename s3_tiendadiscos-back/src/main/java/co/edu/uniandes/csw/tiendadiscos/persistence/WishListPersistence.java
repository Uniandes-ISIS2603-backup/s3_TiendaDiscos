/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.persistence;

import co.edu.uniandes.csw.tiendadiscos.entities.WishListEntity;
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
 * @author Sebastian Martinez
 */
@Stateless
public class WishListPersistence {
    
 
    
    @PersistenceContext(unitName = "VinylAppPU")
    protected EntityManager em;
    
    private static final Logger LOGGER = Logger.getLogger(CarritoDeComprasPersistence.class.getName());
    
    public WishListEntity create(WishListEntity wish)
    {
        em.persist(wish);
        return wish;
    }
    
    public WishListEntity update(WishListEntity wish)
    {
        em.merge(wish);
       
        return wish;
    }
    
    public void delete(Long id)
    {
        WishListEntity wish = em.find(WishListEntity.class, id);
        em.remove(wish);
    }
    
    public WishListEntity find(Long wishlistId)
    {
        LOGGER.log(Level.INFO, "Consultando WishList con id={0}", wishlistId);
        return em.find(WishListEntity.class, wishlistId); 
    }
 
}
