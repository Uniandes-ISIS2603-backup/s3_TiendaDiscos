package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
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
     * Envio asociado a la transaccion
     */
    private EnvioDTO envio;

    private CarritoDeComprasDTO carrito;

    /**
     * Constructor vacio de TransaccionDTO.
     */
    public TransaccionDTO() {
    }

    /**
     * Modifica el valor del atributo vendedorID.
     *
     * @param vendedor nuevo valor del atributo.
     */
    public void setUsuarioVendedor(UsuarioDTO vendedor) {
        this.usuarioVendedor = vendedor;
    }

    /**
     * Modifica el valor del atributo compradorID.
     *
     * @param comprador nuevo valor del atributo.
     */
    public void setUsuarioComprador(UsuarioDTO comprador) {
        this.usuarioComprador = comprador;
    }

    /**
     * Modifica el valor del atributo formaDePago.
     *
     * @param formaDePago nuevo valor del atributo.
     */
    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    /**
     * Modifica el valor del atributo estado.
     *
     * @param estado nuevo valor del atributo.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Modifica el valor del atributo vinilo.
     *
     * @param vinilo nuevo valor del atributo.
     */
    public void setVinilo(ViniloDTO vinilo) {
        this.vinilo = vinilo;
    }

    /**
     * Obtiene le atributo nombre.
     *
     * @return atributo nombre.
     */
    public UsuarioDTO getUsuarioVendedor() {
        return usuarioVendedor;
    }

    /**
     * Retorna la duración de la cación.
     *
     * @return atributo duración.
     */
    public UsuarioDTO getUsuarioComprador() {
        return usuarioComprador;
    }

    /**
     * Obtiene el atributo preview URI.
     *
     * @return previewURI
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Obtiene la descripcion.
     *
     * @return atributo descripcion.
     */
    public String getFormaDePago() {
        return formaDePago;
    }

    /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el atributo vinilo.
     *
     * @return atributo vinilo.
     */
    public ViniloDTO getVinilo() {
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

    public EnvioDTO getEnvio() {
        return envio;
    }

    public void setEnvio(EnvioDTO envio) {
        this.envio = envio;
    }

    public CarritoDeComprasDTO getCarrito() {
        return carrito;
    }

    public void setCarrito(CarritoDeComprasDTO carrito) {
        this.carrito = carrito;
    }

    public TransaccionEntity toEntity() {
        TransaccionEntity transaccion = new TransaccionEntity();
        transaccion.setId(this.id);
        transaccion.setEstado(this.estado);
        transaccion.setFormaDePago(this.formaDePago);

        if (this.usuarioComprador != null) {
            transaccion.setUsuarioComprador(this.usuarioComprador.toEntity());
        }
        if (this.usuarioVendedor != null) {
            transaccion.setUsuarioVendedor(this.usuarioVendedor.toEntity());
        }

        if (this.vinilo != null) {
            transaccion.setVinilo(this.vinilo.toEntity());
        }
        if (this.envio != null) {
            transaccion.setEnvio(getEnvio().toEntity());
        }

        if (this.carrito != null) {
            transaccion.setCarritoDeCompras(getCarrito().toEntity());
        }
        return transaccion;
    }

    public TransaccionDTO(TransaccionEntity transaccion) {
        if (transaccion != null) {
            this.id = transaccion.getId();
            this.estado = transaccion.getEstado();
            this.formaDePago = transaccion.getFormaDePago();
            if (transaccion.getUsuarioComprador() != null) {
                this.usuarioComprador = new UsuarioDTO(transaccion.getUsuarioComprador());
            } else {
                this.usuarioComprador = null;
            }
            if (transaccion.getUsuarioVendedor() != null) {
                this.usuarioVendedor = new UsuarioDTO(transaccion.getUsuarioVendedor());
            } else {
                this.usuarioVendedor = null;
            }
            if (transaccion.getVinilo() != null) {
                this.vinilo = new ViniloDTO(transaccion.getVinilo());
            } else {
                this.vinilo = null;
            }
            if (transaccion.getEnvio() != null) {
                this.envio = new EnvioDTO(transaccion.getEnvio());
            } else {
                this.envio = null;
            }

            if (transaccion.getCarritoDeCompras() != null) {
                this.carrito = new CarritoDeComprasDTO(transaccion.getCarritoDeCompras());
                
            }
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
