package hibernatecrud.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private int id;

    @Column(name = "book_name")
    private String name;

    @ManyToMany(mappedBy = "books" , fetch = FetchType.LAZY)

    Set<Author> authors = new HashSet<>();


}
