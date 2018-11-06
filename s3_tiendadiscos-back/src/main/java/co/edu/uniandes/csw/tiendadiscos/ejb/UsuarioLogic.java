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
    private UsuarioPersistence usuarioPersistence;
    
    public UsuarioEntity createUsuario(UsuarioEntity usuarioEntity)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de creación del usuario");
        //Verifica que el email no esté en uso
        if(usuarioPersistence.findByEmail(usuarioEntity.getEmail())!=null){
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
        usuarioEntity.setCalificacion(0.0);
        usuarioPersistence.create(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del usuario");
        return usuarioEntity;
    }
    /**
     * Obtiene la lista de los registros de usuarios.
     *
     * @return Colección de objetos de UsuarioEntity.
     */
    public List<UsuarioEntity> getUsuarios() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los usuarios");
        List<UsuarioEntity> lista = usuarioPersistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los usuarios");
        return lista;
    }
    
    /**
     * Obtiene los datos de una instancia de Usuario a partir de su ID.
     *
     * @param usuariId Identificador de la instancia a consultar
     * @return Instancia de UsuarioEntity con los datos del Usuario consultado.
     */
    public UsuarioEntity getUsuario(Long usuarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el usuario con id = {0}", usuarioId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        if (usuarioEntity == null) {
            LOGGER.log(Level.SEVERE, "el usuario con el id = {0} no existe", usuarioId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el usuario con id = {0}", usuarioId);
        return usuarioEntity;
    }
    /**
     * Actualiza la información de una instancia de Usuario.
     *
     * @param usuarioId Identificador de la instancia a actualizar
     * @param usuarioEntity Instancia de UsuarioEntity con los nuevos datos.
     * @return Instancia de UsuarioEntity con los datos actualizados.
     */
    public UsuarioEntity updateUsuario(Long usuarioId, UsuarioEntity usuarioEntity) throws BusinessLogicException {
        if(usuarioPersistence.find(usuarioId)==null){
            throw  new BusinessLogicException("El Usuario con el id: "+usuarioId +" no existe");
        }
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el usuario con id = {0}", usuarioId);
        UsuarioEntity newUsuarioEntity = usuarioPersistence.update(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el usuario con id = {0}", usuarioId);
        return newUsuarioEntity;
    }
    /**
     * Elimina una instancia de Usuario de la base de datos.
     *
     * @param usuarioId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el usuario tiene libros asociados.
     */
    public void deleteUsuario(Long usuarioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el usuario con id = {0}", usuarioId);
        UsuarioEntity usuario = getUsuario(usuarioId);
        if (usuario != null) {
          usuarioPersistence.delete(usuarioId); 
        }
       
        
        LOGGER.log(Level.INFO, "Termina proceso de borrar el autor con id = {0}", usuarioId);
    }
}