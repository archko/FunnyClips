package com.victor.clips.ui.adapter

import android.content.Context
import android.graphics.Typeface
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.AdapterView
import com.victor.clips.R
import com.victor.clips.data.HomeItemInfo
import com.victor.clips.ui.holder.ContentViewHolder
import com.victor.clips.util.FontUtils
import com.victor.clips.util.ImageUtils
import kotlinx.android.synthetic.main.rv_category_detail_cell.view.*
import kotlinx.android.synthetic.main.rv_category_detail_cell.view.mTvTitle

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: CategoryDetailAdapter.java
 * Author: Victor
 * Date: 2018/9/28 14:52
 * Description: 
 * -----------------------------------------------------------------
 */
class CategoryDetailAdapter(context: Context, listener: AdapterView.OnItemClickListener): BaseRecycleAdapter<HomeItemInfo, RecyclerView.ViewHolder>(context,listener) {
    var fontStyle: Typeface? = null

    init {
        fontStyle = FontUtils.getTypeface(mContext?.assets!!);
    }

    override fun onCreateHeadVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return null
    }

    override fun onBindHeadVHolder(viewHolder: RecyclerView.ViewHolder, data: HomeItemInfo, position: Int) {
    }

    override fun onCreateContentVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContentViewHolder(mLayoutInflater!!.inflate(R.layout.rv_category_detail_cell, parent, false))
    }

    override fun onBindContentVHolder(viewHolder: RecyclerView.ViewHolder, data: HomeItemInfo, position: Int) {
        val contentViewHolder = viewHolder as ContentViewHolder
        contentViewHolder.itemView.mTvTitle.typeface = fontStyle;
        contentViewHolder.itemView.mTvTitle.setText(data.data?.title)
        ImageUtils.instance.loadImage(mContext!!,contentViewHolder.itemView.mIvCategoryDetailPoster, data.data?.cover?.feed)
        contentViewHolder.setOnItemClickListener(mOnItemClickListener)
    }

}