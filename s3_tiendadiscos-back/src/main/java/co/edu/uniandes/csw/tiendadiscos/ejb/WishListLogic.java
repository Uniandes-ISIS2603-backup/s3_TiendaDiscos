/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;


import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.WishListEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.WishListPersistence;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;


/**
 *
 * @author Laura Isabella Forero Camacho
 */
@Stateless
public class WishListLogic {
    
    private static final Logger LOGGER = Logger.getLogger(WishListLogic.class.getName());

    @Inject
    public WishListPersistence wishlistPersistence;
    
    @Inject
    public UsuarioPersistence usuarioPersistence;
    
    /**
     * Se encarga de crear un Carrito de compras en la base de datos
     * 
     * @param usuarioId id del usuario el cual será el padre del nuevo CarritoDeCompras.
     * @param entity Objeto de CarritoDeComprasEntity con los datos nuevos y su ID.
     * @return Objeto de CarritoDeComprasEntity con los nuevos datos y su ID.
     * @throws BusinessLogicException si el usuario con id UsuarioId ya tiene asignado un carrito.
     *                                Si el usuario con id usuarioId no existe.
     */
    public WishListEntity create(Long usuarioId,WishListEntity entity)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de creación de Carrito de compras.");

        UsuarioEntity usuario = usuarioPersistence.find(usuarioId);
        if (usuario == null)           
            throw new BusinessLogicException("No existe el usuario con ese id");

        if(wishlistPersistence.find(usuarioId)!=null)
        {
            throw new BusinessLogicException("El usuario ya tiene un carrito de compras");
        }
        entity.setUsuario(usuario);

        

        wishlistPersistence.create(entity);
        LOGGER.log(Level.INFO, "Termina el proceso de creación de Carrito de compras.");
        return entity;
    }
    
    public WishListEntity get(Long usuarioId)
    {
        UsuarioEntity user = usuarioPersistence.find(usuarioId);
        if(user == null)
            throw new WebApplicationException("El Usuario con el id "+usuarioId+" no existe.", 404);

        WishListEntity carrito = usuarioPersistence.find(usuarioId).getWishList();
        if(carrito == null)
            throw new WebApplicationException("El usuario con el id " + usuarioId + " no tiene un Carrito de compras", 404);

        return carrito;
    }
    
    public WishListEntity update(WishListEntity wishList,Long usuarioId) throws BusinessLogicException    {
        LOGGER.log(Level.INFO , "Inicia el proceso de actualizar el Carrito de compras del usuario con el id{0}", usuarioId);
        if(usuarioPersistence.find(usuarioId).getCarritoCompras() == null)
            throw new BusinessLogicException("El usuario no tiene asociado un carrito.");

        WishListEntity newEntity = wishlistPersistence.update(wishList);
        LOGGER.log(Level.INFO, "Termina el proceso de actualizar el carrito de compras con id{0}", newEntity.getId());
        return newEntity;
    }
    
    
    public void delete(Long usuarioId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de borrar el carrito de compras del usuario con id={0}", usuarioId);
        WishListEntity carrito = usuarioPersistence.find(usuarioId).getWishList();
        if(carrito == null)
            throw new BusinessLogicException("El usuario no tiene un carrito de compras asociado.");

        wishlistPersistence.delete(usuarioId);
        LOGGER.log(Level.INFO, "Termina el proceso de borrar el carrito de compras del usuario con id{0}" , usuarioId);
    }    
}