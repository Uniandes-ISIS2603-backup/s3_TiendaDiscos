/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.persistence;
import co.edu.uniandes.csw.tiendadiscos.entities.ComentarioEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.WishListEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Sebastian Martinez
 */
@Stateless 
public class ComentarioPersistence {

        
    @PersistenceContext(unitName = "VinylAppPU")
    protected EntityManager em;
    
    public ComentarioEntity create(ComentarioEntity comentarioEntity) {
        em.persist(comentarioEntity);
        return comentarioEntity;
    }
    
    public ComentarioEntity update(ComentarioEntity comentarioEntity)
    {
        em.merge(comentarioEntity);
        return comentarioEntity;
    }
    
    public void delete(Long id)
    {
        ComentarioEntity temp = em.find(ComentarioEntity.class,id);
        em.remove(temp);
    }
    public ComentarioEntity find(Long id)
    {
        return em.find(ComentarioEntity.class,id);
    }
    public List<ComentarioEntity> findAllHechos(Long usuarioId)
    {
        TypedQuery<ComentarioEntity> q = em.createQuery("select u from ComentarioEntity u where (u.usuarioInicio.id = :usuarioId)",ComentarioEntity.class);
        q.setParameter("usuarioId", usuarioId);
        return q.getResultList();
    }
    
        public List<ComentarioEntity> findAllRecibidos(Long usuarioId)
    {
        TypedQuery<ComentarioEntity> q = em.createQuery("select u from ComentarioEntity u where (u.usuarioDestino.id = :usuarioId)",ComentarioEntity.class);
        q.setParameter("usuarioId", usuarioId);
        return q.getResultList();
    }
}
