package com.example.abdullahi.weblinksaver.data;

import com.example.abdullahi.weblinksaver.model.Web;
import com.example.abdullahi.weblinksaver.model.WebLink;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager ourInstance = null;

    List<WebLink> links = new ArrayList<>();

    public static DataManager getInstance(){
        if (ourInstance == null){
            ourInstance = new DataManager();
            ourInstance.loadWebLinks();
        }
        return ourInstance;
    }

    private void loadWebLinks(){

        DataManager dm = getInstance();
        dm.links.clear();
        dm.links.add(new WebLink(1,new Web("Java Essential for all users"),"goal.com",null));
        dm.links.add(new WebLink(2,new Web("Kotlin Essential for all users"),"goal.com",null));
        dm.links.add(new WebLink(3,new Web("Scala Essential for all users"),"goal.com",null));
        dm.links.add(new WebLink(4,new Web("Jovago Essential for all users"),"goal.com",null));
        dm.links.add(new WebLink(5,new Web("Node js. Essential for all users"),"goal.com",null));
        dm.links.add(new WebLink(6,new Web("React Essential for all users"),"goal.com",null));
        dm.links.add(new WebLink(7,new Web("Javascript Essential for all users"),"goal.com",null));
    }

    public List<WebLink> getLinks() {
        return links;
    }
}
