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
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * WishListDetailDTO clase que extiende de {@link WishListDTO} para manejar las relaciones entre los
 * WishListDTO y otros DTOs. Para conocer el contenido de un vinilo vaya a la 
 * documentación de {@link WishListDTO}
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *  {
 *      "id": number,
 *      "totalCost":number,
 *      "vinilos": [{@link ViniloDTO}
 *  }
 * </pre> Por ejemplo una WishList se representa así:<br>
 * 
 * <pre>
 * 
 *  {
 *      "id":1,
 *      "totalCost":200000,
 *      "vinilos": [
            {
                "artista": "Pink Floyd",
                "calificacion": 5,
                "id": 2,
                "informacionAdicional": "Nunca ha sido abierto.",
                "nombre": "The wall",
                "precio": 50000,
                "previewURI": "URI",
                "productora": "Columbia Records",
                "usuario": {
                    "calificacion": 0,
                    "contrasenha": "ContraseÃ±aDificilXD",
                    "direccion": "cll 6 #1A 99 este Madrid Cund.",
                    "email": "iam@camilosalinas5.me",
                    "id": 3,
                    "nombre": "Camilo Andres Salinas Martinez",
                    "rol": "ADMIN",
                    "username": "Rembrandtsx"
                }
            }
        ]
 *  }
 * 
 * </pre>
 * @author Sebastian Martinez
 */
public class WishListDetailDTO extends WishListDTO implements Serializable{
    
    
    // Relación de cero o muchos vinilos.
    private List<ViniloDTO> vinilos;
    
    /**
     * Constructor por defecto.
     */
    public WishListDetailDTO()
    {
        super();
    }
    /**
     * Crea un objeto WishListDetailDTO a partir de un objeto WishListEntity
     * incluyendo los atributos de WishListDTO.
     * 
     * @param wishListEntity Entidad WishListEntity desde la cual se va a crear el
     * nuevo objeto.
     */
    public WishListDetailDTO(WishListEntity wishListEntity)
    {
        super(wishListEntity);
        if(wishListEntity.getVinilos() != null){
            vinilos = new ArrayList<>();
            for(ViniloEntity actual : wishListEntity.getVinilos())
            {
                vinilos.add(new ViniloDTO(actual));
            }
        }   
    }
    
    /**
     * Convierte un objeto WishListDetaildDTO a WishListEntity incluyendo
     * los atributos de viniloDTO.
     * 
     * @return Nuevo objeto WishListEntity
     */
    @Override
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
     * @return Lista de DTOs de vinilos.
     */
    public List<ViniloDTO> getVinilos() 
    {
        return vinilos;
    }

    /**
     * Modifica los vinilos de la wishlist
     *
     * @param vinilos Los nuevos vinilos
     */
    public void setVinilos(List<ViniloDTO> vinilos) 
    {
        this.vinilos = vinilos;
    }
    
    @Override
    public String toString() 
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}