/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;
import co.edu.uniandes.csw.tiendadiscos.entities.WishListEntity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author estudiante
 */
@Entity
public class WishListDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double totalCost;

    public WishListDTO()
    {
    }
    
    public WishListDTO(WishListEntity wish){
        this.id = wish.getId();
        this.totalCost = wish.getCosto();
    }
    
    public WishListEntity toEntity()
    {
        WishListEntity wish = new WishListEntity();
        wish.setCosto(this.totalCost);
        wish.setId(this.id);
        return wish;
    }
    
    public Double getTotalCost()
    {
        return totalCost;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setTotalCost(Double totalCost)
    {
        this.totalCost = totalCost;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WishListDTO)) {
            return false;
        }
        WishListDTO other = (WishListDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.csw.tiendadiscos.dtos.WishListDTO[ id=" + id + " ]";
    }
    
}
