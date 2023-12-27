package com.santosjhony.demo.park.api.web.dto.mapper;

import com.santosjhony.demo.park.api.entity.Usuario;
import com.santosjhony.demo.park.api.web.dto.UsuarioCreateDto;
import com.santosjhony.demo.park.api.web.dto.UsuarioResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class UsuarioMapper {
    public static Usuario toUsuario(UsuarioCreateDto usuarioCreateDto){
        return new ModelMapper().map(usuarioCreateDto, Usuario.class);
    }
    public static UsuarioResponseDto toDto(Usuario usuario){
        String role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario, UsuarioResponseDto> props = new PropertyMap<Usuario, UsuarioResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(usuario, UsuarioResponseDto.class);
    }
}
