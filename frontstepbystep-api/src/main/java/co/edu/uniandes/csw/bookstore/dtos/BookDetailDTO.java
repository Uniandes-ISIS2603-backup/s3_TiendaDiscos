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

import co.edu.uniandes.csw.bookstore.entities.BookEntity;
import co.edu.uniandes.csw.bookstore.entities.ReviewEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que extiende de {@link BookDTO} para manejar las relaciones entre los
 * BookDTO y otros DTOs. Para conocer el contenido de la un Libro vaya a la
 * documentacion de {@link BookDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "name": string,
 *      "isbn": string,
 *      "image: string,
 *      "description": string,
 *      "publishingdate": date,
 *      "editorial": {@link EditorialDTO},
 *      "reviews": [{@link ReviewDTO}]
 *   }
 * </pre> Por ejemplo un libro se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 123,
 *      "name": "Historia de los hombres lobo",
 *      "isbn": "930330149-8",
 *      "image: "https://static.iris.net.co/arcadia/upload/images/2017/7/31/64899_1.jpg",
 *      "description": "Jorge Fondebrider traza un mundo fantástico con mapas de la geografía real y de sus mitologías, observando a los hombres lobo que han vivido en la imaginación de Europa y América.",
 *      "publishingdate": "2000-08-20T00:00:00-05:00",
 *      "editorial":
 *      {
 *          "id" : 1,
 *          "name" : "Plaza y Janes"
 *      },
 *      "reviews": [
 *          {
 *              "id": 123,
 *              "name": "Mi nuevo review",
 *              "source": "NY Times",
 *              "description": "Un relato terrorífico",
 *              "book":
 *              {
 *                  "id": 123,
 *                  "name": "Historia de los hombres lobo",
 *                  "isbn": "930330149-8",
 *                  "image: "https://static.iris.net.co/arcadia/upload/images/2017/7/31/64899_1.jpg",
 *                  "description": "Jorge Fondebrider traza un mundo fantástico con mapas de la geografía real y de sus mitologías, observando a los hombres lobo que han vivido en la imaginación de Europa y América.",
 *                  "publishingdate": "2000-08-20T00:00:00-05:00",
 *                  "editorial":
 *                  {
 *                      "id" : 1,
 *                      "name" : "Plaza y Janes"
 *                  }
 *              }
 *          },
 *          {
 *              "id": 124,
 *              "name": "Mi nuevo review 2",
 *              "source": "NY Times",
 *              "description": "Un relato terrorífico 2",
 *              "book":
 *              {
 *                  "id": 123,
 *                  "name": "Historia de los hombres lobo",
 *                  "isbn": "930330149-8",
 *                  "image: "https://static.iris.net.co/arcadia/upload/images/2017/7/31/64899_1.jpg",
 *                  "description": "Jorge Fondebrider traza un mundo fantástico con mapas de la geografía real y de sus mitologías, observando a los hombres lobo que han vivido en la imaginación de Europa y América.",
 *                  "publishingdate": "2000-08-20T00:00:00-05:00",
 *                  "editorial":
 *                  {
 *                      "id" : 1,
 *                      "name" : "Plaza y Janes"
 *                  }
 *              }
 *          }
 *      ]
 *   }
 *
 * </pre>
 *
 * @author ISIS2603
 */
public class BookDetailDTO extends BookDTO implements Serializable {

    // relación  cero o muchos reviews 
    private List<ReviewDTO> reviews;

    public BookDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param bookEntity La entidad de la cual se construye el DTO
     */
    public BookDetailDTO(BookEntity bookEntity) {
        super(bookEntity);
        if (bookEntity.getReviews() != null) {
            reviews = new ArrayList<>();
            for (ReviewEntity entityReview : bookEntity.getReviews()) {
                reviews.add(new ReviewDTO(entityReview));
            }
        }
    }

    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el libro.
     */
    @Override
    public BookEntity toEntity() {
        BookEntity bookEntity = super.toEntity();
        if (reviews != null) {
            List<ReviewEntity> reviewsEntity = new ArrayList<>();
            for (ReviewDTO dtoReview : getReviews()) {
                reviewsEntity.add(dtoReview.toEntity());
            }
            bookEntity.setReviews(reviewsEntity);
        }
        return bookEntity;
    }

    /**
     * Devuelve las reseñas asociadas a este libro
     *
     * @return Lista de DTOs de Reseñas
     */
    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    /**
     * Modifica las reseñas de este libro.
     *
     * @param reviews Las nuevas reseñas
     */
    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

}
