/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;

import co.edu.uniandes.csw.tiendadiscos.entities.ComentarioEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.persistence.ComentarioPersistence;
import co.edu.uniandes.csw.tiendadiscos.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Sebastian Martinez
 */
@Stateless
public class ComentarioLogic{
    
    @Inject
    private ComentarioPersistence persitence;
    
    @Inject
    private UsuarioPersistence usuarioPersistence;
    
    
    public ComentarioEntity createComentario(Long usuarioId,ComentarioEntity entity)
    {
        UsuarioEntity usuario = usuarioPersistence.find(usuarioId);
        entity.setUsuarioI(usuario);
        return persitence.create(entity);
    }
    
    public List<ComentarioEntity> getComentariosHechos(Long usuarioId){
        return persitence.findAllHechos(usuarioId);
    }
    
    public List<ComentarioEntity> getComentariosRecibidos(Long usuarioId){
        return persitence.findAllRecibidos(usuarioId);
    }
    
    public ComentarioEntity getComentario(Long comentarioId)
    {
        return persitence.find(comentarioId);
    }
}
