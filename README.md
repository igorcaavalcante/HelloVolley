# HelloVolley
This is my simple hello world with Volley (Android)

"Volley is an HTTP library that makes networking for Android apps easier and most importantly, faster."

This small application makes a request for an external API that returns a JSON object and displays its contents. Besides, makes part of my initial studies in world of Android. :)

The app can be resumed in this function:

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
