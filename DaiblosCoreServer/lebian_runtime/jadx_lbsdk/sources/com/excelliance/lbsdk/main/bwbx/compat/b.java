package com.excelliance.lbsdk.main.bwbx.compat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.excelliance.lbsdk.main.bwbx.BwbxUtil;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class b extends HashMap<String, HoleFileEntry> {
    private String a;
    private int b;
    private int c;
    private a d;
    private String e;
    private long f;
    private HashMap<Long, String> g;

    private class a extends SQLiteOpenHelper {
        public a(Context context, int i) {
            super(context, "lbcfg", (SQLiteDatabase.CursorFactory) null, i);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("create table if not exists hole_config('id'not null primary key,'name' TEXT not null , 'position' int, 'inflate' int, 'size' int, 'md5' TEXT, 'version' VARCHAR) ");
            sQLiteDatabase.execSQL("create table if not exists hole_v4('id'not null primary key,'name' TEXT not null , 'position' int, 'inflate' int, 'size' int, 'md5' TEXT, 'version' VARCHAR) ");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    public b(Context context, String str, boolean z) {
        this.a = z ? "hole_v4" : "hole_config";
        com.excelliance.lbsdk.main.g.b.c("HoleConfig", "TABLE_HOLE_CONFIG:" + this.a, new Object[0]);
        this.d = new a(context, 1);
        this.e = str;
        this.g = new HashMap<>();
        a();
    }

    private ContentValues a(String str) {
        HoleFileEntry holeFileEntry = get(str);
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", holeFileEntry.getId());
        contentValues.put("name", holeFileEntry.getName());
        contentValues.put("position", Long.valueOf(holeFileEntry.getPosition()));
        contentValues.put("inflate", Integer.valueOf(holeFileEntry.isInflate() ? 1 : 0));
        contentValues.put("version", this.e);
        contentValues.put("md5", holeFileEntry.getMd5());
        contentValues.put(BwbxUtil.ACTION_SIZE, Long.valueOf(holeFileEntry.getSize()));
        return contentValues;
    }

    private void a() {
        Cursor cursorRawQuery = this.d.getWritableDatabase().rawQuery("SELECT * FROM " + this.a + " where version glob " + this.e, null);
        while (cursorRawQuery.moveToNext()) {
            try {
                try {
                    String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("name"));
                    long j = cursorRawQuery.getLong(cursorRawQuery.getColumnIndexOrThrow("position"));
                    boolean z = cursorRawQuery.getInt(cursorRawQuery.getColumnIndexOrThrow("inflate")) != 0;
                    int i = cursorRawQuery.getInt(cursorRawQuery.getColumnIndexOrThrow(BwbxUtil.ACTION_SIZE));
                    String string2 = cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("md5"));
                    String string3 = cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow("version"));
                    if (z) {
                        this.b++;
                        this.f += (long) i;
                    }
                    this.c++;
                    HoleFileEntry holeFileEntry = new HoleFileEntry(string, j, i, string2, z, string3);
                    put(string, holeFileEntry);
                    if (!z) {
                        this.g.put(Long.valueOf(j), string);
                    }
                    if (com.excelliance.lbsdk.main.g.b.a()) {
                        com.excelliance.lbsdk.main.g.b.b("AssetCompat", "inflateInternal name:" + string + " entry:" + holeFileEntry, new Object[0]);
                    }
                } catch (Exception unused) {
                    com.excelliance.lbsdk.main.g.b.c("AssetCompat", "inflateInternal error version" + this.e + " data" + this, new Object[0]);
                }
            } catch (Throwable th) {
                cursorRawQuery.close();
                throw th;
            }
        }
        cursorRawQuery.close();
        if (com.excelliance.lbsdk.main.g.b.a()) {
            com.excelliance.lbsdk.main.g.b.b("AssetCompat", "inflateInternal version" + this.e + " data" + this, new Object[0]);
        }
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public void clear() {
        super.clear();
        this.c = 0;
        this.b = 0;
        this.f = 0L;
        if (isEmpty()) {
            return;
        }
        this.d.getWritableDatabase().execSQL("delete from " + this.a + " where version glob " + this.e);
        com.excelliance.lbsdk.main.g.b.c("AssetCompat", "delete success", new Object[0]);
    }

    public int getInflateCount() {
        return this.b;
    }

    public HashMap<Long, String> getPositionMap() {
        return this.g;
    }

    public long getSize() {
        return this.f;
    }

    public int getTotalCount() {
        return this.c;
    }

    public void inflateAll(HashMap<String, HoleFileEntry> map) {
        clear();
        putAll(map);
        SQLiteDatabase writableDatabase = this.d.getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            try {
                for (String str : keySet()) {
                    writableDatabase.insertWithOnConflict(this.a, null, a(str), 5);
                    if (get(str).isInflate()) {
                        this.b++;
                    } else {
                        this.g.put(Long.valueOf(map.get(str).getPosition()), str);
                    }
                    this.c++;
                }
                writableDatabase.setTransactionSuccessful();
            } catch (Exception e) {
                com.excelliance.lbsdk.main.g.b.c("AssetCompat", "inflateAll: " + e, new Object[0]);
            }
            writableDatabase.endTransaction();
            if (com.excelliance.lbsdk.main.g.b.a()) {
                com.excelliance.lbsdk.main.g.b.b("AssetCompat", "inflateAll: " + this, new Object[0]);
            }
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }
}
