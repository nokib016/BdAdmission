package com.example.asus.bdadmission.Model;

/**
 * Created by ASUS on 8/18/2017.
 */
public class all_notice_item {

    private String notice;
    private String versity;

    public all_notice_item() {
    }

    public all_notice_item(String uniNameDate, String dstetNotice) {
        this.notice = uniNameDate;
        this.versity = dstetNotice;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getVersity() {
        return versity;
    }

    public void setVersity(String versity) {
        this.versity = versity;
    }
}
