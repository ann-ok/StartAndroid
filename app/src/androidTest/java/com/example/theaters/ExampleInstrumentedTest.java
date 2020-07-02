package com.example.theaters;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.example.theaters.models.Actor;
import com.example.theaters.models.Theater;
import com.example.theaters.service.JSoupActorService;
import com.example.theaters.service.JSoupService;

import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.theaters", appContext.getPackageName());
    }

    @Test
    public void getTheaterActorList() throws IOException {
        Document document = JSoupService.goToHref("https://kirovdramteatr.ru/truppa/");
        ArrayList<Actor> actors = JSoupActorService.getDramaTheaterActorList(document, "kirovdramteatr.ru");

        assertEquals("Исаева Наталья Николаевна", actors.get(0).getName());
        assertEquals("Васильева Анна Александровна", actors.get(actors.size() - 1).getName());
    }
}
