package com.company.izintakip.web.screens;

import javax.persistence.Column;

public class Shortcut {

    @Column(name="BACKGROUND")
    private String background;

    @Column(name = "CAPTION")
    private String caption;



    public String getBackground() {
        return background;
    }

    public String getCaption() {
        return caption;
    }


}
