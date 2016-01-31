package eus.alvurion.arenavisionlist;

public class Emision {
    String date;
    String time;
    String type;
    String event;
    String league;
    String channels;

    public Emision(String date, String time, String type, String event, String league, String channels) {
        this.date = date;
        this.time = time;
        this.type = type;
        this.event = event;
        this.league = league;
        this.channels = channels;
    }

    public Emision(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }
}
