package com.example.meditative.ui.music;

public class MusicModel {

    private String Artist;
    private String Name;
    private String URL;

    private MusicModel(){}

    private MusicModel (String Artist, String Name, String URL)
    {
        this.Artist = Artist;
        this.Name = Name;
        this.URL = URL;
    }


    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
