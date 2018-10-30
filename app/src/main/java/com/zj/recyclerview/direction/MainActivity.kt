package com.zj.recyclerview.direction

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
 * CreateTime:2018/10/30  11:34
 * @author 郑炯
 * @version 1.0
 */
class MainActivity : AppCompatActivity() {
    lateinit var itemAdapter: ItemAdapter
    var lastPosition = 5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemAdapter = ItemAdapter(mutableListOf("1", "2", "3", "4", "5"))

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        PagerSnapHelper().attachToRecyclerView(recyclerView)
        recyclerView.mListener = object : CustomRecyclerView.Listener {
            override fun loadMore() {
                val addLists = mutableListOf<String>()
                for (x in 1..5) {
                    addLists.add((lastPosition + x).toString())
                }
                lastPosition += 5
                itemAdapter.items.addAll(addLists)
                itemAdapter.notifyDataSetChanged()

                Toast.makeText(this@MainActivity, "到底部了, 加载更多数据...", Toast.LENGTH_SHORT).show()

                recyclerView.postDelayed({
                    recyclerView.loadMoreEnd()
                }, 800)
            }
        }

        recyclerView.adapter = itemAdapter


    }
}
