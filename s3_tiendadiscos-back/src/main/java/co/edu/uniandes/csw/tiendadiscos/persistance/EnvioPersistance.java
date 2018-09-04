/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.persistance;


import co.edu.uniandes.csw.tiendadiscos.entities.EnvioEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Camilo Andres Salinas Martinez
 */
public class EnvioPersistance {
    
         @PersistenceContext(unitName = "VinylAppPU")
    protected EntityManager em;

    public EnvioEntity create(EnvioEntity envioEntity) {

        em.persist(envioEntity);
        return envioEntity;
    }
    
    
    
}
