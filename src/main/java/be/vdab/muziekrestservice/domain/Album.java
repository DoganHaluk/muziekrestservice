package be.vdab.muziekrestservice.domain;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
@NamedEntityGraph(name = Album.MET_ARTIEST, attributeNodes = @NamedAttributeNode("artiest"))
public class Album {
    public static final String MET_ARTIEST = "Album.metArtiest";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artiestId")
    private Artiest artiest;
    private String naam;
    private int score;
    @ElementCollection
    @CollectionTable(name = "tracks", joinColumns = @JoinColumn(name = "albumId"))
    private Set<Track> tracks;

    public Album(Artiest artiest, String naam, int score) {
        this.artiest = artiest;
        this.naam = naam;
        this.score = score;
        this.tracks = new LinkedHashSet<>();
        ;
    }

    protected Album() {
    }

    public long getId() {
        return id;
    }

    public Artiest getArtiest() {
        return artiest;
    }

    public String getNaam() {
        return naam;
    }

    public int getScore() {
        return score;
    }
}