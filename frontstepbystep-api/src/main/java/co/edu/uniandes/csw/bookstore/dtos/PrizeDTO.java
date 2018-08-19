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
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * PrizeDTO Objeto de transferencia de datos de Premios. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "name": string,
 *      "description": string,
 *      "fechaEntrega": date,
 *      "organization": {@link OrganizationDTO}
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
 *      }
 *  }
 * </pre>
 *
 * @author ISIS2603
 */
public class PrizeDTO implements Serializable {

    private Long id;
    private String name;
    private Date fechaEntrega;
    private String description;

    /*
    * Relación a una organizacion.
    dado que esta tiene cardinalidad 1.
     */
    private OrganizationDTO organization;

    /**
     * Constructor por defecto
     */
    public PrizeDTO() {
    }

    /**
     * Constructor a partir de la entidad
     *
     * @param prizeEntity La entidad del premio
     */
    public PrizeDTO(PrizeEntity prizeEntity) {
        if (prizeEntity != null) {
            this.id = prizeEntity.getId();
            this.name = prizeEntity.getName();
            this.description = prizeEntity.getDescription();
            this.fechaEntrega = prizeEntity.getFechaEntrega();
            if (prizeEntity.getOrganization() != null) {
                this.organization = new OrganizationDTO(prizeEntity.getOrganization());
            } else {
                prizeEntity.setOrganization(null);
            }
        }
    }

    /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del del premio.
     */
    public PrizeEntity toEntity() {
        PrizeEntity prizeEntity = new PrizeEntity();
        prizeEntity.setId(this.id);
        prizeEntity.setName(this.name);
        prizeEntity.setDescription(this.description);
        prizeEntity.setFechaEntrega(this.fechaEntrega);
        if (organization != null) {
            prizeEntity.setOrganization(organization.toEntity());
        }
        return prizeEntity;
    }

    /**
     * Devuelve el ID del premio
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el ID del premio.
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del premio.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Modifica el nombre del premio.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Devuelve la descripción del premio.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifica la descripción del premio.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Devuelve la fecha de entrega del premio.
     *
     * @return the fechaEntrega
     */
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * Modifica la fecha de entrega del premio.
     *
     * @param fechaEntrega the fechaEntrega to set
     */
    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    /**
     * Modifica la organizacion asociada a este premio.
     *
     * @param organization the organization to set
     */
    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }

    /**
     * Devuelve la organizacion asociada a este premio
     *
     * @return DTO de la organizacion
     */
    public OrganizationDTO getOrganization() {
        return organization;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
