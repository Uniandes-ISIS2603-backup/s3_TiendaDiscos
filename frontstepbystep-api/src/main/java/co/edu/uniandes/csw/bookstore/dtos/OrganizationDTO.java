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

import co.edu.uniandes.csw.bookstore.entities.OrganizationEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * OrganizationDTO Objeto de transferencia de datos de Organizaciones. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "name": string,
 *      "tipo": TIPO_ORGANIZACION
 *   }
 * </pre> Por ejemplo una organizacion se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 123,
 *      "name": "Real Academia Sueca de Ciencias",
 *      "tipo": "PRIVADA"
 *    }
 * </pre>
 *
 * @author ISIS2603
 */
public class OrganizationDTO implements Serializable {

    private Long id;

    private String name;

    private OrganizationEntity.TIPO_ORGANIZACION tipo;

    /**
     * Constructor por defecto
     */
    public OrganizationDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param organizationEntity La entidad de la organizacion para transformar
     * a DTO.
     */
    public OrganizationDTO(OrganizationEntity organizationEntity) {
        if (organizationEntity != null) {
            this.id = organizationEntity.getId();
            this.name = organizationEntity.getName();
            this.tipo = organizationEntity.getTipo();
        }
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de la organizacion para transformar a Entity
     */
    public OrganizationEntity toEntity() {
        OrganizationEntity entity = new OrganizationEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setTipo(this.tipo);
        return entity;
    }

    /**
     * Devuelve el ID de la organizacion.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el ID de la organizacion.
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre de la organizacion.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Modifica el nombre de la organizacion.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Devuelve el tipo de la organizacion.
     *
     * @return the tipo
     */
    public OrganizationEntity.TIPO_ORGANIZACION getTipo() {
        return tipo;
    }

    /**
     * Modifica el tipo de la organizacion.
     *
     * @param tipo the tipo to set
     */
    public void setTipo(OrganizationEntity.TIPO_ORGANIZACION tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
