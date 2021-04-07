package umn.ac.id.week9_32864;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Mahasiswa.class}, version = 1, exportSchema = false)
public abstract class MahasiswaRoomDatabase extends RoomDatabase {

    public abstract MahasiswaDAO daoMahasiswa();
    private static MahasiswaRoomDatabase INSTANCE;
    static MahasiswaRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MahasiswaRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder( context.getApplicationContext(), MahasiswaRoomDatabase.class, "dbMahasiswa") .addCallback(sRoomDatabaseCallback) .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}