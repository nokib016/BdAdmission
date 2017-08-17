package com.example.asus.bdadmission.Model;

/**
 * Created by ASUS on 8/14/2017.
 */

public class other_notice_item {
    private String versity;
    private String notice;

    public other_notice_item() {
    }

    public other_notice_item(String othersName, String othersNotice) {
        this.versity = othersName;
        this.notice = othersNotice;
    }

    public String getVersity() {
        return versity;
    }

    public void setVersity(String versity) {
        this.versity = versity;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
