/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;



import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * UsuarioDTO Objeto de transferencia de datos de Usuario. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "username":string,
 *      "email":string,
 *      "contrasenha":string,
 *      "nombre": string,
 *      "direccion":string,
 *      "rol":string,
 *      "calificacion": number,
 *      "imagen":String,
 *      "wishList": {@link WishListDTO},
 *      "billingInformation": {@link BillingInformationDTO},
 *      "carritoCompras": {@link CarritoDeComprasDTO}
 *   }
 * </pre> Por ejemplo un usuario se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 123412,
 *      "username": JohnDoe,
 *      "email": John@doe.com,
 *      "contrasenha": "12341321wsadfsda",
 *      "name": "John Doe",
 *      "direccion": "cll 21 #12321",
 *      "rol": "ADMIN",
 *      "wishList":{},
 *      "billingInfo":{},
 *      "carritoDeCompras":{}
 *   }
 * </pre>
 *
 * @author Camilo Andres Salinas Martinez
 */
public class UsuarioDTO implements Serializable 
{  
    public enum ROLES 
    {
        USUARIO,
        ADMIN,
        INVITADO  
    }
    
    /**
     * Identificador único del usuario.
     */
    private Long id;
    
    /**
     * Username del usuario.
     */
    private String username;
    
    /**
     * Email único del usuario.
     */
    private String email;
    
    /**
     * Contraseña del usuario.
     */
    private String contrasenha;
    
    /**
     * Nombre del usuario.
     */
    private String nombre;
    
    /**
     * Dirección del usuario.
     */
    private String direccion;
    
    /**
     * Rol del usuario.
     */
    private String rol;

    /**
     * Calificación del usuario.
     */
    private Double calificacion;
    
    /**
     * Imagen del usuario.
     */
    private String imagen;
    
    /**
     * WishList del usuario.
     */
    private WishListDTO wishList;
    
    /**
     * Billing information del usuario.
     */
    private BillingInformationDetailDTO billingInformation;    
    
    /**
     * Carrito de compras del usuario.
     */
    private CarritoDeComprasDTO carritoCompras; 

