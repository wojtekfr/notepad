package pl.wojtek;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Crawler {
    public void crawl() throws IOException {
        Document doc = null;
        doc = Jsoup.connect("http://gikme.pl").get();
        String text = doc.body().text();
        System.out.println(text);
    }

}
