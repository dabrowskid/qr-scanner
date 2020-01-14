package pl.carbuddy.qr_scanner

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.budiyev.android.codescanner.CodeScanner

class QrScannerActivity: Activity() {

    private val mCodeScanner: CodeScanner? = null

    companion object {
        val REQUEST_TAKE_PHOTO_CAMERA_PERMISSION = 100
        val TOGGLE_FLASH = 200

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Peruna"


        // this paramter will make your HUAWEI phone works great!

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause();
    }


//    private fun requestCameraAccessIfNecessary(): Boolean {
//        val array = arrayOf(Manifest.permission.CAMERA)
//        if (ContextCompat
//                        .checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(this, array,
//                    REQUEST_TAKE_PHOTO_CAMERA_PERMISSION)
//            return true
//        }
//        return false
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,grantResults: IntArray) {
//        when (requestCode) {
//            REQUEST_TAKE_PHOTO_CAMERA_PERMISSION -> {
//                if (PermissionUtil.verifyPermissions(grantResults)) {
//                    scannerView.startCamera()
//                } else {
//                    finishWithError("PERMISSION_NOT_GRANTED")
//                }
//            }
//            else -> {
//                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//            }
//        }
//    }

}

object PermissionUtil {

    /**
     * Check that all given permissions have been granted by verifying that each entry in the
     * given array is of the value [PackageManager.PERMISSION_GRANTED].

     * @see Activity.onRequestPermissionsResult
     */
    fun verifyPermissions(grantResults: IntArray): Boolean {
        // At least one result must be checked.
        if (grantResults.size < 1) {
            return false
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (result in grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }
}