package co.edu.uniandes.csw.bookstore.ejb;

import co.edu.uniandes.csw.bookstore.entities.OrganizationEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.persistence.OrganizationPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Organizacion.
 *
 * @author ISIS2603
 */
@Stateless
public class OrganizationLogic {

    private static final Logger LOGGER = Logger.getLogger(OrganizationLogic.class.getName());

    @Inject
    private OrganizationPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una organizacion en la persistencia.
     *
     * @param organizationEntity La entidad que representa la organizacion a
     * persistir.
     * @return La entidad de la organizacion luego de persistirla.
     * @throws BusinessLogicException Si la organizacion a persistir ya existe.
     */
    public OrganizationEntity createOrganization(OrganizationEntity organizationEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la organizacion");
        // Verifica la regla de negocio que dice que no puede haber dos organizaciones con el mismo nombre
        if (persistence.findByName(organizationEntity.getName()) != null) {
            throw new BusinessLogicException("Ya existe una Organization con el nombre \"" + organizationEntity.getName() + "\"");
        }
        // Invoca la persistencia para crear la organizacion
        LOGGER.log(Level.INFO, "Termina proceso de creación de la organizacion");
        return persistence.create(organizationEntity);
    }

    /**
     * Obtener todas las organizaciones existentes en la base de datos.
     *
     * @return una lista de organizaciones.
     */
    public List<OrganizationEntity> getOrganizations() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las organizaciones");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<OrganizationEntity> organizacions = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las organizaciones");
        return organizacions;
    }

    /**
     * Obtener una organizacion por medio de su id.
     *
     * @param organizationsId: id de la organizacion para ser buscada.
     * @return la organizacion solicitada por medio de su id.
     */
    public OrganizationEntity getOrganization(Long organizationsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar organizacion con id = {0}", organizationsId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        OrganizationEntity organizacion = persistence.find(organizationsId);
        if (organizacion == null) {
            LOGGER.log(Level.SEVERE, "La organizacion con el id = {0} no existe", organizationsId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar organizacion con id = {0}", organizationsId);
        return organizacion;
    }

    /**
     * Actualizar una organizacion.
     *
     * @param organizationsId: id de la organizacion para buscarla en la base de
     * datos.
     * @param organizationEntity: organizacion con los cambios para ser
     * actualizada, por ejemplo el nombre.
     * @return la organizacion con los cambios actualizados en la base de datos.
     */
    public OrganizationEntity updateOrganization(Long organizationsId, OrganizationEntity organizationEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar organizacion con id = {0}", organizationsId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        OrganizationEntity newEntity = persistence.update(organizationEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar organizacion con id={0}", organizationEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un organizacion
     *
     * @param organizationsId: id de la organizacion a borrar
     * @throws BusinessLogicException si la organizacion tiene un premio
     * asociado.
     */
    public void deleteOrganization(Long organizationsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar organizacion con id = {0}", organizationsId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        OrganizationEntity organizationEntity = persistence.find(organizationsId);
        if (organizationEntity.getPrize() != null) {
            throw new BusinessLogicException("No se puede borrar la organizacion con id = " + organizationsId + " porque tiene un premio asociado");
        }
        persistence.delete(organizationsId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar organizacion con id = {0}", organizationsId);
    }
}
