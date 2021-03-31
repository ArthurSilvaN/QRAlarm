package cn.com.liaozp.alarmclock.utils.db

import android.media.RingtoneManager
import android.os.Parcelable
import cn.com.liaozp.alarmclock.AlockApp
import kotlinx.android.parcel.Parcelize
import org.xutils.db.annotation.Column
import org.xutils.db.annotation.Table



@Parcelize
@Table(name = "T_ALARM_CLOCK")
data class T_ALARM_CLOCK(
        @Column(name = "ID",isId = true)var ID:String="",
        @Column(name = "TIME")var TIME:String="",
        @Column(name = "REPEAT_DAY")var REPEAT_DAY:String="Never",
        @Column(name = "NOTE")var NOTE:String="",
        @Column(name = "SOUND")var SOUND:String=RingtoneManager.
                getActualDefaultRingtoneUri(
                        AlockApp.instance(),RingtoneManager.TYPE_RINGTONE).toString(),
        @Column(name = "UPDATE_TIME")var UPDATE_TIME:String="",
        @Column(name = "ACTIVE")var ACTIVE:String="1"
        ) : Parcelable{

}