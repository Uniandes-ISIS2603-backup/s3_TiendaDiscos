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
import javax.persistence.TypedQuery;

/**
 *
 * @author Andrés Hernández
 */
@Stateless
public class ViniloPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ViniloPersistence.class.getName());
    
    @PersistenceContext(unitName= "VinylAppPU")
    protected EntityManager em;
    
    /**
     * Crea un vinilo en la base de datos.
     * 
     * @param viniloEntity objeto vinilo que creará en la base de datos.
     * @return Devuelve la entidad creada con un id dado por la base de datos.
     */
    public ViniloEntity create(ViniloEntity viniloEntity)
    {
        LOGGER.log(Level.INFO, "Creando un vinilo nuevo");
        em.persist(viniloEntity);
        LOGGER.log(Level.INFO, "Vinilo creado");
        return viniloEntity;
    }
    
    /**
     * Devuelve todas los vinilos de la base de datos.
     * 
     * @return una lista con todos los vinilos que encuentre en la base de datos.
     */
    public List<ViniloEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Consultando todos los vinilos");
        TypedQuery q = em.createQuery("select u from ViniloEntity u", ViniloEntity.class);
        return q.getResultList();
    }
    
    /**
     * Devuelve todos los vinilos de un usuario.
     * @param usuarioId - El usuario dueño de los vinilos.
     * @return Todos los vinilos de un usuario de la base de datos.
     */
    public List<ViniloEntity> findAllByUsuario(Long usuarioId)
    {
        LOGGER.log(Level.INFO , "Consultando todos los vinilos del usuario:{0}", usuarioId );
         TypedQuery<ViniloEntity> q = em.createQuery("select p from ViniloEntity p where (p.usuario.id = :usuarioId)", ViniloEntity.class);
        q.setParameter("usuarioId", usuarioId);
        return q.getResultList();
    }
    
    /**
     * Busca si hay algun Vinilo con el id que se envía de argumento.
     * 
     * @param viniloId: id correspondiente al vinilo buscado.
     * @return un vinilo.
     */
    public ViniloEntity find(Long viniloId)
    {
        LOGGER.log(Level.INFO, "Consultando el vinilo con id={0}", viniloId);
        return em.find(ViniloEntity.class, viniloId);
    }
    
    /**
     * Actualiza un vinilo.
     * 
     * @param viniloEntity: el vinilo que viene con los nuevos cambios.
     * @return el vinilo con los cambios aplicados.
     */
    public ViniloEntity update(ViniloEntity viniloEntity)
    {
        LOGGER.log(Level.INFO, "Actualizando el vinilo con id={0}", viniloEntity.getId());
        return em.merge(viniloEntity);
    }
    
    /**
     * Borra un vinilo de la base de datos recibiendo como argumento el id del vinilo.
     * 
     * @param viniloId: id correspondiente al vinilo a borrar.
     */
    public void delete(Long viniloId)
    {
        LOGGER.log(Level.INFO, "Borrando el vinilo con id={0}", viniloId);
        ViniloEntity viniloEntity = em.find(ViniloEntity.class, viniloId);
        em.remove(viniloEntity);
    }
}