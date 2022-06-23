package com.company.izintakip.web.screens;

import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@UiController("izintakip_Testscreen")
@UiDescriptor("testScreen.xml")
public class Testscreen extends Screen {

    @Inject
    private UserSession userSession;
    @Inject
    private DateField<Date> bitisTarih;
    @Inject
    private DateField<Date> baslangicTarih;
    @Inject
    protected DataManager dataManager;
    @Inject
    private TextField<String> izinAciklama;
    @Inject
    private LookupField<String> lf_izinNeden;
    @Inject
    private Notifications notifications;
    @Inject
    private EmailService emailService;
    @Inject
    private TextField<String> tf_talepSahip;
    @Inject
    private TextField<String> tf_rol;
    @Inject
    private TextField<String> tf_izin;


    @Subscribe
    public void onInit(InitEvent event) {

        List<String> optionList = new ArrayList<>();
        optionList.add("Yıllık İzin");
        optionList.add("Evden Çalışma");
        optionList.add("Ölüm İzni");
        optionList.add("Doğum İzni");
        optionList.add("Evlilik İzni");
        optionList.add("Pantolonum yok izni");
        lf_izinNeden.setOptionsList(optionList);


    }



}