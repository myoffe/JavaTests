package zipfsong;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: myoffe
 * Date: 26/05/2013
 * Time: 19:25
 * To change this template use File | Settings | File Templates.
 */
public class SongQualityComparator implements Comparator<Song> {

    private final Album album;

    public SongQualityComparator(Album album) {
        this.album = album;
    }

    @Override
    public int compare(Song a, Song b) {
        final float aQuality = album.getPredictedQuality(a.getNumber());
        final float bQuality = album.getPredictedQuality(b.getNumber());

        return (int) (aQuality - bQuality);
    }
}
