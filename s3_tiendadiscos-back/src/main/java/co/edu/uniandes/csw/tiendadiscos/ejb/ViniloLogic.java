/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;

import co.edu.uniandes.csw.tiendadiscos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.ViniloPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.UsuarioPersistence;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexión con la persitencia para la entidad de
 * vinilo.
 *
 * @author Andrés Hernández
 */
@Stateless
public class ViniloLogic {

    private static final Logger LOGGER = Logger.getLogger(ViniloLogic.class.getName());

    @Inject
    private ViniloPersistence persistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    /**
     * Crea un vinilo en la persistence. No pueden haber campos Vacios.
     *
     * @param viniloEntity Vinilo que se desea registrar.
     * @return vinilo que se agrego a persistence.
     */
    public ViniloEntity createVinilo(ViniloEntity viniloEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia el proceso de creación del vinilo.");
        // Se procede a crear el vinilo.
        if (viniloEntity.getNombre().equals("") || viniloEntity.getPrecio() < 0 || viniloEntity.getProductora().equals("") || viniloEntity.getArtista().equals("")) {
            throw new BusinessLogicException("El vinilo no cumple con los requisitos para ser creado.");
        }
        persistence.create(viniloEntity);
        LOGGER.log(Level.INFO, "Termina el proceso de creación del vinilo.");
        return viniloEntity;
    }

    public ViniloEntity createViniloUsuario(Long usuarioId, ViniloEntity viniloEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia el proceso de creación del vinilo de un usuario con id = {0}", usuarioId);
        if (usuarioPersistence.find(usuarioId) == null) {
            throw new BusinessLogicException("El usuario no existe. id Recibido: " + usuarioId);
        }
        viniloEntity.setUsuario(usuarioPersistence.find(usuarioId));
        persistence.create(viniloEntity);
        LOGGER.log(Level.INFO, "Termina el proceso de creación del vinilo al usuario.");
        return viniloEntity;
    }

    /**
     * Obtiene todos los vinilos en la persistencia. Permite mostrar el catalogo
     * completo de vinilos.
     *
     * @return Lista de vinilos.
     */
    public List<ViniloEntity> getVinilos() {
        LOGGER.log(Level.INFO, "Inicia la consulta de todos los vinilos.");
        List<ViniloEntity> vinilos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina el proceso de consultar los vinilos.");
        return vinilos;
    }

    public List<ViniloEntity> getVinilosByUsuario(Long usuarioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia la consulta de todos los vinilos del usuario:{0}", usuarioId);
        if (usuarioPersistence.find(usuarioId) == null) {
            throw new BusinessLogicException("El usuario no existe. id Recibido: " + usuarioId);
        }

        List<ViniloEntity> vinilos = persistence.findAllByUsuario(usuarioId);
        LOGGER.log(Level.INFO, "Termina el proceso de consulta de los vinilos de un usuario.");
        return vinilos;
    }

    /**
     * Obtiene un vinilo por medio de su id.
     *
     * @param viniloId Id del vinilo para ser buscado.
     * @return El vinilo solicitado por id.
     */
    public ViniloEntity getVinilo(Long viniloId) {
        LOGGER.log(Level.INFO, "inicia proceso de consulta del vinilo con id = {0}", viniloId);
        ViniloEntity viniloEntity = persistence.find(viniloId);
        if (viniloEntity == null) {
            LOGGER.log(Level.INFO, "El vinilo con el id = {0} no existe", viniloId);
        }
        LOGGER.log(Level.INFO, "Termina el proceso de consulta del vinilo con el id = {0}", viniloId);
        return viniloEntity;
    }

    /**
     * Actualizar un vinilo.
     *
     * @param viniloId Id del vinilo para buscarlo en persistence.
     * @param viniloEntity Objeto entity con la nueva información del vinilo.
     * @return Vinilo Actualizado.
     */
    public ViniloEntity updateVinilo(Long viniloId, ViniloEntity viniloEntity) {
        LOGGER.log(Level.INFO, "Inicia el proceso de actualizar el vinilo con el id = {0}", viniloId);
        ViniloEntity newEntity = persistence.update(viniloEntity);
        LOGGER.log(Level.INFO, "Termina el proceso de actualizar el vinilo con el id = {0}", viniloId);
        return newEntity;
    }

    /**
     * Elimina un vinilo por od id.
     *
     * @param viniloId vinilosId
     */
    public void deleteVinilo(Long viniloId) {
        LOGGER.log(Level.INFO, "Inicia el proceso para borrar el vinilo con id = {0}", viniloId);
        List<CancionEntity> canciones = persistence.find(viniloId).getCanciones();
        persistence.delete(viniloId);
        LOGGER.log(Level.INFO, "Termina el proceso de borrar un vinilo con id = {0}", viniloId);
    }

}
