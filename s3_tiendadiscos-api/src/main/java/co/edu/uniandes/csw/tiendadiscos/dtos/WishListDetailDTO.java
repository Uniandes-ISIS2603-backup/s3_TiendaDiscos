/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.WishListEntity;

import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sebastian Martinez
 */
public class WishListDetailDTO extends WishListDTO implements Serializable{
    
    
    //relacion 0 a muchos vinilos
    private List<ViniloDTO> vinilos;
    
    public WishListDetailDTO()
    {
        super();
    }
    /**
     * transforma un entity en un dto
     * @param wish 
     */
    public WishListDetailDTO(WishListEntity wish)
    {
        super(wish);
        if(wish.getVinilos() != null){
            vinilos = new ArrayList<>();
            for(ViniloEntity actual : wish.getVinilos())
            {
                vinilos.add(new ViniloDTO(actual));
            }
        }
            
    }
    
    /**
     * Transforma un dto en un entity
     */
    public WishListEntity toEntity()
    {
        WishListEntity wish = super.toEntity();
        if(this.vinilos !=null)
        {
            List<ViniloEntity> vinilosEntity= new ArrayList<>();
            for(ViniloDTO actual : getVinilos())
            {
                vinilosEntity.add(actual.toEntity());
            }
            wish.setVinilos(vinilosEntity);
        }
        
        return wish;
    }
    
    /**
     * Devuelve los vinilos en la wishlist
     *
     * @return Lista de DTOs de Reseñas
     */
    public List<ViniloDTO> getVinilos() {
        return vinilos;
    }

    /**
     * Modifica los vinilos de la wishlist
     *
     * @param vinilos Los nuevos vinilos
     */
    public void setVinilos(List<ViniloDTO> vinilos) {
        this.vinilos = vinilos;
    }
}
