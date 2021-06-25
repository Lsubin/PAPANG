package com.papang.perfume.object;

public class Review {

    private String review_text;
    private String review_tag;
    private String review_date;
    private int review_rating;
    private int review_type;
    private String review_name;
    private int review_heart_count;

    public void setReview_text(String text) {
        review_text = text;
    }

    public String getReview_text() {
        return this.review_text;
    }

    public void setReview_tag(String tag) {
        review_tag = tag;
    }

    public String getReview_tag() {
        return this.review_tag;
    }

    public void setReview_date(String date) {
        review_date = date;
    }

    public String getReview_date() {
        return this.review_date;
    }

    public void setReview_name(String name) {
        review_name = name;
    }

    public String getReview_name() {
        return this.review_name;
    }

    public void setReview_rating(int rating) {
        review_rating = rating;
    }

    public int getReview_rating() {
        return this.review_rating;
    }

    public void setReview_type(int type) {
        review_type = type;
    }

    public int getReview_type() {
        return this.review_type;
    }

    public void setReview_heart_count(int count) {
        review_heart_count = count;
    }

    public int getReview_heart_count() {
        return this.review_heart_count;
    }
}
