package com.lexlabor.entities;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USR_ID")
    private Long id;

    @Column(name = "USR_NOME", nullable = false)
    private String nome;

    @Column(name = "USR_EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "USR_SENHA", nullable = false)
    private String senha;

    @Column(name = "USR_ATIVO", nullable = false)
    private boolean ativo = true;

    @ManyToMany
    @JoinTable(
        name = "FAV_FAVORITOS",
        joinColumns = @JoinColumn(name = "FAV_USUARIO_ID"),
        inverseJoinColumns = @JoinColumn(name = "FAV_LEI_ID")
    )
    private List<LeiTrabalhista> leisFavoritas;
}
