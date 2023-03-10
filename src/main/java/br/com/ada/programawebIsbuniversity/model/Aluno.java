package br.com.ada.programawebIsbuniversity.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "tb_aluno")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String documento;
    private String matricula;
    private String curso;

}