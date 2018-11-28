/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.persistence;

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

    @PersistenceContext(unitName = "VinylAppPU")
    protected EntityManager em;

    /**
     * Método para persistir una canción en la base de datos.
     *
     * @param cancionEntity Objeto cancion que se creará en la base de datos.
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CancionEntity create(CancionEntity cancionEntity) {
        LOGGER.log(Level.INFO, "Creando una nueva cancion");
        em.persist(cancionEntity);
        LOGGER.log(Level.INFO, "Cancion creada");
        return cancionEntity;
    }

    public List<CancionEntity> findCancionesUsuario(Long viniloId) {
        LOGGER.log(Level.INFO, "Consultando todos las canciones del vinilo:{0}", viniloId);
        TypedQuery<CancionEntity> q = em.createQuery("select p from CancionEntity p where (p.vinilo.id = :viniloId)", CancionEntity.class);
        q.setParameter("viniloId", viniloId);
        return q.getResultList();
    }

    public List<CancionEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las canciones.");
        Query q = em.createQuery("select u from CancionEntity u");
        return q.getResultList();
    }

    public CancionEntity find(Long cancionId, Long viniloId) {
        LOGGER.log(Level.INFO, "Consultando cancion con id={0} del vinilo con id = {1}", new Object[]{cancionId,viniloId});
        TypedQuery<CancionEntity> q = em.createQuery("select p from CancionEntity p where (p.vinilo.id = :viniloId) and (p.id = :cancionId)", CancionEntity.class);
        q.setParameter("viniloId", viniloId);
        q.setParameter("cancionId", cancionId);
        List<CancionEntity> results = q.getResultList();
        CancionEntity cancion = null;
        if (results.size() >= 1) 
            cancion = results.get(0);

        LOGGER.log(Level.INFO, "Saliendo de consultar la cancion  con id = {0} del vinilo con id ={1}", new Object[]{cancionId,viniloId});
        return cancion;
    }

    public CancionEntity update(CancionEntity cancionEntity) {
        LOGGER.log(Level.INFO, "Actualizando la canción con el id={0}", cancionEntity.getId());
        return em.merge(cancionEntity);
    }

    public void delete(Long cancionId) {
        LOGGER.log(Level.INFO, "Borrando la canción con el id={0}", cancionId);
        CancionEntity cancionEntity = em.find(CancionEntity.class, cancionId);
        em.remove(cancionEntity);
    }
}
