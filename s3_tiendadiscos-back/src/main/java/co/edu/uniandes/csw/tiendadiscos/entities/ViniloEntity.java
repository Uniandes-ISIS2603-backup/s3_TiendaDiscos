/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Andrés Hernández.
 */
@Entity
public class ViniloEntity extends BaseEntity implements Serializable 
{
    /**
     * Nombre del vinilo.
     */
    private String nombre;
    
    /**
     * Nombre del artista del vinilo.
     */
    private String artista;
    
    /**
     * Fecha de lanzamiento del vinilo.
     */
     @Temporal(TemporalType.DATE)
    private Date fechaLanzamiento;
    
    /**
     * Productora que lanzó el vinilo.
     */
    private String productora;
    
    /**
     * Información adicional del proveedor. 
     */
    private String informacionAdicional;
    
    /**
     * URI que redirige a la vista previa del vinilo si esta disponible.
     */
    private String previewURI;
    
    /**
     * Calificación promedio del vinilo.
     */
    private Double calificacion;
    
    /**
     * Precio del vinilo.
     */
    private Double precio;
    
    
    private String categoria ;
    
    @PodamExclude
    @OneToMany(mappedBy = "vinilo", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<CancionEntity> canciones = new ArrayList<CancionEntity>();
    
    @PodamExclude
    @ManyToMany
    private List<WishListEntity> wishLists;
    
    @PodamExclude
    @ManyToMany
    private List<CarritoDeComprasEntity> carritosDeCompras;
    
    @PodamExclude
    @OneToMany(mappedBy ="vinilo", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<TransaccionEntity> transacciones;
    
    
    @PodamExclude
    @OneToMany(mappedBy = "vinilo", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ComentarioEntity> comentarios;
    
    
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;
    
     /**
     * Obtiene el atributo nombre. 
     * @param nombre  atributo nombre.
     */    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Modifica el valor del del atributo artista.
     * @param artista valor del atributo.
     */
    public void setArtista(String artista) {
        this.artista = artista;
    }

    /**
     * Modifica el valor del atributo fecha de lanzamiento.
     * @param fechaLanzamiento nuevo valor del atributo.
     */
    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    /**
     * Modifica el valor del atributo productora.
     * @param productora nuevo valor del atributo.
     */
    public void setProductora(String productora) {
        this.productora = productora;
    }

    /**
     * Modifica la información adicional dada por el proveedor. 
     * @param informacionAdicional nuevo valor del atributo.
     */
    public void setInformacionAdicional(String informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }

    /**
     * Modifica el valor del atributo previewURI.
     * @param previewURI nuevo valor del atributo.
     */
    public void setPreviewURI(String previewURI) {
        this.previewURI = previewURI;
    }
    
    /**
     * Establece el valor del atributo calificación.
     * @param calificacion nuevo valor de la calificación.
     */
    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }
    
    /**
     * Establece el precio del vinilo.
     * 
     * @param precio nuevo valor del atributo.
     */
    public void setPrecio(Double precio)
    {
        this.precio = precio;
    }
    
    /**
     * Modifica las canciones del vinilo.
     * @param canciones Las nuevas Canciones.
     */
    public void setCanciones(List<CancionEntity> canciones) {
        this.canciones = canciones;
    }
    
    /**
     * Establece las WishList a las que pertenece el vinilo.
     * @param wishLists Lista de entidades de tipo WishList.
     */
    public void setWishlists(List<WishListEntity> wishLists)
    {
        this.wishLists = wishLists;
    }
    
    /**
     * Establece los carritos de compras a los que pertenece el vinilo.
     * @param carritosDeCompras Lista de entidades de tipo CarritoDeCompras.
     */
    public void setCarritosDeCompras(List<CarritoDeComprasEntity> carritosDeCompras)
    {
        this.carritosDeCompras = carritosDeCompras;
    }
    
    /**
     * Establece las transacciones con las que esta asociado el vinilo.
     * @param transacciones Lista de entidades de tipo Transaccion.
     */
    public void setTransacciones(List<TransaccionEntity> transacciones)
    {
        this.transacciones = transacciones;
    }
    
    /**
     * Modifica los comentarios del vinilo.
     * @param comentarios Los nuevos comentarios.
     */
    public void setComentarios(List<ComentarioEntity> comentarios)
    {
        this.comentarios = comentarios;
    }
    
    /**
     * Modifica el usuario dueño del vinilo.
     * @param usuario Nuevo usuario del vinilo.
     */
    public void setUsuario(UsuarioEntity usuario)
    {
        this.usuario = usuario;
    }
    
    /**
     * Obtiene el atributo nombre.
     * @return atributo nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el atributo artista.
     * @return atributo artista.
     */
    public String getArtista() {
        return artista;
    }

    /**
     * Obtiene el atributo fecha de lanzamiento.
     * @return atributo fechaLanzamiento.
     */
    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }
    
    /**
     * Obtiene el atributo productora.
     * @return atributo productora.
     */
    public String getProductora() {
        return productora;
    }

    /**
     * Obtiene la información adicional del vinilo.
     * @return atributo informacionAdicional.
     */
    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    /**
     * Obtiene el atributo previewURI.
     * @return atributo previewURI.
     */
    public String getPreviewURI() {
        return previewURI;
    }
    
    /**
     * Obtiene el atributo calificacion.
     * @return atributo calificación.
     */
    public Double getCalificacion() {
        return calificacion;
    }
    
    /**
     * Obtiene las canciones del vinilo.
     * @return canciones del vinilo.
     */
    public List<CancionEntity> getCanciones() 
    {
        return canciones;
    }
    
    /**
     * Obtiene el precio del vinilo.
     * @return atributo precio.
     */
    public Double getPrecio()
    {
        return precio;
    }
    
    /**
     * Devuelve las wishList a las que pertenece el vinilo.
     * @return Lista de entidades de tipo WishList.
     */
    public List<WishListEntity> getWishLists()
    {
        return wishLists;
    }
    
    /**
     * Devuelve los carritos de compras a los que pertenece el vinilo.
     * @return Lista de entidades de tipo Carrito de Compras. 
     */
    public List<CarritoDeComprasEntity> getCarritosDeCompras()
    {
        return carritosDeCompras;
    }
    
    /**
     * Devuelve las transacciones asociadas al vinilo.
     * @return Lista de entidades de transaccion.
     */
    public List<TransaccionEntity> getTransacciones()
    {
        return transacciones;
    }
    
    /**
     * Devuelve los comentarios que referencian a la canción.
     * @return La lista de entidades de tipo Comentario.
     */
    public List<ComentarioEntity> getComentarios()
    {
        return comentarios;
    }
    
    /**
     * Devuelve el usuario al que pertenece el vinilo.
     * @return 
     */
    public UsuarioEntity getUsuario()
    {
        return usuario;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
}