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
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Sebastian Martinez
 */
@Stateless
public class WishListLogic {
    
    @Inject
    public WishListPersistence wishPersitence;
    
    @Inject
    public UsuarioPersistence usuarioPersistence;
    
    
    public WishListEntity createWishList(Long usuarioId,WishListEntity entity)throws BusinessLogicException
    {
        UsuarioEntity usuario = usuarioPersistence.find(usuarioId);
        if(wishPersitence.find(usuarioId)!=null)
        {
            throw new BusinessLogicException("El usuario ya tiene una wishList");
        }
        entity.setUsuario(usuario);
        return wishPersitence.create(entity);
    }
    
    public WishListEntity get(Long usuarioId)
    {
        UsuarioEntity usuario = usuarioPersistence.find(usuarioId);
        return usuario.getWishList();
    }
    
    public WishListEntity update(WishListEntity wish,Long usuarioId)
    {
        wish.setUsuario(usuarioPersistence.find(usuarioId));
        wishPersitence.update(wish);
        return wish;
    }
    
    
    public void delete(Long usuarioId)throws BusinessLogicException{
        UsuarioEntity temp = usuarioPersistence.find(usuarioId);
        if(temp.getWishList()==null){
            throw new BusinessLogicException("El usuario con id: "+usuarioId+" no tiene wishList");
        }
        usuarioPersistence.delete(usuarioId);
    }
    
    
    
}
