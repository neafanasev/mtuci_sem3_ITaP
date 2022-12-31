package java_lab_7;

import java.util.regex.Pattern;
import java.net.MalformedURLException;
import java.net.URL;

public class URLDepthPair {
    private String sUrl;
    private int depth;
    public URL url;

    static final Pattern urlPattern = Pattern.compile("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)");

    public boolean check() {
        return urlPattern.matcher(sUrl).find();
    }

    public URLDepthPair(String url, int depth) throws MalformedURLException {
        this.sUrl = url;
        this.depth = depth;
        this.url = new URL(url);
    }

    public String getUrl() {
        return sUrl;
    }

    public int getDepth() {
        return depth;
    }

    public String toString() {
        return "(" + sUrl + ", " + depth + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof URLDepthPair p) {
            return sUrl.equals(p.sUrl);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return sUrl.hashCode();
    }

}