package zipfsong;

/**
 * Created with IntelliJ IDEA.
 * User: myoffe
 * Date: 26/05/2013
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
public class Song {

    private final String name;

    public long getPlayCount() {
        return playCount;
    }

    public String getName() {
        return name;
    }

    private final long playCount;
    private final int number;

    public int getNumber() {
        return number;
    }

    public Song(int number, long playCount, String name) {
        this.number = number;
        this.playCount = playCount;
        this.name = name;
    }


}
