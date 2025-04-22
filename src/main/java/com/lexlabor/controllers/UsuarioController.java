package com.lexlabor.controllers;

import com.lexlabor.entities.Usuario;
import com.lexlabor.exceptions.BusinessException;
import com.lexlabor.exceptions.EmailAlreadyExistsException;
import com.lexlabor.exceptions.UsuarioNotFoundException;
import com.lexlabor.services.UsuarioService;
import com.lexlabor.utils.ValidationUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }

    public void cadastrarUsuario(String nome, String email, String senha) throws BusinessException {
        try {
            if (nome == null || nome.trim().isEmpty()) {
                throw new BusinessException("Nome é obrigatório");
            }

            if (email == null || email.trim().isEmpty()) {
                throw new BusinessException("Email é obrigatório");
            }

            if (senha == null || senha.trim().isEmpty()) {
                throw new BusinessException("Senha é obrigatória");
            }

            if (!ValidationUtil.isValidEmail(email)) {
                throw new BusinessException("Formato de email inválido");
            }

            if (!ValidationUtil.isValidPassword(senha)) {
                throw new BusinessException("A senha deve conter pelo menos 8 caracteres");
            }

            Usuario usuario = new Usuario();
            usuario.setNome(nome.trim());
            usuario.setEmail(email.trim().toLowerCase());
            usuario.setSenha(BCrypt.hashpw(senha, BCrypt.gensalt()));

            usuarioService.criarUsuario(usuario);

        } catch (EmailAlreadyExistsException e) {
            throw new BusinessException("O email já está cadastrado");
        } catch (Exception e) {
            throw new BusinessException("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    public List<Usuario> listarUsuarios() {
        return usuarioService.listarTodosUsuarios();
    }

    public Usuario buscarUsuarioPorEmail(String email) throws BusinessException {
        if (email == null || email.trim().isEmpty()) {
            throw new BusinessException("O email não pode ser vazio");
        }

        try {
            return usuarioService.buscarUsuarioPorEmail(email.trim().toLowerCase());
        } catch (UsuarioNotFoundException e) {
            throw new BusinessException("Usuário não encontrado");
        } catch (Exception e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
            throw new BusinessException("Ocorreu um erro ao buscar o usuário");
        }
    }

    public void atualizarUsuario(Long id, Usuario usuarioParaAtualizar) throws BusinessException {
        try {
            if (id == null || id <= 0) {
                throw new BusinessException("ID do usuário inválido");
            }

            String nome = usuarioParaAtualizar.getNome().trim();
            String email = usuarioParaAtualizar.getEmail().trim().toLowerCase();
            String senha = usuarioParaAtualizar.getSenha();

            if (nome.isEmpty()) {
                throw new BusinessException("Nome é obrigatório");
            }

            if (email.isEmpty()) {
                throw new BusinessException("Email é obrigatório");
            }

            if (!ValidationUtil.isValidEmail(email)) {
                throw new BusinessException("Formato de email inválido");
            }

            if (senha != null && !senha.isEmpty() && !ValidationUtil.isValidPassword(senha)) {
                throw new BusinessException("A senha deve conter pelo menos 8 caracteres");
            }

            Usuario usuarioAtualizado = new Usuario();
            usuarioAtualizado.setNome(nome);
            usuarioAtualizado.setEmail(email);

            if (senha != null && !senha.isEmpty()) {
                usuarioAtualizado.setSenha(BCrypt.hashpw(senha, BCrypt.gensalt()));
            }

            usuarioService.atualizarUsuario(id, usuarioAtualizado);

        } catch (UsuarioNotFoundException e) {
            throw new BusinessException("Usuário não encontrado");
        } catch (EmailAlreadyExistsException e) {
            throw new BusinessException("O email já está em uso");
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            throw new BusinessException("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public void removerUsuario(Long id) throws BusinessException {
        try {
            usuarioService.deletarUsuario(id);
        } catch (UsuarioNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}