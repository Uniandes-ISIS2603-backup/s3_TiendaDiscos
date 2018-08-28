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

import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import co.edu.uniandes.csw.bookstore.entities.PrizeEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.persistence.AuthorPersistence;
import co.edu.uniandes.csw.bookstore.persistence.PrizePersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Prize y Author
 *
 * @author ISIS2603
 */
@Stateless
public class PrizeAuthorLogic {

    private static final Logger LOGGER = Logger.getLogger(PrizeAuthorLogic.class.getName());

    @Inject
    private AuthorPersistence authorPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private PrizePersistence prizePersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Agregar un autor a un premio
     *
     * @param prizesId El id premio a guardar
     * @param authorsId El id del autor al cual se le va a guardar el premio.
     * @return El premio que fue agregado al autor.
     */
    public AuthorEntity addAuthor(Long authorsId, Long prizesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociar el autor con id = {0} al premio con id = " + prizesId, authorsId);
        AuthorEntity autorEntity = authorPersistence.find(authorsId);
        PrizeEntity prizeEntity = prizePersistence.find(prizesId);
        prizeEntity.setAuthor(autorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el autor con id = {0} al premio con id = " + prizesId, authorsId);
        return authorPersistence.find(authorsId);
    }

    /**
     *
     * Obtener un premio por medio de su id y el de su autor.
     *
     * @param prizesId id del premio a ser buscado.
     * @return el autor solicitada por medio de su id.
     */
    public AuthorEntity getAuthor(Long prizesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el autor del premio con id = {0}", prizesId);
        AuthorEntity authorEntity = prizePersistence.find(prizesId).getAuthor();
        LOGGER.log(Level.INFO, "Termina proceso de consultar el autor del premio con id = {0}", prizesId);
        return authorEntity;
    }

    /**
     * Remplazar autor de un premio
     *
     * @param prizesId el id del premio que se quiere actualizar.
     * @param authorsId El id del nuebo autor asociado al premio.
     * @return el nuevo autor asociado.
     */
    public AuthorEntity replaceAuthor(Long prizesId, Long authorsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el autor del premio premio con id = {0}", prizesId);
        AuthorEntity autorEntity = authorPersistence.find(authorsId);
        PrizeEntity prizeEntity = prizePersistence.find(prizesId);
        prizeEntity.setAuthor(autorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociar el autor con id = {0} al premio con id = " + prizesId, authorsId);
        return authorPersistence.find(authorsId);
    }

    /**
     * Borrar el autor de un premio
     *
     * @param prizesId El premio que se desea borrar del autor.
     * @throws BusinessLogicException si el premio no tiene autor
     */
    public void removeAuthor(Long prizesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el autor del premio con id = {0}", prizesId);
        PrizeEntity prizeEntity = prizePersistence.find(prizesId);
        if (prizeEntity.getAuthor() == null) {
            throw new BusinessLogicException("El premio no tiene autor");
        }
        AuthorEntity authorEntity = authorPersistence.find(prizeEntity.getAuthor().getId());
        prizeEntity.setAuthor(null);
        authorEntity.getPrizes().remove(prizeEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el autor con id = {0} del premio con id = " + prizesId, authorEntity.getId());
    }
}
