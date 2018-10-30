package com.zj.recyclerview.direction

import android.content.Context
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration

/**
 *
 * CreateTime:2018/10/30  11:47
 * @author 郑炯
 * @version 1.0
 */
class CustomRecyclerView(context: Context, attrs: AttributeSet) : RecyclerView(context, attrs) {
    var isOnLoadMoreProcess = false
    var mListener: Listener? = null

    interface Listener {
        fun loadMore()
    }

    val TOUCHSLOP = ViewConfiguration.getTouchSlop()

    var lastMoveX = 0F
    var lastMoveY = 0F
    override fun onTouchEvent(e: MotionEvent): Boolean {
        val onTouchEvent = super.onTouchEvent(e)
        println("onTouchEvent=$onTouchEvent")
        when (e.action) {

            MotionEvent.ACTION_MOVE -> {
                val offsetX = Math.abs(e.x - lastMoveX)
                val offsetY = Math.abs(e.y - lastMoveY)
                println("lastMoveX=$lastMoveX  ,e.x=${e.x}")
                val itemCount = adapter?.itemCount

                val lastPosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                if (offsetX > TOUCHSLOP
                        && offsetX > offsetY * 2
                        && !isOnLoadMoreProcess
                        && lastPosition == itemCount!!.minus(1)
                        && !canScrollHorizontally(ViewCompat.SCROLL_AXIS_HORIZONTAL)) {
                    println("向左滑动")
                    mListener?.loadMore()
                    isOnLoadMoreProcess = true
                }
                lastMoveX = e.x
                lastMoveY = e.y
            }
            else -> {
                lastMoveX = 0F
                lastMoveY = 0F
            }
        }
        return onTouchEvent
    }

    var lastMotionEvent: MotionEvent? = null
    /*override fun onInterceptTouchEvent(e: MotionEvent): Boolean {

        if (MotionEvent.ACTION_MOVE == e.action) {
            if (lastMotionEvent != null) {
                if (lastMotionEvent!!.x - e.x > TOUCHSLOP) {
                    println("向左滑...")
                    return true;
                }
                if (e.x - lastMotionEvent!!.x > TOUCHSLOP) {
                    println("向右滑...")
                    return true;
                }
            }
            lastMotionEvent = e
        }
        val onSuperInterceptTouchEvent = super.onInterceptTouchEvent(e)
        println("onInterceptTouchEvent gestureDetector=$onTouchEvent ,onSuperInterceptTouchEvent=$onSuperInterceptTouchEvent")
        return onSuperInterceptTouchEvent
    }*/

    fun loadMoreEnd() {
        isOnLoadMoreProcess = false
    }
}