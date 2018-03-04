package com.work.hany.mosquitoproject.data

/**
 * Created by hany on 2018. 3. 4..
 * ㅠㅠ 적당한 명칭이 떠오르지않는당...
 */

interface Explanation {
     var type: Type
}

enum class Type(var code: Int) {
    BEHAVIOR(1), SITUATION(2), HEADER(3)
}

data class Behavior(override var type: Type,
                    var level: Int,
                    var levelTitle: String,
                    var levelInfoItems: List<String>,
                    var steps: List<Step>,
                    var defensiveBehaviorItems: List<String>,
                    var activeBehaviorItems: List<String>): Explanation

/** eg.
 * title : 하
 * sectionLevel : 0 ~ 29.0 */
data class Step(var title: String, var sectionLevel: String)


data class Situation(override var type: Type,
                     var title: String,
                     var subTitle: String,
                     var subItems: List<String>): Explanation
