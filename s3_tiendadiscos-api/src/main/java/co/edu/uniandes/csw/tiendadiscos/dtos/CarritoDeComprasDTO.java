package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.CarritoDeComprasEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * CarritoDeComprasDTO Objeto de transferencia de datos de Carritos de compras. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * 
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *  {
 *      "id": number,
 *      "totalCost":number
 *  }
 * </pre> Por ejemplo un carrito de compras se representa así:<br>
 * 
 * <pre>
 *  {
 *      "id":1,
 *      "totalCost":200000
 *  }
 * </pre>
 * @author Isabela Forero
 */
public class CarritoDeComprasDTO implements Serializable 
{
    
    /**
     * id único del carrito..
     */
    private Long id;

    /**
     * Costo total del carrito.
     */
    private Double totalCost;

    /**
     * Constructor vacio del carrito.
     */
    public CarritoDeComprasDTO() 
    {}

    /**
     * Crea un objeto CarritoDeComprasDTO a partir de un objeto CarritoDeComprasEntity.
     * 
     * @param carritoDeComprasEntity Entidad CarritoDeComprasEntity desde la cual se va a crear el 
     * nuevo objeto.
     */
    public CarritoDeComprasDTO(CarritoDeComprasEntity carritoDeComprasEntity)
    {
        if(carritoDeComprasEntity!=null)
        {
            this.id= carritoDeComprasEntity.getId();
            this.totalCost = carritoDeComprasEntity.getTotalCostDeCarritoCompras();
        }
    }
    
    /**
     * Convierte un CarritoDeComprasDTO a CarritoDeComprasEntity
     * @return Nuevo objeto CarritoDeComprasEntity.
     */
    public CarritoDeComprasEntity toEntity()
    {
        CarritoDeComprasEntity carritoCompras = new CarritoDeComprasEntity();
        carritoCompras.setId(this.id);
        carritoCompras.setTotalCostDeCarritoCompras(this.totalCost);
        return carritoCompras;
    }
    
    /**
     * Obtiene el atributo id.
     * @return atributo id.
     */
    public Long getId() 
    {
        return id;
    }
    
    /**
     * Obtiene el atributo totalCost.
     * @return atributo totalCost.
     */
    public Double getTotalCost() 
    {
        return totalCost;
    }
    /**
     * Establece el valor del atributo id.     *
     * @param id nuevo valor del atributo     *
     */
    public void setId(Long id) 
    {
        this.id = id;
    }
    
    /**
     * Establece el valor del atributo totalCost.
     * @param totalCost nuevo valor del atributo.
     */
    public void setTotalCost(Double totalCost) 
    {
        this.totalCost = totalCost;
    }
    
    @Override
    public String toString() 
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}