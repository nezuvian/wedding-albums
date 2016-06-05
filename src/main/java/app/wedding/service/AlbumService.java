package app.wedding.service;

import app.wedding.domain.Album;
import app.wedding.domain.Picture;
import app.wedding.repository.AlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.*;
import java.util.ArrayList;
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

    @Value("${app.gallery-dir}")
    private String albumDirectory = null;

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
    public List<Album> findAll() throws IOException {
        log.debug("Request to get all Albums");
        List<Album> result = new ArrayList<>();
        File path = new File(albumDirectory);
        for (File file : path.listFiles()) {
            result.add(findOne(file.getName()));
        }
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

    public Album findOne(String id) throws IOException {
        log.debug("Request to get Album : {}", id);
        File path = new File(albumDirectory + File.separator + id);
        String title = new BufferedReader(
            new InputStreamReader(
                new BufferedInputStream(
                    new FileInputStream(
                        path + File.separator + "title.txt"
                    )
                )
            )
        ).readLine();
        Album album = new Album();
        album.setName(title);
        album.setSlug(id);

        for (File file : path.listFiles()) {
            String name = file.getName();
            if (!name.endsWith(".txt")) {
                String uriPath = "/image/" + id + "/" + name;
                album.addPicture(new Picture(uriPath, uriPath));
            }
        }

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
