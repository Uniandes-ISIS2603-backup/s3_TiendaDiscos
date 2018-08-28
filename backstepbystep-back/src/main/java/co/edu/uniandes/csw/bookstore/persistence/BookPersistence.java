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

import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Book. Se conecta a través del Entity
 * Manager de javax.persistance con la base de datos SQL.
 *
 * @author ISIS2603
 */
@Stateless
public class BookPersistence {

    private static final Logger LOGGER = Logger.getLogger(BookPersistence.class.getName());

    @PersistenceContext(unitName = "BookStorePU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param bookEntity objeto libro que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public BookEntity create(BookEntity bookEntity) {
        LOGGER.log(Level.INFO, "Creando un libro nuevo");
        em.persist(bookEntity);
        LOGGER.log(Level.INFO, "Libro creado");
        return bookEntity;
    }

    /**
     * Devuelve todos loslibros de la base de datos.
     *
     * @return una lista con todos los libros que encuentre en la base de datos,
     * "select u from BookEntity u" es como un "select * from BookEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<BookEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los libros");
        Query q = em.createQuery("select u from BookEntity u");
        return q.getResultList();
    }

    /**
     * Busca si hay algun lubro con el id que se envía de argumento
     *
     * @param booksId: id correspondiente al libro buscado.
     * @return un libro.
     */
    public BookEntity find(Long booksId) {
        LOGGER.log(Level.INFO, "Consultando el libro con id={0}", booksId);
        return em.find(BookEntity.class, booksId);
    }

    /**
     * Actualiza un libro.
     *
     * @param bookEntity: el libro que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return un libro con los cambios aplicados.
     */
    public BookEntity update(BookEntity bookEntity) {
        LOGGER.log(Level.INFO, "Actualizando el libro con id={0}", bookEntity.getId());
        return em.merge(bookEntity);
    }

    /**
     *
     * Borra un libro de la base de datos recibiendo como argumento el id del
     * libro
     *
     * @param booksId: id correspondiente al libro a borrar.
     */
    public void delete(Long booksId) {
        LOGGER.log(Level.INFO, "Borrando el libro con id={0}", booksId);
        BookEntity bookEntity = em.find(BookEntity.class, booksId);
        em.remove(bookEntity);
    }

    /**
     * Busca si hay algun libro con el ISBN que se envía de argumento
     *
     * @param isbn: ISBN de la editorial que se está buscando
     * @return null si no existe ningun libro con el isbn del argumento. Si
     * existe alguno devuelve el primero.
     */
    public BookEntity findByISBN(String isbn) {
        LOGGER.log(Level.INFO, "Consultando libros por isbn ", isbn);
        // Se crea un query para buscar libros con el isbn que recibe el método como argumento. ":isbn" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From BookEntity e where e.isbn = :isbn", BookEntity.class);
        // Se remplaza el placeholder ":isbn" con el valor del argumento 
        query = query.setParameter("isbn", isbn);
        // Se invoca el query se obtiene la lista resultado
        List<BookEntity> sameISBN = query.getResultList();
        BookEntity result;
        if (sameISBN == null) {
            result = null;
        } else if (sameISBN.isEmpty()) {
            result = null;
        } else {
            result = sameISBN.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar libros por isbn ", isbn);
        return result;
    }
}
