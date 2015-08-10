import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sergius on 10.08.15.
 * Search pocdcast
 */
public class SearchPodcast {

    public static List<Object> searchUrlPodcast(Podcast podcast) throws IOException {
        LinkedHashSet<URL> urls = new LinkedHashSet<URL>();
        StringBuilder sb = new StringBuilder();
        HttpURLConnection con;
        URL rss = new URL(podcast.getRss());
        if (!("no".equals(podcast.getProxy()))) {
            String[] ipAndPort = podcast.getProxy().split(":");
            String ip = ipAndPort[0];
            int port = Integer.valueOf(ipAndPort[1]);
            Proxy proxy =  new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
            con = (HttpURLConnection) rss.openConnection(proxy);
        } else {
            con = (HttpURLConnection) rss.openConnection();
        }

        BufferedReader buf = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String line;
        while ((line = buf.readLine()) != null) {
            sb.append(line);
        }

        Matcher urlMp3 = Pattern.compile(podcast.getPattern()).matcher(sb.toString());
        while (urlMp3.find()) {
            urls.add(new URL(urlMp3.group()));
        }
        List<Object> podcastList = Arrays.asList(urls.toArray());
        return podcastList;
    }
}
