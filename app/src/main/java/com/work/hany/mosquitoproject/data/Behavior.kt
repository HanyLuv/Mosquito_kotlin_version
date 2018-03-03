package com.work.hany.mosquitoproject.data

/**
 * Created by hany on 2018. 3. 3..
 */
data class Behavior(override var type: TabItemType = TabItemType.BEHAVIOR, var level: Int,
                    var levelTitle: String,
                    var levelInfoItems: List<String>,
                    var steps: List<Step>,
                    var defensiveBehaviorItems: List<String>,
                    var activeBehaviorItems: List<String>) : ExplanationData
/** eg.
 * title : 하
 * sectionLevel : 0 ~ 29.0 */
data class Step(var title: String, var sectionLevel: String)