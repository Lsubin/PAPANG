package com.example.perfume.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perfume.R;
import com.example.perfume.main.home.Event_RecyclerView_Adapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class RecentSearchAdapter extends RecyclerView.Adapter<RecentSearchAdapter.ViewHolder> {
    Context context;

    ArrayList<String> search_words;          // 상품 이미지

    RecentSearchAdapter(Context context) {
        this.context = context;
        search_words = getStringArrayPref();
    }

    public void allDelete() {
        this.search_words.clear();
        deleteArrayPref("Search", search_words);
        notifyDataSetChanged();
    }

    public void update(String word){
        search_words.add(word);
        setStringArrayPref("Search", search_words);
        notifyDataSetChanged();
    }

    private void setStringArrayPref(String key, ArrayList values) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }
        editor.apply();
    }

    private void deleteArrayPref(String key, ArrayList values){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray a = new JSONArray();
        for (int i = 0; i < values.size(); i++) {
            a.put(values.get(i));
        }
        if (!values.isEmpty()) {
            editor.putString(key, a.toString());
        } else {
            editor.putString(key, null);
        }
        editor.apply();
    }

    private ArrayList<String> getStringArrayPref() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString("Search", null);
        ArrayList<String> words = new ArrayList();
        if (json != null) {
            try {
                JSONArray a = new JSONArray(json);
                for (int i = 0; i < a.length(); i++) {
                    String url = a.optString(i);
                    words.add(url);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return words;
    }

    @NonNull
    @Override
    public RecentSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recent_search_word, parent, false);
        RecentSearchAdapter.ViewHolder holder = new RecentSearchAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecentSearchAdapter.ViewHolder holder, int position) {
        holder.recent_search_word.setText(search_words.get(position));
    }

    @Override
    public int getItemCount() {
        return search_words.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView recent_search_word;
        ImageButton delete_recent_search_word;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recent_search_word = (TextView) itemView.findViewById(R.id.recent_search_word);
            delete_recent_search_word = (ImageButton) itemView.findViewById(R.id.delete_recent_search_word);

            delete_recent_search_word.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    search_words.remove(position);
                    setStringArrayPref("Search", search_words);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
