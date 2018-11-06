package co.edu.uniandes.csw.tiendadiscos.persistence;


import co.edu.uniandes.csw.tiendadiscos.entities.CarritoDeComprasEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;



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
    
    public CarritoDeComprasEntity findByUserId(Long userId){
       LOGGER.log(Level.INFO, "Consultando el Carrito de Compras asociado al usuario con el id"+userId);
       TypedQuery<CarritoDeComprasEntity> q = em.createQuery("select p from CarritoDeComprasEntity p where p.usuario.id = :usuarioId", CarritoDeComprasEntity.class);
       q.setParameter("usuarioId", userId);
       List<CarritoDeComprasEntity> results = q.getResultList();
       CarritoDeComprasEntity carrito = null;
       if(results == null){
           carrito = null;
       }else if(results.isEmpty()){
           carrito = null;
       }else if (results.size()>=1){
           carrito = results.get(0);
       }
       LOGGER.log(Level.INFO, "Saliendo de consultar el Carrito  del usuario con id =" + userId);
       return carrito;
    }
}