/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dhbw.se.giftit.jpa;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Viktoria
 */
public class Idea implements Serializable {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idea;
    
    private int like;
    private int dislike;
    private String name;
    private int price;
    private String description;
    private String link;
    private String picture;
    
    @ManyToOne
    private RoomEntry roomentry;
    
    public Idea (){
        
    }
    //<editor-fold defaultstate="collapsed" desc="Konstruktor">
    
    public Idea ( int like, int dislike, String name, int price, String description, String link, String picture){
        this.like = like;
        this.dislike = dislike;
        this.name = name;
        this.price = price;
        this.description = description;
        this.link = link;
        this.picture = picture;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="getter and setter">
    public Long getIdea() {
        return idea;
    }
    
    public void setIdea(Long idea) {
        this.idea = idea;
    }
    
    public int getLike() {
        return like;
    }
    
    public void setLike(int like) {
        this.like = like;
    }
    
    public int getDislike() {
        return dislike;
    }
    
    public void setDislike(int dislike) {
        this.dislike = dislike;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getLink() {
        return link;
    }
    
    public void setLink(String link) {
        this.link = link;
    }
    
    public String getPicture() {
        return picture;
    }
    
    public void setPicture(String picture) {
        this.picture = picture;
    }
    
    public RoomEntry getRoomentry() {
        return roomentry;
    }
    
    public void setRoomentry(RoomEntry roomentry) {
        this.roomentry = roomentry;
    }
//</editor-fold>
    
}