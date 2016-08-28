package com.spring.elevator.utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by AlexJIANG on 11/21/15.
 */
public class Spider {
    public static void main(String args[]) {
        try {
            Spider spi =new Spider();
            spi.extr("http://www.google.com");


        }catch(Exception e){

            e.printStackTrace();
        }
    }

    public void extr(String url) throws IOException {

        Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
        Elements e = doc.select("a[href]");
        System.out.print(e.toString());


    }

}
