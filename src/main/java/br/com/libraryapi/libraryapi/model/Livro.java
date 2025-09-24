package br.com.libraryapi.libraryapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "livro")
@Data //cria getters setters toString hashcode e equals
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn" , length = 20, nullable = false)
    private String isbn;

    @Column(name = "titulo" , length = 150, nullable = false)
    private String titulo;

    @Column(name = "data_publicacao")
    private String dataPublicacao;

    @Enumerated(EnumType.STRING) //aponta para jpa que nossa enum armazena valores do tipo String (ORDINAL guarda posicao da Enum 0,1,2,3...)
    @Column(name = "genero" , length = 30, nullable = false)
    private GeneroLivro genero;

    @Column(name = "preco" , precision = 18, scale = 2)
    private Double preco;

    //relacionamento de chave estrangeira
    @ManyToOne //muitos livros para um autor (muitos para um)
    @JoinColumn(name = "id_autor")
    private UUID idAutor;

}
