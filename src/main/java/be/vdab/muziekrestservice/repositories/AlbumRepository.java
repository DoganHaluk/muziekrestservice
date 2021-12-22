package be.vdab.muziekrestservice.repositories;

import be.vdab.muziekrestservice.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
