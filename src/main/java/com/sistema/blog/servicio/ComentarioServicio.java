package com.sistema.blog.servicio;

import com.sistema.blog.dto.ComentarioDTO;

import java.util.List;

public interface ComentarioServicio {

    public ComentarioDTO crearComentario(long publicacionId, ComentarioDTO comentarioDTO);
    public List<ComentarioDTO> obtenerComentariosPorPublicacionId(long publicacionId);
    public ComentarioDTO obtenerComentariosPorId(long publicacionId, long comentarioId);
    public ComentarioDTO actualizarComentario(Long publicacionId, Long comentarioId, ComentarioDTO solicitudDeComentario);
    public void eliminarComentario(Long publicacionId, Long comentarioId);

}
