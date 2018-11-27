package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * TransaccionDTO Objeto de transferencia de datos de Transacciones. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *  {
 *      "estado": String,
 *      "formaDePago":String   
 *      "id": number,
 *      "usuarioComprador": {@link UsuarioDTO},
 *      "usuarioVendedor": {@link UsuarioDTO},     
 *      "vinilo" : {@link ViniloDTO},
 *      "estado" : String,
 *      "envio": {@link EnvioDTO},
 *      "carrito" : {@link CarritoDeComprasDTO}
 *  }
 * </pre> Por ejemplo un autor se representa asi:<br>
 *
 * <pre>
 *  {
 *      "estado" : "peticion",
 *      "formaDePago" : "tarjeta-credito",
 *      "id": 1,
 *      "usuarioComprador": 
 *           {
 *              "type": "usuarioDetailDTO",
 *              "billingInformation": 
 *                  {
 *                      "cuentaAhorro": "987654321",
 *                      "id":{{usuarioComprador_billing_id}},
 *                      "recieved": 0,
 *                      "spent": 0
 *                  },
 *              "calificacion": 0,
 *              "contrasenha": "ContraseÃ±aDificilXD",
 *              "direccion": "cll 12 #25 105 este BogotÃ¡",
 *              "email": "comprador@gmail.com",
 *              "id": {{usuarioComprador_id}},
 *              "nombre": "El Que Compra el vinilo",
 *              "rol": "Usuario",
 *              "username": "elComprador",
 *              "comentarios": [],
 *              "transaccionesGeneradas": [],
 *              "transaccionesRecibidas": [],
 *              "vinilos": []
 *           },
 *      "usuarioVendedor":
 *           {
 *              "type": "usuarioDetailDTO",
 *              "billingInformation": 
 *                  {
 *                      "cuentaAhorro": "654654654",
 *                      "id": {{usuarioVendedor_billing_id}},
 *                      "recieved": 0,
 *                      "spent": 0
 *                  },
 *              "calificacion": 0,
 *              "contrasenha": "ContraseÃ±aDificilXD",
 *              "direccion": "cll 32 #85 5 este BogotÃ¡",
 *              "email": "vendedor@gmail.com",
 *              "id": {{usuarioVendedor_id}},
 *              "nombre": "El Que Vende el vinilo",
 *              "rol": "Usuario",
 *              "username": "elVendedor",
 *              "comentarios": [],
 *              "transaccionesGeneradas": [],
 *              "transaccionesRecibidas": [],
 *              "vinilos": [
 *                  {
 *                      "fechaLanzamiento": "2002-11-15",
 *                      "artista": "El Rey Leon",
 *                      "calificacion": 4.9,
 *                      "id": {{viniloVender_id}},
 *                      "informacionAdicional": "Fue grabado en la selva",
 *                      "nombre": "Jazz in the Jungle",
 *                      "precio": 70000,
 *                      "previewURI": "URI",
 *                      "productora": "Sony Music",
 *                      "usuario": 
 *                          {
 *                              "billingInformation": 
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
 *                  } ]
 *           },
 *      "vinilo":
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
 *                  {
 *                      "billingInformation": 
 *                      {
 *                          "cuentaAhorro": "654654654",
 *                          "id": {{usuarioVendedor_billing_id}},
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
 *                  },
 *              "canciones": []
 *          }
 *      "estado":"pagada",
 *      
 *  }
 * </pre>
 * 
 * @author Laura Isabella Forero Camacho
 */
public class TransaccionDTO implements Serializable {

    /**
     * id único de la transaccion.
     */
    private Long id;

    /**
     * id único del comprador.
     */
    private UsuarioDTO usuarioComprador;

    /**
     * id único del vendedor.
     */
    private UsuarioDTO usuarioVendedor;

    /**
     * Forma de pago de la transaccion.
     */
    private String formaDePago;

    /**
     * Estado de la transaccion.
     */
    private String estado;

    /**
     * Vinilo asociado a la transacción.
     */
    private ViniloDTO vinilo;

    /**
     * Envio asociado a la transaccion.
     */
    private EnvioDTO envio;

    /**
     * Constructor vacio de TransaccionDTO.
     */
    public TransaccionDTO() 
    {}
    
    /**
     * Crea un objeto TransaccionDTO a partir de un objeto TransaccionEntity.
     * 
     * @param transaccion Entidad TransaccionEntity desde el cual se va a crear el
     * nuevo objeto.
     */
    public TransaccionDTO(TransaccionEntity transaccion) 
    {
        if (transaccion != null) 
        {
            this.id = transaccion.getId();
            this.estado = transaccion.getEstado();
            this.formaDePago = transaccion.getFormaDePago();
            if (transaccion.getUsuarioComprador() != null) 
                this.usuarioComprador = new UsuarioDTO(transaccion.getUsuarioComprador());
            if (transaccion.getUsuarioVendedor() != null) 
                this.usuarioVendedor = new UsuarioDTO(transaccion.getUsuarioVendedor());
            if (transaccion.getVinilo() != null) 
                this.vinilo = new ViniloDTO(transaccion.getVinilo());
            if (transaccion.getEnvio() != null) 
                this.envio = new EnvioDTO(transaccion.getEnvio());            
        }
    }
    
    /**
     * Convierte un objeto TransaccionDTO a TransaccionEntity
     * @return Nuevo objeto TransaccionEntity.
     */
    public TransaccionEntity toEntity() 
    {
        TransaccionEntity transaccion = new TransaccionEntity();
        transaccion.setId(this.id);
        transaccion.setEstado(this.estado);
        transaccion.setFormaDePago(this.formaDePago);
        if (this.usuarioComprador != null)
            transaccion.setUsuarioComprador(this.usuarioComprador.toEntity());
        if (this.usuarioVendedor != null)
            transaccion.setUsuarioVendedor(this.usuarioVendedor.toEntity());
        if (this.vinilo != null)
            transaccion.setVinilo(this.vinilo.toEntity());
        if (this.envio != null) 
            transaccion.setEnvio(getEnvio().toEntity());
       
        return transaccion;
    }
    
    /**
     * Modifica el valor del atributo vendedor.
     *
     * @param vendedor nuevo valor del atributo.
     */
    public void setUsuarioVendedor(UsuarioDTO vendedor) 
    {
        this.usuarioVendedor = vendedor;
    }

    /**
     * Modifica el valor del atributo compradorID.
     *
     * @param comprador nuevo valor del atributo.
     */
    public void setUsuarioComprador(UsuarioDTO comprador) 
    {
        this.usuarioComprador = comprador;
    }

    /**
     * Modifica el valor del atributo formaDePago.
     *
     * @param formaDePago nuevo valor del atributo.
     */
    public void setFormaDePago(String formaDePago) 
    {
        this.formaDePago = formaDePago;
    }

    /**
     * Modifica el valor del atributo estado.
     *
     * @param estado nuevo valor del atributo.
     */
    public void setEstado(String estado) 
    {
        this.estado = estado;
    }

    /**
     * Modifica el valor del atributo vinilo.
     *
     * @param vinilo nuevo valor del atributo.
     */
    public void setVinilo(ViniloDTO vinilo) 
    {
        this.vinilo = vinilo;
    }

    /**
     * Obtiene el atributo usuario vendedor.
     *
     * @return atributo usuarioVendedor.
     */
    public UsuarioDTO getUsuarioVendedor() 
    {
        return usuarioVendedor;
    }

    /**
     * Retorna el usuario comprador
     *
     * @return atributo usuarioComprador.
     */
    public UsuarioDTO getUsuarioComprador() 
    {
        return usuarioComprador;
    }

    /**
     * Obtiene el atributo estado.
     *
     * @return atributo estado.
     */
    public String getEstado() 
    {
        return estado;
    }

    /**
     * Obtiene la forma de pago.
     *
     * @return atributo formaDePago.
     */
    public String getFormaDePago() 
    {
        return formaDePago;
    }

    /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     */
    public Long getId() 
    {
        return id;
    }

    /**
     * Obtiene el atributo vinilo.
     *
     * @return atributo vinilo.
     */
    public ViniloDTO getVinilo() 
    {
        return vinilo;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     *
     */
    public void setId(Long id) 
    {
        this.id = id;

    }

    /**
     * Obtiene el atributo envio.
     * @return atributo envio.
     */
    public EnvioDTO getEnvio() 
    {
        return envio;
    }

    /**
     * Establece el valor del envio.
     * @param envio nuevo valor del atributo.
     */
    public void setEnvio(EnvioDTO envio) 
    {
        this.envio = envio;
    }    

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}