/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.persistence;


import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


/**
 *
 * @author Camilo Andres Salinas Martinez
 *
 */
@Stateless
public class UsuarioPersistence {
    private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());
    @PersistenceContext(unitName = "VinylAppPU")
    protected EntityManager em;
    
    
    
    /**
     * Crea un usuario en la base de datos
     * @param UsuarioEntity objeto author que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    
    public UsuarioEntity create(UsuarioEntity usuarioEntity) {
        LOGGER.log(Level.INFO, "Creando un Usuario nuevo");     
        em.persist(usuarioEntity);
        LOGGER.log(Level.INFO, "Usuario creado");
        return usuarioEntity;
    }
    /**
     * Devuelve todas los usuarios de la base de datos.
     * @return una lista con todas los usuarios que encuentre en la base de datos
     */
    public List<UsuarioEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los autores");
        TypedQuery query = em.createQuery("select u from UsuarioEntity u", UsuarioEntity.class);
        return query.getResultList();
    }
     /**
     * Busca si hay algun usuario con el id que se envía de argumento
     *
     * @param usuarioId: id correspondiente a la author buscada.
     * @return un usuario.
     */
    public UsuarioEntity find(Long usuarioId) {
        LOGGER.log(Level.INFO, "Consultando el usuario con id={0}", usuarioId);
        return em.find(UsuarioEntity.class, usuarioId);
    }
    /**
     * Busca si hay algun usuario con el email que se envía de argumento
     *
     * @param email: email del usuario que se está buscando
     * @return null si no existe ningun usuario con el email del argumento. Si
     * existe alguno devuelve el primero.
     */
    public UsuarioEntity findByEmail(String email) {
        LOGGER.log(Level.INFO, "Consultando libros por isbn ", email);
        // Se crea un query para buscar usuarios con el email que recibe el método como argumento. ":email" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From UsuarioEntity e where e.email = :email", UsuarioEntity.class);
        // Se remplaza el placeholder ":email" con el valor del argumento 
        query = query.setParameter("email", email);
        // Se invoca el query se obtiene la lista resultado
        List<UsuarioEntity> sameEmail = query.getResultList();
        UsuarioEntity result;
        if (sameEmail == null) {
            result = null;
        } else if (sameEmail.isEmpty()) {
            result = null;
        } else {
            result = sameEmail.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar usuarios por email ",email);
        return result;
    }
     /**
     * Actualiza una usuario.
     * @param usuarioEntity: la usuario que viene con los nuevos cambios.
     * @return una usuario con los cambios aplicados.
     */
    
    public UsuarioEntity update(UsuarioEntity usuarioEntity) {
        LOGGER.log(Level.INFO, "Actualizando el usuario con id={0}", usuarioEntity.getId());
        return em.merge(usuarioEntity);
    }
     /**
     * Borra un usuario de la base de datos recibiendo como argumento el id del usuario.
     * @param usuarioId: id correspondiente a la usuario a borrar.
     */
    
    public void delete(Long usuarioId) {
        LOGGER.log(Level.INFO, "Borrando el usuario con id={0}", usuarioId);     
        UsuarioEntity usuarioEntity = em.find(UsuarioEntity.class, usuarioId);
        System.out.println("usr "+ usuarioEntity);
        em.remove(usuarioEntity);
    }

}
