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

import co.edu.uniandes.csw.bookstore.entities.PrizeEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase que extiende de {@link PrizeDTO} para manejar las relaciones entre los
 * PrizeDTO y otros DTOs. Para conocer el contenido de un Premio vaya a la
 * documentacion de {@link PrizeDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "name": string,
 *      "description": string,
 *      "fechaEntrega": date,
 *      "organization": {@link OrganizationDTO},
 *      "author": {@link AuthorDTO}
 *   }
 * </pre> Por ejemplo un autor se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "name": "Hugo Award",
 *      "description": "The Hugo Awards are a set of literary awards given annually for the best science fiction or fantasy works and achievements of the previous year.",
 *      "fechaEntrega": "2015-10-1 3:00 PM GMT+1:00",
 *      "organization":
 *      {
 *          "id": 123,
 *          "name": "Real Academia Sueca de Ciencias",
 *          "tipo": "PRIVADA"
 *      },
 *      "author":
 *      {
 *          "id": 1,
 *          "name": "Gabriel García Márquez",
 *          "birthDate": "23091935",
 *          "image": "mifoto.com"
 *      }
 *  }
 *
 * </pre>
 *
 * @author ISIS2603
 */
public class PrizeDetailDTO extends PrizeDTO implements Serializable {

    private AuthorDTO author;

    /**
     * Constructor por defecto
     */
    public PrizeDetailDTO() {
        super();
    }

    /**
     * Crea un objeto PrizeDetailDTO a partir de un objeto PrizeEntity
     * incluyendo los atributos de PrizeDTO.
     *
     * @param prizeEntity Entidad PrizeEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public PrizeDetailDTO(PrizeEntity prizeEntity) {
        super(prizeEntity);
        if (prizeEntity.getAuthor() != null) {
            this.author = new AuthorDTO(prizeEntity.getAuthor());
        }
    }

    /**
     * Convierte un objeto PrizeDetailDTO a PrizeEntity incluyendo los atributos
     * de PrizeDTO.
     *
     * @return Nueva objeto PrizeEntity.
     *
     */
    @Override
    public PrizeEntity toEntity() {
        PrizeEntity prizeEntity = super.toEntity();
        if (author != null) {
            prizeEntity.setAuthor(author.toEntity());
        }
        return prizeEntity;
    }

    /**
     * Modifica el autor asociada a este premio.
     *
     * @param author the author to set
     */
    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    /**
     * Devuelve el autor asociada a este premio
     *
     * @return DTO de Author
     */
    public AuthorDTO getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
