package com.lexlabor.services;

import com.lexlabor.daos.UsuarioDAO;
import com.lexlabor.entities.Usuario;
import com.lexlabor.exceptions.EmailAlreadyExistsException;
import com.lexlabor.exceptions.UsuarioNotFoundException;
import com.lexlabor.exceptions.ValidaçãoSenhaException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }



    public Usuario buscarUsuarioPorId(Long id) throws UsuarioNotFoundException {
        Usuario usuario = usuarioDAO.getById(id);
        if (usuario == null) {
            throw new UsuarioNotFoundException("Usuário não encontrado");
        }
        return usuario;
    }

    public Usuario buscarUsuarioPorEmail(String email) throws UsuarioNotFoundException {
        Usuario usuario = usuarioDAO.getByEmail(email);
        if (usuario == null) {
            throw new UsuarioNotFoundException("Usuário não encontrado");
        }
        return usuario;
    }

    public void criarUsuario(Usuario usuario) throws EmailAlreadyExistsException, ValidaçãoSenhaException {
        if (usuarioDAO.getByEmail(usuario.getEmail()) != null) {
            throw new EmailAlreadyExistsException("Email já cadastrado: " + usuario.getEmail());
        }

        if (usuario.getSenha() == null || usuario.getSenha().length() < 8) {
            throw new ValidaçãoSenhaException("A senha deve ter pelo menos 8 caracteres");
        }

        usuario.setSenha(BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt()));
        usuarioDAO.save(usuario);
    }

    public void atualizarUsuario(Long id, Usuario usuarioAtualizado)
            throws UsuarioNotFoundException, EmailAlreadyExistsException {

        Usuario usuarioExistente = usuarioDAO.getById(id);
        if (usuarioExistente == null) {
            throw new UsuarioNotFoundException("Usuário não encontrado");
        }

        if (!usuarioExistente.getEmail().equals(usuarioAtualizado.getEmail())) {
            if (usuarioDAO.getByEmail(usuarioAtualizado.getEmail()) != null) {
                throw new EmailAlreadyExistsException("Novo email já está em uso");
            }
        }

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());

        usuarioDAO.update(usuarioExistente);
    }

    public void deletarUsuario(Long id) throws UsuarioNotFoundException {
        Usuario usuario = usuarioDAO.getById(id);
        if (usuario == null) {
            throw new UsuarioNotFoundException("Usuário não encontrado");
        }
        usuarioDAO.delete(id);
    }

    public List<Usuario> listarTodosUsuarios() {
        return usuarioDAO.getAll();
    }
}