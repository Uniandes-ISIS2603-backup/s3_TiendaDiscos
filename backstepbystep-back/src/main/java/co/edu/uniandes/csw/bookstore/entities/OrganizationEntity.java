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

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;
import javax.persistence.FetchType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa una organizacion en la persistencia y permite su
 * serialización
 *
 * @author ISIS2603
 */
@Entity
public class OrganizationEntity extends BaseEntity implements Serializable {

    public enum TIPO_ORGANIZACION {
        PRIVADA,
        PUBLICA,
        FUNDACION
    }

    private String name;
    private TIPO_ORGANIZACION tipo;

    @PodamExclude
    @OneToOne(mappedBy = "organization", fetch=FetchType.LAZY)
    private PrizeEntity prize;

    /**
     * Devuelve el nombre de la organización.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Modifica el nombre de la organización.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Devuelve el tipo de la organización.
     *
     * @return the tipo
     */
    public TIPO_ORGANIZACION getTipo() {
        return tipo;
    }

    /**
     * Modifica el tipo de la organización.
     *
     * @param tipo the tipo to set
     */
    public void setTipo(TIPO_ORGANIZACION tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve el premio de la organización.
     *
     * @return the prize
     */
    public PrizeEntity getPrize() {
        return prize;
    }

    /**
     * Modifica el premio de la .
     *
     * @param prizeEntity the prize to set
     */
    public void setPrize(PrizeEntity prizeEntity) {
        this.prize = prizeEntity;
    }
}
