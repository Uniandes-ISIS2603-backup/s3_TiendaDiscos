/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;
import co.edu.uniandes.csw.tiendadiscos.entities.WishListEntity;
import java.io.Serializable;

/**
 *
 * @author Sebastian Martinez
 */
public class WishListDTO implements Serializable 
{
    private Long id;
    
    private Double totalCost;

    public WishListDTO()
    {}
    
    public WishListDTO(WishListEntity wishlist)
    {
        if(wishlist != null)
        {
            this.id = wishlist.getId();
            this.totalCost = wishlist.getCosto();
        }
    }
    
    public WishListEntity toEntity()
    {
        WishListEntity wish = new WishListEntity();
        wish.setCosto(this.totalCost);
        wish.setId(this.id);
        return wish;
    }
    
    public Double getTotalCost()
    {
        return totalCost;
    }
    
    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }
    
    public void setTotalCost(Double totalCost)
    {
        this.totalCost = totalCost;
    }   
}