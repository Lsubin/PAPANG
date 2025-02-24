package com.papang.perfume.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.papang.perfume.ProductDetailsActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class SearchPrice extends AsyncTask<Void, Void, Void> {
    Context context;
    String name;
    String url;
    public ArrayList<String> urls;
    public ArrayList<String> prices;
    public ArrayList<String> shops;
    Document doc;
    String price;

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
                        price = doc.select("p.price strong").first().text();
                        if(Character.isDigit(price.charAt(price.length()-1)))
                            price += "원";
                        prices.add(price);
                        shops.add("SKMall");
                        break;
                    case "thehyundai":
                        price = doc.select("em#totItemPrcSpan").first().text();
                        if(Character.isDigit(price.charAt(price.length()-1)))
                            price += "원";
                        prices.add(price);
                        shops.add("현대Mall");
                        break;
                    case "ssg":
                        price = doc.select("em.ssg_price").first().text();
                        if(Character.isDigit(price.charAt(price.length()-1)))
                            price += "원";
                        prices.add(price);
                        shops.add("SSG");
                        break;
                    case "akmall":
                        price = doc.select("li.c_price i").first().text();
                        if(Character.isDigit(price.charAt(price.length()-1)))
                            price += "원";
                        prices.add(price);
                        shops.add("AKMall");
                        break;
                    case "galleria":
                        price = doc.select("div.gds_amt dd").first().text();
                        if(Character.isDigit(price.charAt(price.length()-1)))
                            price += "원";
                        prices.add(price);
                        shops.add("갤러리아");
                        break;
                    case "hmall":
                        price = doc.select("span.enroll-price").first().text();
                        if(Character.isDigit(price.charAt(price.length()-1)))
                            price += "원";
                        prices.add(price);
                        shops.add("HMall");
                        break;
                    case "lotteimall":
                        price = doc.select("strong.final span").first().text();
                        if(Character.isDigit(price.charAt(price.length()-1)))
                            price += "원";
                        prices.add(price);
                        shops.add("롯데홈쇼핑");
                        break;
                    case "gsshop":
                        price = doc.select("span.price-definition-ins ins").first().text();
                        if(Character.isDigit(price.charAt(price.length()-1)))
                            price += "원";
                        prices.add(price);
                        shops.add("GSSHOP");
                        break;
                    case "naver":
                        price = doc.select("span._1LY7DqCnwR").first().text();
                        if(Character.isDigit(price.charAt(price.length()-1)))
                            price += "원";
                        prices.add(price);
                        shops.add("네이버쇼핑");
                        break;
                    case "hnsmall":
                        price = doc.select("span.sellPrice02 em").first().text();
                        if(Character.isDigit(price.charAt(price.length()-1)))
                            price += "원";
                        prices.add(price);
                        shops.add("HnsMAll");
                        break;
                    case "nsmall":
                        price = doc.select("em.price").first().text();
                        if(Character.isDigit(price.charAt(price.length()-1)))
                            price += "원";
                        prices.add(price);
                        shops.add("NsMall");
                        break;
                    case "shinsegaetvshopping":
                        price = doc.select("span._bestPrice").first().text();
                        if(Character.isDigit(price.charAt(price.length()-1)))
                            price += "원";
                        prices.add(price);
                        shops.add("신세계홈쇼핑");
                        break;
                    default:
                        break;
                }
            }
        }catch (IOException e){
            Log.e("크롤링 오류", e.getMessage().toString());
        }
        catch (NullPointerException e){
            Log.e("크롤링 오류", e.getMessage().toString());
        }
        catch (StringIndexOutOfBoundsException e){
            Log.e("크롤링 오류", e.getMessage().toString());
        }

        return null;
    }


    @Override
    protected void onPostExecute(Void voids) {
        try{
            ((ProductDetailsActivity)ProductDetailsActivity.pContext).setURl();
        } catch(NullPointerException e){
            Log.e("불러오기 오류", e.getMessage().toString());
        }

    }

    public String checkSite(String url){
        int index = url.indexOf(".");
        int index2 = url.indexOf(".", index+1);

        String site = url.substring(index + 1, index2);

        Log.e("URL", site);
        return site;
    }

}

