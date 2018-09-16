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
 *      "name": string,
 *      "direccion":string,
 *      "rol":string
 *   }
 * </pre> Por ejemplo un autor se representa asi:<br>
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
 *
 * </pre>
 *
 * @author Camilo Andres Salinas Martinez
 */
public class UsuarioDTO implements Serializable {
    
    public enum ROLES {
        USUARIO,
        ADMIN,
        INVITADO  
    }
    
    private Long id;
    private String username;
    private String email;
    private String contrasenha;
    private String nombre;
    private String direccion;
    private String rol;
    private Double calificacion;

   
    
    private WishListDTO wishList;
    
    private BillingInformationDTO billingInformation;
    
    private CarritoDeComprasDTO carritoCompras;

   

    public UsuarioDTO(){
        
    }
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param userEntity: Es la entidad que se va a convertir a DTO
     */
    public UsuarioDTO(UsuarioEntity userEntity){
        if(userEntity != null ){
            this.id = userEntity.getId();
            this.username = userEntity.getUsername();
            this.email = userEntity.getEmail();
            this.contrasenha = userEntity.getContrasenha();
            this.nombre = userEntity.getNombre();
            this.direccion = userEntity.getDireccion();
            this.rol = userEntity.getRol();
            this.calificacion = userEntity.getCalificacion();
            this.carritoCompras = new CarritoDeComprasDTO(userEntity.getCarritoCompras());
            this.billingInformation = new BillingInformationDTO(userEntity.getBillingInformation());
            this.wishList = new WishListDTO(userEntity.getWishList());
            
          
        }
    }
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public UsuarioEntity toEntity(){
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
        usuarioEntity.setCalificacion(this.calificacion);
        if(this.wishList !=null){
            WishListDTO wishListTemp = this.wishList;
            usuarioEntity.setWishList(wishListTemp.toEntity());
        }    
        if(this.billingInformation !=null){
            BillingInformationDTO billingInformationTemp = this.billingInformation;
            usuarioEntity.setBillingInformation(billingInformationTemp.toEntity());
        }
        if(this.carritoCompras != null){
            CarritoDeComprasDTO carritoComprasTemp = this.carritoCompras;
            usuarioEntity.setCarritoCompras(carritoComprasTemp.toEntity());
        }
       
        
        return usuarioEntity;  
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
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
     public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public WishListDTO getWishList() {
        return wishList;
    }

    public void setWishList(WishListDTO wishList) {
        this.wishList = wishList;
    }

    public BillingInformationDTO getBillingInformation() {
        return billingInformation;
    }

    public void setBillingInformation(BillingInformationDTO billingInformation) {
        this.billingInformation = billingInformation;
    }

    public CarritoDeComprasDTO getCarritoCompras() {
        return carritoCompras;
    }

    public void setCarritoCompras(CarritoDeComprasDTO carritoCompras) {
        this.carritoCompras = carritoCompras;
    }
    
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
