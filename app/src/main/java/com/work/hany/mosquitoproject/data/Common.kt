package com.work.hany.mosquitoproject.data

class Common {
    companion object {
        var EXTRA_KEY = "behavior_detail"
        var EXTRA_KEY_TABMENU = "forecast_tab_menu_type"
    }

}

enum class TabMenu(var code: Int) {
    PERSONAL(1), PUBLIC(2)
}