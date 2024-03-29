package com.victor.clips.ui.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.victor.clips.R
import com.victor.clips.data.CategoryInfo
import com.victor.clips.ui.holder.ContentViewHolder
import com.victor.clips.util.FontUtils
import kotlinx.android.synthetic.main.rv_category_cell.view.*

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2018-2028, by Victor, All rights reserved.
 * -----------------------------------------------------------------
 * File: CategoryAdapter.java
 * Author: Victor
 * Date: 2019/10/31 10:47
 * Description:
 * -----------------------------------------------------------------
 */
class CategoryAdapter(context: Context, listener: AdapterView.OnItemClickListener): BaseRecycleAdapter<CategoryInfo, RecyclerView.ViewHolder>(context,listener) {
    var fontStyle: Typeface? = null
    var currentPosition: Int = -1

    init {
        fontStyle = FontUtils.getTypeface(mContext?.assets!!);
    }
    override fun onCreateHeadVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return null
    }

    override fun onBindHeadVHolder(viewHolder: RecyclerView.ViewHolder, data: CategoryInfo, position: Int) {
    }

    override fun onCreateContentVHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContentViewHolder(mLayoutInflater!!.inflate(R.layout.rv_category_cell, parent, false))
    }

    override fun onBindContentVHolder(viewHolder: RecyclerView.ViewHolder, data: CategoryInfo, position: Int) {
        val contentViewHolder = viewHolder as ContentViewHolder

        contentViewHolder.itemView.mTvCategoryTitle.typeface = fontStyle;

        contentViewHolder.itemView.mTvCategoryTitle.setText(data.categoryName)
        contentViewHolder.itemView.mCivCategoryAvatar.setImageResource(data.categoryImgRes)

        if (currentPosition == position) {
            contentViewHolder.itemView.mIvCategoryChecked.visibility = View.VISIBLE
        } else {
            contentViewHolder.itemView.mIvCategoryChecked.visibility = View.GONE
        }

        contentViewHolder.setOnItemClickListener(mOnItemClickListener)
    }

}
 