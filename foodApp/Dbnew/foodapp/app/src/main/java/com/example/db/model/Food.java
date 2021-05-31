package com.example.db.model;



public class Food {
    public String title;
    public String detail ;
    public String image_url;
    public long id;
//    ImageView image;
//    Image images;

    public Food(String title, String image_url, String detail){
//        this.image = image;
        this.detail = detail;
        this.image_url = image_url;
        this.title = title;
    }
//
//    public String getName(){ return name;}
//
//    public void setName(String name){ this.name = name;}


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTitle(){ return title;}

    public void setTitle(String title){ this.title = title;}

//    public ImageView getImage(){ return image;}

//    public void setImage(ImageView image){ this.image = image;}

    public String getDetail(){ return detail;}

    public void setDetail(String detail){ this.detail = detail;}
}
