package info.devexchanges.barcodescannermobilevisionapi;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class PhotoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        View btnPhotoScan = findViewById(R.id.photo_scan);
        final ImageView imageView = (ImageView) findViewById(R.id.image);
        final TextView textView = (TextView) findViewById(R.id.qr_code_content);
        btnPhotoScan.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                try {
                    Bitmap myQRCode = BitmapFactory.decodeStream(getAssets().open("qr_code.png"));
                    BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(PhotoActivity.this)
                            .setBarcodeFormats(Barcode.QR_CODE)
                            .build();

                    Frame frame = new Frame.Builder().setBitmap(myQRCode).build();
                    SparseArray<Barcode> barcodes = barcodeDetector.detect(frame);

                    // Check if at least one barcode was detected
                    if (barcodes.size() != 0) {
                        // Display the QR code's message
                        textView.setText("QR CODE Data: " + barcodes.valueAt(0).displayValue);
                        //Display QR code image to ImageView
                        imageView.setImageBitmap(myQRCode);
                    } else {
                        textView.setText("No QR Code found!");
                        textView.setTextColor(Color.RED);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
