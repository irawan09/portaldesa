package com.laelektronik.user.portaldesa.Util;

import java.io.Serializable;

/**
 * Created by doell on 19/12/17.
 */

public class Kegiatan implements Serializable {

    private int id;
    private String namaKegiatan;
    private String desa;
    private String kecamatan;
    private String kabupaten;
    private String provinsi;
    private String subdit;
    private String penanggungJawab;
    private String nilaiKontrak;
    private String pelaksana;

    public Kegiatan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getSubdit() {
        return subdit;
    }

    public void setSubdit(String subdit) {
        this.subdit = subdit;
    }

    public String getPenanggungJawab() {
        return penanggungJawab;
    }

    public void setPenanggungJawab(String penanggungJawab) {
        this.penanggungJawab = penanggungJawab;
    }

    public String getNilaiKontrak() {
        return nilaiKontrak;
    }

    public void setNilaiKontrak(String nilaiKontrak) {
        this.nilaiKontrak = nilaiKontrak;
    }

    public String getPelaksana() {
        return pelaksana;
    }

    public void setPelaksana(String pelaksana) {
        this.pelaksana = pelaksana;
    }
}
