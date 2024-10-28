package com.techforb.challenge.mappers;

import com.techforb.challenge.dtos.UsuarioDto;
import com.techforb.challenge.models.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUsuarioMapper {
    public IUsuarioMapper INSTANCE = Mappers.getMapper(IUsuarioMapper.class);
    public UsuarioDto usuarioToUsuarioDto(Usuario usuario);
    public Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);
    public List<UsuarioDto> usuariosToUsuariosDto(List<Usuario> usuarios);
    public List<Usuario> usuariosDtoToUsuarios(List<UsuarioDto> usuariosDto);
}
