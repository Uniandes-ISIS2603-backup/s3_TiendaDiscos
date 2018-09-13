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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Camilo Andres Salinas Martinez
 */
@Stateless
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
        LOGGER.log(Level.INFO, "Creando una tarjeta nueva");
        em.persist(envioEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una tarjeta nueva ");
        return envioEntity;
    }

     /**
     * Busca si hay algun envio con el id que se envía de argumento
     *
     * @param envioId: id correspondiente a la author buscada.
     * @return un usuario.
     */
     public EnvioEntity find(Long envioId) {
        LOGGER.log(Level.INFO, "Consultando Envio con id={0}", envioId);
        return em.find(EnvioEntity.class, envioId);

    }
     /**
     * Actualiza una envio.
     * @param envioEntity: la usuario que viene con los nuevos cambios.
     * @return una usuario con los cambios aplicados.
     */
     
    public EnvioEntity update(EnvioEntity envioEntity) {
        LOGGER.log(Level.INFO, "Actualizando tarjeta con id={0}", envioEntity.getId());

        LOGGER.log(Level.INFO, "Saliendo de actualizar tarjeta con id = {0}", envioEntity.getId());

        return em.merge(envioEntity);

    }
     /**
     * Borra un envio de la base de datos recibiendo como argumento el id del envio.
     * @param envioId: id correspondiente a la usuario a borrar.
     */
    
    public void delete(Long envioId) {
        LOGGER.log(Level.INFO, "Borrando tarjeta con id={0}", envioId);
        EnvioEntity envioEntity = find(envioId);
        em.remove(envioEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar tarjeta con id = {0}", envioId);

    }
 
    
    





    
}
