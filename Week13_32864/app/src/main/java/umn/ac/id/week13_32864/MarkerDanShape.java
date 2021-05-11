package umn.ac.id.week13_32864;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MarkerDanShape {
    private String jenis = "Marker";
    private String keterangan = "";
    private double radius = 0.0;
    private List<LatLng> titiks = new ArrayList<LatLng>();
    private Object objekPeta = null;

    public MarkerDanShape(String jenis, String keterangan, double radius, List<LatLng> titiks){
        this.jenis = jenis;
        this.keterangan = keterangan;
        this.radius = radius;
        this.titiks = new ArrayList<LatLng>();
        this.titiks.addAll(titiks);
    }

    public String getJenis(){ return this.jenis;}
    public String getKeterangan() { return this.keterangan;}
    public double getRadius() { return this.radius;}
    public List<LatLng> getTitiks() { return this.titiks;}
    public int getJumlahTitiks() {return this.titiks.size();}
    public Object getObjekPeta(){return this.objekPeta;}
    public void setKeterangan(String keterangan) { this.keterangan = keterangan;}
    public void setRadius(double radius) { this.radius = radius;}
    public void setTitiks(List<LatLng> titiks) { this.titiks = titiks;}
    public void setObjekPeta(Object objekPeta) { this.objekPeta = objekPeta;}

}