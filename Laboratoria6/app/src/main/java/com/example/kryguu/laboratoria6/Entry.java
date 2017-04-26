package com.example.kryguu.laboratoria6;

/**
 * Created by kryguu on 12.04.2017.
 */

public class Entry {
    private int mLogo;
    private String mName;
    private String[] mChildren;

    public Entry(int logo, String name) {
        this.mLogo = logo;
        this.mName = name;
    }

    public Entry(int logo, String name, String[] children) {
        this.mLogo = logo;
        this.mName = name;
        this.mChildren = children;
    }

    public int getLogo() {
        return mLogo;
    }

    public String getName() {
        return mName;
    }

    public String[] getChildren() {
        return mChildren;
    }
}
