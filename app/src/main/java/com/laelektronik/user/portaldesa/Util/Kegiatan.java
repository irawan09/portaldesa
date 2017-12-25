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
    private String nilaiPagu;
    private String pemberiTugas;
    private String subdit;
    private String penanggungJawab;
    private String noHpPj;
    private String alamatPj;
    private String nilaiKontrak;
    private String pelaksana;
    private String pimpinanPelaksana;
    private String noHpPelaksana;
    private String noKontrak;
    private String tglKontrak;
    private String mulaiKerja;
    private String selesaiKerja;
    private String imgUrl;
    private double lat;
    private double lng;

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getNilaiPagu() {
        return nilaiPagu;
    }

    public void setNilaiPagu(String nilaiPagu) {
        this.nilaiPagu = nilaiPagu;
    }

    public String getPemberiTugas() {
        return pemberiTugas;
    }

    public void setPemberiTugas(String pemberiTugas) {
        this.pemberiTugas = pemberiTugas;
    }

    public String getNoHpPj() {
        return noHpPj;
    }

    public void setNoHpPj(String noHpPj) {
        this.noHpPj = noHpPj;
    }

    public String getAlamatPj() {
        return alamatPj;
    }

    public void setAlamatPj(String alamatPj) {
        this.alamatPj = alamatPj;
    }

    public String getPimpinanPelaksana() {
        return pimpinanPelaksana;
    }

    public void setPimpinanPelaksana(String pimpinanPelaksana) {
        this.pimpinanPelaksana = pimpinanPelaksana;
    }

    public String getNoHpPelaksana() {
        return noHpPelaksana;
    }

    public void setNoHpPelaksana(String noHpPelaksana) {
        this.noHpPelaksana = noHpPelaksana;
    }

    public String getNoKontrak() {
        return noKontrak;
    }

    public void setNoKontrak(String noKontrak) {
        this.noKontrak = noKontrak;
    }

    public String getTglKontrak() {
        return tglKontrak;
    }

    public void setTglKontrak(String tglKontrak) {
        this.tglKontrak = tglKontrak;
    }

    public String getMulaiKerja() {
        return mulaiKerja;
    }

    public void setMulaiKerja(String mulaiKerja) {
        this.mulaiKerja = mulaiKerja;
    }

    public String getSelesaiKerja() {
        return selesaiKerja;
    }

    public void setSelesaiKerja(String selesaiKerja) {
        this.selesaiKerja = selesaiKerja;
    }
}
