package co.edu.uniandes.csw.tiendadiscos.persistence;

import co.edu.uniandes.csw.tiendadiscos.entities.CarritoDeComprasEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
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

    public CarritoDeComprasEntity create(CarritoDeComprasEntity carritoDeComprasEntity) {
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
        CarritoDeComprasEntity carritoDeComprasEntity = find(carritoDeComprasId);
        em.remove(carritoDeComprasEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar carrito compras con id = {0}", carritoDeComprasId);
    }

    public CarritoDeComprasEntity findByUserId(Long userId) {
        LOGGER.log(Level.INFO, "Consultando el Carrito de Compras asociado al usuario con el id = {0}", userId);
        TypedQuery<CarritoDeComprasEntity> q = em.createQuery("select p from CarritoDeComprasEntity p where p.usuario.id = :usuarioId", CarritoDeComprasEntity.class);
        q.setParameter("usuarioId", userId);
        List<CarritoDeComprasEntity> results = q.getResultList();
        CarritoDeComprasEntity carrito = null;
        if (!results.isEmpty()) {
            carrito = results.get(0);
        }

        LOGGER.log(Level.INFO, "Saliendo de consultar el Carrito  del usuario con id ={0}", userId);
        return carrito;
    }

  /*  public Long findViniloEnCarrito(Long carritoId, Long viniloId) {
        LOGGER.log(Level.INFO, "Consultando el vinilo con id" + viniloId + " del carrito de Compras con el id = {0}", carritoId);
        TypedQuery<Long> q = em.createQuery("select vinilos_id from VINILOENTITY_CARRITODECOMPRASENTITY p where p.carritosdecompras_id = :carritoId AND p.vinilos_id = :viniloId", Long.class);
        q.setParameter("carritoId", carritoId);
        q.setParameter("carritoId", viniloId);

        List<Long> results = q.getResultList();
        Long vinilo = null;
        if (!results.isEmpty()) {
            vinilo = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar el vinilo con id" + viniloId + " del carrito de Comprascon el id = {0}" + vinilo.toString(), carritoId);
        return vinilo;
    }
*/
}
