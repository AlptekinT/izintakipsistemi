package com.company.izintakip.web.screens;

//import com.company.izintakip.entity.AnnualPermit;

//import com.company.izintakip.web.screens.talep.EskiTalep;
//import com.company.izintakip.web.screens.talep.TalepBrowse;
//import com.company.izintakip.entity.Talep;
import com.company.izintakip.web.screens.Talep;
import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.EmailInfo;
import com.haulmont.cuba.core.global.EmailInfoBuilder;
import com.haulmont.cuba.core.global.View;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.app.core.inputdialog.DialogActions;
import com.haulmont.cuba.gui.app.core.inputdialog.DialogOutcome;
import com.haulmont.cuba.gui.app.core.inputdialog.InputParameter;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.Role;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.entity.UserRole;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.app.main.MainScreen;

import javax.inject.Inject;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;


@UiController("extMainScreen")
@UiDescriptor("ext-main-screen.xml")
public class ExtMainScreen extends MainScreen {
    @Inject
    protected DataManager dataManager;
    @Inject
    private VBoxLayout vbox_tüm_talep;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private UserSession userSession;
    @Inject
    private Dialogs dialogs;
    @Inject
    private Notifications notifications;
    @Inject
    private VBoxLayout vbox_annual_permit;
    @Inject
    private ScrollBoxLayout scrollBox;
    @Inject
    private EmailService emailService;
    @Inject
    private Screens screens;

    @Subscribe
    public void onInit(InitEvent event) {
        final List<UserRole> userRoles = userSession.getUser().getUserRoles();
        if (userSession.getUser().getName().contains("dministrator")) {
            vbox_tüm_talep.setVisible(true);
            vbox_annual_permit.setVisible(true);
        }
        for (UserRole userRole : userRoles) {
            if (userRole.getRoleName() != null && userRole.getRoleName().contains("full")) {
                vbox_tüm_talep.setVisible(true);
                vbox_annual_permit.setVisible(true);
            }
            if (userRole.getRole() != null) {
                if (userRole.getRole().getName().contains("ire") || userRole.getRole().getName().contains("nsan")) {
                    vbox_tüm_talep.setVisible(true);
                    vbox_annual_permit.setVisible(true);
                }
            }
        }
    }


    @Subscribe("img_saglik")
    public void onImg_saglikClick(Image.ClickEvent event) {

        dialogs.createInputDialog(this).withCaption("Rapor Girişi")
                .withParameter(InputParameter.intParameter("gun")
                        .withCaption("Rapor Gün Sayısı")
                        .withRequired(true))
                .withActions(DialogActions.OK_CANCEL).withCloseListener(closeEvent -> {
                    if (closeEvent.closedWith(DialogOutcome.OK)) {
                        Talep talep = dataManager.create(Talep.class);
                        talep.setDurum("Onaylandı");
                        LocalDateTime localDateTime = LocalDateTime.now();
                        talep.setBaslangicTarihi(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));


                        Calendar cal = Calendar.getInstance();
                        cal.setTime(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
                        cal.add(Calendar.DAY_OF_MONTH, closeEvent.getValue("gun"));
                        talep.setBitisTarihi(cal.getTime());


                        for (UserRole userRole : userSession.getUser().getUserRoles()) {
                            if (userRole.getRole() != null && !userRole.getRole().getName().contains("-")) {
                                final String name = userRole.getRole().getName();
                                talep.setRol(name);
                            }
                        }


                        talep.setTalepSahibi(userSession.getUser().getName());
                        talep.setIzinAciklamasi("Raporlu");
                        talep.setIzinNedeni("Rapor");

                        String izinGunSayisi = closeEvent.getValue("gun").toString();

                        talep.setIzinGunSayisi(izinGunSayisi);

                        talep = dataManager.commit(talep);
                        notifications.create(Notifications.NotificationType.HUMANIZED).withCaption("Talep Alındı").show();

                        final List<User> userList = dataManager.load(User.class).list();
                        for (User user : userList) {
                            user = dataManager.reload(user, new View(User.class)
                                    .addProperty("userRoles")
                                    .addProperty("email")
                            );
                            final List<UserRole> userRoles = user.getUserRoles();
                            for (UserRole userRole : userRoles) {
                                userRole = dataManager.reload(userRole, new View(UserRole.class)
                                        .addProperty("role", new View(Role.class).addProperty("name"))
                                );
                                if (userRole.getRole() != null && !userRole.getRole().getName().contains("-")) {
                                    if (userRole.getRole().getName().contains("nsan") || userRole.getRole().getName().contains("ire")) {
                                        EmailInfo emailInfo = EmailInfoBuilder.create()
                                                .setAddresses(user.getEmail())
                                                .setCaption("Yeni İzin Talebi Hakkında")
                                                .setBody(talep.getTalepSahibi() + " kullanıcısı yeni bir izin talebi yaptı. Lütfen onay işleminizi yapın.\n" +
                                                        "https://creas-technologies-hr.herokuapp.com/#login")
                                                .build();
                                        emailService.sendEmailAsync(emailInfo);
                                    }
                                }
                            }
                        }
                    }
                }).show();

    }

    @Subscribe("img_yeni_talep")
    public void onImg_yeni_talepClick(Image.ClickEvent event) {


        screenBuilders.screen(this).withScreenClass(Testscreen.class).build().show();

/*
        YeniTalep screen = screens.create(YeniTalep.class, OpenMode.DIALOG);
        screen.initialize(false);
        screen.show();
*/
    }

    
    
    
    
    
    
    
}

