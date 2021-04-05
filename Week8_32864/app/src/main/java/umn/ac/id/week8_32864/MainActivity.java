package umn.ac.id.week8_32864;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rgJenis;
    private EditText etFileName;
    private EditText etText;
    private File tempDir;
    private File lokalDir;
    private File extDir;
    private File curDir;
    private Context context;
    private Button btnOpen;
    private static PopupMenu pilihFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rgJenis = (RadioGroup) findViewById(R.id.rgJenis);
        etFileName = (EditText) findViewById(R.id.etNamaFile);
        etText = (EditText) findViewById(R.id.etText);
        tempDir = getCacheDir();
        lokalDir = getFilesDir();

        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            extDir = getExternalFilesDir(null);
        } else {
            findViewById(R.id.rbExternal).setEnabled(false);
            extDir = null;
        }
        curDir = lokalDir;
        rgJenis.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String pilihan = ((RadioButton) findViewById(rgJenis.getCheckedRadioButtonId())).getText().toString();

                if(pilihan.equalsIgnoreCase("Temporary")) curDir = tempDir;
                else if(pilihan.equalsIgnoreCase("Internal")) curDir = lokalDir;
                else curDir = extDir;
            }
        });

        context = this;
        btnOpen = (Button) findViewById(R.id.btnOpen);
        pilihFile = new PopupMenu(context,btnOpen);
        pilihFile.getMenuInflater().inflate(R.menu.menu_kosong, pilihFile.getMenu());
        pilihFile.setOnMenuItemClickListener(
                new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        etFileName.setText(item.getTitle().toString());
                        etFileName.setText("");
                        etText.setText("");
                        return true;
                    }
                });

    }

    public void clearText(View view){
        etText.setText("");
        etFileName.setText("");
    }

    public void openFile(View view){
        File[] files = null;
        if(curDir != null) files = curDir.listFiles(); if(files != null) {
            int n = files.length; pilihFile.getMenu().clear();
            for(int i = 0; i < files.length; i++)
                pilihFile.getMenu().add(files[i].getName()); pilihFile.show();
            bacaFile();
        } else {
            Toast.makeText(context,"Ada masalah akses folder atau folder masih kosong",Toast.LENGTH_LONG).show();
        }
    }

    private void bacaFile(){
        if(etFileName.getText().toString().length()>0){
            File file = new File(curDir,etFileName.getText().toString());
            String isiFile = "";
            try{
                InputStream inStream = new FileInputStream(file); if(inStream != null){
                    InputStreamReader isReader = new
                            InputStreamReader(inStream);
                    BufferedReader bReader = new BufferedReader(isReader); String terimaString = "";
                    StringBuilder sb = new StringBuilder();
                    while( (terimaString = bReader.readLine()) != null){
                        sb.append(terimaString).append("\n"); }
                    inStream.close();
                    isiFile = sb.toString();
                    etText.setText(isiFile);
                }
            }catch (FileNotFoundException e){
                Toast.makeText(context,"File tidak Ditemukan", Toast.LENGTH_LONG).show();
            }catch (IOException e){
                Toast.makeText(context,"Error di I/O ", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void saveFile(View view){
        String nFile = etFileName.getText().toString();
        String isiText = etText.getText().toString();
        if(nFile.length() > 0 && isiText.length() > 0 && curDir != null){
            File file = new File(curDir,nFile);
            try{
                OutputStreamWriter writer = new OutputStreamWriter( new FileOutputStream(file));
                writer.write(isiText);
                writer.close();
                Toast.makeText(this,"Text sudah tersimpan",
                        Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e){
                Toast.makeText(this,"File tidak Ditemukan", Toast.LENGTH_LONG).show();
            } catch (IOException e){
                Toast.makeText(this,"Ada kesalahan I/O", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void deleteFile(View view) {
        if(etFileName.getText().toString().length() > 0) {
            boolean sukses = false;
            if( curDir != null && curDir == lokalDir){
                sukses = context.deleteFile(etFileName.getText() .toString());
            } else {
                sukses = new File(curDir, etFileName.getText().toString())
                        .delete();
            }
            if (sukses) Toast.makeText(context, "File berhasil dihapus",
                    Toast.LENGTH_LONG).show(); else Toast.makeText(context, "File GAGAL dihapus",
                    Toast.LENGTH_LONG).show(); etFileName.setText("");
            etText.setText("");
        }
    }

    public void keluarApp(View view){
        finishAffinity();
    }

    @Override
    protected void onDestroy(){ super.onDestroy();
        File[] tempFiles = tempDir.listFiles();

        for(File tempFile : tempFiles){
            if(tempFile.isFile()) tempFile.delete();
        }
    }

}