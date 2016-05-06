package app.wedding.service;

import app.wedding.domain.Album;
import app.wedding.repository.AlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Album.
 */
@Service
@Transactional
public class AlbumService {

    private final Logger log = LoggerFactory.getLogger(AlbumService.class);
    
    @Inject
    private AlbumRepository albumRepository;
    
    /**
     * Save a album.
     * 
     * @param album the entity to save
     * @return the persisted entity
     */
    public Album save(Album album) {
        log.debug("Request to save Album : {}", album);
        Album result = albumRepository.save(album);
        return result;
    }

    /**
     *  Get all the albums.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Album> findAll() {
        log.debug("Request to get all Albums");
        List<Album> result = albumRepository.findAll();
        return result;
    }

    /**
     *  Get one album by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Album findOne(Long id) {
        log.debug("Request to get Album : {}", id);
        Album album = albumRepository.findOne(id);
        return album;
    }

    /**
     *  Delete the  album by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Album : {}", id);
        albumRepository.delete(id);
    }
}
