package umn.ac.id.week13_32864;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class TambahObject extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private int pilihan=0;
    private Button btnSimpan;
    private EditText etRadius;
    private EditText etKeterangan;
    private RadioGroup rgShape;
    private List<LatLng> titiks;
    private UiSettings mUiSettings;
    private static MarkerDanShape objMS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_object);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnSimpan = findViewById(R.id.simpan);
        etRadius = findViewById(R.id.etRadius);
        etKeterangan = findViewById(R.id.etKeterangan);
        rgShape = findViewById(R.id.rgShape);
        titiks = new ArrayList<LatLng>();

        rgShape.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                titiks.clear();
                btnSimpan.setEnabled(false);
                if (mMap != null) {
                    mMap.clear();
                }
                switch (checkedId) {
                    case R.id.marker:
                        pilihan = 0;
                        etRadius.setEnabled(false);
                        break;

                    case R.id.circle:
                        pilihan = 1;
                        etRadius.setEnabled(true);
                        break;

                    case R.id.polyline:
                        pilihan = 2;
                        etRadius.setEnabled(false);
                        break;

                    case R.id.polygon:
                        pilihan = 3;
                        etRadius.setEnabled(false);
                        break;
                }
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!objMS.getKeterangan().equalsIgnoreCase(etKeterangan.getText().toString())) {
                    objMS.setKeterangan(etKeterangan.getText().toString());
                }
                Intent jawabIntent = new Intent();
                jawabIntent.putExtra("jenis", objMS.getJenis());
                jawabIntent.putExtra("keterangan", objMS.getKeterangan());
                jawabIntent.putExtra("radius", objMS.getRadius());
                List<LatLng> mTitiks = objMS.getTitiks();
                double[] dTitiks = new double[mTitiks.size() * 2];
                for (int i = 0; i < mTitiks.size(); i++) {
                    dTitiks[2 * i] = mTitiks.get(i).latitude;
                    dTitiks[2 * i + 1] = mTitiks.get(i).longitude;
                }
                jawabIntent.putExtra("titiks", dTitiks);
                setResult(RESULT_OK, jawabIntent);
                finish();
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mUiSettings = mMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MapsActivity.currentLocation,15));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                switch (pilihan) {
                    case 0:
                        if (titiks.size() == 0) {
                            titiks.add(latLng);
                        } else {
                            titiks.set(0, latLng);
                        }
                        if (mMap != null) {
                            mMap.clear();
                            mMap.addMarker(new MarkerOptions().position(titiks.get(0)).title(etKeterangan.getText().toString()));
                            objMS = new MarkerDanShape("Marker", etKeterangan.getText().toString(), 0.0, titiks);
                            btnSimpan.setEnabled(true);
                        }
                        break;
                    case 1:
                        if (titiks.size() == 0) {
                            titiks.add(latLng);
                        } else {
                            titiks.set(0, latLng);
                        }
                        if (mMap != null) {
                            mMap.clear();
                            mMap.addMarker(new MarkerOptions()
                                    .position(titiks.get(0)).title(etKeterangan.getText().toString()));
                            String rad = etRadius.getText().toString().trim();
                            if (rad.length() > 0
                                    && Double.parseDouble(rad) > 0.0) {
                                double radius = Double.parseDouble(rad);
                                mMap.addCircle(new CircleOptions().radius(radius).center(titiks.get(0)).strokeColor(Color.YELLOW).fillColor(Color.argb(30, 255, 255, 0)));
                                objMS = new MarkerDanShape("Circle", etKeterangan.getText().toString(), radius, titiks);
                                btnSimpan.setEnabled(true);
                            }
                        }
                        break;
                    case 2:
                        titiks.add(latLng);
                        if (mMap != null) {
                            mMap.clear();
                        }
                        if (titiks.size() == 1) {
                            mMap.addMarker(new MarkerOptions().position(titiks.get(0)));
                        } else {
                            PolylineOptions pl = new PolylineOptions().color(Color.RED).width(10.0f);
                            for (int i = 0; i < titiks.size(); i++) {
                                mMap.addMarker(new MarkerOptions().position(titiks.get(i)));
                                pl.add(titiks.get(i));
                            }
                            mMap.addPolyline(pl);
                            objMS = new MarkerDanShape("PolyLine",
                                    etKeterangan.getText().toString(), 0, titiks);
                            btnSimpan.setEnabled(true);
                        }
                        break;
                    case 3:
                        titiks.add(latLng);
                        if (mMap != null) {
                            mMap.clear();
                        }
                        if (titiks.size() == 1) {
                            mMap.addMarker(new MarkerOptions().position(titiks.get(0)));
                        } else if (titiks.size() == 2) {
                            PolylineOptions pl = new PolylineOptions().color(Color.RED).width(10.0f);
                            for (int i = 0; i < titiks.size(); i++) {
                                mMap.addMarker(new MarkerOptions().position(titiks.get(i)));
                                pl.add(titiks.get(i));
                            }
                            mMap.addPolyline(pl);
                        } else if (titiks.size() > 2) {
                            PolygonOptions pg = new PolygonOptions().strokeColor(Color.BLUE).strokeWidth(10.0f).fillColor(Color.argb(20, 0, 255, 255));
                            for (int i = 0; i < titiks.size(); i++) {
                                mMap.addMarker(new MarkerOptions().position(titiks.get(i)));
                                pg.add(titiks.get(i));
                            }
                            pg.add(titiks.get(0));
                            mMap.addPolygon(pg);
                            objMS = new MarkerDanShape("Polygon",
                                    etKeterangan.getText().toString(),
                                    0, titiks);
                            btnSimpan.setEnabled(true);
                        }
                        break;
                }
            }
        });

    }
}