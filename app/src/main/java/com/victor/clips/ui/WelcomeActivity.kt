package com.victor.clips.ui

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Message
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.victor.clips.R
import com.victor.clips.util.AppUtil
import com.victor.clips.util.Constant.Msg.Companion.ENTER_HAPPY_COAST
import com.victor.clips.util.FontUtils
import kotlinx.android.synthetic.main.activity_welcome.*
import org.victor.khttp.library.util.MainHandler


class WelcomeActivity : BaseActivity(),MainHandler.OnMainHandlerImpl {
    val delayMillis: Long = 1000;

    var fontStyle: Typeface? = null

    override fun handleMainMessage(message: Message?) {
        when (message?.what) {
            ENTER_HAPPY_COAST -> {
                MainActivity.intentStart(this)
                finish()
            }
        }
    }

    companion object {
        fun  intentStart (activity: AppCompatActivity) {
            activity.startActivity(Intent(activity, WelcomeActivity::class.java))
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_welcome
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun initialize () {
        MainHandler.get().register(this)

        fontStyle = FontUtils.getTypeface(assets);
        mTvAppName.typeface = fontStyle
        mTvVersion.typeface = fontStyle

        mTvAppName.setText(AppUtil.getAppName(this))
        mTvVersion.setText(getString(R.string.current_version) + AppUtil.getAppVersionName(this))
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_welcome)
        mClWelcome.startAnimation(animation)
        MainHandler.get().sendEmptyMessageDelayed(ENTER_HAPPY_COAST,delayMillis)
    }

    override fun onDestroy() {
        super.onDestroy()
        MainHandler.get().unregister(this)
    }

}
