/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.CarritoDeComprasEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase que extiende de {@link CarritoDeComprasDTO} para manejar las relaciones entre los
 * CarritoDeComprasDTO y otros DTOs. Para conocer el contenido de un CarritoDeCompras vaya a la
 * documentacion de {@link CarritoDeComprasDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *  {
 *      "id": number,
 *      "totalCost":number,
 *      "vinilos": [{@link ViniloDTO}],
 *      "transacciones": [{@link TransaccionDTO}]      
 *  }
 * </pre> Por ejemplo un Carrito de compras se representa así:<br>
 * <pre>
 *  {
 *      "id":1,
 *      "totalCost":200000,
 *      "vinilos": [
 *      
 *      ]
 *      "transacciones":[
 *          {
 *          "estado" : "peticion",
 *          "formaDePago" : "tarjeta-credito",
 *          "usuarioComprador": 
 *              {
 *                  "type": "usuarioDetailDTO",
 *                  "billingInformation": 
 *                  {
 *                      "cuentaAhorro": "987654321",
 *                      "id":{{usuarioComprador_billing_id}},
 *                      "recieved": 0,
 *                      "spent": 0
 *                  },
 *                  "calificacion": 0,
 *                  "contrasenha": "ContraseÃ±aDificilXD",
 *                  "direccion": "cll 12 #25 105 este BogotÃ¡",
 *                  "email": "comprador@gmail.com",
 *                  "id": {{usuarioComprador_id}},
 *                  "nombre": "El Que Compra el vinilo",
 *                  "rol": "Usuario",
 *                  "username": "elComprador",
 *                  "comentarios": [],
 *                  "transaccionesGeneradas": [],
 *                  "transaccionesRecibidas": [],
 *                  "vinilos": []
 *              },
 *          "usuarioVendedor":
 *              {
 *                  "type": "usuarioDetailDTO",
 *                  "billingInformation": 
 *                  {
 *                      "cuentaAhorro": "654654654",
 *                      "id": {{usuarioVendedor_billing_id}},
 *                      "recieved": 0,
 *                      "spent": 0
 *                  },
 *                  "calificacion": 0,
 *                  "contrasenha": "ContraseÃ±aDificilXD",
 *                  "direccion": "cll 32 #85 5 este BogotÃ¡",
 *                  "email": "vendedor@gmail.com",
 *                  "id": {{usuarioVendedor_id}},
 *                  "nombre": "El Que Vende el vinilo",
 *                  "rol": "Usuario",
 *                  "username": "elVendedor",
 *                  "comentarios": [],
 *                  "transaccionesGeneradas": [],
 *                  "transaccionesRecibidas": [],
 *          "vinilos": [
 *              {
 *                  "fechaLanzamiento": "2002-11-15",
 *                  "artista": "El Rey Leon",
 *                  "calificacion": 4.9,
 *                  "id": {{viniloVender_id}},
 *                  "informacionAdicional": "Fue grabado en la selva",
 *                  "nombre": "Jazz in the Jungle",
 *                  "precio": 70000,
 *                  "previewURI": "URI",
 *                  "productora": "Sony Music",
 *                  "usuario": 
 *                  {
 *                      "billingInformation": 
 *                      {      
 *                          "cuentaAhorro": "654654654",
 *                          "id":  {{usuarioVendedor_billing_id}},
 *                          "recieved": 0,
 *                          "spent": 0
 *                      },
 *                      "calificacion": 0,
 *                      "contrasenha": "ContraseÃ±aDificilXD",
 *                      "direccion": "cll 32 #85 5 este BogotÃ¡",
 *                      "email": "vendedor@gmail.com",
 *                      "id": {{usuarioVendedor_id}},
 *                      "nombre": "El Que Vende el vinilo",
 *                      "rol": "Usuario",
 *                      "username": "elVendedor"
 *                  }
 *              } ]
 *          },
 *          "vinilo":
 *          {
 *              "type": "viniloDetailDTO",
 *              "fechaLanzamiento": "2002-11-15",
 *              "artista": "El Rey Leon",
 *              "calificacion": 4.9,
 *              "id": {{viniloVender_id}},
 *              "nombre": "Jazz in the Jungle",
 *              "precio": 70000,
 *              "previewURI": "URI",
 *              "productora": "Sony Music",
 *              "usuario": 
 *              {
 *                  "billingInformation": {
 *                      "cuentaAhorro": "654654654",
 *                      "id": {{usuarioVendedor_billing_id}},
 *                      "recieved": 0,
 *                      "spent": 0
 *                  },
 *                  "calificacion": 0,
 *                  "contrasenha": "ContraseÃ±aDificilXD",
 *                  "direccion": "cll 32 #85 5 este BogotÃ¡",
 *                  "email": "vendedor@gmail.com",
 *                  "id": {{usuarioVendedor_id}},
 *                  "nombre": "El Que Vende el vinilo",
 *                  "rol": "Usuario",
 *                  "username": "elVendedor"
 *              },
 *              "canciones": []
 *          }
 *      }
 *      ]
 *  }
 * </pre> 
 * @author Isabela Forero
 */
public class CarritoDeComprasDetailDTO extends CarritoDeComprasDTO implements Serializable 
{
    //Relación de cero a muchos vinilos.
    private List<ViniloDTO> vinilos;

    
    
    /**
     * Constructor vacio.
     */
    public CarritoDeComprasDetailDTO()
    {}
    
    /**
     * Crea un objeto CarritoDeComprasDetailDTO a partir de un objeto CarritoDeComrpasEntity
     * incluyendo los atributos de CarritoDeComprasDTO.
     * 
     * @param carritoDeComprasEntity Entidad de CarritoDeComprasEntity desde la cual se va a crear el
     * nuevo objeto.
     */
    public CarritoDeComprasDetailDTO(CarritoDeComprasEntity carritoDeComprasEntity)
    {
        super(carritoDeComprasEntity);
        if(carritoDeComprasEntity != null && carritoDeComprasEntity.getVinilosDeCarritoCompras() != null)
        {
            vinilos = new ArrayList<>();
            for(ViniloEntity viniloEntity : carritoDeComprasEntity.getVinilosDeCarritoCompras())
                vinilos.add(new ViniloDTO(viniloEntity));
        }
    }

    /**
     * Convierte un objeto CarritoDeComprasDetailDTO a CarritoDeComprasEntity incluyendo los
     * atributos de CarritoDeComprasDTO.
     * 
     * @return Nuevo objeto CarritoDeComprasEntity.
     */
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
       
        return entity;
    }
    
    /**
     * Obtiene la lista de vinilos del carrito de compras.
     * @return los vinilos.
     */
    public List<ViniloDTO> getVinilos() 
    {
        return vinilos;
    }
    

    
    /**
     * Modifica la lista de vinilos del carrito de compras.
     * @param vinilos vinilos to set.
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