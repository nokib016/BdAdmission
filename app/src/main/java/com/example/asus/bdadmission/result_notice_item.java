package com.example.asus.bdadmission;

/**
 * Created by ASUS on 8/14/2017.
 */

public class result_notice_item {
    private String uniName;
    private String resultNotice;

    public result_notice_item(String uniName, String resultNotice) {
        this.uniName = uniName;
        this.resultNotice = resultNotice;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public String getResultNotice() {
        return resultNotice;
    }

    public void setResultNotice(String resultNotice) {
        this.resultNotice = resultNotice;
    }
}
