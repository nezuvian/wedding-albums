package app.wedding.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "picture")
public class Picture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "path")
    private String path;

    @Column(name = "thumbnail")
    private String thumbnail;

    @ManyToOne
    private Album album;

    public Picture(String path, String thumbnail) {
        this.path = path;
        this.thumbnail = thumbnail;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        if (id != null ? !id.equals(picture.id) : picture.id != null) return false;
        if (path != null ? !path.equals(picture.path) : picture.path != null) return false;
        if (thumbnail != null ? !thumbnail.equals(picture.thumbnail) : picture.thumbnail != null) return false;
        return album != null ? album.equals(picture.album) : picture.album == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (thumbnail != null ? thumbnail.hashCode() : 0);
        result = 31 * result + (album != null ? album.hashCode() : 0);
        return result;
    }
}
