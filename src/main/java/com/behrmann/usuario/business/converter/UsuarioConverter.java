package com.behrmann.usuario.business.converter;

import com.behrmann.usuario.business.dto.EnderecoDTO;
import com.behrmann.usuario.business.dto.TelefoneDTO;
import com.behrmann.usuario.business.dto.UsuarioDTO;
import com.behrmann.usuario.infraestructure.entity.Endereco;
import com.behrmann.usuario.infraestructure.entity.Telefone;
import com.behrmann.usuario.infraestructure.entity.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO usuarioDTO){
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEnderecos(usuarioDTO.getEnderecos()))
                .telefones(paraListaTelefones(usuarioDTO.getTelefones()))
                .build();
    }

    public UsuarioDTO paraUsuarioDTO(Usuario usuario){
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(paraListaEnderecosDTO(usuario.getEnderecos()))
                .telefones(paraListaTelefonesDTO(usuario.getTelefones()))
                .build();
    }

    public List<Endereco> paraListaEnderecos(List<EnderecoDTO> enderecoDTOS){
        return enderecoDTOS.stream().map(this::paraEndereco).toList();

//        List<Endereco> enderecos = new ArrayList<>();
//        for (EnderecoDTO enderecoDTO : enderecoDTOS){
//            enderecos.add(paraEndereco(enderecoDTO));
//        }
//        return enderecos;

    }

    public List<EnderecoDTO> paraListaEnderecosDTO(List<Endereco> endereco){
        return endereco.stream().map(this::paraEnderecoDTO).toList();
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .build();
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco){
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .cep(endereco.getCep())
                .build();
    }

    public List<Telefone> paraListaTelefones(List<TelefoneDTO> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }

    public List<TelefoneDTO> paraListaTelefonesDTO(List<Telefone> telefones){
        return telefones.stream().map(this::paraTelefoneDTO).toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone){
        return TelefoneDTO.builder()
                .id(telefone.getId())
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }

    // compara usuario
    public Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario usuarioEntity){
        return Usuario.builder()
                .id(usuarioEntity.getId())
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : usuarioEntity.getNome())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : usuarioEntity.getEmail())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : usuarioEntity.getSenha())
                .enderecos(usuarioEntity.getEnderecos())
                .telefones(usuarioEntity.getTelefones())
                .build();
    }
    
    public Endereco updateEndereco(EnderecoDTO enderecoDTO, Endereco enderecoEntity){
        return Endereco.builder()
                .id(enderecoEntity.getId())
                .rua(enderecoDTO.getRua() != null ? enderecoDTO.getRua() : enderecoEntity.getRua())
                .numero(enderecoDTO.getNumero() != null ? enderecoDTO.getNumero() : enderecoEntity.getNumero())
                .complemento(enderecoDTO.getComplemento() != null ? enderecoDTO.getComplemento() : enderecoEntity.getComplemento())
                .cidade(enderecoDTO.getCidade() != null ? enderecoDTO.getCidade() : enderecoEntity.getCidade())
                .estado(enderecoDTO.getEstado() != null ? enderecoDTO.getEstado() : enderecoEntity.getEstado())
                .cep(enderecoDTO.getCep() != null ? enderecoDTO.getCep() : enderecoEntity.getCep())
                .build();
    }

    public Telefone updateTelefone(TelefoneDTO telefoneDTO, Telefone telefoneEntity){
        return Telefone.builder()
                .id(telefoneEntity.getId())
                .numero(telefoneDTO.getNumero() != null ? telefoneDTO.getNumero() : telefoneEntity.getNumero())
                .ddd(telefoneDTO.getDdd() != null ? telefoneDTO.getDdd() : telefoneEntity.getDdd())
                .build();
    }

    public Endereco paraEnderecoEntity(EnderecoDTO enderecoDTO, Long idUsuario){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .usuario_id(idUsuario)
                .build();
    }

    public Telefone paraTelefoneEntity(TelefoneDTO telefoneDTO, Long idUsuario){
        return Telefone.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .usuario_id(idUsuario)
                .build();
    }
    
}
