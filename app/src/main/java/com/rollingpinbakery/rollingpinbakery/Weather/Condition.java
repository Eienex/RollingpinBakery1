package com.rollingpinbakery.rollingpinbakery.Weather;

/**
 * Created by rudst on 3/20/18.
 */

public class Condition {
    private String text;
    private String icon;
    private int code;
    public Condition() {}
    public String getText() {return text;}
    public void setText(String mText){this.text = mText;}
    public String getIcon(){return icon;}
    public void setIcon(String mIcon){this.icon = mIcon;}
    public int getCode(){return code;}
    public void setCode(int mCode){this.code = mCode;}
}
