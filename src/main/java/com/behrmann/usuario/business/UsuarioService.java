package com.behrmann.usuario.business;

import com.behrmann.usuario.business.converter.UsuarioConverter;
import com.behrmann.usuario.business.dto.UsuarioDTO;
import com.behrmann.usuario.infraestructure.entity.Usuario;
import com.behrmann.usuario.infraestructure.exceptions.ConflictException;
import com.behrmann.usuario.infraestructure.exceptions.ResourceNotFoundException;
import com.behrmann.usuario.infraestructure.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        try {
            Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
            emailExiste(usuario.getEmail());
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
        } catch (ConflictException e) {
           throw  new ConflictException("Email já cadastrado ", e.getCause());
        }
    }

    public void emailExiste(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado: " + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado ", e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscaUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado " + email));
    }

    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

}
