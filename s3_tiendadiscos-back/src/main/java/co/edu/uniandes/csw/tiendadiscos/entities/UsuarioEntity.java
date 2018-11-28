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
import javax.persistence.FetchType;
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
    private String imagen;
    private Double calificacion;
    
    @PodamExclude
    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private WishListEntity wishList;
    
    @PodamExclude
    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private CarritoDeComprasEntity carritoCompras;
    
    @PodamExclude
    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private BillingInformationEntity billingInformation;
    
    @PodamExclude
    @OneToMany(mappedBy="usuario",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ViniloEntity> vinilos;
    
    @PodamExclude
    @OneToMany(mappedBy = "usuarioVendedor",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<TransaccionEntity> transaccionesR;
    
    @PodamExclude
    @OneToMany(mappedBy = "usuarioComprador",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<TransaccionEntity> transaccionesG;
    
    @PodamExclude
    @OneToMany(mappedBy = "usuarioDestino",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ComentarioEntity> comentariosR;
    
    @PodamExclude
    @OneToMany(mappedBy = "usuarioInicio",cascade = CascadeType.PERSIST, orphanRemoval = true)
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

    public String getImagen(){
        
        return imagen;
    }
    public void setImagen(String imagen){
        
        this.imagen= imagen;
    }

    /**
     * Obtiene el Username del Usuario
     * @return 
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
     * @return 
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

    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.username);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.contrasenha);
        hash = 29 * hash + Objects.hashCode(this.nombre);
        hash = 29 * hash + Objects.hashCode(this.direccion);
        hash = 29 * hash + Objects.hashCode(this.rol);
        hash = 29 * hash + Objects.hashCode(this.imagen);
        hash = 29 * hash + Objects.hashCode(this.calificacion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (getClass() != obj.getClass()) 
            return false;
        final UsuarioEntity other = (UsuarioEntity) obj;
        
        if (!Objects.equals(this.username, other.username) && !Objects.equals(this.email, other.email) && !Objects.equals(this.contrasenha, other.contrasenha) &&
            !Objects.equals(this.nombre, other.nombre) && !Objects.equals(this.direccion, other.direccion) && !Objects.equals(this.rol, other.rol) &&
            !Objects.equals(this.imagen, other.imagen) && !Objects.equals(this.calificacion, other.calificacion) && !Objects.equals(this.wishList, other.wishList) &&
            !Objects.equals(this.carritoCompras, other.carritoCompras) && !Objects.equals(this.billingInformation, other.billingInformation) && !Objects.equals(this.vinilos, other.vinilos) &&
            !Objects.equals(this.transaccionesR, other.transaccionesR) && !Objects.equals(this.transaccionesG, other.transaccionesG) && !Objects.equals(this.comentariosR, other.comentariosR))
            return false;
        
        return Objects.equals(this.comentariosH, other.comentariosH);
    }
    
    
}