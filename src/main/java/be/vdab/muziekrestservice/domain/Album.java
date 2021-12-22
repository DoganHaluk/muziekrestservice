package be.vdab.muziekrestservice.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
@NamedEntityGraph(name = Album.MET_ARTIEST, attributeNodes = @NamedAttributeNode("artiest"))
public class Album {
    public static final String MET_ARTIEST = "Album.metArtiest";
    @Id
    private long id;
    private String naam;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artiestId")
    private Artiest artiest;
    @ElementCollection
    @CollectionTable(name = "tracks", joinColumns = @JoinColumn(name = "albumId"))
    private Set<Track> tracks = new LinkedHashSet<>();

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Artiest getArtiest() {
        return artiest;
    }

    public Set<Track> getTracks() {
        return Collections.unmodifiableSet(tracks);
    }
}