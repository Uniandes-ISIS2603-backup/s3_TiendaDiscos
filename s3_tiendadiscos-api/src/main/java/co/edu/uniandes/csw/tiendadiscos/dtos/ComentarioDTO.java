package co.edu.uniandes.csw.tiendadiscos.dtos;

import co.edu.uniandes.csw.tiendadiscos.entities.ComentarioEntity;
import java.io.Serializable;

/**
 * ComentarioDTO Objeto de transferencia de datos de Comentarios. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa 4 modelos dependiendo del tipo de comentario, siguiendo esta estructura: <br>
 * <pre>
 * {
 *      "id" : number ,
 *      "escritoPor" : {@link UsuarioDTO} ,
 *      "contenido" : String,
 *      recurso a ser comentado : {@link UsuarioDTO}{@link ViniloDTO}{@link TransaccionDTO}{@link CancionDTO} 
 *  }
 * </pre> Por ejemplo un comentario a un usuario se representa así:
 * <pre>
 * {
                "contenido": "AndrÃ©s tu puedes.",
                "escritoPor": {
                    "calificacion": 0,
                    "contrasenha": "ContraseÃ±aDificilXD",
                    "direccion": "cll 6 #1A 99 este Madrid Cund.",
                    "email": "im@camilosalinas.me",
                    "id": 2,
                    "nombre": "Camilo Andres Salinas Martinez",
                    "rol": "ADMIN",
                    "username": "Rembrandtsx"
                },
                "id": 1,
                "usuario": {
                    "calificacion": 0,
                    "contrasenha": "ContraseÃ±aDificilXD",
                    "direccion": "cll 6 #1A 99 este Madrid Cund.",
                    "email": "iam@camilosalinas.me",
                    "id": 1,
                    "nombre": "Camilo Andres Salinas Martinez",
                    "rol": "ADMIN",
                    "username": "Rembrandtsx"
                }
            }
 * </pre> Un comentario a un vinilo se representa así:
 * <pre>
 *  {
 *      "contenido": "Lindo vinilo",
 *      "escritoPor": {
 *          "calificacion": 0,
 *          "contrasenha": "ContraseÃ±aDificilXD",
 *          "direccion": "cll 6 #1A 99 este Madrid Cund.",
 *          "email": "iam@camilosalinas.me",
 *          "id": 1,
 *          "nombre": "Camilo Andres Salinas Martinez",
 *          "rol": "ADMIN",
 *          "username": "Rembrandtsx"
 *      },
 *      "id": 6,
 *      "vinilo": {
 *          "artista": "Pink Floyd",    
 *          "calificacion": 5,
 *          "id": 1,
 *          "informacionAdicional": "Nunca ha sido abierto.",
 *          "nombre": "The wall",
 *          "precio": 50000,
 *          "previewURI": "URI",
 *          "productora": "Columbia Records"
 *      }
 *  }
 * </pre> Un comentario a una transacción se representa así:
 * <pre>
 *  {
 *      "contenido": "Lindo vinilo",
 *      "escritoPor": {
 *          "calificacion": 0,
 *          "contrasenha": "ContraseÃ±aDificilXD",
 *          "direccion": "cll 6 #1A 99 este Madrid Cund.",
 *          "email": "iam@camilosalinas.me",
 *          "id": 1,
 *          "nombre": "Camilo Andres Salinas Martinez",
 *          "rol": "ADMIN",
 *          "username": "Rembrandtsx"
 *      },
 *      "id": 7,
 *      "transaccion":
 *      {
 *          "estado" : "peticion",
 *          "formaDePago" : "tarjeta-credito",
 *          "usuarioComprador": 
 *              {
 *                  "type": "usuarioDetailDTO",
 *                  "billingInformation": 
 *                  {
 *                      "cuentaAhorro": "987654321",
 *                      "id":{{usuarioComprador_billing_id}},
 *                      "recieved": 0,
 *                      "spent": 0
 *                  },
 *                  "calificacion": 0,
 *                  "contrasenha": "ContraseÃ±aDificilXD",
 *                  "direccion": "cll 12 #25 105 este BogotÃ¡",
 *                  "email": "comprador@gmail.com",
 *                  "id": {{usuarioComprador_id}},
 *                  "nombre": "El Que Compra el vinilo",
 *                  "rol": "Usuario",
 *                  "username": "elComprador",
 *                  "comentarios": [],
 *                  "transaccionesGeneradas": [],
 *                  "transaccionesRecibidas": [],
 *                  "vinilos": []
 *              },
 *          "usuarioVendedor":
 *              {
 *                  "type": "usuarioDetailDTO",
 *                  "billingInformation": 
 *                  {
 *                      "cuentaAhorro": "654654654",
 *                      "id": {{usuarioVendedor_billing_id}},
 *                      "recieved": 0,
 *                      "spent": 0
 *                  },
 *                  "calificacion": 0,
 *                  "contrasenha": "ContraseÃ±aDificilXD",
 *                  "direccion": "cll 32 #85 5 este BogotÃ¡",
 *                  "email": "vendedor@gmail.com",
 *                  "id": {{usuarioVendedor_id}},
 *                  "nombre": "El Que Vende el vinilo",
 *                  "rol": "Usuario",
 *                  "username": "elVendedor",
 *                  "comentarios": [],
 *                  "transaccionesGeneradas": [],
 *                  "transaccionesRecibidas": [],
 *          "vinilos": [
 *              {
 *                  "fechaLanzamiento": "2002-11-15",
 *                  "artista": "El Rey Leon",
 *                  "calificacion": 4.9,
 *                  "id": {{viniloVender_id}},
 *                  "informacionAdicional": "Fue grabado en la selva",
 *                  "nombre": "Jazz in the Jungle",
 *                  "precio": 70000,
 *                  "previewURI": "URI",
 *                  "productora": "Sony Music",
 *                  "usuario": 
 *                  {
 *                      "billingInformation": 
 *                      {      
 *                          "cuentaAhorro": "654654654",
 *                          "id":  {{usuarioVendedor_billing_id}},
 *                          "recieved": 0,
 *                          "spent": 0
 *                      },
 *                      "calificacion": 0,
 *                      "contrasenha": "ContraseÃ±aDificilXD",
 *                      "direccion": "cll 32 #85 5 este BogotÃ¡",
 *                      "email": "vendedor@gmail.com",
 *                      "id": {{usuarioVendedor_id}},
 *                      "nombre": "El Que Vende el vinilo",
 *                      "rol": "Usuario",
 *                      "username": "elVendedor"
 *                  }
 *              } ]
 *          },
 *          "vinilo":
 *          {
 *              "type": "viniloDetailDTO",
 *              "fechaLanzamiento": "2002-11-15",
 *              "artista": "El Rey Leon",
 *              "calificacion": 4.9,
 *              "id": {{viniloVender_id}},
 *              "nombre": "Jazz in the Jungle",
 *              "precio": 70000,
 *              "previewURI": "URI",
 *              "productora": "Sony Music",
 *              "usuario": 
 *              {
 *                  "billingInformation": {
 *                      "cuentaAhorro": "654654654",
 *                      "id": {{usuarioVendedor_billing_id}},
 *                      "recieved": 0,
 *                      "spent": 0
 *                  },
 *                  "calificacion": 0,
 *                  "contrasenha": "ContraseÃ±aDificilXD",
 *                  "direccion": "cll 32 #85 5 este BogotÃ¡",
 *                  "email": "vendedor@gmail.com",
 *                  "id": {{usuarioVendedor_id}},
 *                  "nombre": "El Que Vende el vinilo",
 *                  "rol": "Usuario",
 *                  "username": "elVendedor"
 *              },
 *              "canciones": []
 *          }
 *      }
 *  }
 * </pre> Un comentario a una canción se representa así:
 *  {
 *      "cancion": 
 *      {
 *          "calificacion": 4.5,
 *          "descripcion": "Excelente canciÃ³n de George Michael.",
 *          "duracion": "5:05",
 *          "id": 3,
 *          "nombre": "Fast Love",
 *          "previewURI": "URI"
 *      },
 *      "contenido": "AndrÃ©s tu puedes.",
 *      "escritoPor": 
 *      {
 *          "calificacion": 0,
 *          "contrasenha": "ContraseÃ±aDificilXD",
 *          "direccion": "cll 6 #1A 99 este Madrid Cund.",
 *          "email": "iam@camilosalinas.me",
 *          "id": 1,
 *          "nombre": "Camilo Andres Salinas Martinez",
 *          "rol": "ADMIN",
 *          "username": "Rembrandtsx"
 *      },
 *      "id": 3
 *  }
 * </pre>
 * @author Sebastian Martinez y Andrés :)
 */
