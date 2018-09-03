/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author Camilo Andres Salinas Martinez
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable{
    private String username;
    private String email;
    private String contrasenha;
    private String nombre;
    private String direccion;
    private String rol;
    private Double calificacion;
    private WishListEntity wishList;
    //private CarritoDeComprasEntity id;
    private BillingInformationEntity billingInformation;
    private List<ViniloEntity> vinilos;
    //private List<TransaccionEntity> transacciones;
    //private List<ComentarioEntity> comentarios;

    
    
    
    
    /**
     * Obtiene el Username del Usuario
     */
    public String getUsername() {
        return username;
    }
    /**
     * Modifica el Username del Usuario
     *
     * @param username el nuevo username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Obtiene el email del usuario
     */
    public String getEmail() {
        return email;
    }
    /**
     * Modifica el email del usuario
     *
     * @param email El nuevo email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public WishListEntity getWishList() {
        return wishList;
    }

    public void setWishList(WishListEntity wishList) {
        this.wishList = wishList;
    }

    public BillingInformationEntity getBillingInformation() {
        return billingInformation;
    }

    public void setBillingInformation(BillingInformationEntity billingInformation) {
        this.billingInformation = billingInformation;
    }

    public List<ViniloEntity> getVinilos() {
        return vinilos;
    }

    public void setVinilos(List<ViniloEntity> vinilos) {
        this.vinilos = vinilos;
    }
    
}
