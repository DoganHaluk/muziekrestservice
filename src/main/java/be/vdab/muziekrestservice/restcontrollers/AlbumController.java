package be.vdab.muziekrestservice.restcontrollers;

import be.vdab.muziekrestservice.domain.Album;
import be.vdab.muziekrestservice.domain.Track;
import be.vdab.muziekrestservice.exceptions.AlbumNietGevondenException;
import be.vdab.muziekrestservice.services.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.TypedEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
@ExposesResourceFor(Album.class)
@CrossOrigin
class AlbumController {
    private final AlbumService albumService;
    private final TypedEntityLinks.ExtendedTypedEntityLinks<Album> links;

    AlbumController(AlbumService albumService, EntityLinks links) {
        this.albumService = albumService;
        this.links = links.forType(Album.class, Album::getId);
    }

    @GetMapping("{id}")
    @Operation(summary = "Een album zoeken op id")
    EntityModel<AlbumArtiest> getAlbum(@PathVariable long id) {
        return albumService.findById(id)
                .map(album -> EntityModel.of(new AlbumArtiest(album)).add(links.linkToItemResource(album)).add(links.linkForItemResource(album).slash("tracks").withRel("tracks")))
                .orElseThrow(AlbumNietGevondenException::new);
    }

    @GetMapping("{id}/tracks")
    @Operation(summary = "Tracks zoeken op id van een album")
    CollectionModel<Track> getTracks(@PathVariable long id) {
        return albumService.findById(id)
                .map(album -> CollectionModel.of(album.getTracks()).add(links.linkForItemResource(album).slash("tracks").withRel("self")).add(links.linkToItemResource(album).withRel("album")))
                .orElseThrow(AlbumNietGevondenException::new);
    }

    @ExceptionHandler(AlbumNietGevondenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void albumNietGevonden() {
    }

    private static class AlbumArtiest {
        private final String album;
        private final String artiest;

        private AlbumArtiest(Album album) {
            this.album = album.getNaam();
            this.artiest = album.getArtiest().getNaam();
        }

        public String getAlbum() {
            return album;
        }

        public String getArtiest() {
            return artiest;
        }
    }
}
