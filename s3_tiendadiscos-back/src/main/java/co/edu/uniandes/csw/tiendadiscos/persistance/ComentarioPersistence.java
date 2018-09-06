/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.persistance;
import co.edu.uniandes.csw.tiendadiscos.entities.ComentarioEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Sebastian Martinez
 */
@Stateless 
public class ComentarioPersistence {

    private static final Logger LOGGER = Logger.getLogger(ComentarioPersistence.class.getName());
        
    @PersistenceContext(unitName = "VinylAppPU")
    protected EntityManager em;
    
    public ComentarioEntity create(ComentarioEntity comentarioEntity) {
        LOGGER.log(Level.INFO, "Creando un comentario nuevo");
        em.persist(comentarioEntity);
        LOGGER.log(Level.INFO, "Libro creado");
        return comentarioEntity;
    }

}
