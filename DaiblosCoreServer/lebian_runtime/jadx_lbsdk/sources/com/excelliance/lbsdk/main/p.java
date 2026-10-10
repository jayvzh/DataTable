package com.excelliance.lbsdk.main;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public interface p extends IInterface {

    public static abstract class a extends Binder implements p {
        public a() {
            attachInterface(this, "com.excelliance.lbsdk.main.IDownloadComponent");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.excelliance.lbsdk.main.IDownloadComponent");
                return true;
            }
            parcel.enforceInterface("com.excelliance.lbsdk.main.IDownloadComponent");
            a();
            parcel2.writeNoException();
            return true;
        }
    }

    void a();
}
