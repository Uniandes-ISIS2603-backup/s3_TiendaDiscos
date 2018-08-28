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
package co.edu.uniandes.csw.bookstore.dtos;

import co.edu.uniandes.csw.bookstore.entities.ReviewEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ReviewDTO Objeto de transferencia de datos de Reseñas. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "name: string,
 *      "source": string,
 *      "description": string,
 *      "book": {@link BookDTO}
 *   }
 * </pre> Por ejemplo una reseña se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 123,
 *      "name": "Mi nueva review",
 *      "source": "NY Times",
 *      "description": "Un relato terrorífico",
 *      "book":
 *      {
 *          "id": 123,
 *          "name": "Historia de los hombres lobo",
 *          "isbn": "930330149-8",
 *          "image: "https://static.iris.net.co/arcadia/upload/images/2017/7/31/64899_1.jpg",
 *          "description": "Jorge Fondebrider traza un mundo fantástico con mapas de la geografía real y de sus mitologías, observando a los hombres lobo que han vivido en la imaginación de Europa y América.",
 *          "publishingdate": "2000-08-20T00:00:00-05:00",
 *          "editorial":
 *          {
 *              "id" : 1,
 *              "name" : "Plaza y Janes"
 *          }
 *      }
 *   }
 *
 * </pre>
 *
 * @author ISIS2603
 */
public class ReviewDTO implements Serializable {

    private Long id;
    private String name;
    private String source;
    private String description;

    /*
    * Relación a un book
    * dado que esta tiene cardinalidad 1.
     */
    private BookDTO book;

    /**
     * Constructor por defecto
     */
    public ReviewDTO() {
    }

    /**
     * Constructor a partir de una entidad
     *
     * @param reviewEntity La entidad de la cual se construye el DTO
     */
    public ReviewDTO(ReviewEntity reviewEntity) {
        if (reviewEntity != null) {
            this.id = reviewEntity.getId();
            this.name = reviewEntity.getName();
            this.source = reviewEntity.getSource();
            this.description = reviewEntity.getDescription();
            if (reviewEntity.getBook() != null) {
                this.book = new BookDTO(reviewEntity.getBook());
            } else {
                this.book = null;
            }
        }
    }

    /**
     * Método para transformar del DTO a una entidada.
     *
     * @return La entidad de esta reseña.
     */
    public ReviewEntity toEntity() {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setId(this.id);
        reviewEntity.setName(this.name);
        reviewEntity.setSource(this.source);
        reviewEntity.setDescription(this.description);
        if (this.book != null) {
            reviewEntity.setBook(this.book.toEntity());
        }
        return reviewEntity;
    }

    /**
     * Devuelve el ID de la reseña.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el ID de la reseña.
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre de la reseña.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Modifica el ID de la reseña.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Devuelve la fuente de la reseña.
     *
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * Modifica la fuente de la reseña.
     *
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Devuelve la descripción de la reseña.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifica la descripción de la reseña.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Devuelve el libro asociado a esta reseña.
     *
     * @return the book
     */
    public BookDTO getBook() {
        return book;
    }

    /**
     * Modifica el libro asociado a esta reseña.
     *
     * @param book the book to set
     */
    public void setBook(BookDTO book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
