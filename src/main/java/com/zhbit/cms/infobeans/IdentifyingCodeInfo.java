package com.zhbit.cms.infobeans;

public class IdentifyingCodeInfo {
    private String content;
    private String img;
    private int count = 0;

    public String getContent() {
        return content;
    }

    public String getImg() {
        return img;
    }

    public int getCount() {
        return count;
    }

    public void incCount() {
        count--;
    }

    public void reUser(String content, String img) {
        this.content = content;
        this.img = img;
        count = 5;
    }
}
