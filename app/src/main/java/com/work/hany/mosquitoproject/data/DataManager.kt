package com.work.hany.mosquitoproject.data

/**
 * Created by hany on 2018. 3. 4..
 */
class DataManager {
    fun createBehaviorItems(): List<Behavior> {

        var behaviorItems: ArrayList<Behavior> = ArrayList()
        var levelInfoItems: List<String> = ArrayList()
        var steps: List<Step> = ArrayList()

        behaviorItems.add(Behavior(TabItemType.BEHAVIOR,1,"쾌적",levelInfoItems,steps,levelInfoItems,levelInfoItems))
        behaviorItems.add(Behavior(TabItemType.BEHAVIOR,2,"불쾌",levelInfoItems,steps,levelInfoItems,levelInfoItems))
        behaviorItems.add(Behavior(TabItemType.BEHAVIOR,3,"답답",levelInfoItems,steps,levelInfoItems,levelInfoItems))
        behaviorItems.add(Behavior(TabItemType.BEHAVIOR,4,"위험",levelInfoItems,steps,levelInfoItems,levelInfoItems))

        return behaviorItems
    }


    fun createSituationItems(): List<Situation> {

        var situationItems: ArrayList<Situation> = ArrayList()

        return situationItems
    }
}