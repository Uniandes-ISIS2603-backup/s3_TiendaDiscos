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
package co.edu.uniandes.csw.bookstore.persistence;

import co.edu.uniandes.csw.bookstore.entities.PrizeEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 * Clase que maneja la persistencia para Prize. Se conecta a través del Entity
 * Manager de javax.persistance con la base de datos SQL.
 *
 * @author ISIS2603
 */
@Stateless
public class PrizePersistence {

    private static final Logger LOGGER = Logger.getLogger(PrizePersistence.class.getName());

    @PersistenceContext(unitName = "BookStorePU")
    protected EntityManager em;

    /**
     * Busca si hay algun premio con el id que se envía de argumento
     *
     * @param prizeId: id correspondiente al premio buscado.
     * @return un premio.
     */
    public PrizeEntity find(Long prizeId) {
        LOGGER.log(Level.INFO, "Consultando premio con id = {0}", prizeId);
        return em.find(PrizeEntity.class, prizeId);
        
    }

    /**
     * Devuelve todos los premios de la base de datos.
     *
     * @return una lista con todos los premios que encuentre en la base de
     * datos, "select u from PrizeEntity u" es como un "select * from
     * PrizeEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<PrizeEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los premios");
        Query q = em.createQuery("select u from PrizeEntity u");
        return q.getResultList();
    }

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param prizeEntity objeto premio que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PrizeEntity create(PrizeEntity prizeEntity) {
        LOGGER.log(Level.INFO, "Creando un premio nuevo");
        em.persist(prizeEntity);
        LOGGER.log(Level.INFO, "Premio creado");
        return prizeEntity;
    }

    /**
     * Actualiza un premio.
     *
     * @param prizeEntity: el premio que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un premio con los cambios aplicados.
     */
    public PrizeEntity update(PrizeEntity prizeEntity) {
        LOGGER.log(Level.INFO, "Actualizando premio con id = {0}", prizeEntity.getId());
        return em.merge(prizeEntity);
    }

    /**
     *
     * Borra un premio de la base de datos recibiendo como argumento el id del
     * premio
     *
     * @param prizeId: id correspondiente al premio a borrar.
     */
    public void delete(Long prizeId) {
        LOGGER.log(Level.INFO, "Borrando premio con id = {0}", prizeId);
        PrizeEntity entity = em.find(PrizeEntity.class, prizeId);
        em.remove(entity);
    }
}
