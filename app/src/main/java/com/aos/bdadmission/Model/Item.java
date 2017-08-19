package com.aos.bdadmission.Model;

public class Item {
	public String name;
	public long updateTime;
    public String logoLink;

    public Item() {
    }

    public Item(String name, long date, String logoLink) {
        this.name = name;
        this.updateTime = date;
        this.logoLink = logoLink;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", date=" + updateTime +
                ", logo='" + logoLink + '\'' +
                '}';
    }
}
