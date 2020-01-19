package pl.carbuddy.qr_scanner

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.ErrorCallback
import com.google.zxing.BarcodeFormat


class CodeScannerActivity : AppCompatActivity() {

    private var RC_PERMISSION = 10
    private var mCodeScanner: CodeScanner? = null
    private var mPermissionGranted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_scanner)


        mCodeScanner = CodeScanner(this, findViewById(R.id.scanner))
        mCodeScanner!!.formats = listOf(BarcodeFormat.AZTEC)
        mCodeScanner!!.autoFocusMode = AutoFocusMode.CONTINUOUS
        mCodeScanner!!.setDecodeCallback { result ->
            runOnUiThread {
                val output = Intent()
                output.putExtra(QrScannerPlugin.RESULT_CODE, result.text)
                setResult(Activity.RESULT_OK, output)
                finish()
            }
        }

        mCodeScanner!!.errorCallback = ErrorCallback { error: Exception? ->
            runOnUiThread {
                Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                mPermissionGranted = false
                requestPermissions(arrayOf(Manifest.permission.CAMERA), RC_PERMISSION)
            } else {
                mPermissionGranted = true
            }
        } else {
            mPermissionGranted = true
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RC_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mPermissionGranted = true
                mCodeScanner?.startPreview()
            } else {
                mPermissionGranted = false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (mPermissionGranted) {
            mCodeScanner?.startPreview()
        }
    }

    override fun onPause() {
        mCodeScanner?.releaseResources()
        super.onPause()
    }
}
