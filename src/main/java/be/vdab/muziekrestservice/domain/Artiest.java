package be.vdab.muziekrestservice.domain;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "artiesten")
public class Artiest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    @OneToMany(mappedBy = "artiest")
    private List<Album> albums;

    public Artiest(String naam) {
        this.naam = naam;
        this.albums = new LinkedList<>();
    }

    protected Artiest() {
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public List<Album> getAlbums() {
        return albums;
    }
}
