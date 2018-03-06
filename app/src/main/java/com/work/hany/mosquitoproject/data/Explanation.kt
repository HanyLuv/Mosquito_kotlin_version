package com.work.hany.mosquitoproject.data

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

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

data class Behavior(override var type: Type, var levelTitle: String, var steps: List<Step>) : Explanation, Parcelable {
    constructor(source: Parcel) : this(
            Type.values()[source.readInt()],
            source.readString(),
            ArrayList<Step>().apply { source.readList(this, Step::class.java.classLoader) }
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(type.ordinal)
        writeString(levelTitle)
        writeList(steps)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Behavior> = object : Parcelable.Creator<Behavior> {
            override fun createFromParcel(source: Parcel): Behavior = Behavior(source)
            override fun newArray(size: Int): Array<Behavior?> = arrayOfNulls(size)
        }
    }
}

/** eg.
 * title : 하 (0 ~ 29.0) */
data class Step(var title: String,
                var defensiveBehaviorItems: List<String>,
                var activeBehaviorItems: List<String>,
                var publicBehaviorItems: List<String>) : Parcelable {

    constructor(source: Parcel) : this(
            source.readString(),
            source.createStringArrayList(),
            source.createStringArrayList(),
            source.createStringArrayList()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeStringList(defensiveBehaviorItems)
        writeStringList(activeBehaviorItems)
        writeStringList(publicBehaviorItems)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Step> = object : Parcelable.Creator<Step> {
            override fun createFromParcel(source: Parcel): Step = Step(source)
            override fun newArray(size: Int): Array<Step?> = arrayOfNulls(size)
        }
    }
}


data class Situation(override var type: Type,
                     var title: String,
                     var subTitle: String,
                     var subItems: List<String>) : Explanation

