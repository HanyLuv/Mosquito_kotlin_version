package com.work.hany.mosquitoproject.data

import android.content.Context
import com.work.hany.mosquitoproject.R


/**
 * Created by hany on 2018. 3. 4..
 * single tone
 */
class DataManager private constructor(){

    private object Holder {
        var INSTANCE = DataManager()
    }
//
    companion object {
        val instance: DataManager by lazy {
            Holder.INSTANCE
        }
    }
//
//    private fun getMosquitoStep(mqValue: String): String {
//        var mqStep = Key.STEP_NULL
//        if (TextUtils.isEmpty(mqValue)) {
//            return mqStep
//        }
//        val fMqValue = java.lang.Float.valueOf(mqValue)
//        if (fMqValue >= 0 && fMqValue <= 250) {
//            mqStep = Key.STEP_ONE
//        } else if (fMqValue >= 251 && fMqValue <= 500) {
//            mqStep = Key.STEP_TWO
//        } else if (fMqValue >= 501 && fMqValue <= 750) {
//            mqStep = Key.STEP_THREE
//        } else if (fMqValue >= 751) {
//            mqStep = Key.STEP_FOUR
//        }
//        return mqStep
//
//    }
//
    fun mosquitoLevel(levelTitle: String): String {
        return when (levelTitle) {
            "쾌적" -> "1"
            "관심" -> "2"
            "주의" -> "3"
            "불쾌" -> "4"
            else -> ""
        }

    }

    /***
     *
     *
    String STEP_ONE = "1단계(쾌적)";
    String STEP_TWO = "2단계(관심)";
    String STEP_THREE = "3단계(주의)";
    String STEP_FOUR = "4단계(불쾌)";
    String STEP_NULL = "NULL";
     */
    /** 모기 발생 단계를 체크한다.
     * 어디 적합한....  클래스가 어디일까? */


    fun mosquitoStage(mosquitoValue: Float): String {
        var step = "없음" //값이 안들어오는 경우 테스트 해야한다.
        when {
            mosquitoValue in 0..250 -> step = "쾌적"
            mosquitoValue in 251..500 -> step = "관심"
            mosquitoValue in 501..750 -> step = "주의"
            mosquitoValue >= 751 -> step = "불쾌"
        }
        return step
    }

    fun mosquitoStageIndex(mosquitoValue: Float): Int {
        // 0 하, 1 중, 2 상
        var index = 0
        when {
            mosquitoValue in 0.0..83.3 -> index = 0
            mosquitoValue in 500.1..583.3 -> index = 0
            mosquitoValue in 250.1..333.3 -> index = 0
            mosquitoValue in 750.1..833.3 -> index = 0

            mosquitoValue in 83.4..166.6 -> index = 1
            mosquitoValue in 333.4..416.6 -> index = 1
            mosquitoValue in 583.4..666.6 -> index = 1
            mosquitoValue in 833.4..916.6 -> index = 1

            mosquitoValue in 166.7..250.0 -> index = 2
            mosquitoValue in 416.7..500.0 -> index = 2
            mosquitoValue in 666.7..750.0 -> index = 2
            mosquitoValue >= 916.7 -> index = 2
        }
        return index
    }

    fun createBehaviorItems(context: Context): List<Behavior> {
        var resource = context.resources
        var resKey = arrayOf("above", "average", "below")

        var behaviorItems: ArrayList<Behavior> = ArrayList()
        behaviorItems.add(Behavior(Type.HEADER, "header", ArrayList()))

        var levelTitles = resource.getStringArray(R.array.behavior_levels).toCollection(ArrayList())
        var levelCount = 0
        for (title in levelTitles) {

            var strStepRes = StringBuffer().append("array/").append("behavior_").append(levelCount).append("_steps").toString()
            var stepsRes = resource.getIdentifier(strStepRes, null, context.packageName)
            var stepCategories = resource.getStringArray(stepsRes)

            var stepCount = 0
            var steps = ArrayList<Step>()
            stepCategories.forEach { stepCategory ->
                                                                                                            // 2임..
                var strDefensiveRes = StringBuffer().append("array/").append("behavior_").append(levelCount).append("_").append(resKey[stepCount]).append("_defensive_items").toString()
                var defensiveItemsRes = resource.getIdentifier(strDefensiveRes, null, context.packageName)
                var defensiveItems = resource.getStringArray(defensiveItemsRes).toCollection(ArrayList())

//                                                                                           / arrayOf("above", "below", "average")
                                                                                          "behavior_2_above_average_items"
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
            levelCount++
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