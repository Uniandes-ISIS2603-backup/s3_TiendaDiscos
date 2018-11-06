package co.edu.uniandes.csw.tiendadiscos.persistence;


import co.edu.uniandes.csw.tiendadiscos.entities.CarritoDeComprasEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Laura Isabella Forero Camacho
 */
@Stateless
public class CarritoDeComprasPersistence {
   
    @Inject
    private CarritoDeComprasPersistence carritoDeComprasPersistence;

    @PersistenceContext(unitName = "VinylAppPU")
    private EntityManager em;
    
    private static final Logger LOGGER = Logger.getLogger(CarritoDeComprasPersistence.class.getName());

   public CarritoDeComprasEntity create(CarritoDeComprasEntity carritoDeComprasEntity){
       LOGGER.log(Level.INFO, "Creando un carrito de compra nuevo");
       em.persist(carritoDeComprasEntity);
       LOGGER.log(Level.INFO, "Saliendo de crear un carrito de compras nuevo");
       return carritoDeComprasEntity;
   } 
   public CarritoDeComprasEntity find(Long carritoDeComprasId) {
        LOGGER.log(Level.INFO, "Consultando CarritoDeCompras con id={0}", carritoDeComprasId);
        return em.find(CarritoDeComprasEntity.class, carritoDeComprasId);

    }

    public CarritoDeComprasEntity update(CarritoDeComprasEntity carritoDeComprasEntity) {
        LOGGER.log(Level.INFO, "Actualizando CarritoDeCompras con id={0}", carritoDeComprasEntity.getId());

        LOGGER.log(Level.INFO, "Saliendo de actualizar CarritoDeCompras con id = {0}", carritoDeComprasEntity.getId());

        return em.merge(carritoDeComprasEntity);

    }

    public void delete(Long carritoDeComprasId) {
        LOGGER.log(Level.INFO, "Borrando un carrito de compras con id={0}", carritoDeComprasId);
        CarritoDeComprasEntity carritoDeComprasEntity = find( carritoDeComprasId);
        em.remove(carritoDeComprasEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar carrito compras con id = {0}",  carritoDeComprasId);

    }
}
