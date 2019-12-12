package com.article.app.utils

import android.app.Activity
import android.content.Intent
import android.os.Handler

/**
 * @author vikas.grover
 * Activity Switcher Class
 */
class ActivitySwitcher {

    companion object Singleton {

        private const val HANDLER_TIMEOUT: Long = 3000L // 3 seconds

        init {
            println("Singleton class invoked.")
        }


        fun switchActivity(activity: Activity, intent: Intent, withHandler: Boolean, isFinish: Boolean) {
            when (withHandler) {
                true -> Handler().postDelayed({
                    activity.startActivity(intent)
                    if (isFinish)
                        activity.finish()
                }, HANDLER_TIMEOUT)
                else -> {
                    activity.startActivity(intent)
                    if (isFinish)
                        activity.finish()
                }
            }
        }


        fun <T> switchActivity(activity: Activity, clazz: Class<T>, withHandler: Boolean, isFinish: Boolean) {
            val intent = Intent(activity, clazz)
            when (withHandler) {
                true -> Handler().postDelayed({
                    activity.startActivity(intent)
                    if (isFinish)
                        activity.finish()
                }, HANDLER_TIMEOUT)
                else -> {
                    activity.startActivity(intent)
                    if (isFinish)
                        activity.finish()
                }
            }
        }
    }
}
