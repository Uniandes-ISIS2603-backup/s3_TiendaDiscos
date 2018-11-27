/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;
import co.edu.uniandes.csw.tiendadiscos.entities.WishListEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * WishListDTO Objeto de transferencia de datos de WishList. Los DTO contienen las 
 * representaciones de los JSON que se transfieren entre el cliente y el servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *  {
 *      "id": number,
 *      "totalCost":number
 *  }
 * </pre> Por ejemplo una WishList se representa así:<br>
 * 
 * <pre>
 * 
 *  {
 *      "id":1,
 *      "totalCost":200000
 *  }
 * 
 * </pre>
 * 
 * @author Sebastian Martinez
 */
public class WishListDTO implements Serializable 
{
    /**
     * id de la wishList.
     */
    private Long id;
    
    /**
     * Costo total de la WishList.
     */
    private Double totalCost;
    
    /**
     * Constructor por defecto.
     */
    public WishListDTO()
    {}
    
    /**
     * Constructor a partir de la entidad
     *
     * @param wishListEntity La entidad del libro
     */
    public WishListDTO(WishListEntity wishListEntity)
    {
        if(wishListEntity != null)
        {
            this.id = wishListEntity.getId();
            this.totalCost = wishListEntity.getCosto();
        }
    }
    
    /**
     * Método para transformar el DTO a una entidad.
     * 
     * @return La entidad WishList asociada.
     */
    public WishListEntity toEntity()
    {
        WishListEntity wish = new WishListEntity();
        wish.setCosto(this.totalCost);
        wish.setId(this.id);
        return wish;
    }
    
    /**
     * Devuelve el costo total de la wishList.
     * 
     * @return the totalCost.
     */
    public Double getTotalCost()
    {
        return totalCost;
    }
    
    /**
     * Devuelve el ID de la wishList.
     *
     * @return the id
     */
    public Long getId() 
    {
        return id;
    }

    /**
     * Modifica el ID de la wishList.
     *
     * @param id the id to set
     */
    public void setId(Long id) 
    {
        this.id = id;
    }
    
    /**
     * Modifica el costo total de la wishList.
     * 
     * @param totalCost totalCost to set.
     */
    public void setTotalCost(Double totalCost)
    {
        this.totalCost = totalCost;
    }   
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}