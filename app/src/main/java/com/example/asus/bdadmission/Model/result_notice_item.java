package com.example.asus.bdadmission.Model;

/**
 * Created by ASUS on 8/14/2017.
 */

public class result_notice_item {
    private String versity;
    private String notice;

    public result_notice_item() {
    }

    public result_notice_item(String uniName, String resultNotice) {
        this.versity = uniName;
        this.notice = resultNotice;
    }

    public String getVersity() {
        return versity;
    }

    public void setVersity(String uniName) {
        this.versity = uniName;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
