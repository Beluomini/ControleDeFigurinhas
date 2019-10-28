
package Main;

/**
 *
 * @author radames
 */
public class Figurinha {
    private int figurinha;
    private String album;

    public Figurinha() {
    }

    public Figurinha(int figurinha, String album) {
        this.figurinha = figurinha;
        this.album = album;
    }

    @Override
    public String toString() {
        return "Figurinha{" + "figurinha=" + figurinha + ", album=" + album + '}';
    }

    public int getFigurinha() {
        return figurinha;
    }

    public void setFigurinha(int figurinha) {
        this.figurinha = figurinha;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    
}
