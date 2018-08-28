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

import co.edu.uniandes.csw.bookstore.entities.ReviewEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Review. Se conecta a través del Entity
 * Manager de javax.persistance con la base de datos SQL.
 *
 * @author ISIS2603
 */
@Stateless
public class ReviewPersistence {

    private static final Logger LOGGER = Logger.getLogger(ReviewPersistence.class.getName());

    @PersistenceContext(unitName = "BookStorePU")
    protected EntityManager em;

    /**
     * Crear una reseña
     *
     * Crea una nueva reseña con la información recibida en la entidad.
     *
     * @param reviewEntity La entidad que representa la nueva reseña
     * @return La entidad creada
     */
    public ReviewEntity create(ReviewEntity reviewEntity) {
        LOGGER.log(Level.INFO, "Creando un review nuevo");
        em.persist(reviewEntity);
        LOGGER.log(Level.INFO, "Review creado");
        return reviewEntity;
    }

    /**
     * Actualizar una reseña
     *
     * Actualiza la entidad que recibe en la base de datos
     *
     * @param reviewEntity La entidad actualizada que se desea guardar
     * @return La entidad resultante luego de la acutalización
     */
    public ReviewEntity update(ReviewEntity reviewEntity) {
        LOGGER.log(Level.INFO, "Actualizando review con id = {0}", reviewEntity.getId());
        return em.merge(reviewEntity);
    }

    /**
     * Eliminar una reseña
     *
     * Elimina la reseña asociada al ID que recibe
     *
     * @param reviewsId El ID de la reseña que se desea borrar
     */
    public void delete(Long reviewsId) {
        LOGGER.log(Level.INFO, "Borrando review con id = {0}", reviewsId);
        ReviewEntity reviewEntity = em.find(ReviewEntity.class, reviewsId);
        em.remove(reviewEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar El review con id = {0}", reviewsId);
    }

    /**
     * Buscar una reseña
     *
     * Busca si hay alguna reseña asociada a un libro y con un ID específico
     *
     * @param booksId El ID del libro con respecto al cual se busca
     * @param reviewsId El ID de la reseña buscada
     * @return La reseña encontrada o null. Nota: Si existe una o más reseñas
     * devuelve siempre la primera que encuentra
     */
    public ReviewEntity find(Long booksId, Long reviewsId) {
        LOGGER.log(Level.INFO, "Consultando el review con id = {0} del libro con id = " + booksId, reviewsId);
        TypedQuery<ReviewEntity> q = em.createQuery("select p from ReviewEntity p where (p.book.id = :bookid) and (p.id = :reviewsId)", ReviewEntity.class);
        q.setParameter("bookid", booksId);
        q.setParameter("reviewsId", reviewsId);
        List<ReviewEntity> results = q.getResultList();
        ReviewEntity review = null;
        if (results == null) {
            review = null;
        } else if (results.isEmpty()) {
            review = null;
        } else if (results.size() >= 1) {
            review = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar el review con id = {0} del libro con id =" + booksId, reviewsId);
        return review;
    }
}
