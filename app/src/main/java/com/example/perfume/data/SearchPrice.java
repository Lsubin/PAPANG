package com.example.perfume.data;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import com.example.perfume.ProductDetailsActivity;
import com.example.perfume.adapter.PerfumeSizeAdapter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import lombok.val;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class SearchPrice extends AsyncTask<Void, Void, Void> {
    Context context;
    String name;
    String url;
    public ArrayList<String> urls;
    public ArrayList<String> prices;
    public ArrayList<String> shops;
    Document doc;

    public SearchPrice(Context context, ArrayList<String> urls){
        this.context = context;
        this.urls = urls;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Void doInBackground(Void... voids) {
        doc = null;
        prices = new ArrayList<>();
        shops = new ArrayList<>();
        try{
            for(int i = 0; i < urls.size(); i++){
                String site = checkSite(urls.get(i));
                if(site.equals("shinsegaemall"))
                    site = "ssg";
                Log.e("사이트", site);
                doc = Jsoup.connect(urls.get(i)).get();

                switch(site){
                    case "skstoa":
                        prices.add(doc.select("p.price strong").first().text());
                        shops.add("SKMall");
                        break;
                    case "thehyundai":
                        prices.add(doc.select("em#totItemPrcSpan").first().text());
                        shops.add("현대Mall");
                        break;
                    case "ssg":
                        prices.add(doc.select("em.ssg_price").first().text());
                        shops.add("SSG");
                        break;
                    case "akmall":
                        prices.add(doc.select("li.c_price i").first().text());
                        shops.add("AKMall");
                        break;
                    case "galleria":
                        prices.add(doc.select("div.gds_amt dd").first().text());
                        shops.add("갤러리아");
                        break;
                    case "hmall":
                        prices.add(doc.select("span.enroll-price").first().text());
                        shops.add("HMall");
                        break;
                    case "lotteimall":
                        prices.add(doc.select("span.num").first().text());
                        shops.add("롯데홈쇼핑");
                        break;
                    case "gsshop":
                        prices.add(doc.select("span.price-definition-ins ins").first().text());
                        shops.add("GSSHOP");
                        break;
                    case "naver":
                        prices.add(doc.select("span._1LY7DqCnwR").first().text());
                        shops.add("네이버쇼핑");
                        break;
                    case "hnsmall":
                        prices.add(doc.select("span.sellPrice02 em").first().text());
                        shops.add("HnsMAll");
                        break;
                    case "nsmall":
                        prices.add(doc.select("em.price").first().text());
                        shops.add("NsMall");
                        break;
                    case "shinsegaetvshopping":
                        prices.add(doc.select("span._bestPrice").first().text());
                        shops.add("신세계홈쇼핑");
                        break;
                    default:
                        break;
                }
            }
        }catch (IOException e){}

        return null;
    }


    @Override
    protected void onPostExecute(Void voids) {
        ((ProductDetailsActivity)ProductDetailsActivity.pContext).setURl();
    }

    public String checkSite(String url){
        int index = url.indexOf(".");
        int index2 = url.indexOf(".", index+1);

        String site = url.substring(index + 1, index2);

        Log.e("URL", site);
        return site;
    }

}

