package com.behrmann.usuario.business;

import com.behrmann.usuario.business.converter.UsuarioConverter;
import com.behrmann.usuario.business.dto.UsuarioDTO;
import com.behrmann.usuario.infraestructure.entity.Usuario;
import com.behrmann.usuario.infraestructure.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }
}
