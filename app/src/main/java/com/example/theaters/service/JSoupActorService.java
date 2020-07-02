package com.example.theaters.service;

import com.example.theaters.R;
import com.example.theaters.models.Actor;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class JSoupActorService extends JSoupService {
    public static ArrayList<Actor> getTUZTheaterActorList(Document doc, String site) {
        ArrayList<Actor> actors = new ArrayList<>();

        try {
            Elements persons = doc.getElementsByClass("t_user").first().select("div[class^=td]");
            for (Element person : persons) {
                String name = person.getElementsByTag("a").get(1).text();
                String photoURL = "https://" + site + person.getElementsByTag("img").first().attr("src");

                actors.add(new Actor(name, photoURL));
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }

        return actors;
    }

    public static ArrayList<Actor> getDramaTheaterActorList(Document doc, String site) {
        ArrayList<Actor> actors = new ArrayList<>();

        try {
            Elements persons = doc.getElementsByClass("t_person_list").first().select("a[href^=/person]");
            for (int i = 0; i < persons.size(); i += 2) {
                String photoURL = "https://" +  site + persons.get(i).getElementsByTag("img").first().attr("src");
                String name = persons.get(i + 1).text();

                actors.add(new Actor(name, photoURL));
            }
        } catch(Exception e) {
            return new ArrayList<>();
        }

        return actors;
    }

    public static ArrayList<Actor> getDollsTheaterActorList(Document doc, String site) {
        ArrayList<Actor> actors = new ArrayList<>();

        // это ужас какой-то, структуры никакой нет
        // парсить невозможно
//        Elements images = doc.getElementsByTag("img");
//        images.remove(0);
//        images.remove(0);
//        for (Element img : images) {
//            String name = img
//                    .parent().parent().parent().parent().parent()
//                    .children().get(1).getElementsByTag("span").first().text();
//            String photoURL = "http://kirovkukla.ru" + img.attr("src");
//
//            actors.add(new Actor(name, photoURL));
//        }

        return actors;
    }
}
