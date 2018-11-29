/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Kevin Blanco
 */
@Entity
public class BillingInformationEntity extends BaseEntity implements Serializable 
{

    /**
     * numero de cuenta de ahorro
     */
    private String cuentaAhorro;

    /**
     * Dinero gastado
     */
    private Double spent;

    /**
     * dinero recibido
     */
    private Double recieved;

    /**
     * tarjetas de credito del Billing
     */
    @PodamExclude
    @OneToOne
    private UsuarioEntity usuario;
    
    @PodamExclude
    @OneToMany(mappedBy = "billing", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MedioDePagoEntity> tarjetas;

    
    /**
     * devuelve el usuario asociado al billing 
     * @return 
    */
    public UsuarioEntity getUsuario() {
        return usuario;
    }
    
    /**
     * Modifica el usuario asociado al billing 
     * @param usuario usuario nuevo para el billing
     */
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }  
    
    /**
     * retorna cuenta de ahorro asociada al billing
     *
     * @return cuenta de ahorro asociada al billing
     */
    public String getCuentaAhorro() {
        return cuentaAhorro;
    }

    /**
     * retorna dinero gastado en el billing
     *
     * @return dinero gastado en el billing
     */
    public Double getSpent() {
        return spent;
    }

    /**
     * retorna dinero recibido en el billing
     *
     * @return dinero recibido en el billing
     */
    public Double getRecieved() {
        return recieved;
    }

    /**
     * modifica el numero de la cuenta de ahorro del billing
     *
     * @param cuentaAhorro nuevo numero de cuenta de ahorro
     */
    public void setCuentaAhorro(String cuentaAhorro) {
        this.cuentaAhorro = cuentaAhorro;
    }

    /**
     * modifica el dinero gastado en el billing
     *
     * @param spent nuevo valor del dinero gastado
     */
    public void setSpent(Double spent) {
        this.spent = spent;
    }

    /**
     * modifica dinero recibido en el Billing
     *
     * @param recieved nuevo valor del dinero recibido
     */
    public void setRecieved(Double recieved) {

        this.recieved = recieved;
    }

    /**
     * Devuelve las tarjetas de credito del billing.
     *
     * @return Lista de entidades de tipo Reseña
     */
    public List<MedioDePagoEntity> getTarjetas() {
        return tarjetas;
    }

    /**
     * modifica las tarjetas de credito del Billing
     *
     * @param tarjetas nuevas tarjetas para el Billing
     */
    public void setTarjetas(List<MedioDePagoEntity> tarjetas) 
    {
        this.tarjetas = tarjetas;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.cuentaAhorro);
        hash = 59 * hash + Objects.hashCode(this.spent);
        hash = 59 * hash + Objects.hashCode(this.recieved);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BillingInformationEntity other = (BillingInformationEntity) obj;
        if (!Objects.equals(this.cuentaAhorro, other.cuentaAhorro)) {
            return false;
        }
        if (!Objects.equals(this.spent, other.spent)) {
            return false;
        }
        if (!Objects.equals(this.recieved, other.recieved)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return Objects.equals(this.tarjetas, other.tarjetas);
    }
    
    
}