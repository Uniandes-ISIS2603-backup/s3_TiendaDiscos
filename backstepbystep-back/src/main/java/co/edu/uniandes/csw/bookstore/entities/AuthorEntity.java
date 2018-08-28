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

import co.edu.uniandes.csw.bookstore.podam.DateStrategy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 * Clase que representa un autor en la persistencia y permite su serialización
 *
 * @author ISIS2603
 */
@Entity
public class AuthorEntity extends BaseEntity implements Serializable {

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date birthDate;

    @PodamExclude
    @ManyToMany(mappedBy = "authors")
    private List<BookEntity> books = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "author",fetch=FetchType.LAZY)
    private List<PrizeEntity> prizes = new ArrayList<>();

    private String name;
    private String description;
    private String image;

    /**
     * Devuelve el nombre del autor.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Modifica el nombre del autor.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el atributo birthDate.
     *
     * @return atributo birthDate.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Establece el valor del atributo birthDate.
     *
     * @param birthDate nuevo valor del atributo
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Obtiene la colección de books.
     *
     * @return colección books.
     */
    public List<BookEntity> getBooks() {
        return books;
    }

    /**
     * Establece el valor de la colección de books.
     *
     * @param books nuevo valor de la colección.
     */
    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    /**
     * Obtiene la colección de premios.
     *
     * @return colección prizes.
     */
    public List<PrizeEntity> getPrizes() {
        return prizes;
    }

    /**
     * Establece el valor de la colección de prizes.
     *
     * @param prizes nuevo valor de la colección.
     */
    public void setPrizes(List<PrizeEntity> prizes) {
        this.prizes = prizes;
    }

    /**
     * Devuelve la descripción del autor
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifica la descripción del autor
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Devuelve la imagen del autor
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Modifica la imagen del autor
     *
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }
}
