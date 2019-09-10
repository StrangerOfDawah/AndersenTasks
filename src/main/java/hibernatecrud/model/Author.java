package hibernatecrud.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAuthor")
    private int id;

    @Column(name = "author_name")
    private String name;

    @ManyToMany(cascade = {
            CascadeType.ALL
    }, fetch = FetchType.LAZY)

    @JoinTable(name = "author_book",
            joinColumns = @JoinColumn(name = "id_author"),
            inverseJoinColumns = @JoinColumn(name = "id_book")
    )

    private Set<Book> books = new HashSet<>();


}
