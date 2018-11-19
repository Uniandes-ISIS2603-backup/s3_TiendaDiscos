package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.CancionEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.ViniloEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Andrés Hernández
 */
public class ViniloDetailDTO extends ViniloDTO implements Serializable
{
    /**
     * Relación de 1 a muchas canciones.
     */
    private List<CancionDTO> canciones;

   /**
    * Constructor vacio.
    */
   public ViniloDetailDTO()
   {
       super();
   }
   
   /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param viniloEntity La entidad de la editorial para transformar a DTO.
     */
   public ViniloDetailDTO(ViniloEntity viniloEntity)
   {
       super(viniloEntity);
       if(viniloEntity != null )
       {
           canciones = new ArrayList<>();
           for(CancionEntity cancionEntity: viniloEntity.getCanciones())
               canciones.add(new CancionDTO(cancionEntity));                       
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
        List<CancionEntity> cancionesEntity;
        if (canciones != null) 
        {
            cancionesEntity = new ArrayList<>();
            for (CancionDTO cancionDTO : canciones) 
                cancionesEntity.add(cancionDTO.toEntity());
        }
        return viniloEntity;
    }
    
    /**
     * Devuelve las canciones asociadas al vinilo.
     * @return atributo canciones.
     */
    public List<CancionDTO> getCanciones() 
    {
        return canciones;
    }

    /**
     * Modifica las canciones del vinilo.
     * @param canciones nuevas canciones vinilo.
     */
    public void setCanciones(List<CancionDTO> canciones) 
    {
        this.canciones = canciones;
    }
    
    @Override
    public String toString() 
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}