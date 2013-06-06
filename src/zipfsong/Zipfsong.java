package zipfsong;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: myoffe
 * Date: 26/05/2013
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */
public class Zipfsong {

    private static Logger log = Logger.getLogger(Zipfsong.class);

    public static void main(String[] args) {

        Zipfsong zfp = new Zipfsong();

        Album album;
        album = new Album();
        album.add(197812, "re_hash");
        album.add(78906, "5_4");
        album.add(189518, "tomorrow_comes_today");
        album.add(39453, "new_genius");
        album.add(210492, "clint_eastwood");
        album.add(26302, "man_research");
        album.add(22544, "punk");
        album.add(19727, "sound_check");
        album.add(17535, "double_bass");
        album.add(18782, "rock_the_house");
        album.add(198189, "19_2000");
        album.add(13151, "latin_simone");
        album.add(12139, "starshine");
        album.add(11272, "slow_country");
        album.add(10521, "m1_a1");
        zfp.showBestSongs(album, 3);

        Album anotherAlbum = new Album();
        anotherAlbum.add(30, "one");
        anotherAlbum.add(30, "two");
        anotherAlbum.add(15, "three");
        anotherAlbum.add(25, "four");
        zfp.showBestSongs(anotherAlbum, 2);
    }

    private void showBestSongs(Album album, int numSongsToShow) {
        Iterator<Song> iter = album.songsQualityIterator();

        while (iter.hasNext() && numSongsToShow > 0) {
            Song song = iter.next();
            System.out.format("%d %s\n", song.getNumber(), song.getName());

            --numSongsToShow;
        }
    }
}
