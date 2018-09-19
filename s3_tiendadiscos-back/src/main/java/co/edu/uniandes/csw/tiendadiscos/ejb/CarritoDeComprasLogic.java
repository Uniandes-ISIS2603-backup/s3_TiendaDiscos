/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;

import co.edu.uniandes.csw.tiendadiscos.entities.CarritoDeComprasEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;

import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.CarritoDeComprasPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.UsuarioPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Laura Isabella Forero Camacho
 */
@Stateless
public class CarritoDeComprasLogic {
    
  /**  @Inject
    public CarritoDeComprasPersistence carritoComprasPersitence;
    
    @Inject
    public UsuarioPersistence usuarioPersistence;
    
    
    public CarritoDeComprasEntity create(Long usuarioId,CarritoDeComprasEntity entity)throws BusinessLogicException
    {
        UsuarioEntity usuario = usuarioPersistence.find(usuarioId);
        if(carritoComprasPersitence.find(usuarioId)!=null)
        {
            throw new BusinessLogicException("El usuario ya tiene un carrito de compras");
        }
        entity.setUsuario(usuario);
        carritoComprasPersitence.create(entity);
        return entity;
    }
    
    public CarritoDeComprasEntity get(Long usuarioId)
    {
        UsuarioEntity usuario = usuarioPersistence.find(usuarioId);
        return usuario.getCarritoCompras();
    }
    
    public CarritoDeComprasEntity update(CarritoDeComprasEntity carritoCompras,Long usuarioId)
    {
        carritoCompras.setUsuario(usuarioPersistence.find(usuarioId));
        carritoComprasPersitence.update(carritoCompras);
        return carritoCompras;
    }
    
    
    public void delete(Long usuarioId)throws BusinessLogicException{
        UsuarioEntity temp = usuarioPersistence.find(usuarioId);
        if(temp.getCarritoCompras()==null){
            throw new BusinessLogicException("El usuario con id: "+usuarioId+" no tiene carrito de compras");
        }
        usuarioPersistence.delete(usuarioId);
    }
    
    **/
    
}
