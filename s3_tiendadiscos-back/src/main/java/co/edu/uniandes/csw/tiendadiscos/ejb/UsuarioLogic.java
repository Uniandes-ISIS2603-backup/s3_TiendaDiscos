/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;
/*Imports*/
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.UsuarioPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Usuario.
 *
 * @author Camilo Andres Salinas Martinez
 */
@Stateless
public class UsuarioLogic {
    
        private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());

    
    @Inject
    private UsuarioPersistence usuariPerseistence;
    
    public UsuarioEntity createUsuario(UsuarioEntity usuarioEntity)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de creación del usuario");
        //Verifica que el email no esté en uso
        if(usuariPerseistence.findByEmail(usuarioEntity.getEmail())!=null){
            throw new BusinessLogicException("Este email ya está en uso por otro usuario");
        }
        //Verifica que la contraseña no contenga el nombre del usuario
        if(usuarioEntity.getContrasenha().contains(usuarioEntity.getNombre())){
            throw new BusinessLogicException("La contraseña no puede contener el nombre del usuario");
        }
        /*
            Verifica que la contraseña cumpla las siguintes reglas:
                Tiene almenos un digito
                Tiene almenos una letra en minuscula
                Tiene almenos una letra en mayuscula
                Tiene almenos un caracter especial
                Tiene almenos largo de 8 caracteres
                No contiene espacios en blanco
        */
        if(usuarioEntity.getContrasenha().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")){
            throw new BusinessLogicException("La contraseña no cumple los parametros de seguridad minimos.");
        }
        usuariPerseistence.create(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del usuario");
        return usuarioEntity;
    }
    
}
