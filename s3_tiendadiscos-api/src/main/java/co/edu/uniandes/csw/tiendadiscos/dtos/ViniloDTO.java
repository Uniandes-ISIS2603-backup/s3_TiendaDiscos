/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author af.hernandezl
 */
public class ViniloDTO implements Serializable
{
    /**
     * Nombre del vinilo.
     */
    private String nombre;
    
    /**
     * Nombre del artista del vinilo.
     */
    private String artista;
    
    /**
     * Fecha de lanzamiento del vinilo.
     */
    private Date fechaLanzamiento;
    
    /**
     * Productora que lanzó el vinilo.
     */
    private String productora;
    
    /**
     * Información adicional del 
     */
    private String informacionAdicional;
    
    /**
     * 
     */
    private String previewURI;
    
    
}