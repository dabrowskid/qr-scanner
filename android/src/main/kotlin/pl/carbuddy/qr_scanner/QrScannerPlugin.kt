package pl.carbuddy.qr_scanner

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.util.Log
import androidx.annotation.NonNull;
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugin.common.PluginRegistry.Registrar

/** QrScannerPlugin */
class QrScannerPlugin(val activity: Activity) : MethodCallHandler,
        PluginRegistry.ActivityResultListener {

    var result: Result? = null


    companion object {
        val RESULT_CODE = "pl.carbuddy.qr_scanner.scan_result"

        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "pl.carbuddy.qr_scanner")
            val qrScannerPlugin = QrScannerPlugin(registrar.activity())
            channel.setMethodCallHandler(qrScannerPlugin)
            registrar.addActivityResultListener(qrScannerPlugin)
        }
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "scanCode") {
            this.result = result
            showBarcodeView()
        } else {
            result.notImplemented()
        }
    }


    private fun showBarcodeView() {
        val intent = Intent(activity, CodeScannerActivity::class.java)
        activity.startActivityForResult(intent, 100)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            val code = data.getStringExtra(RESULT_CODE)
            code?.let { this.result?.success(code) }
            return true
        }
        return false
    }
}
