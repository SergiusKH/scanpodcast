import java.io.*;
import java.net.*;

/**
 * Created by sergius on 10.08.15.
 */
public class DownloadPodcast {

    public static void downloadsPodcast(Podcast podcast, URL url) throws IOException {
        HttpURLConnection con;
        int start = url.toString().lastIndexOf("/") + 1;
        String name = url.toString().substring(start, url.toString().length());
        System.out.println(name);
        if (!("no".equals(podcast.getProxy()))) {
            String[] ipAndPort = podcast.getProxy().split(":");
            String ip = ipAndPort[0];
            int port = Integer.valueOf(ipAndPort[1]);
            Proxy proxy =  new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
            con = (HttpURLConnection) url.openConnection(proxy);
        } else {
            con = (HttpURLConnection) url.openConnection();
        }


        System.out.println("Podcast : " + podcast.getName() + "\n"
                + "url : " + url + "\n"
                + "byte : " + con.getContentLengthLong());
        BufferedInputStream bufIn = new BufferedInputStream(con.getInputStream());
        BufferedOutputStream buffOut = new BufferedOutputStream(new FileOutputStream(new File(name)));
        try {
            int b;
            int readByte = 0;
            while ((b = bufIn.read()) != -1) {
                buffOut.write(b);
                System.out.print(readByte++);
                System.out.print("\r");
            }
            buffOut.flush();
        } finally {
            bufIn.close();
            buffOut.close();
        }

    }
}
