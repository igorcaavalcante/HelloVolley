package com.igor.hellovolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main extends AppCompatActivity {

    RequestQueue requestQueque;
    Button botaoget;
    TextView resultadoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoget = (Button) findViewById(R.id.btnGET);
        resultadoTextView = (TextView) findViewById(R.id.resultado);

        requestQueque = Volley.newRequestQueue(this);

        botaoget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });
    }

    private void jsonParse(){
        String url = "https://api.myjson.com/bins/mzzmh";

        Response.Listener responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrayFilmes = response.getJSONArray("filmes");
                    for(int i =0; i < arrayFilmes.length(); i++){
                        JSONObject filme = arrayFilmes.getJSONObject(i);
                        String titulo = filme.getString("Titulo");
                        String ano = filme.getString("Ano");
                        resultadoTextView.append(titulo + ", " + ano + "\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener responseListenerError = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, (String) null, responseListener, responseListenerError);

        requestQueque.add(request);
    }
}
