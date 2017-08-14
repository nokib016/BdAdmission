package com.example.asus.bdadmission.Model;

/**
 * Created by ASUS on 8/14/2017.
 */

public class date_notice_item {

    private String uniNameDate;
    private String dstetNotice;

    public date_notice_item(String uniNameDate, String dstetNotice) {
        this.uniNameDate = uniNameDate;
        this.dstetNotice = dstetNotice;
    }

    public String getUniNameDate() {
        return uniNameDate;
    }

    public void setUniNameDate(String uniNameDate) {
        this.uniNameDate = uniNameDate;
    }

    public String getDstetNotice() {
        return dstetNotice;
    }

    public void setDstetNotice(String dstetNotice) {
        this.dstetNotice = dstetNotice;
    }
}
