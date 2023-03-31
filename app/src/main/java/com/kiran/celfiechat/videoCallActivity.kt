package com.kiran.celfiechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import com.facebook.react.modules.core.PermissionListener
import org.jitsi.meet.sdk.JitsiMeetActivityDelegate
import org.jitsi.meet.sdk.JitsiMeetActivityInterface
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetView

class videoCallActivity : FragmentActivity(), JitsiMeetActivityInterface {

     private var view : JitsiMeetView? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        JitsiMeetActivityDelegate.onActivityResult(this, requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        JitsiMeetActivityDelegate.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_call)

        val videoCalls = findViewById<FrameLayout>(R.id.videoCall)
        val callInt = intent
        val callId = callInt.getStringExtra("callId")

        view = JitsiMeetView(this)
        val options = JitsiMeetConferenceOptions.Builder()
            .setRoom("https://meet.jit.si/$callId")
            .build()
        view!!.join(options)
        videoCalls.addView(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        view!!.dispose()
        view = null
        JitsiMeetActivityDelegate.onHostDestroy(this)

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        JitsiMeetActivityDelegate.onNewIntent(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        JitsiMeetActivityDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun requestPermissions(p0: Array<out String>?, p1: Int, p2: PermissionListener?) {

    }

    override fun onResume() {
        super.onResume()
        JitsiMeetActivityDelegate.onHostResume(this)
    }

    override fun onStop() {
        super.onStop()
        JitsiMeetActivityDelegate.onHostPause(this)
    }
}