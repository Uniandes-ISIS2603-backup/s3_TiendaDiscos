/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.entities;

import java.io.Serializable;

/**
 *
 * @author Andrés Hernández
 */
public class CancionEntity extends BaseEntity implements Serializable
{
    /**
     * Nombre de la canción.
     */
    private String nombre;
    
    /**
     * duración de la canción.
     */
    private String duracion;
    
    /**
     * URI del preview de la canción.
     */
    private String previewURI;
    
    /**
     * Descripción del proveedor.
     */
    private String descripcion;
    
    /**
     * Modifica el valor del atributo nombre.
     * @param nombre nuevo valor del atributo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Modifica el valor de la duración.
     * @param duracion nuevo valor de la duración.
     */
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    /**
     * Modifica el valor de la URI,
     * @param previewURI nuevo valor de la URI.
     */
    public void setPreviewURI(String previewURI) {
        this.previewURI = previewURI;
    }
    
    /**
     * Modifica el atributo descripción.
     * @param descripcion nuevo valor de la descripción del proveedor.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene le atributo nombre.
     * @return atributo nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna la duración de la cación.
     * @return atributo duración.
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * Obtiene el atributo preview URI.
     * @return previewURI
     */
    public String getPreviewURI() {
        return previewURI;
    }

    /**
     * Obtiene la descripcion.
     * @return atributo descripcion.
     */
    public String getDescripcion() {
        return descripcion;
    }
}
