package com.lexlabor.entities;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "LEITRABALHISTA")
public class LeiTrabalhista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LEI_ID")
    private Long id;

    @Column(name = "LEI_TITULO", nullable = false)
    private String titulo;

    @Column(name = "LEI_TEXTO", nullable = false, columnDefinition = "TEXT")
    private String texto;

    @ManyToMany(mappedBy = "leisFavoritas")
    private List<Usuario> usuariosFavoritos;

}
