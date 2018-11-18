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
 * @author Sebastian Martinez y Andrés :)
 */
@Stateless 
public class ComentarioPersistence {

    private static final Logger LOGGER = Logger.getLogger(ComentarioPersistence.class.getName());
    

    @PersistenceContext(unitName = "VinylAppPU")
    protected EntityManager em;
    
    public ComentarioEntity create(ComentarioEntity comentarioEntity) 
    {
        LOGGER.log(Level.INFO, "Creando un comentario nuevo.");
        em.persist(comentarioEntity);
        LOGGER.log(Level.INFO, "Comentario creado");
        return comentarioEntity;
    }
    
    public ComentarioEntity update(ComentarioEntity comentarioEntity)
    {
        LOGGER.log(Level.INFO, "Actualizando el comentario con id={0}", comentarioEntity.getId());
        return em.merge(comentarioEntity);
    }
    
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando el Comentario con id{0}" , id);
        ComentarioEntity temp = em.find(ComentarioEntity.class,id);
        em.remove(temp);
    }
    
    //-------------------------------------------------------
    // Getters de las listas de comentarios.
    //-------------------------------------------------------


    public List<ComentarioEntity> findAllToVinilo(Long viniloId)
    {
        LOGGER.log(Level.INFO, "Consultando todos los comentarios al vinilo con id = {0}", viniloId);
        TypedQuery<ComentarioEntity> q = em.createQuery("select p from ComentarioEntity p where (p.vinilo.id = :viniloId)", ComentarioEntity.class);
        q.setParameter("viniloId", viniloId);
        return q.getResultList();
    }


    public List<ComentarioEntity> findAllToUsuario(Long usuarioId)
    {
        LOGGER.log(Level.INFO, "Consultando todos los comentarios al usuario con id = {0}" , usuarioId);
        TypedQuery<ComentarioEntity> q = em.createQuery("select u from ComentarioEntity u where (u.usuarioDestino.id = :usuarioId)",ComentarioEntity.class);
        q.setParameter("usuarioId", usuarioId);
        return q.getResultList();
    }


    public List<ComentarioEntity> findAllToCancion(Long cancionId)
    {
        LOGGER.log(Level.INFO, "Consultando todos los comentarios a la cancion con el id = {0}" , cancionId);
        TypedQuery<ComentarioEntity> q = em.createQuery("select p from ComentarioEntity p where (p.cancion.id = :cancionId)", ComentarioEntity.class);
        q.setParameter("cancionId", cancionId);
        return q.getResultList();
    }


    public List<ComentarioEntity> findAllToTransaccion(Long transaccionId)
    {
        LOGGER.log(Level.INFO, "Consultando todos los comentarios a la transacción con id = {0}" , transaccionId);
        TypedQuery<ComentarioEntity> q = em.createQuery("select p from ComentarioEntity p where (p.transaccion.id = :transaccionId)", ComentarioEntity.class);
        q.setParameter("transaccionId", transaccionId);
        return q.getResultList();
    }

    public ComentarioEntity find(Long comentarioId)
    {
        LOGGER.log(Level.INFO, "Consultando el comentario con id{0} ", comentarioId);
        return em.find(ComentarioEntity.class, comentarioId);
    }
    /** linkhl was here :p */
}