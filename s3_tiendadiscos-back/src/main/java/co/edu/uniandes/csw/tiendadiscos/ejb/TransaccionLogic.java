/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;



import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.TransaccionPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Laura Isabella Forero Camacho
 */
@Stateless
public class TransaccionLogic {
    
    @Inject
    public TransaccionPersistence transaccionPersistence;
    
    
    
    
    public TransaccionEntity create(TransaccionEntity entity)throws BusinessLogicException
    {
        if(transaccionPersistence.find(entity.getId())!=null)
        {
            throw new BusinessLogicException("Ya existe una transaccion con esta id.");
        }
        
        return transaccionPersistence.create(entity);
    }
    
    public TransaccionEntity get(Long transaccionId)
    {
        return transaccionPersistence.find(transaccionId);
    }
    
    public TransaccionEntity update(TransaccionEntity transaccion,Long transaccionId) throws BusinessLogicException
    {   TransaccionEntity temp = transaccionPersistence.find(transaccionId);
        if(temp==null){
            throw new BusinessLogicException("No existe una transacción con este id.");
        }
        transaccionPersistence.update(transaccion);
        return transaccion;
    }
    
    
    public void delete(Long transaccionId)throws BusinessLogicException{
        TransaccionEntity temp = transaccionPersistence.find(transaccionId);
        if(temp==null){
            throw new BusinessLogicException("No existe una transacción con este id.");
        }
        transaccionPersistence.delete(transaccionId);
    }
    
    
    
}
