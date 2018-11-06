/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class CarritoDeComprasDetailDTO extends CarritoDeComprasDTO implements Serializable {
    
    private List<ViniloDTO> vinilos;
 
    private List<TransaccionDTO> transacciones;
    
    
    public CarritoDeComprasDetailDTO(){
    
}
    public List<ViniloDTO> getBooks() {
        return vinilos;
    }
    
    public List<TransaccionDTO> getTransacciones() {
        return transacciones;
    }

    /**
     * Modifica la lista de libros de la editorial.
     *
     * @param books the books to set
     */
    public void setBooks(List<ViniloDTO> vinilos) {
        this.vinilos = vinilos;
    }

    public void setTransacciones(List<TransaccionDTO> transacciones) {
        this.transacciones = transacciones;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    
}