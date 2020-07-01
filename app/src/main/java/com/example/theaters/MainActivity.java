package com.example.theaters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.theaters.models.Theater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void CardClick(View view) {
        boolean error = false;

        // получаем id театра
        int theaterId = 0;
        switch (view.getId()) {
            case R.id.theaterTUZ: theaterId = 0; break;
            case R.id.theaterDrama: theaterId = 1; break;
            case R.id.theaterDolls: theaterId = 2; break;
            default: error = true;
        }

        // получаем изображение театра, используя id
        int imageId = 0;
        if (!error) {
            switch (theaterId) {
                case 0: imageId = R.drawable.t_tuz; break;
                case 1: imageId = R.drawable.t_drama; break;
                case 2: imageId = R.drawable.t_dolls; break;
                default: error = true;
            }
        }

        if (!error) {
            // создаем объект класса Theater
            Theater theater = new Theater(
                    imageId,
                    GetTheaterParamByResources(TheaterParams.theaterNames, theaterId),
                    GetTheaterParamByResources(TheaterParams.theaterAddress, theaterId),
                    GetTheaterParamByResources(TheaterParams.theaterSite, theaterId),
                    GetTheaterParamByResources(TheaterParams.theaterVk, theaterId),
                    GetTheaterParamByResources(TheaterParams.theaterPhone, theaterId)
            );

            // создаем объект для вызова новой Activity
            Intent intent = new Intent(getApplicationContext(), TheaterActivity.class);

            // передаем theater бущущей Activity
            intent.putExtra(Theater.class.getSimpleName(), theater);

            startActivity(intent);
        }
    }

    /**
     * Хранит и возвращает строковые значение name для string-array из res/values/string.
     */
    public enum TheaterParams
    {
        theaterNames ("theater_names"),
        theaterAddress ("theater_address"),
        theaterVk ("theater_vk"),
        theaterSite ("theater_site"),
        theaterPhone ("theater_phone");


        private String arrayName;

        TheaterParams(String arrayName) {
            this.arrayName = arrayName;
        }

        public String getName() {
            return arrayName;
        }
    }

    /**
     * Метод возвращает строку - параметр театра из res/values/string.
     * @param param Нужный параметр - значение name для string-array.
     * @param theaterId Id театра - номер строки из string-array.
     * @return
     */
    public String GetTheaterParamByResources(TheaterParams param, int theaterId) {
        String paramName = param.getName();
        int arrayId = getResources().getIdentifier(paramName, "array", getApplicationContext().getPackageName());
        String[] array = getResources().getStringArray(arrayId);
        return array[theaterId];
    }
}
