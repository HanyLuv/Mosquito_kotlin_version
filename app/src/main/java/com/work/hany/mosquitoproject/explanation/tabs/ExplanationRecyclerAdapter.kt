package com.work.hany.mosquitoproject.explanation.tabs

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.work.hany.mosquitoproject.R
import com.work.hany.mosquitoproject.data.*

/**
 *
 * Created by hany on 2018. 3. 2..
 *
 * Video type X
 * Situation type
 * Behavior type
 */

class ExplanationRecyclerAdapter: RecyclerView.Adapter<BaseViewHolder<ExplanationData>>() {

    private var ITEM_TYPE_BEHAVIOR = 1
    private var ITEM_TYPE_SITUATION = 2


    private var items: List<ExplanationData> = ArrayList()


    override fun getItemViewType(position: Int): Int {
        return if (items[position].type == TabItemType.BEHAVIOR) ITEM_TYPE_BEHAVIOR else ITEM_TYPE_SITUATION
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ExplanationData> {
        if (viewType == ITEM_TYPE_BEHAVIOR) {
            return BehaviorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_behavior_item, parent,false)) as BaseViewHolder<ExplanationData>
        } else {
            return SituationViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_situation_item, parent)) as BaseViewHolder<ExplanationData>
        }

    }

    override fun onBindViewHolder(holder: BaseViewHolder<ExplanationData>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return  items.size
    }

    fun addAll(items: List<out ExplanationData>) {
        this.items = items
        this.notifyDataSetChanged()
    }


}

abstract class BaseViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)

}

private class BehaviorViewHolder(itemView: View) : BaseViewHolder<Behavior>(itemView) {

    private var levelTitleView: TextView = itemView.findViewById(R.id.title_text_view)
    private var levelView: TextView = itemView.findViewById(R.id.level_text_view)

    override fun bind(item: Behavior) {

        levelTitleView.text = item.levelTitle
        levelView.text = item.level.toString()
    }

}


private class SituationViewHolder(itemView: View) : BaseViewHolder<Situation>(itemView) {
    override fun bind(item: Situation) {
    }

}

/** TODO: 추후에 고민해볼것. adapterview에 MVP적용 방법..*/
//interface AdapterContract {
//    interface View {
//        fun refresh()
//    }
//
//    interface Model {
//        fun getSize(): Int
//        fun addAll(items: List<out ExplanationData>)
//    }
//
//}