public class ComentarioDTO implements Serializable
{   
    /**
     * Id del comentario.
     */
    private Long id;

    /**
     * Conexión al usuario que escribe el comentario.
     */
    private UsuarioDTO escritoPor;

    /**
     * Contenido del comentario.
     */
    private String contenido;
    
    /**
     * Si es comentario a usuario, el usuario que recibe el comentario.
     */
    private UsuarioDTO usuario;
    
    /**
     * Si es comentario a vinilo, el vinilo que recibe el comentario.
     */
    private ViniloDTO vinilo;

    /**
     * Si es comentario a transacción, la transacción que recibe el comentario.
     */
    private TransaccionDTO transaccion;
    
    /**
     * Si es comentario a canción, la canción que recibe el comentario.
     */
    private CancionDTO cancion;    
    
    /**
     * Contructor por defecto
     */
    public ComentarioDTO(){ }
    
   /** 
    * Crea un objeto ComentarioDTO a partir de un objeto ComentarioEntity.
    * 
    * @param comentarioEntity Entidad ComentarioEntity desde la cual se va a crear el 
    * nuevo objeto.
    */
    public ComentarioDTO(ComentarioEntity comentarioEntity)
    {
        if(comentarioEntity != null)
        {
            this.id = comentarioEntity.getId();            
            this.contenido = comentarioEntity.getContenido();            
            if(comentarioEntity.getVinilo() != null)
                this.vinilo = new ViniloDTO(comentarioEntity.getVinilo());
            
            if(comentarioEntity.getCancion() != null)
                this.cancion = new CancionDTO(comentarioEntity.getCancion());
            
            if(comentarioEntity.getTransaccion() != null)
                this.transaccion = new TransaccionDTO(comentarioEntity.getTransaccion());            
            
            if(comentarioEntity.getUsuario() != null)
                this.usuario = new UsuarioDTO(comentarioEntity.getUsuario());            
            
            if(comentarioEntity.getUsuarioI() != null)
                this.escritoPor = new UsuarioDTO(comentarioEntity.getUsuarioI());          
        }
    }
    
