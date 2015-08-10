/**
 * Created by sergius on 10.08.15.
 * Type podcast
 */
public class Podcast {

    private String name;
    private String rss;
    private String pattern;
    private String proxy;

    public Podcast(String name, String rss, String pattern, String proxy) {
        this.name = name;
        this.rss = rss;
        this.pattern = pattern;
        this.proxy = proxy;
    }

    public String getName() {
        return name;
    }

    public String getRss() {
        return rss;
    }

    public String getPattern() {
        return pattern;
    }

    public String getProxy() {
        return proxy;
    }

    @Override
    public String toString() {
        if (proxy.equals("no")) {
            return "Podcast : " + name + "\n"
                    + rss + "\n"
                    + pattern + "\n";
        } else {
            return "Podcast : " + name + "\n"
                    + "rss : " + rss + "\n"
                    + "pattern : " + pattern + "\n"
                    + "proxy : \n"
                    + "\t ip : " + proxy.split(":")[0] + "\n"
                    + "\t port : " + proxy.split(":")[1];
        }
    }
}
