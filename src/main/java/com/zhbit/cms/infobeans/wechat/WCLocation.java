package com.zhbit.cms.infobeans.wechat;

public class WCLocation extends WCInfo {
    private double location_X;
    private double Location_Y;
    private double scale;
    private String label;

    public double getLocation_X() {
        return location_X;
    }

    public void setLocation_X(double location_X) {
        this.location_X = location_X;
    }

    public double getLocation_Y() {
        return Location_Y;
    }

    public void setLocation_Y(double location_Y) {
        Location_Y = location_Y;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
