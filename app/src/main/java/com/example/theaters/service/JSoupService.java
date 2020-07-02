package com.example.theaters.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JSoupService {
    public static Document goToHref(String url) throws IOException{
        return Jsoup.connect(url).get();
    }
}
