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
import java.util.List;
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
    
    
    public WishListEntity createWishList(Long usuarioId,WishListEntity entity)//throws BusinessLogicException
    {
        UsuarioEntity usuario = usuarioPersistence.find(usuarioId);
    /*    if(usuario.getWishList()!=null)
        {
            throw new BusinessLogicException("El usuario ya tiene una wishList");
        }*/
        entity.setUsuario(usuario);
        return wishPersitence.create(entity);
    }
    
    public WishListEntity get(Long usuarioId)
    {
        WishListEntity resp = wishPersitence.find(usuarioId);
        return resp;
    }
    
    
    
    
}
