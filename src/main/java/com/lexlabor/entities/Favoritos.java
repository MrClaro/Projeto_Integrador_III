package com.lexlabor.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "FAVORITOS")
public class Favoritos {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "FAV_ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "FAV_USUARIO_ID")
  private Usuario usuario;

  @ManyToOne
  @JoinColumn(name = "FAV_LEI_ID")
  private LeiTrabalhista lei;

}
