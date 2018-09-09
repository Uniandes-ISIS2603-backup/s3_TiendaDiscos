/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.persistence;


import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Camilo Andres Salinas Martinez
 *
 */
public class UsuarioPersistence {
     @PersistenceContext(unitName = "VinylAppPU")
    protected EntityManager em;

    public UsuarioEntity create(UsuarioEntity usuarioEntity) {

        em.persist(usuarioEntity);
        return usuarioEntity;
    }

}
