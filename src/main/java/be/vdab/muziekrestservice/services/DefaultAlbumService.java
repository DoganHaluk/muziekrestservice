package be.vdab.muziekrestservice.services;

import be.vdab.muziekrestservice.domain.Album;
import be.vdab.muziekrestservice.repositories.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DefaultAlbumService implements AlbumService {
    private final AlbumRepository albumRepository;

    public DefaultAlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Album> findById(long id) {
        return albumRepository.findById(id);
    }
}
