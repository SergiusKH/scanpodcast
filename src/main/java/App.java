import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by sergius on 10.08.15.
 */
public class App {

    public static void main(String[] args) throws IOException {

        List<Podcast> podcasts = XMLReadPodcast.xmlReadPodcast();
        Iterator<Podcast> iPodcast = podcasts.iterator();
        int id = 1;
        while (iPodcast.hasNext()) {
            Podcast podcast = iPodcast.next();
            System.out.println(id + ") " + podcast.getName());
            id++;
        }

        Scanner in = new Scanner(System.in);
        System.out.print(" : ");
        int idPodcast = in.nextInt() - 1;
        id = 1;
        List<Object> urls = SearchPodcast.searchUrlPodcast(podcasts.get(idPodcast));
        Iterator<Object> iUrl =  urls.iterator();
        while (iUrl.hasNext()) {
            URL url = (URL) iUrl.next();
            System.out.println(id + ") " + url);
            id++;
        }
        System.out.print(" : ");
        int idUrl = in.nextInt() - 1;
        URL url = (URL) urls.get(idUrl);

        DownloadPodcast.downloadsPodcast(podcasts.get(idPodcast), (URL) urls.get(idUrl));

    }
}
