package umn.ac.id.week9_32864;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetilActivity extends AppCompatActivity {
    private EditText etNim, etNama, etEmail, etNomorHp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil);
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etNomorHp = findViewById(R.id.etNomorHp);
        Intent intent = getIntent();
        if (intent.hasExtra("MAHASISWA")){
            Mahasiswa mhs = (Mahasiswa) intent.getSerializableExtra("MAHASISWA");
            etNim.setText(mhs.getNim());
            etNama.setText(mhs.getNama());
            etEmail.setText(mhs.getEmail());
            etNomorHp.setEnabled(false);
        } else{
            etNim.setEnabled(true);
        }
    }

    public void simpanData(View view){
        String mNIM = etNim.getText().toString();
        String mNama = etNama.getText().toString();
        String mEmail = etEmail.getText().toString();
        String mNoHp = etNomorHp.getText().toString();
        if (mNIM.length() <= 0 || mNama.length() <= 0 || mEmail.length() <= 0 || mNoHp.length() <= 0){
            Toast.makeText(this, "Semua harus Diisi", Toast.LENGTH_LONG).show();
        } else{
            Intent intentJawab = new Intent();
            Mahasiswa mhs = new Mahasiswa(mNIM, mNama, mEmail, mNoHp);
            intentJawab.putExtra("MAHASISWA", mhs);
            setResult(RESULT_OK, intentJawab);
            finish();
        }
    }
    public void batal(View view){
        Intent intentJawab = new Intent();
        setResult(RESULT_CANCELED, intentJawab);
        finish();
    }
}