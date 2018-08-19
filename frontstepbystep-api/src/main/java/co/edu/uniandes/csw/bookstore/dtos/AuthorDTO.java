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

import co.edu.uniandes.csw.bookstore.entities.AuthorEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * AuthorDTO Objeto de transferencia de datos de Autores. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "name": string,
 *      "birthDate": date,
 *      "image": string
 *   }
 * </pre> Por ejemplo un autor se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "name": "Gabriel García Márquez",
 *      "birthDate": "23091935",
 *      "image": "mifoto.com"
 *   }
 *
 * </pre>
 *
 * @author ISIS2603
 */
public class AuthorDTO implements Serializable {

    private Long id;
    private String name;
    private Date birthDate;
    private String description;
    private String image;

    /**
     * Constructor vacio
     */
    public AuthorDTO() {
    }

    /**
     * Crea un objeto AuthorDTO a partir de un objeto AuthorEntity.
     *
     * @param authorEntity Entidad AuthorEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public AuthorDTO(AuthorEntity authorEntity) {
        if (authorEntity != null) {
            this.id = authorEntity.getId();
            this.name = authorEntity.getName();
            this.birthDate = authorEntity.getBirthDate();
            this.description = authorEntity.getDescription();
            this.image = authorEntity.getImage();
        }
    }

    /**
     * Convierte un objeto AuthorDTO a AuthorEntity.
     *
     * @return Nueva objeto AuthorEntity.
     *
     */
    public AuthorEntity toEntity() {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setId(this.getId());
        authorEntity.setName(this.getName());
        authorEntity.setBirthDate(this.getBirthDate());
        authorEntity.setDescription(this.description);
        authorEntity.setImage(this.image);
        return authorEntity;
    }

    /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     *
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     *
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el atributo name.
     *
     * @return atributo name.
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el valor del atributo name.
     *
     * @param name nuevo valor del atributo
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el atributo birthDate.
     *
     * @return atributo birthDate.
     *
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Establece el valor del atributo birthDate.
     *
     * @param birthdate nuevo valor del atributo
     *
     */
    public void setBirthDate(Date birthdate) {
        this.birthDate = birthdate;
    }

    /**
     * Obtiene el atributo descripción
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Ontiene el atributo de imagen
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Establece el atributo de descripción
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Establece la imagen del autor
     *
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
