package hr.fer.oop.meteo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Grabber {

  public Grabber (String... date){

  }



   public void n () throws IOException{
        Document doc=Jsoup.connect("http://www.meteo-info.hr/povijesni-podaci/"+"2019-05-09").get();
        System.out.println(doc.title());
        Elements places=doc.select("ul.pseudo-columns li");
        Elements rainfall=doc.select("ul.pseudo-columns b");
        int sum=0;
        for(
        Element headline:places){
        System.out.println(headline.text()+" "+headline.ownText());
        sum++;
        //System.out.println();
        // System.getProperty("line.separator");
        }
        System.out.println(sum);
        }
}
