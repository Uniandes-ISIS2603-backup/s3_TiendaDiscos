/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.CarritoDeComprasEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Isabela Forero
 */
public class CarritoDeComprasDetailDTO extends CarritoDeComprasDTO implements Serializable 
{
    
    private List<ViniloDTO> vinilos;
 
    private List<TransaccionDTO> transacciones;
    
    
    public CarritoDeComprasDetailDTO()
    {}
    
    public CarritoDeComprasDetailDTO(CarritoDeComprasEntity entity)
    {
        super(entity);
        if(entity != null)
        {
            if(entity.getVinilosDeCarritoCompras() != null)
            {
                vinilos = new ArrayList<ViniloDTO>();
                for(ViniloEntity viniloEntity : entity.getVinilosDeCarritoCompras())
                    vinilos.add(new ViniloDTO(viniloEntity));
            }
            if(entity.getTransaccionesDeCarritoCompras() != null)
            {
                transacciones = new ArrayList<TransaccionDTO>();
                for(TransaccionEntity transaccionEntity : entity.getTransaccionesDeCarritoCompras())
                    transacciones.add(new TransaccionDTO(transaccionEntity));
            }
            
        }
    }

    @Override
    public CarritoDeComprasEntity toEntity()
    {
        CarritoDeComprasEntity entity = super.toEntity();
        //Pasar todos los DTO de vinilos a Entity.
        if(vinilos != null)
        {
            List<ViniloEntity> vinilosEntity = new ArrayList<>();
            for(ViniloDTO vinilo: vinilos)
                vinilosEntity.add(vinilo.toEntity());
            entity.setVinilosDeCarritoCompras(vinilosEntity);
        }
        if(transacciones != null)
        {
            List<TransaccionEntity> transaccionesEntity = new ArrayList<>();
            for(TransaccionDTO transaccion: transacciones)
                transaccionesEntity.add(transaccion.toEntity());
            entity.setTransaccionesDeCarritoCompras(transaccionesEntity);
        }
        
        return entity;
    }
    
    public List<ViniloDTO> getVinilos() 
    {
        return vinilos;
    }
    
    public List<TransaccionDTO> getTransacciones() 
    {
        return transacciones;
    }

    
    public void setVinilos(List<ViniloDTO> vinilos) 
    {
        this.vinilos = vinilos;
    }

    public void setTransacciones(List<TransaccionDTO> transacciones) 
    {
        this.transacciones = transacciones;
    }
    
    @Override
    public String toString() 
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}