    /**
     * Convertir un objeto ComentarioDTO a ComentarioEntity.
     * 
     * @return Nuevo objeto ComentarioEntity
     */
    public ComentarioEntity toEntity()
    {
        ComentarioEntity comentario = new ComentarioEntity();
        comentario.setId(id);
        comentario.setContenido(contenido);
        if(this.escritoPor!= null)
            comentario.setUsuarioI(escritoPor.toEntity());
        if(this.transaccion!=null)
            comentario.setTransaccion(this.transaccion.toEntity());
        if(this.usuario!=null)
            comentario.setUsuario(this.usuario.toEntity());
        if(this.cancion!=null)
            comentario.setCancion(this.cancion.toEntity());
        if(this.vinilo!=null)
            comentario.setVinilo(this.vinilo.toEntity());
        
        return comentario;
    }
    
    /**
     * Devuelve el id del comentario
     * 
     * @return id el comentario
     */
    public Long getId()
    {
        return id;
    }
    
    /**
     * Devuelve el usuario que escribio el comentario.
     * 
     * @return el usuario que escribió el comentario.
     */
    public UsuarioDTO getEscritoPor()
    {
        return escritoPor;
    }

    /**
     * Devuelve el id del comentario.
     * 
     * @return el comentario
     */
    public String getContenido()
    {
        return contenido;
    }

    /**
     * Devuelve el id de la transaccion.
     * 
     * @return conexion con la transaccion.
     */
    public TransaccionDTO getTransaccion()
    {
        return transaccion;
    }
    
    /**
     * Devuelve el id del usuario.
     * @return conexion con el usuario
     */
    public UsuarioDTO getUsuario()
    {
        return usuario;
    }
    
    /**
     * Devuelve el id del vinilo.
     * @return conexion con el vinilo
     */
    public ViniloDTO getVinilo()
    {
        return vinilo;
    } 
        
    /**
     * Devuelve el id de la canción.
     * @return conexion con la canción
     */
    public CancionDTO getCancion()
    {
        return cancion;
    }

    /**
     * Modifica el id del comentario
     * 
     * @param id nuevo id
     */
    public void setId(Long id)
    {
        this.id = id;
    }
    
    /**
     * Modifica 
     * @param escritoPor usuario que comento.
     */
    public void setEscritoPor(UsuarioDTO escritoPor)
    {
        this.escritoPor = escritoPor;
    }


    /**
     * Cambia el contenido del comentario
     * 
     * @param contenido nuevo comentario
     */
    public void setContenido(String contenido)
    {
        this.contenido = contenido;
    }

    /** 
     * @param usuario usuario al que se comenta
     */
    public void setUsuario(UsuarioDTO usuario)
    {
        this.usuario = usuario;
    }
    
    /** 
     * @param transaccion usuario al que se comenta
     */
    public void setTransaccion(TransaccionDTO transaccion)
    {
        this.transaccion = transaccion;
    }
    
    /** 
     * @param cancion usuario al que se comenta
     */
    public void setCancion(CancionDTO cancion)
    {
        this.cancion = cancion;
    }
    
    /** 
     * @param vinilo vinilo al que se comenta.
     */
    public void setVinilo(ViniloDTO vinilo)
    {
        this.vinilo = vinilo;
    }   
}