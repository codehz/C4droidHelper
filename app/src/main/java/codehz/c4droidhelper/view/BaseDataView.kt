package codehz.c4droidhelper.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import codehz.c4droidhelper.R
import codehz.c4droidhelper.model.DataSource
import codehz.c4droidhelper.model.Kii.Repo

open class BaseDataView(context: Context, source: DataSource<Repo>) : RecyclerView(context) {
    class ViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(
                    viewGroup.getContext()).inflate(R.layout.view_card, viewGroup, false)) {
        val titleView = TextView(viewGroup.getContext())
        val preview = TextView(viewGroup.getContext())

        init {
            val card: LinearLayout = (itemView findViewById R.id.content) as LinearLayout
            titleView setTextSize 20f
            titleView setTextColor 0xFF000000.toInt()
            card.addView(titleView, LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT))
            preview setTextColor 0x7F000000
            card.addView(preview, LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT))
        }

        fun setText(titleText: String?, previewText: String?) {
            titleView setText titleText
            preview setText previewText
        }
    }

    init {
        setLayoutManager(LinearLayoutManager(context))
        setAdapter(object : RecyclerView.Adapter<ViewHolder>() {
            init {
                source.callback = { exception ->
                    run {
                        if (exception != null) exception.printStackTrace()
                        else notifyDataSetChanged()
                    }
                }
            }

            override fun getItemCount(): Int = source.getCount()

            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder?
                    = ViewHolder(parent!!)

            override fun onBindViewHolder(holder: ViewHolder?, position: Int)
                    = holder?.setText(source[position].title, source[position].preview)
        })
    }
}
