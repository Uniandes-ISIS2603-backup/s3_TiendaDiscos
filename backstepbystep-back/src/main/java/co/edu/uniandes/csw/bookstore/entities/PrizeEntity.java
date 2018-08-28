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
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 * Clase que representa un premio en la persistencia y permite su serialización
 *
 * @author ISIS2603
 */
@Entity
public class PrizeEntity extends BaseEntity implements Serializable {

    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaEntrega;

    @PodamExclude
    @ManyToOne
    private AuthorEntity author;

    private String name;
    private String description;

    @PodamExclude
    @OneToOne
    private OrganizationEntity organization;

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
     * Obtiene el atributo fechaEntrega.
     *
     * @return atributo fechaEntrega.
     */
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    /**
     * Establece el valor del atributo fechaEntrega.
     *
     * @param fechaEntrega nuevo valor del atributo
     */
    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    /**
     * Devuelve el autor al que le pertenece el premio.
     *
     * @return Una entidad de editorial.
     */
    public AuthorEntity getAuthor() {
        return author;
    }

    /**
     * Modifica el autor al que le pertenece el premio.
     *
     * @param authorEntity el nuevo autor.
     */
    public void setAuthor(AuthorEntity authorEntity) {
        this.author = authorEntity;
    }

    /**
     * Devuelve la descripción del premio
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modifica la descripción del premio
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Devuelve la organizacion del premio
     *
     * @return the organization
     */
    public OrganizationEntity getOrganization() {
        return organization;
    }

    /**
     * Modifica la organizacion del premio
     *
     * @param organizationEntity the organization to set
     */
    public void setOrganization(OrganizationEntity organizationEntity) {
        this.organization = organizationEntity;
    }
}
