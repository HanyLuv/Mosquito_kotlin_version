package com.work.hany.mosquitoproject.data

/**
 * Created by hany on 2018. 3. 4..
 * ㅠㅠ 적당한 명칭이 떠오르지않는당...
 */

interface ExplanationData {
     var type: TabItemType
}


enum class TabItemType{
    BEHAVIOR, SITUATION
}