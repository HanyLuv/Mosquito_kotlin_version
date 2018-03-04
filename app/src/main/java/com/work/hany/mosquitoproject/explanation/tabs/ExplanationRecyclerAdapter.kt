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
 *
 * 으아아아으아아앙 맘에안들어 ㅠㅡㅠ
 */

class ExplanationRecyclerAdapter(var clickListener: ClickListener) : RecyclerView.Adapter<BaseViewHolder<Explanation>>() {

    private var items: List<Explanation> = ArrayList()

    interface ClickListener {
        fun onClick(view: View, position: Int)
    }

    override fun getItemViewType(position: Int): Int {
        when (items[position].type) {
            Type.HEADER -> return Type.HEADER.code
            Type.BEHAVIOR -> return Type.BEHAVIOR.code
            Type.SITUATION -> return Type.SITUATION.code
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Explanation> {
        var inflater = LayoutInflater.from(parent.context)
        //TODO : 해결
        if (viewType == Type.BEHAVIOR.code)
            return BehaviorViewHolder(inflater.inflate(R.layout.row_behavior_item, parent, false),clickListener) as BaseViewHolder<Explanation>
        else if (viewType == Type.SITUATION.code)
            return SituationViewHolder(inflater.inflate(R.layout.row_situation_item, parent, false)) as BaseViewHolder<Explanation>
        else
            return HeaderViewHolder(inflater.inflate(R.layout.row_header_item, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Explanation>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addAll(newItems: List<out Explanation>) {
        this.items = newItems
        this.notifyDataSetChanged()
    }


}

abstract class BaseViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}

private class BehaviorViewHolder(itemView: View, var clickListener: ExplanationRecyclerAdapter.ClickListener) : BaseViewHolder<Behavior>(itemView) {

    private var levelTitleView: TextView = itemView.findViewById(R.id.step_text_view)

    override fun bind(item: Behavior) {
        itemView.setOnClickListener {
            clickListener.onClick(itemView, adapterPosition)
        }

        var levelTitle = StringBuilder()
                .append(item.level)
                .append(itemView.context.getString(R.string.explanation_tab_behavior_item_step))
                .append(item.levelTitle)

        levelTitleView.text = levelTitle
    }

}

private class SituationViewHolder(itemView: View) : BaseViewHolder<Situation>(itemView) {
    override fun bind(item: Situation) {
    }

}

//ㅠㅡㅠ 고민해보자.
private class HeaderViewHolder(itemView: View) : BaseViewHolder<Explanation>(itemView) {
    override fun bind(item: Explanation) {}

}