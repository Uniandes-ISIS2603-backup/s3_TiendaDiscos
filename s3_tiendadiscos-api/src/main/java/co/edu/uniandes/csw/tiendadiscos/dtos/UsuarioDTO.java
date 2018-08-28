/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;


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
 *      "rol": "ADMIN"
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
    private String name;
    private String direccion;
    private String rol;

    public UsuarioDTO(){
        
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
