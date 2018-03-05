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


/** 모기 예보 발생 단계 데이터
 * @param levelTitle 1단계 쾌적
 * @param steps 하, 중, 상의 데이터들...
 * */

data class Behavior(override var type: Type,var levelTitle: String, var steps: List<Step>): Explanation
/** eg.
 * title : 하 (0 ~ 29.0) */
data class Step(var title: String, var defensiveBehaviorItems: List<String>, var activeBehaviorItems: List<String>)


data class Situation(override var type: Type,
                     var title: String,
                     var subTitle: String,
                     var subItems: List<String>): Explanation

