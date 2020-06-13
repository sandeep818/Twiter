package com.jungle.twiter.market;

public class Market_model {
    public String market_name,status,id_n,start_time,end_time;

    public Market_model(String market_name, String status, String id_n, String start_time, String end_time) {
        this.market_name = market_name;
        this.status = status;
        this.id_n = id_n;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getMarket_name() {
        return market_name;
    }

    public void setMarket_name(String market_name) {
        this.market_name = market_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId_n() {
        return id_n;
    }

    public void setId_n(String id_n) {
        this.id_n = id_n;
    }
}
