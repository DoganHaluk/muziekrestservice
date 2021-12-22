package be.vdab.muziekrestservice.services;

import be.vdab.muziekrestservice.domain.Album;

import java.util.Optional;

public interface AlbumService {
    Optional<Album> findById(long id);
}
