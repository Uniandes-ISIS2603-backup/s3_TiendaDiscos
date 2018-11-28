package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase que extiende de {@link ViniloDTO} para manejar las relaciones entre los
 * ViniloDTO y otros DTOs. Para conocer el contenido de un Vinilo vaya a la
 * documentación de {@link ViniloDTO}
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *  {
 *      "id":number,
 *      "nombre":String,
 *      "artista":String,
 *      "fechaLanzamiento":Date,
 *      "productora":String,
 *      "informacionAdicional":String,
 *      "previewURI":String,
 *      "calificacion":number,
 *      "precio":number,
 *      "usuario":{@link UsuarioDTO},
 *      "categoria": String,
 *      "canciones":[{@link CancionDTO}]
 *  }
 * </pre> Por ejemplo un autor se representa asi:<br>
 *
 * <pre>
 *  {
 *      "id": 1,
 *      "nombre": "Por pasar desarrollo",
 *      "artista": "IDK",
 *      "fechaLanzamiento": 04/10/1999,
 *      "productora": "Records de los alpes" ,
 *      "informacionAdicional": "Nope, that's all",
 *      "previewURI": "nananananana.batman.mp3",
 *      "calificacion": 4.4,
 *      "precio": 20000,
 *      "usuario":
 *      {
 *          "id": 123412,
 *          "username": JohnDoe,
 *          "email": John@doe.com,
 *          "contrasenha": "12341321wsadfsda",
 *          "name": "John Doe",
 *          "direccion": "cll 21 #12321",
 *          "rol": "ADMIN",
 *          "wishList":{},
 *          "billingInfo":{},
 *          "carritoDeCompras":{}
 *      },
 *      "categoria": "Rock",
 *      "canciones":[{}]
 *  }
 * </pre>
 * @author Andrés Hernández
 */
public class ViniloDetailDTO extends ViniloDTO implements Serializable {

    /**
     * Relación de 1 a muchas canciones.
     */
    private List<CancionDTO> canciones;

    /**
     * Constructor vacio.
     */
    public ViniloDetailDTO() {
        super();
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param viniloEntity La entidad de la editorial para transformar a DTO.
     */
    public ViniloDetailDTO(ViniloEntity viniloEntity) {
        super(viniloEntity);
        if (viniloEntity != null) {
            canciones = new ArrayList<>();
            for (CancionEntity cancionEntity : viniloEntity.getCanciones()) {
                canciones.add(new CancionDTO(cancionEntity));
            }
        }
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de la editorial para transformar a Entity
     */
    @Override
    public ViniloEntity toEntity() {
        ViniloEntity viniloEntity = super.toEntity();
        List<CancionEntity> cancionesEntity = null;

        if (getCanciones() != null && getCanciones().isEmpty()) {
            cancionesEntity = new ArrayList<>();
            for (CancionDTO cancionDTO : canciones) {
                cancionesEntity.add(cancionDTO.toEntity());
            }
            viniloEntity.setCanciones(cancionesEntity);
        }

        return viniloEntity;
    }

    /**
     * Devuelve las canciones asociadas al vinilo.
     *
     * @return atributo canciones.
     */
    public List<CancionDTO> getCanciones() {
        return canciones;
    }

    /**
     * Modifica las canciones del vinilo.
     *
     * @param canciones nuevas canciones vinilo.
     */
    public void setCanciones(List<CancionDTO> canciones) {
        this.canciones = canciones;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
