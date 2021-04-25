package vu.lt.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Show.findAll", query = "select t from Show as t")
})
@Table(name = "SHOW")
@Getter @Setter
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "show", fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    @ManyToMany(mappedBy="shows")
    @Getter @Setter
    private List<Genre> genres = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Show show = (Show) o;
        return Objects.equals(name, show.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
