package noseryoung.ch.vinyl_store.Vinyl;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Paths;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "vinyl")
public class VinylEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vinyl_ID")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "size")
    private int size;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_artist", referencedColumnName = "artist_ID")
    private VinylArtistEntity artist;

    @Column(name = "release_year")
    @Temporal(TemporalType.DATE)
    private Date releaseYear;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status", referencedColumnName = "status_ID")
    private VinylStatusEntity status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_genre", referencedColumnName = "genre_ID")
    private VinylGenreEntity genre;

    @Column(name = "price")
    private Double price;

    @Column(name = "image_file_path")
    private String imageFilePath;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public VinylArtistEntity getArtist() {
        return artist;
    }

    public void setArtist(VinylArtistEntity artist) {
        this.artist = artist;
    }


    public VinylStatusEntity getStatus() {
        return status;
    }

    public void setStatus(VinylStatusEntity status) {
        this.status = status;
    }

    public VinylGenreEntity getGenre() {
        return genre;
    }

    public void setGenre(VinylGenreEntity genre) {
        this.genre = genre;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String getImageFilePath() {
        return "/images/" + Paths.get(imageFilePath).getFileName().toString(); // Ensures the correct path is returned
    }
    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public Date getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Date releaseYear) {
        this.releaseYear = releaseYear;
    }
}
