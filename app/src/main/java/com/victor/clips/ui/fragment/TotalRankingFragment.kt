package com.victor.clips.ui.fragment

import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import android.view.View
import android.widget.AdapterView
import com.victor.clips.R
import com.victor.clips.ui.MainActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.victor.clips.ui.VideoDetailActivity
import com.victor.clips.ui.adapter.RankingAdapter
import com.victor.clips.data.HomeItemInfo
import com.victor.clips.data.TrendingReq
import com.victor.clips.presenter.RankingPresenterImpl
import com.victor.clips.ui.adapter.ScaleInAnimatorAdapter
import com.victor.clips.util.DeviceUtils
import com.victor.clips.util.WebConfig
import com.victor.clips.ui.view.RankingView
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.fragment_total_ranking.*


/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: TotalRankingFragment.java
 * Author: Victor
 * Date: 2018/8/30 15:40
 * Description: 
 * -----------------------------------------------------------------
 */
class TotalRankingFragment : BaseFragment(),AdapterView.OnItemClickListener,RankingView,
        SwipeRefreshLayout.OnRefreshListener {

    var rankingAdapter: RankingAdapter? = null

    var rankingPresenter: RankingPresenterImpl? = null

    companion object {
        fun newInstance(): TotalRankingFragment {
            return newInstance(0)
        }
        fun newInstance(id: Int): TotalRankingFragment {
            val fragment = TotalRankingFragment()
            val bundle = Bundle()
            bundle.putInt(ID_KEY, id)
            fragment.setArguments(bundle)
            return fragment
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.fragment_total_ranking
    }
    override fun handleBackEvent(): Boolean {
        return false
    }
    override fun freshFragData() {
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialize()
        initData()
    }

    fun initialize () {
        (activity as MainActivity).toolbar.setTitle(getString(R.string.total_ranking))

        rankingPresenter = RankingPresenterImpl(this)

        //设置 进度条的颜色变化，最多可以设置4种颜色
        mSrlTotalRanking.setColorSchemeResources(android.R.color.holo_purple, android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSrlTotalRanking.setOnRefreshListener(this);

        mRvTotalRanking.setHasFixedSize(true)

        rankingAdapter = RankingAdapter(activity!!,this)
        rankingAdapter?.setHeaderVisible(false)
        rankingAdapter?.setFooterVisible(false)

        val animatorAdapter = ScaleInAnimatorAdapter(rankingAdapter!!,mRvTotalRanking)
        mRvTotalRanking.adapter = animatorAdapter


        mRvTotalRanking.addOnScrollListener((activity as MainActivity).OnScrollListener())

    }

    fun initData () {
        sendMonthlyRankingRequest()
    }

    override fun onRefresh() {
        sendMonthlyRankingRequest()
    }

    fun sendMonthlyRankingRequest () {
        rankingPresenter?.sendRequest(String.format(WebConfig.getRequestUrl(WebConfig.HOT_TOTAL_RANKING_URL),
                DeviceUtils.getPhoneModel()),null,null)
        mSrlTotalRanking.isRefreshing = true
    }

    override fun OnRanking(data: Any?, msg: String) {
        mSrlTotalRanking.isRefreshing = false
        var totalRankingReq = data!! as TrendingReq
        rankingAdapter?.clear()
        rankingAdapter?.add(totalRankingReq.itemList)
        rankingAdapter?.notifyDataSetChanged()
    }

    override fun onItemClick(parentView: AdapterView<*>?, view: View?, position: Int, id: Long) {
        VideoDetailActivity.intentStart(activity as AppCompatActivity,
                rankingAdapter?.getItem(position) as HomeItemInfo,
                view?.findViewById(R.id.mIvRankingPoster) as View,
                getString(R.string.transition_video_img))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rankingPresenter?.detachView()
        rankingPresenter = null
    }

}