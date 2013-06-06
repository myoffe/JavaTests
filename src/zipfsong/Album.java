package zipfsong;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: myoffe
 * Date: 26/05/2013
 * Time: 16:35
 * To change this template use File | Settings | File Templates.
 */
public class Album {

    private Map<Integer, Song> songs = new HashMap<Integer, Song>();
    private NavigableSet<Song> songsByQuality;


    public Album() {
        songsByQuality = new TreeSet<Song>(new SongQualityComparator(this));
    }

    public void add(long playCount, String songName) {
        Song song = new Song(songs.size()+1, playCount, songName);
        songs.put(songs.size()+1, song);
        songsByQuality.add(song);
    }

    public float getPredictedQuality(int songNum) {
        final long playCount = songs.get(songNum).getPlayCount();
        final long firstSongPlayCount = songs.get(Integer.valueOf(1)).getPlayCount();
        final long predictedPlayCount = firstSongPlayCount / songNum;

        return playCount / predictedPlayCount;
    }

    public Iterator<Song> songsQualityIterator() {
        return songsByQuality.descendingIterator();
    }
}
