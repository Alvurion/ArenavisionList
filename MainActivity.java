package eus.alvurion.arenavisionlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String baseUrl = "http://arenavision.in/";
    String agenda = "agenda";
    String output = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = baseUrl + agenda;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("APp", e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    output = response.body().string();
                    Log.e("output_resultado", output);
                } else {
                    Log.e("APp", "Error");
                }
            }
        });
    }

    public void ponerTexto(View view) {
        TextView lista = (TextView) findViewById(R.id.tv_lista);
        Document document = Jsoup.parse(output);
        Elements element = document.select("p:contains(/av1)");
        String[] s = element.html().split("<br>");

        Emision[] emision = new Emision[s.length];

        for (int i = 0; i < s.length; i++) {
            s[i] = s[i].trim();

            String date = s[i].substring(0, 8);
            String time = s[i].substring(9, 18);

            String temporal1 = s[i].substring(19);
            int puntos = temporal1.indexOf(":");
            String type = temporal1.substring(0, puntos);

            String temporal2 = temporal1.substring(puntos + 2);
            int parentesis_inicial = temporal2.indexOf(" (");
            String event = temporal2.substring(0, parentesis_inicial);

            String temporal3 = temporal2.substring(parentesis_inicial + 2);
            int parentesis_final = temporal3.indexOf(")");
            String league = temporal3.substring(0, parentesis_final);

            String channels = temporal3.substring(parentesis_final + 1);

            emision[i] = new Emision();
            emision[i].setDate(date);
            emision[i].setTime(time);
            emision[i].setType(type);
            emision[i].setEvent(event);
            emision[i].setLeague(league);
            emision[i].setChannels(channels);

            lista.append(emision[i].getDate() + " "
                    + emision[i].getTime() + "\n"
                    + emision[i].getType() + "\n"
                    + emision[i].getEvent() + "\n"
                    + emision[i].getLeague() + "\n"
                    + emision[i].getChannels() + "\n\n");
        }
    }


}
