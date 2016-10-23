package info.devexchanges.barcodescannermobilevisionapi;

import com.google.android.gms.vision.barcode.Barcode;

public interface OnScannedListener {

    void onScanned(Barcode barcode);
}
