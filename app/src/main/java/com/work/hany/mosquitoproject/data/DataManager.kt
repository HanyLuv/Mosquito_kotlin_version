package com.work.hany.mosquitoproject.data

/**
 * Created by hany on 2018. 3. 4..
 */
class DataManager {

    companion object {
        fun newInstance() = DataManager()
    }
    fun createBehaviorItems(): List<Behavior> {

        var behaviorItems: ArrayList<Behavior> = ArrayList()
        var levelInfoItems: List<String> = ArrayList()
        var steps: List<Step> = ArrayList()

        behaviorItems.add(Behavior(Type.HEADER,0,"-",levelInfoItems,steps,levelInfoItems,levelInfoItems))
        behaviorItems.add(Behavior(Type.BEHAVIOR,1,"쾌적",levelInfoItems,steps,levelInfoItems,levelInfoItems))
        behaviorItems.add(Behavior(Type.BEHAVIOR,2,"불쾌",levelInfoItems,steps,levelInfoItems,levelInfoItems))
        behaviorItems.add(Behavior(Type.BEHAVIOR,3,"답답",levelInfoItems,steps,levelInfoItems,levelInfoItems))
        behaviorItems.add(Behavior(Type.BEHAVIOR,4,"위험",levelInfoItems,steps,levelInfoItems,levelInfoItems))

        return behaviorItems
    }


    fun createSituationItems(): List<Situation> {

        var situationItems: ArrayList<Situation> = ArrayList()
        situationItems.add(Situation(Type.SITUATION,"타이틀","서브타이틀", ArrayList()))
        situationItems.add(Situation(Type.SITUATION,"타이틀","서브타이틀", ArrayList()))
        situationItems.add(Situation(Type.SITUATION,"타이틀","서브타이틀", ArrayList()))
        situationItems.add(Situation(Type.SITUATION,"타이틀","서브타이틀", ArrayList()))
        situationItems.add(Situation(Type.SITUATION,"타이틀","서브타이틀", ArrayList()))
        situationItems.add(Situation(Type.SITUATION,"타이틀","서브타이틀", ArrayList()))
        situationItems.add(Situation(Type.SITUATION,"타이틀","서브타이틀", ArrayList()))

        return situationItems
    }
}