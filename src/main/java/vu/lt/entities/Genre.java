package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Genre.findAll", query = "select a from Genre as a"),
        @NamedQuery(name = "Genre.findAllWithShows", query = "select distinct genre from Genre genre left join fetch genre.shows"),
})
@Table(name = "GENRE")
@Getter
@Setter
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    public Genre() {
    }

    @ManyToMany
    @JoinTable(name= "show_genre",
            joinColumns = @JoinColumn(name = "GENRE_ID"),
            inverseJoinColumns = @JoinColumn(name = "SHOW_ID"))
    @Getter @Setter
    private List<Show> shows = new ArrayList<>();

    public void addShow(Show show){
        shows.add(show);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id) &&
                Objects.equals(name, genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
