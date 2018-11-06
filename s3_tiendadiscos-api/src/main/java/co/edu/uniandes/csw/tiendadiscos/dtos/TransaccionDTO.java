package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Laura Isabella Forero Camacho
 */

public class TransaccionDTO implements Serializable 
{
    private static final long serialVersionUID = 1L;
   
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

    
    
    
    private ViniloDTO vinilo;
    
    /**
     * Constructor vacio de TransaccionDTO.
     */
    public TransaccionDTO()
    {}
    
    
    
    
    /**
     * Modifica el valor del atributo vendedorID.
     * @param vendedorID nuevo valor del atributo.
     */
    public void setUsuarioVendedor(UsuarioDTO vendedor) {
        this.usuarioVendedor = vendedor;
    }

    /**
     * Modifica el valor del atributo compradorID.
     * @param compradorID nuevo valor del atributo.
     */
    public void setUsuarioComprador(UsuarioDTO comprador) {
        this.usuarioComprador = comprador;
    }

    /**
     * Modifica el valor del atributo formaDePago.
     * @param formaDePago nuevo valor del atributo.
     */
    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }
    
    /**
     * Modifica el valor del atributo estado.
     * @param estado nuevo valor del atributo.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    public void setVinilo(ViniloDTO vinilo) {
        this.vinilo = vinilo;
    }
    /**
     * Obtiene le atributo nombre.
     * @return atributo nombre.
     */
    public UsuarioDTO getUsuarioVendedor() {
        return usuarioVendedor;
    }

    /**
     * Retorna la duración de la cación.
     * @return atributo duración.
     */
    public UsuarioDTO getUsuarioComprador() {
        return usuarioComprador;
    }

    /**
     * Obtiene el atributo preview URI.
     * @return previewURI
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Obtiene la descripcion.
     * @return atributo descripcion.
     */
    public String getFormaDePago() {
        return formaDePago;
    }   
    
    /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     *
     */
    public Long getId() {
        return id;
    }

    
    public ViniloDTO getViniloDTO() {
        return vinilo;
    }
    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     *
     */
    public void setId(Long id) {
        this.id = id;

    }

    public TransaccionEntity toEntity()
    {
        TransaccionEntity transaccion = new TransaccionEntity();
        transaccion.setId(id);
        transaccion.setEstado(estado);
        transaccion.setFormaDePago(formaDePago);
        
        
        if(this.usuarioComprador!=null)
            transaccion.setUsuarioComprador(this.usuarioComprador.toEntity());
        if(this.usuarioVendedor!=null)
             transaccion.setUsuarioComprador(this.usuarioVendedor.toEntity());
        
        if(this.vinilo!=null)
             transaccion.setVinilo(this.vinilo.toEntity());
        return transaccion;
    }
    
    public TransaccionDTO(TransaccionEntity transaccion)
    {
        if(transaccion!=null)
        {
            this.id = transaccion.getId();
            this.estado = transaccion.getEstado();
            this.formaDePago = transaccion.getFormaDePago();
            if(transaccion.getUsuarioComprador()!=null)
                this.usuarioComprador = new UsuarioDTO(transaccion.getUsuarioComprador());
            else
                this.usuarioComprador = null;
            if(transaccion.getUsuarioVendedor() !=null)
                this.usuarioVendedor = new UsuarioDTO(transaccion.getUsuarioVendedor());
            else
                this.usuarioVendedor = null;
            if(transaccion.getVinilo() !=null)
                this.vinilo = new ViniloDTO(transaccion.getVinilo());
            else
                this.vinilo = null;
        }
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}