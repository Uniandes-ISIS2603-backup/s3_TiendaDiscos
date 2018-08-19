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
 * Clase que extiende de {@link OrganizationDTO} para manejar las relaciones
 * entre los OrganizationDTO y otros DTOs. Para conocer el contenido de una
 * organizacion vaya a la documentacion de {@link OrganizationDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "name": string,
 *      "tipo": TIPO_ORGANIZACION,
 *      "prize":{@link PrizeDTO}
 *   }
 * </pre> Por ejemplo una organizacion se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 123,
 *      "name": "Real Academia Sueca de Ciencias",
 *      "tipo": "PRIVADA",
 *      "prize":
 *      {
 *          "id": 1,
 *      	"name": "Hugo Award",
 *      	"description": "The Hugo Awards are a set of literary awards given annually for the best science fiction or fantasy works and achievements of the previous year.",
 *      	"fechaEntrega": "2015-10-1 3:00 PM GMT+1:00",
 *      	"organization":
 *      	{
 *          	"id": 123,
 *          	"name": "Real Academia Sueca de Ciencias",
 *          	"tipo": "PRIVADA"
 *      	}
 *      }
 *  }
 * </pre>
 *
 * @author ISIS2603
 */
public class OrganizationDetailDTO extends OrganizationDTO implements Serializable {

    // relación  uno a uno premio
    private PrizeDTO prize;

    public OrganizationDetailDTO() {
        super();

    }

    /**
     * Crea un objeto OrganizationDetailDTO a partir de un objeto
     * OrganizationEntity incluyendo los atributos de OrganizationDTO.
     *
     * @param organizationEntity Entidad OrganizationEntity desde la cual se va
     * a crear el nuevo objeto.
     *
     */
    public OrganizationDetailDTO(OrganizationEntity organizationEntity) {
        super(organizationEntity);
        if (organizationEntity.getPrize() != null) {
            this.prize = new PrizeDTO(organizationEntity.getPrize());
        }
    }

    /**
     * Convierte un objeto OrganizationDetailDTO a OrganizationEntity incluyendo
     * los atributos de OrganizationDTO.
     *
     * @return Nueva objeto OrganizationEntity.
     *
     */
    @Override
    public OrganizationEntity toEntity() {
        OrganizationEntity entity = super.toEntity();
        if (getPrize() != null) {
            entity.setPrize(getPrize().toEntity());
        }
        return entity;
    }

    /**
     * Devuelve el premio asociado a esta organizacion
     *
     * @return DTO del premio
     */
    public PrizeDTO getPrize() {
        return prize;
    }

    /**
     * Modifica el premio asociado a esta organizacion.
     *
     * @param prize the author to set
     */
    public void setPrize(PrizeDTO prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
