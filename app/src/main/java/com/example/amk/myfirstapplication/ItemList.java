package com.example.amk.myfirstapplication;

/**
 * Created by AdilMateenKhan1 on 12-01-2017.
 */

public class ItemList {

    public String author;
    public String title;
    public String description;
    public String url;
    public String urlToImage;
    public String publishedAt;

    public ItemList(String author,String title,String description,String url,String urlToImage,String publishedAt)
    {
        this.author=author;
        this.title=title;
        this.description=description;
        this.url=url;
        this.urlToImage=urlToImage;
        this.publishedAt=publishedAt;
    }


}
