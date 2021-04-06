package umn.ac.id.week9_32864;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MahasiswaViewModel extends AndroidViewModel {
    private MahasiswaRepository mhsRepository;
    private LiveData<List<Mahasiswa>> daftarMahasiswa;

    public MahasiswaViewModel(@NonNull Application application) {
        super(application);
        mhsRepository = new MahasiswaRepository(application);
        daftarMahasiswa = mhsRepository.getDaftarMahasiswa();
    }

    LiveData<List<Mahasiswa>> getDaftarMahasiswa(){
        return this.daftarMahasiswa;
    }

    public void insert(Mahasiswa mhs){
        mhsRepository.insert(mhs);
    }

    public void deleteAll(){
        mhsRepository.deleteAll();
    }

    public void delete(Mahasiswa mhs){
        mhsRepository.delete(mhs);
    }

    public void update(Mahasiswa mhs){
        mhsRepository.update(mhs);
    }
}
