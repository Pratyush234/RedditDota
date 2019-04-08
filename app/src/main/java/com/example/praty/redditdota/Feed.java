package com.example.praty.redditdota;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

@Root(name="feed", strict=false)
public class Feed implements Serializable {
    @Element(name="icon")
    private String icon;

    @Element(name="id")
    private String id;

    @Element(name="logo")
    private String logo;

    @Element(name="subtitle")
    private String subtitle;

    @Element(name="updated")
    private String updated;

    @ElementList(inline=true, name="entry")
    public List<Entry> entries;

    @Element(name="title")
    private String title;

    public Feed(String icon, String id, String logo, String subtitle, String updated, List<Entry> entries, String title) {
        this.icon = icon;
        this.id = id;
        this.logo = logo;
        this.subtitle = subtitle;
        this.updated = updated;
        this.entries = entries;
        this.title=title;
    }

    public Feed() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "entries=" + entries +
                '}';
    }
}
