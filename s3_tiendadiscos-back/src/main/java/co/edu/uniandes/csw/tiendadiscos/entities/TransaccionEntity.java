/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Laura Isabella Forero Camacho
 */
@Entity
public class TransaccionEntity extends BaseEntity implements Serializable{
    
    
    /**
     * id único del comprador.
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuarioComprador;
    
    /**
     * id único del vendedor.
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuarioVendedor;
    
    @PodamExclude
    @OneToOne
    private ComentarioEntity comentario;
    @PodamExclude
    @OneToOne
    private EnvioEntity envio;
    /**
     * Forma de pago de la transaccion.
     */
    private String formaDePago;
    
    /**
     * Estado de la transaccion.
     */
    private String estado;
    

   


    public EnvioEntity getEnvio() {
        return envio;
    }

    public void setEnvio(EnvioEntity envio) {
        this.envio = envio;
    }

    private void setComentario(ComentarioEntity comentario)
    {
        this.comentario = comentario;
    }
    
    /**
     * Modifica el valor del atributo vendedorID.
     * @param vendedorID nuevo valor del atributo.
     */
    public void setUsuarioVendedor(UsuarioEntity  vendedor) {
        this.usuarioVendedor = vendedor;
    }

    /**
     * Modifica el valor del atributo compradorID.
     * @param compradorID nuevo valor del atributo.
     */
    public void setUsuarioComprador(UsuarioEntity  comprador) {
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
    public UsuarioEntity  getUsuarioVendedor() {
        return usuarioVendedor;
    }

    /**
     * Retorna la duración de la cación.
     * @return atributo duración.
     */
    public UsuarioEntity  getUsuarioComprador() {
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

    public ComentarioEntity getComentario(){
        return comentario;
    }

    
    
    
}
