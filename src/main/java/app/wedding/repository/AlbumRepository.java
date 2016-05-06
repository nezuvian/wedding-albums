package app.wedding.repository;

import app.wedding.domain.Album;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Album entity.
 */
public interface AlbumRepository extends JpaRepository<Album,Long> {

}
