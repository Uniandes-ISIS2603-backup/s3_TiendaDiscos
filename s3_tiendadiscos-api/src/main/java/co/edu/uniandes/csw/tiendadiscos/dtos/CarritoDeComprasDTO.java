/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.CarritoDeComprasEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.TransaccionEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class CarritoDeComprasDTO implements Serializable {

    /**
     * id único de la transaccion.
     */
    private Long id;

    private Double totalCost;

    private UsuarioDTO usuario;

    private List<ViniloDTO> vinilos;

    private List<TransaccionDTO> transacciones;

    public CarritoDeComprasDTO() {
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

    public Double getTotalCost() {
        return totalCost;
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

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public List<ViniloDTO> getVinilos() {
        return vinilos;
    }

    public void setVinilos(List<ViniloDTO> vinilos) {
        this.vinilos = vinilos;
    }

    public List<TransaccionDTO> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<TransaccionDTO> transacciones) {
        this.transacciones = transacciones;
    }

    public CarritoDeComprasEntity toEntity() {
        CarritoDeComprasEntity carritoCompras = new CarritoDeComprasEntity();
        carritoCompras.setId(this.id);
        carritoCompras.setTotalCostDeCarritoCompras(this.totalCost);
        if (this.usuario != null) {
            carritoCompras.setUsuario(usuario.toEntity());
        }
        if (this.vinilos != null && this.vinilos.size() > 0) {
            List<ViniloEntity> vinilosEntity = new ArrayList<ViniloEntity>();
            for (ViniloDTO vinilo : this.vinilos) {
                vinilosEntity.add(vinilo.toEntity());
            }
            carritoCompras.setVinilosDeCarritoCompras(vinilosEntity);
        }

        if (this.transacciones != null && this.transacciones.size() > 0) {
            List<TransaccionEntity> transaccionesEntity = new ArrayList<TransaccionEntity>();
            for (TransaccionDTO transaccion : this.transacciones) {
                transaccionesEntity.add(transaccion.toEntity());
            }
            carritoCompras.setTransaccionesDeCarritoCompras(transaccionesEntity);
        }
        return carritoCompras;
    }

    public CarritoDeComprasDTO(CarritoDeComprasEntity carritoCompras) {
        if (carritoCompras != null) {

            this.id = carritoCompras.getId();

            this.totalCost = carritoCompras.getTotalCostDeCarritoCompras();

            if (carritoCompras.getUsuario() != null) {
                this.usuario = new UsuarioDTO(carritoCompras.getUsuario());
            }

            if (carritoCompras.getVinilosDeCarritoCompras() != null && carritoCompras.getVinilosDeCarritoCompras().size() > 0) {
                vinilos = new ArrayList<ViniloDTO>();
                for (ViniloEntity vinilosDeCarritoCompra : carritoCompras.getVinilosDeCarritoCompras()) {
                    vinilos.add(new ViniloDTO(vinilosDeCarritoCompra));
                }
            }

            if (carritoCompras.getTransaccionesDeCarritoCompras()!= null && carritoCompras.getTransaccionesDeCarritoCompras().size() > 0) {
                transacciones = new ArrayList<TransaccionDTO>();
                for (TransaccionEntity transaccionCarrito : carritoCompras.getTransaccionesDeCarritoCompras()) {
                    transacciones.add(new TransaccionDTO(transaccionCarrito));
                }
            }

        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
