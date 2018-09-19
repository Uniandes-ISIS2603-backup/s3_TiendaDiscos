/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

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
    
    @PodamExclude
    @OneToOne
    private WishListEntity wishList;
    @PodamExclude
    @OneToOne(cascade = CascadeType.REMOVE)
    private CarritoDeComprasEntity carritoCompras;
    @PodamExclude
    @OneToOne(mappedBy = "usuario")
    private BillingInformationEntity billingInformation;
    @PodamExclude
    @OneToMany(mappedBy="usuario")
    private List<ViniloEntity> vinilos;
    
    @PodamExclude
    @OneToMany(mappedBy = "usuarioVendedor")
    private List<TransaccionEntity> transaccionesR;
    
    @PodamExclude
    @OneToMany(mappedBy = "usuarioComprador")
    private List<TransaccionEntity> transaccionesG;
    
    @PodamExclude
    @OneToMany(mappedBy = "usuarioDestino")
    private List<ComentarioEntity> comentariosR;
    
    @PodamExclude
    @OneToMany(mappedBy = "usuarioInicio")
    private List<ComentarioEntity> comentariosH;
    
    
    
    
    

    public List<TransaccionEntity> getTransaccionesR() {
        return transaccionesR;
    }

    public void setTransaccionesR(List<TransaccionEntity> transaccionesR) {
        this.transaccionesR = transaccionesR;
    }

    public List<TransaccionEntity> getTransaccionesG() {
        return transaccionesG;
    }

    public void setTransaccionesG(List<TransaccionEntity> transaccionesG) {
        this.transaccionesG = transaccionesG;
    }

    
    
    

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

    public CarritoDeComprasEntity getCarritoCompras() {
        return carritoCompras;
    }

    public void setCarritoCompras(CarritoDeComprasEntity carritoCompras) {
        this.carritoCompras = carritoCompras;
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
    
    public void setComentariosR(List<ComentarioEntity> comentariosR)
    {
        this.comentariosR = comentariosR;
    }
    
    public void setComentariosH(List<ComentarioEntity> comentariosH)
    {
        this.comentariosH = comentariosH;
    }
    public List<ComentarioEntity> getComentariosR()
    {
        return comentariosR;
    }
    public List<ComentarioEntity> getComentariosH()
    {
        return comentariosH;
    }
}
