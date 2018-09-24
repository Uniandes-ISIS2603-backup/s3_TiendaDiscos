/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.tiendadiscos.ejb;

import co.edu.uniandes.csw.tiendadiscos.entities.ComentarioEntity;
import co.edu.uniandes.csw.tiendadiscos.entities.UsuarioEntity;
import co.edu.uniandes.csw.tiendadiscos.exceptions.BusinessLogicException;
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
    
    
    public ComentarioEntity createComentario(Long usuarioId,ComentarioEntity entity) throws BusinessLogicException
    {
        if(usuarioPersistence.find(usuarioId)==null)
            throw new BusinessLogicException("El usuario no existe"); ;
        UsuarioEntity usuario = usuarioPersistence.find(usuarioId);
        entity.setUsuarioI(usuario);
        return persitence.create(entity);
    }
    
    public List<ComentarioEntity> getComentarios(Long usuarioId){
        return persitence.findAllHechos(usuarioId);
    }
    
    public ComentarioEntity getComentario(Long comentarioId, Long usuarioId)
    {
        return persitence.find(comentarioId, usuarioId);
    }
    
    /**
     * Actualiza la información de una instancia de Review.
     *
     * @param reviewEntity Instancia de ReviewEntity con los nuevos datos.
     * @param booksId id del Book el cual sera padre del Review actualizado.
     * @return Instancia de ReviewEntity con los datos actualizados.
     *
     */
    public ComentarioEntity updateComentario(Long usuarioId, ComentarioEntity entity) {
        UsuarioEntity usuario = usuarioPersistence.find(usuarioId);
        entity.setUsuarioI(usuario);
        persitence.update(entity);
        return entity;
    }
    
    /**
     * Elimina una instancia de Review de la base de datos.
     *
     * @param reviewsId Identificador de la instancia a eliminar.
     * @param booksId id del Book el cual es padre del Review.
     * @throws BusinessLogicException Si la reseña no esta asociada al libro.
     *
     */
    public void deleteComentario(Long usuarioId, Long comentarioId) throws BusinessLogicException {
        ComentarioEntity old = getComentario(comentarioId, usuarioId);
        if (old == null) {
            throw new BusinessLogicException("El comentario con id = " + comentarioId + " no esta asociado a el usuario con id = " + usuarioId);
        }
        persitence.delete(old.getId());
    }
}
