package com.work.hany.mosquitoproject.explanation.tabs

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.work.hany.mosquitoproject.R
import com.work.hany.mosquitoproject.data.*
import jp.wasabeef.blurry.Blurry

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

class ExplanationRecyclerAdapter(private var clickListener: ClickListener) : RecyclerView.Adapter<BaseViewHolder<Explanation>>() {

    private var items: List<Explanation> = ArrayList()

    interface ClickListener {
        fun onClick(view: View, behavior: Behavior)
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            Type.HEADER -> Type.HEADER.code
            Type.BEHAVIOR -> Type.BEHAVIOR.code
            Type.SITUATION -> Type.SITUATION.code
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Explanation> {
        var inflater = LayoutInflater.from(parent.context)
        //TODO : 해결
        return when (viewType) {
            Type.BEHAVIOR.code -> BehaviorViewHolder(inflater.inflate(R.layout.row_behavior_item, parent, false),clickListener) as BaseViewHolder<Explanation>
            Type.SITUATION.code -> SituationViewHolder(inflater.inflate(R.layout.row_situation_item, parent, false)) as BaseViewHolder<Explanation>
            else -> HeaderViewHolder(inflater.inflate(R.layout.row_header_item, parent, false))
        }
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
    private var backgroundImageView: ImageView = itemView.findViewById(R.id.step_background_view)

    override fun bind(item: Behavior) {
        itemView.setOnClickListener {
            clickListener.onClick(itemView, item)
        }

        var levelTitle = StringBuilder()
                .append(adapterPosition)
                .append(itemView.context.getString(R.string.explanation_tab_behavior_item_step))
                .append(item.levelTitle)

        levelTitleView.text = levelTitle

        var pictureName = StringBuffer().append("stage_").append(adapterPosition).toString()
        var context = itemView.context

        val id = context.resources.getIdentifier(pictureName, "drawable", context.packageName)
        backgroundImageView.setImageResource(id)


        backgroundImageView.post {
            Blurry.with(context)
                    .sampling(1)
                    .async()
                    .animate(700)
                    .capture(backgroundImageView)
                    .into(backgroundImageView)
        }




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