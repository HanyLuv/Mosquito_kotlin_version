package com.work.hany.mosquitoproject.data

import android.content.Context
import com.work.hany.mosquitoproject.R
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by hany on 2018. 3. 4..
 */
class DataManager {
//
//    companion object {
//        fun newInstance() = DataManager()
//    }

    fun createBehaviorItems(context: Context): List<Behavior> {
        var resource = context.resources
        var resKey = arrayOf("average", "below", "above")

        var behaviorItems: ArrayList<Behavior> = ArrayList()
        behaviorItems.add(Behavior(Type.HEADER, "header", ArrayList()))

        var levelTitles = resource.getStringArray(R.array.behavior_levels).toCollection(ArrayList())
        var levelCount = 1
        for (title in levelTitles) {

            var strStepRes = StringBuffer().append("array/").append("behavior_").append(levelCount).append("_steps").toString()
            var stepsRes = resource.getIdentifier(strStepRes, null, context.packageName)
            var stepCategoris = resource.getStringArray(stepsRes)

            var stepCount = 0
            var steps = ArrayList<Step>()
            for (stepCategory in stepCategoris) {

                var strDefensiveRes = StringBuffer().append("array/").append("behavior_").append(levelCount).append("_").append(resKey[stepCount]).append("_defensive_items").toString()
                var defensiveItemsRes = resource.getIdentifier(strDefensiveRes, null, context.packageName)
                var defensiveItems = resource.getStringArray(defensiveItemsRes).toCollection(ArrayList())

                var strActiveRes = StringBuffer().append("array/").append("behavior_").append(levelCount).append("_").append(resKey[stepCount]).append("_active_items").toString()
                var activeItemsRes = resource.getIdentifier(strActiveRes, null, context.packageName)
                var activeItems = resource.getStringArray(activeItemsRes).toCollection(ArrayList())

                var strPublicRes = StringBuffer().append("array/").append("behavior_").append(levelCount).append("_").append(resKey[stepCount]).append("_public_items").toString()
                var publicItemsRes = resource.getIdentifier(strPublicRes, null, context.packageName)
                var publicItems = resource.getStringArray(publicItemsRes).toCollection(ArrayList())


                stepCount++
                steps.add(Step(stepCategory, defensiveItems, activeItems, publicItems))
            }

            behaviorItems.add(Behavior(Type.BEHAVIOR, title, steps))
        }

        return behaviorItems
    }


    fun createSituationItems(context: Context): List<Situation> {

        var situationItems: ArrayList<Situation> = ArrayList()
        situationItems.add(Situation(Type.SITUATION, "타이틀", "서브타이틀", ArrayList()))
        situationItems.add(Situation(Type.SITUATION, "타이틀", "서브타이틀", ArrayList()))
        situationItems.add(Situation(Type.SITUATION, "타이틀", "서브타이틀", ArrayList()))
        situationItems.add(Situation(Type.SITUATION, "타이틀", "서브타이틀", ArrayList()))
        situationItems.add(Situation(Type.SITUATION, "타이틀", "서브타이틀", ArrayList()))
        situationItems.add(Situation(Type.SITUATION, "타이틀", "서브타이틀", ArrayList()))
        situationItems.add(Situation(Type.SITUATION, "타이틀", "서브타이틀", ArrayList()))

        return situationItems
    }
}