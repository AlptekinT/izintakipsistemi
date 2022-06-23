package com.company.izintakip.web.screens;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "IZINTAKIP_TALEP")
@Entity(name = "izintakip_Talep")
public class Talep extends StandardEntity {
    private static final long serialVersionUID = 2019258599917354817L;

    @Column(name = "TALEP_SAHIBI")
    private String talepSahibi;

    @Column(name = "ONAYLAYAN")
    private String onaylayan;

    @Temporal(TemporalType.DATE)
    @Column(name = "ONAY_TARIHI")
    private Date onayTarihi;

    @Column(name = "ONAY_SAATI")
    private String onaySaati;

    @Column(name = "ROL")
    private String rol;

    @Column(name = "IZIN_NEDENI")
    private String izinNedeni;

    @Temporal(TemporalType.DATE)
    @Column(name = "BASLANGIC_TARIHI")
    private Date baslangicTarihi;

    @Temporal(TemporalType.DATE)
    @Column(name = "BITIS_TARIHI")
    private Date bitisTarihi;

    @Column(name = "IZIN_GUN_SAYISI")
    private String izinGunSayisi;

    @Column(name = "DURUM")
    private String durum;

    @Column(name = "IZIN_ACIKLAMASI")
    private String izinAciklamasi;

    public String getOnaySaati() {
        return onaySaati;
    }

    public void setOnaySaati(String onaySaati) {
        this.onaySaati = onaySaati;
    }

    public Date getOnayTarihi() {
        return onayTarihi;
    }

    public void setOnayTarihi(Date onayTarihi) {
        this.onayTarihi = onayTarihi;
    }

    public String getOnaylayan() {
        return onaylayan;
    }

    public void setOnaylayan(String onaylayan) {
        this.onaylayan = onaylayan;
    }

    public String getIzinAciklamasi() {
        return izinAciklamasi;
    }

    public void setIzinAciklamasi(String izinAciklamasi) {
        this.izinAciklamasi = izinAciklamasi;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public String getIzinGunSayisi() {
        return izinGunSayisi;
    }

    public void setIzinGunSayisi(String izinGunSayisi) {
        this.izinGunSayisi = izinGunSayisi;
    }

    public Date getBitisTarihi() {
        return bitisTarihi;
    }

    public void setBitisTarihi(Date bitisTarihi) {
        this.bitisTarihi = bitisTarihi;
    }

    public Date getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public void setBaslangicTarihi(Date baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
    }

    public String getIzinNedeni() {
        return izinNedeni;
    }

    public void setIzinNedeni(String izinNedeni) {
        this.izinNedeni = izinNedeni;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getTalepSahibi() {
        return talepSahibi;
    }

    public void setTalepSahibi(String talepSahibi) {
        this.talepSahibi = talepSahibi;
    }
}