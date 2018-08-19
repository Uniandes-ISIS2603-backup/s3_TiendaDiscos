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

import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.entities.ReviewEntity;
import co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.bookstore.persistence.BookPersistence;
import co.edu.uniandes.csw.bookstore.persistence.ReviewPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Reseña(Review).
 *
 * @author ISIS2603
 */
@Stateless
public class ReviewLogic {

    private static final Logger LOGGER = Logger.getLogger(ReviewLogic.class.getName());

    @Inject
    private ReviewPersistence persistence;

    @Inject
    private BookPersistence bookPersistence;

    /**
     * Se encarga de crear un Review en la base de datos.
     *
     * @param reviewEntity Objeto de ReviewEntity con los datos nuevos
     * @param booksId id del Book el cual sera padre del nuevo Review.
     * @return Objeto de ReviewEntity con los datos nuevos y su ID.
     *
     */
    public ReviewEntity createReview(Long booksId, ReviewEntity reviewEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de crear review");
        BookEntity book = bookPersistence.find(booksId);
        reviewEntity.setBook(book);
        LOGGER.log(Level.INFO, "Termina proceso de creación del review");
        return persistence.create(reviewEntity);
    }

    /**
     * Obtiene la lista de los registros de Review que pertenecen a un Book.
     *
     * @param booksId id del Book el cual es padre de los Reviews.
     * @return Colección de objetos de ReviewEntity.
     */
    public List<ReviewEntity> getReviews(Long booksId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los reviews asociados al book con id = {0}", booksId);
        BookEntity bookEntity = bookPersistence.find(booksId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar los reviews asociados al book con id = {0}", booksId);
        return bookEntity.getReviews();
    }

    /**
     * Obtiene los datos de una instancia de Review a partir de su ID. La
     * existencia del elemento padre Book se debe garantizar.
     *
     * @param booksId El id del Libro buscado
     * @param reviewsId Identificador de la Reseña a consultar
     * @return Instancia de ReviewEntity con los datos del Review consultado.
     *
     */
    public ReviewEntity getReview(Long booksId, Long reviewsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el review con id = {0} del libro con id = " + booksId, reviewsId);
        return persistence.find(booksId, reviewsId);
    }

    /**
     * Actualiza la información de una instancia de Review.
     *
     * @param reviewEntity Instancia de ReviewEntity con los nuevos datos.
     * @param booksId id del Book el cual sera padre del Review actualizado.
     * @return Instancia de ReviewEntity con los datos actualizados.
     */
    public ReviewEntity updateReview(Long booksId, ReviewEntity reviewEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el review con id = {0} del libro con id = " + booksId, reviewEntity.getId());
        BookEntity bookEntity = bookPersistence.find(booksId);
        ReviewEntity old = getReview(booksId, reviewEntity.getId());
        reviewEntity.setBook(bookEntity);
        persistence.update(reviewEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el review con id = {0} del libro con id = " + booksId, reviewEntity.getId());
        return reviewEntity;
    }

   /**
     * Elimina una instancia de Review de la base de datos.
     *
     * @param reviewsId Identificador de la instancia a eliminar.
     * @param booksId id del Book el cual es padre del Review.
     * @throws BusinessLogicException Si la reseña no esta asociada al libro.
     *
     */
    public void deleteReview(Long booksId, Long reviewsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el review con id = {0} del libro con id = " + booksId, reviewsId);
        ReviewEntity old = getReview(booksId, reviewsId);
        if (old == null) {
            throw new BusinessLogicException("El review con id = " + reviewsId + " no esta asociado a el libro con id = " + booksId);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el review con id = {0} del libro con id = " + booksId, reviewsId);
    }
}
