package co.edu.uniandes.csw.tiendadiscos.dtos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     *
     */
    public void setId(Long id) {
        this.id = id;
    }

    
    
}
