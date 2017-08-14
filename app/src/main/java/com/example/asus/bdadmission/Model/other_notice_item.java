package com.example.asus.bdadmission.Model;

/**
 * Created by ASUS on 8/14/2017.
 */

public class other_notice_item {
    private String othersName;
    private String othersNotice;

    public other_notice_item(String othersName, String othersNotice) {
        this.othersName = othersName;
        this.othersNotice = othersNotice;
    }

    public String getOthersName() {
        return othersName;
    }

    public void setOthersName(String othersName) {
        this.othersName = othersName;
    }

    public String getOthersNotice() {
        return othersNotice;
    }

    public void setOthersNotice(String othersNotice) {
        this.othersNotice = othersNotice;
    }
}
