package com.cadernetadegastos;

public class MoedaEstrangeira {

    private String bid;
    private String ask;

    public MoedaEstrangeira() {
    }

    public MoedaEstrangeira(String bid, String ask) {
        this.bid = bid;
        this.ask = ask;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }
}
