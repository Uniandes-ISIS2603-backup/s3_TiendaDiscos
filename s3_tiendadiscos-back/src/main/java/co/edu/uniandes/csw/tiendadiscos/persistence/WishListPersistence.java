/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.persistence;

import co.edu.uniandes.csw.tiendadiscos.entities.WishListEntity;
import java.util.List;
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
    
    public WishListEntity find(Long id)
    {
        WishListEntity wish = em.find(WishListEntity.class, id);
        return wish;
    }
    public List<WishListEntity> findAll()
    {
        TypedQuery<WishListEntity> q = em.createQuery("select u from WishListEntity u",WishListEntity.class);
        return q.getResultList();
    }
}
