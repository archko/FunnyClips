package com.victor.clips.ui

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import android.text.method.LinkMovementMethod
import android.view.View
import com.victor.clips.R
import com.victor.clips.ui.widget.ElasticDragDismissFrameLayout
import com.victor.clips.util.*
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : BaseActivity(),View.OnClickListener {

    var fontStyle: Typeface? = null

    companion object {
        fun  intentStart (activity: AppCompatActivity) {
            var intent = Intent(activity, AboutActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_about
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
        initData()
    }

    fun initialize () {
        setSupportActionBar(mAboutToolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        fontStyle = FontUtils.getTypeface(assets);

        mCtvVersion.typeface = fontStyle
        mTvDescription.typeface = fontStyle
        mTvGmail.typeface = fontStyle
        mTvDownloadApp.typeface = fontStyle
        mTvIssues.typeface = fontStyle
        mTvSupport.typeface = fontStyle

        mTvDownloadApp.movementMethod = LinkMovementMethod.getInstance()
        mTvGmail.movementMethod = LinkMovementMethod.getInstance()
        mTvIssues.movementMethod = LinkMovementMethod.getInstance()

        mFabGitHub.setOnClickListener(this)

        mIvAboutPoster.startAnimation(AnimUtil.topEnter())
        mNsvAbout.startAnimation(AnimUtil.bottomEnter())
    }

    fun initData (){
        mCtvVersion.setText(getString(R.string.current_version) + AppUtil.getAppVersionName(this))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.mFabGitHub -> {
                WebActivity.intentStart(this, getString(R.string.github), getString(R.string.github_url), false)
            }
        }
    }


}
