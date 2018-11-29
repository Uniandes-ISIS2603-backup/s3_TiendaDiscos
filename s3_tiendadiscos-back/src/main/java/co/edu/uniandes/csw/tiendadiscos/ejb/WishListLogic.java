/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;

import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.WishListEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.ViniloPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.WishListPersistence;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 * @author Laura Isabella Forero Camacho
 */
@Stateless
public class WishListLogic {
    
    private static final Logger LOGGER = Logger.getLogger(WishListLogic.class.getName());

    @Inject
    public WishListPersistence wishlistPersitence;
    
    @Inject
    public ViniloPersistence viniloPersistence;
    
    @Inject
    public UsuarioPersistence usuarioPersistence;
    
    /**
     * Se encarga de crear un WishList en la base de datos.
     * @param entity Objeto de WishListEntity con los datos nuevos.
     * @param usuarioId El id del usuario el cual será el padre del nuevo Wishlist.
     * @return Objeto de WishListEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException si el usuario con el id usuarioId ya tiene asignado un Wishlist.
     *                                 Si no existe un usuario con ese id.
     */
    public WishListEntity createWishList(Long usuarioId,WishListEntity entity)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de wishList");
        UsuarioEntity usuario = usuarioPersistence.find(usuarioId);
        if (usuario == null)           
            throw new BusinessLogicException("No existe el usuario con ese id");

        if(wishlistPersitence.find(usuarioId)!=null)
        {
            throw new BusinessLogicException("El usuario ya tiene una wishList");
        }
        entity.setUsuario(usuario);
        LOGGER.log(Level.INFO, "Termina el proceso de creación de WishList.");
        return wishlistPersitence.create(entity);
    }


    public WishListEntity getWishList(Long usuarioId)
    {
        UsuarioEntity user = usuarioPersistence.find(usuarioId);
        if(user == null)
            throw new WebApplicationException("El Usuario con el id "+usuarioId+" no existe.", 404);


        WishListEntity wishList = usuarioPersistence.find(usuarioId).getWishList();
        if(wishList==null)
            throw new WebApplicationException("El usuario con el id " + usuarioId + " no tiene una WishList", 404);
        
        return wishList;
    }
    
    
    public WishListEntity updateWishList(WishListEntity wishList,Long usuarioId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de actualizar la wishlist del usuario con id = {0}", usuarioId);

        if(usuarioPersistence.find(usuarioId).getWishList() == null)
            throw new BusinessLogicException("El usuario no tiene asociada una WishList.");
        
        WishListEntity newEntity = wishlistPersitence.update(wishList);

        LOGGER.log(Level.INFO, "Termina el proceso de actualizar la WishList con el id{0}" , newEntity.getId());
        return wishList;
    }
    
    
    public void deleteWishList(Long usuarioId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO , "Inicia proceso de borrar la WishList del usuario con id {0}", usuarioId);
        WishListEntity wishList = usuarioPersistence.find(usuarioId).getWishList();
        if(wishList == null)
            throw new BusinessLogicException("El usuario no tiene una WishList asociada.");

        wishlistPersitence.delete(usuarioId);
        LOGGER.log(Level.INFO, "Termina el proceso de borrar la WishList del usuario con el id {0}" , usuarioId);        
    }
    
    public WishListEntity agregarVinilo(Long usuariosId, Long vinilosId) throws BusinessLogicException, BusinessLogicException
    {
        LOGGER.log(Level.INFO , "Inicia el proceso de agregar un vinilo con el id = {0} a la wishList del usuario con el id = {1}" , new Object[]{vinilosId, usuariosId});
        ViniloEntity vinilo = viniloPersistence.find(vinilosId);
        if (vinilo == null) 
            throw new BusinessLogicException("El vinilo no existe");
        WishListEntity wishListEntity = wishlistPersitence.findByUserId(usuariosId);
        vinilo.getWishLists().add(wishListEntity);
        wishListEntity.setCosto(wishListEntity.getCosto() + vinilo.getPrecio());
        LOGGER.log(Level.INFO , "Termina el proceso de agregar el vinilo con el id = {0} a la wishList del usuario con el id = {1}" , new Object[]{vinilosId, usuariosId});
        return wishListEntity;
    }   
    
    public WishListEntity eliminarVinilo(Long usuariosId, Long vinilosId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO , "Inicia el proceso de eliminar el vinilo con el id = {0} a la wishList del usuario con el id = {1}" , new Object[]{vinilosId, usuariosId});
        ViniloEntity viniloEntity = viniloPersistence.find(vinilosId);
        if(viniloEntity == null)
            throw new BusinessLogicException("El vinilo no existe.");
        WishListEntity wishListEntity = wishlistPersitence.findByUserId(usuariosId);
        if(!wishListEntity.getVinilos().contains(viniloEntity))
            throw new BusinessLogicException("El vinilo no estaba en la wishList.");
        viniloEntity.getWishLists().remove(wishListEntity);
        wishListEntity.setCosto(wishListEntity.getCosto() - viniloEntity.getPrecio());
        wishListEntity.getVinilos().remove(viniloEntity);
        LOGGER.log(Level.INFO , "Termina el proceso de eliminar el vinilo con el id = {0} a la wishList del usuario con el id = {1}" , new Object[]{vinilosId, usuariosId});
        return wishListEntity;
    }
}