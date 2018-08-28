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
package co.edu.uniandes.csw.bookstore.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa una reseña en la persistencia y permite su serializacion
 *
 * @author ISIS2603
 */
@Entity
public class ReviewEntity extends BaseEntity implements Serializable {

    private String name;
    private String source;
    private String description;

    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private BookEntity book;

    /**
     * Devuelve el nombre de la reseña.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Modifica el nombre de la reseña.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Devuelve la fuente de la reseña
     *
     * @return La fuente
     */
    public String getSource() {
        return source;
    }

    /**
     * Modifica la fuente de la reseña
     *
     * @param source La nueva fuente
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Devuelve la descripción de la reseña
     *
     * @return La descripción
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifica la descripción de la reseña
     *
     * @param description La nueva descripción
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Devuelve el libro asociado a esta reseña
     *
     * @return Entidad de tipo Libro
     */
    public BookEntity getBook() {
        return book;
    }

    /**
     * Modifica el libro asociado a esta reseña
     *
     * @param bookEntity El nuevo libro
     */
    public void setBook(BookEntity bookEntity) {
        this.book = bookEntity;
    }
}
