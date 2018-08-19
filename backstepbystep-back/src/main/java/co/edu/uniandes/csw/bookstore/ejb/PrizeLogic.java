/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.bookstore.ejb;

import co.edu.uniandes.csw.bookstore.entities.OrganizationEntity;
import co.edu.uniandes.csw.bookstore.entities.PrizeEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.persistence.OrganizationPersistence;
import co.edu.uniandes.csw.bookstore.persistence.PrizePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Prize.
 *
 * @author ISIS2603
 */
@Stateless
public class PrizeLogic {

    private static final Logger LOGGER = Logger.getLogger(PrizeLogic.class.getName());

    @Inject
    private PrizePersistence prizePersistence;

    @Inject
    private OrganizationPersistence organizationPersistence;

    /**
     * Guardar un nuevo premio
     *
     * @param prizeEntity La entidad de tipo premio del nuevo premio a
     * persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException si la organizacion no existe o ya tiene
     * premio.
     */
    public PrizeEntity createPrize(PrizeEntity prizeEntity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creación de premio");
        if (prizeEntity.getOrganization() == null) {
            throw new BusinessLogicException("La organizacion es inválida");
        }
        OrganizationEntity organizationEntity = organizationPersistence.find(prizeEntity.getOrganization().getId());
        if (organizationEntity == null) {
            throw new BusinessLogicException("La organizacion es inválida");
        }
        if (organizationEntity.getPrize() != null) {
            throw new BusinessLogicException("La organizacion ya tiene premio");
        }
        prizeEntity.setOrganization(organizationEntity);
        organizationEntity.setPrize(prizeEntity);
        prizeEntity = prizePersistence.create(prizeEntity);
        LOGGER.info("Termina proceso de creación de premio");
        return prizeEntity;
    }

    /**
     * Devuelve todos los premios que hay en la base de datos.
     *
     * @return Lista de entidades de tipo premio.
     */
    public List<PrizeEntity> getPrizes() {
        LOGGER.info("Inicia proceso de consultar todos los premios");
        List<PrizeEntity> prizes = prizePersistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los premios");
        return prizes;
    }

    /**
     * Busca un premio por ID
     *
     * @param prizesId El id del premio a buscar
     * @return El premio encontrado, null si no lo encuentra.
     */
    public PrizeEntity getPrize(Long prizesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar premio con id = {0}", prizesId);
        PrizeEntity prize = prizePersistence.find(prizesId);
        if (prize == null) {
            LOGGER.log(Level.SEVERE, "El premio con el id = {0} no existe", prizesId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar premio con id = {0}", prizesId);
        return prize;
    }

    /**
     * Actualizar un premio por ID
     *
     * @param prizesId El ID del premio a actualizar
     * @param prizeEntity La entidad del premio con los cambios deseados
     * @return La entidad del premio luego de actualizarla
     */
    public PrizeEntity updatePrize(Long prizesId, PrizeEntity prizeEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar premio con id = {0}", prizesId);
        PrizeEntity newEntity = prizePersistence.update(prizeEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar premio con id = {0}", prizeEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar un premio por ID
     *
     * @param prizesId El ID del premio a eliminar
     * @throws BusinessLogicException si el premio tiene un autor asociado.
     */
    public void deletePrize(Long prizesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar premio con id = {0}", prizesId);
        if (prizePersistence.find(prizesId).getAuthor() != null) {
            throw new BusinessLogicException("No se puede borrar el premio con id = " + prizesId + " porque tiene un autor asociado");
        }
        prizePersistence.delete(prizesId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar premio con id = {0}", prizesId);
    }

}
