/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;

import co.edu.uniandes.csw.tiendadiscos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.tiendadiscos.persistence.CancionPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.ViniloPersistence;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexión con la persistencia para la entidad de la
 * canción.
 *
 * @author Andrés Hernández
 */
@Stateless
public class CancionLogic {

    private static final Logger LOGGER = Logger.getLogger(CancionLogic.class.getName());

    @Inject
    private CancionPersistence persistence;

    @Inject
    private ViniloPersistence viniloPersistence;

    /**
     * Crea una canción en la persitence. No hay restricciones para registrarlo.
     *
     * @param cancionEntity Canción que se desea registrar.
     * @param viniloId
     * @return canción que se agrego a persistence.
     * @throws co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException
     */
    public CancionEntity createCancion(CancionEntity cancionEntity, Long viniloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la canción.");
        ViniloEntity vinilo = viniloPersistence.find(viniloId);
        if (vinilo == null) {
            throw new BusinessLogicException("El vinilo no existe. id Recibido: " + viniloId);
        }

        cancionEntity.setVinilo(vinilo);
        persistence.create(cancionEntity);
        LOGGER.log(Level.INFO, "Termina el proceso de creación de la canción.");
        return cancionEntity;
    }

    /**
     * OBtiene todas las canciones en la persistencia.
     *
     * @return Lista de canciones.
     */
    public List<CancionEntity> getCanciones() {
        LOGGER.log(Level.INFO, "Inicia el proceso de consulta de todos las canciones");
        List<CancionEntity> canciones = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina el proceso de consultar todos los libros.");
        return canciones;
    }

    /**
     * OBtiene todas las canciones en la persistencia.
     *
     * @param viniloId
     * @return Lista de canciones.
     * @throws co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException
     */
    public List<CancionEntity> getCancionesDeVinilo(Long viniloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia el proceso de consulta de todos las canciones");
        if (viniloPersistence.find(viniloId) == null) {
            throw new BusinessLogicException("El vinilo no existe. id Recibido: " + viniloId);
        }

        List<CancionEntity> canciones = persistence.findCancionesUsuario(viniloId);
        LOGGER.log(Level.INFO, "Termina el proceso de consultar todos los libros.");
        return canciones;
    }

    /**
     * Obtiene una canción por medio de su id.
     *
     * @param cancionId Id de la canción a ser buscada.
     * @param viniloId
     * @return La canción asociada al id.
     * @throws co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException
     */
    public CancionEntity getCancion(Long viniloId, Long cancionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Incia el proceso de consulta de la cancion con el id = {0}", cancionId);
        if (viniloPersistence.find(viniloId) == null) {
            throw new BusinessLogicException("El vinilo no existe");
        }
        CancionEntity cancionEntity = persistence.find(cancionId, viniloId);

        if (cancionEntity == null) {
            LOGGER.log(Level.INFO, "La canción con el id = {0} no existe", cancionId);
        }
        LOGGER.log(Level.INFO, "Termina el proceso de consulta de la canción ", cancionEntity);
        return cancionEntity;
    }

    /**
     * Actualizar una canción.
     *
     * @param viniloId
     * @param cancionId Id de la canción que se desea actualizar.
     * @param cancionEntity Objeto entity con la nueva información de la
     * canción.
     * @return Canción actualizada.
     * @throws co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException
     */
    public CancionEntity updateCancion(Long viniloId, Long cancionId, CancionEntity cancionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia el proceso para actualizar a la canción con el id = {0}", cancionId);
        ViniloEntity vinilo = viniloPersistence.find(viniloId);
        if (vinilo == null) {
            throw new BusinessLogicException("El vinilo no existe. id Recibido: " + viniloId);
        }
        cancionEntity.setVinilo(vinilo);
        CancionEntity newEntity = persistence.update(cancionEntity);

        LOGGER.log(Level.INFO, "Termina el proceso de actualizar a la canción con el id = {0}", cancionId);
        return newEntity;
    }

    /**
     * Elimina una canción por el id.
     *
     * @param viniloId
     * @param cancionId Id de la canción a borrar.
     * @throws co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException
*/   public void deleteCancion(Long viniloId, Long cancionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia el proceso de borrar la cancion con el id = {0}", cancionId);
        if (persistence.find(cancionId, viniloId) == null) {
            throw new BusinessLogicException("La Cancio con id " + cancionId + "no existe");
        }
        persistence.delete(cancionId);
        LOGGER.log(Level.INFO, "Termina el proceso de borrar la cancion con el id = {0}", cancionId);
    }


}
