/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.WishListEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Camilo Andres Salinas Martinez
 */
public class UsuarioDetailDTO extends UsuarioDTO implements Serializable{
    
    /*Relación 0 a muchos vinilos*/
    private List<ViniloDTO> vinilos;
    /*Relación 0 a muchas transacciones*/
    private List<TransaccionDTO> transacciones;
    /*Relación 0 a muchos comentarios*/
    private List<ComentarioDTO> comentario;
    
    /**
     * Constructor que llama al contructor de UsuarioDetail
     */
    public UsuarioDetailDTO()
    {
        super();
    }    
     /**
     * transforma un entity en un dto
     * @param usuario 
     */
    public UsuarioDetailDTO(UsuarioEntity usuario)
    {
//        super(usuario);
//        if(usuario.getVinilos() != null){
//            vinilos = new ArrayList<>();
//            for(ViniloEntity actual : usuario.getVinilos())
//            {
//                vinilos.add(new ViniloDTO(actual));
//            }
//        }
//            
    }
    
    
    
}
