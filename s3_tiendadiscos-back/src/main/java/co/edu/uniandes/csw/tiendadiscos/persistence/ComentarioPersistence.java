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
    
    public List<ComentarioEntity> findAllToVinilo(Long viniloId)
    {
        TypedQuery<ComentarioEntity> q = em.createQuery("select p from ComentarioEntity p where (p.vinilo.id = :viniloId)", ComentarioEntity.class);
        q.setParameter("viniloId", viniloId);
        return q.getResultList();
    }

    public List<ComentarioEntity> findAllToUsuario(Long usuarioId)
    {
        TypedQuery<ComentarioEntity> q = em.createQuery("select u from ComentarioEntity u where (u.usuarioDestino.id = :usuarioId)",ComentarioEntity.class);
        q.setParameter("usuarioId", usuarioId);
        return q.getResultList();
    }

    public List<ComentarioEntity> findAllToCancion(Long cancionId)
    {
        TypedQuery<ComentarioEntity> q = em.createQuery("select p from ComentarioEntity p where (p.cancion.id = :cancionId)", ComentarioEntity.class);
        q.setParameter("cancionId", cancionId);
        return q.getResultList();
    }

    public List<ComentarioEntity> findAllToTransaccion(Long transaccionId)
    {
        TypedQuery<ComentarioEntity> q = em.createQuery("select p from ComentarioEntity p where (p.transaccion.id = :transaccionId)", ComentarioEntity.class);
        q.setParameter("transaccionId", transaccionId);
        return q.getResultList();
    }
}
