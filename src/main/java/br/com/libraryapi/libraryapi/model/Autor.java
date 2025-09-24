package br.com.libraryapi.libraryapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "autor", schema = "public") //aqui no schema caso quiser separar (como se fosse gavetas guardando as pastas), eu setaria o nome diferente
@Getter
@Setter
public class Autor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false) //length = tamanho da coluna (usado para varchar)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;

    //mappedBy informa que essa entidade nao tem uma coluna, que se trata apenas de um mapeamento de onetomany
    @OneToMany (mappedBy = "autor")//um para muitos (referencia ao nosso autor de livros)
    private List<Livro> livros;

}
