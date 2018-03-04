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

class ExplanationRecyclerAdapter: RecyclerView.Adapter<BaseViewHolder<Explanation>>() {

    private var items: List<Explanation> = ArrayList()


    override fun getItemViewType(position: Int): Int {
        when (items[position].type){
            Type.HEADER -> return Type.HEADER.code
            Type.BEHAVIOR -> return Type.BEHAVIOR.code
            Type.SITUATION -> return Type.SITUATION.code
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Explanation> {
        var inflater = LayoutInflater.from(parent.context)
        //TODO : 해결
        if (viewType == Type.BEHAVIOR.code)
            return BehaviorViewHolder(inflater.inflate(R.layout.row_behavior_item, parent, false)) as BaseViewHolder<Explanation>
        else if (viewType == Type.SITUATION.code)
            return SituationViewHolder(inflater.inflate(R.layout.row_situation_item, parent,false)) as BaseViewHolder<Explanation>
        else
            return HeaderViewHolder(inflater.inflate(R.layout.row_header_item, parent,false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Explanation>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return  items.size
    }

    fun addAll(items: List<out Explanation>) {
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
        itemView.setOnClickListener {

        }

        levelTitleView.text = item.levelTitle
        levelView.text = item.level.toString()
    }

}

private class SituationViewHolder(itemView: View) : BaseViewHolder<Situation>(itemView) {
    override fun bind(item: Situation) {
    }

}

//ㅠㅡㅠ 고민해보자.
private class HeaderViewHolder(itemView: View) : BaseViewHolder<Explanation>(itemView) {
    override fun bind(item: Explanation) { }

}