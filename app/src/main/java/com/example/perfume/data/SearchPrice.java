package com.example.perfume.data;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class SearchPrice extends AsyncTask<Void, Void, Void> {
    Context context;
    String name;
    int size;
    ProgressDialog progressDialog;

    public ArrayList<String> shops;
    public ArrayList<String> lprices;
    public ArrayList<String> links;

    public SearchPrice(Context context, String name, int size){
        this.context = context;
        this.name = name;
        this.size = size;
        shops = new ArrayList<>();
        lprices = new ArrayList<>();
        links = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Void doInBackground(Void... voids) {
        String clientId = "79VPMsZIcnUYA4L0ZqLy";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "kf9QfUlJxx";//애플리케이션 클라이언트 시크릿값";
        try {
            String text = URLEncoder.encode(name + String.valueOf(size) + "ml", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/shop?query="+ text + "&display=3" + "&sort=asc";
            ; // json 결과
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());

            System.out.println(response.toString());
            // 여기서부터 추가한 부분

            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray getArray = jsonObject.getJSONArray("items");

            for (int i = 0; i < getArray.length(); i++) {
                JSONObject object = (JSONObject) getArray.get(i);

                String shop = (String) object.get("mallName");
                String lprice = (String) object.get("lprice");
                String link = (String) object.get("link");

                String titleFilter = shop.replaceAll("<b>", "");
                String title = titleFilter.replaceAll("</b>", "");


                Log.e("test", title);
                Log.e("test", lprice);
                Log.e("test", link);

                shops.add(shop);
                lprices.add(lprice);
                links.add(link);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


    @Override
    protected void onPostExecute(Void voids) {
    }
}