    /**
     * Constructor vacio del usuario.
     */
    public UsuarioDTO()
    {}
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param userEntity: Es la entidad que se va a convertir a DTO
     */
    public UsuarioDTO(UsuarioEntity userEntity)
    {
        if(userEntity != null )
        {
            this.id = userEntity.getId();
            this.imagen= userEntity.getImagen();
            this.username = userEntity.getUsername();
            this.email = userEntity.getEmail();
            this.contrasenha = userEntity.getContrasenha();
            this.nombre = userEntity.getNombre();
            this.direccion = userEntity.getDireccion();
            this.rol = userEntity.getRol();
            this.calificacion = userEntity.getCalificacion();
            
            if(userEntity.getCarritoCompras()!=null)
                this.carritoCompras = new CarritoDeComprasDTO(userEntity.getCarritoCompras());        
            if(userEntity.getBillingInformation() !=null)
                this.billingInformation = new BillingInformationDetailDTO(userEntity.getBillingInformation());
            if(userEntity.getWishList()!=null)
                this.wishList = new WishListDTO(userEntity.getWishList());
        }
    }
    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public UsuarioEntity toEntity()
    {
        //Creo el objeto entity vacio.
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        //Ahora le asigno los valores.
        usuarioEntity.setId(this.id);
        usuarioEntity.setUsername(this.username);
        usuarioEntity.setEmail(this.email);
        usuarioEntity.setContrasenha(this.contrasenha);
        usuarioEntity.setNombre(this.nombre);
        usuarioEntity.setDireccion(this.direccion);
        usuarioEntity.setRol(this.rol);
        usuarioEntity.setImagen(this.imagen);
        usuarioEntity.setCalificacion(this.calificacion);
        if(this.wishList !=null)            
            usuarioEntity.setWishList(wishList.toEntity());
        if(this.billingInformation !=null)            
            usuarioEntity.setBillingInformation(billingInformation.toEntity());
        if(this.carritoCompras != null)
            usuarioEntity.setCarritoCompras(carritoCompras.toEntity());    
        
        return usuarioEntity;  
    }
    
    
    /**
     * Obtiene el atributo id.
     * @return atributo id.
     */
    public Long getId() 
    {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     * @param id nuevo valor del atributo.
     */
    public void setId(Long id) 
    {
        this.id = id;
    }

    /**
     * Obtiene el atributo username.
     * @return atributo username.
     */
    public String getUsername() 
    {
        return username;
    }

    /**
     * Establece el valor del atributo username.
     * @param username nuevo valor del atributo.
     */
    public void setUsername(String username) 
    {
        this.username = username;
    }

    /**
     * Obtiene el atributo email.
     * @return atributo email.
     */
    public String getEmail() 
    {
        return email;
    }

    /**
     * Establece el valor del atributo email.
     * @param email nuevo valor del atributo.
     */
    public void setEmail(String email) 
    {
        this.email = email;
    }

    /**
     * Obtiene el atributo contraseña.
     * @return atributo contrasenha
     */
    public String getContrasenha() 
    {
        return contrasenha;
    }

    /**
     * Establece el valor del atributo contraseña.
     * @param contrasenha nuevo valor del atributo.
     */
    public void setContrasenha(String contrasenha) 
    {
        this.contrasenha = contrasenha;
    }
    
    /**
     * Obtiene el atributo dirección.
     * @return atributo direccion.
     */
    public String getDireccion() 
    {
        return direccion;
    }

    /**
     * Establece el valor del atributo dirección.
     * @param direccion nuevo valor del atributo.
     */
    public void setDireccion(String direccion) 
    {
        this.direccion = direccion;
    }

    /**
     * Obtiene el atributo Rol.
     * @return atributo rol.
     */
    public String getRol() 
    {
        return rol;
    }

    /**
     * Establece el valor del atributo rol.
     * @param rol nuevo valor del atributo 
     */
    public void setRol(String rol) 
    {
        this.rol = rol;
    }

    /**
     * Obtiene el atributo calificación.
     * @return atributo calificación.
     */
    public Double getCalificacion() 
    {
        return calificacion;
    }

    /**
     * Establece el valor del atributo calificación.
     * @param calificacion nuevo valor del atributo.
     */
    public void setCalificacion(Double calificacion) 
    {
        this.calificacion = calificacion;
    }
    
    /**
     * Obtiene el valor del atribulo nombre.
     * @return atributo nombre.
     */
    public String getNombre() 
    {
        return nombre;
    }

    /**
     * Establece el valor del atributo 
     * @param nombre nuevo valor del atributo.
     */
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el valor de la wishlist.
     * @return atributo wishList.
     */
    public WishListDTO getWishList() 
    {
        return wishList;
    }

    /**
     * Establece el valor de la wishlist.
     * @param wishList nuevo valor del atributo.
     */
    public void setWishList(WishListDTO wishList) 
    {
        this.wishList = wishList;
    }

    /**
     * Obtiene el valor de la billing information.
     * @return atributo billingInformation.
     */
    public BillingInformationDetailDTO getBillingInformation() 
    {
        return billingInformation;
    }

    /**
     * Establece el valor de la billing information.
     * @param billingInformation nuevo valor del atributo.
     */
    public void setBillingInformation(BillingInformationDetailDTO billingInformation) 
    {
        this.billingInformation = billingInformation;
    }

    /**
     * Obtiene el valor del carrito de compras.
     * @return atributo carritoCompras.
     */
    public CarritoDeComprasDTO getCarritoCompras() 
    {
        return carritoCompras;
    }

    /**
     * Establece el valor del carrito de compras.
     * @param carritoCompras nuevo valor del atributo.
     */
    public void setCarritoCompras(CarritoDeComprasDTO carritoCompras) 
    {
        this.carritoCompras = carritoCompras;
    }

    /**
     * Obtiene el valor de la imagen.
     * @return atributo imagen.
     */
    public String getImagen() 
    {
        return imagen;
    }

    /**
     * Establece el valor de la imagen.
     * @param imagen nuevo valor del atributo.
     */
    public void setImagen(String imagen) 
    {
        this.imagen = imagen;
    }
    
    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }   
}