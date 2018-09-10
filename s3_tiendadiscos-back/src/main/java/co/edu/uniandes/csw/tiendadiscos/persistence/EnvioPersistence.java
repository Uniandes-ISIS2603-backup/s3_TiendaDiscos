/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.persistence;


import co.edu.uniandes.csw.tiendadiscos.entities.EnvioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Camilo Andres Salinas Martinez
 */
public class EnvioPersistence {
    private static final Logger LOGGER = Logger.getLogger(EnvioPersistence.class.getName());
    @PersistenceContext(unitName = "VinylAppPU")
    protected EntityManager em;

   /**
     * Crea un envio en la base de datos
     * @param EnvioEntity objeto author que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public EnvioEntity create(EnvioEntity envioEntity) {
        LOGGER.log(Level.INFO, "Creando un Envio nuevo");     
        em.persist(envioEntity);
        LOGGER.log(Level.INFO, "Envio creado");
        return envioEntity;
    }
    /**
     * Devuelve todas los envios de la base de datos.
     * @return una lista con todas los usuarios que encuentre en la base de datos
     */
    public List<EnvioEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los autores");
        TypedQuery query = em.createQuery("select u from EnvioEntity u", EnvioEntity.class);
        return query.getResultList();
    }
     /**
     * Busca si hay algun envio con el id que se envía de argumento
     *
     * @param envioId: id correspondiente a la author buscada.
     * @return un usuario.
     */
    public EnvioEntity find(Long envioId) {
        LOGGER.log(Level.INFO, "Consultando el envio con id={0}", envioId);
        return em.find(EnvioEntity.class, envioId);
    }
     /**
     * Actualiza una envio.
     * @param envioEntity: la usuario que viene con los nuevos cambios.
     * @return una usuario con los cambios aplicados.
     */
    public EnvioEntity update(EnvioEntity envioEntity) {
        LOGGER.log(Level.INFO, "Actualizando el envio con id={0}", envioEntity.getId());

        return em.merge(envioEntity);
    }
     /**
     * Borra un envio de la base de datos recibiendo como argumento el id del envio.
     * @param envioId: id correspondiente a la usuario a borrar.
     */
    public void delete(Long envioId) {
        LOGGER.log(Level.INFO, "Borrando el envio con id={0}", envioId);       
        EnvioEntity envioEntity = em.find(EnvioEntity.class, envioId);
        em.remove(envioEntity);
    }

}
