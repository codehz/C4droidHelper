package codehz.c4droidhelper.view

import android.content.Context
import android.os.Handler
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.ViewGroup
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import java.lang
import java.util.ArrayList
import kotlin.text.Regex

class ContentDataView(context: Context, attrs: AttributeSet?, defStyle: Int) :
        RecyclerView(context, attrs, defStyle) {
    val list = ArrayList<String>()

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null)

    init {
        setLayoutManager(LinearLayoutManager(context))
    }

    class ViewHolder(context: Context) : RecyclerView.ViewHolder(CardView(context)) {
        companion object {
            private val FORMAT = "<html><body style='padding: 0'>%s</body></html>"
        }

        val card = itemView!! as CardView
        val web = WebView(context)
        val handler = Handler()

        init {
            val lp = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            lp.setMargins(0, 0, 0,
                    (10 * context.getResources().getDisplayMetrics().density).toInt())
            card setLayoutParams lp
            card setRadius 0f
            web setBackgroundColor 0
            web.getSettings() setJavaScriptEnabled true
            web setVerticalScrollBarEnabled false
            web setWebViewClient object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    view loadUrl "javascript:alert(document.body.getBoundingClientRect().bottom" +
                            "document.body.getBoundingClientRect().top)"
                    super.onPageFinished(view, url)
                }
            }
            web setWebChromeClient object : WebChromeClient() {
                override fun onJsAlert(view: WebView,
                                       url: String?,
                                       message: String?,
                                       result: JsResult): Boolean {
                    if (message != null)
                        handler post {
                            web setLayoutParams FrameLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    (message.toFloat()
                                            * context.getResources().getDisplayMetrics().density)
                                            .toInt())
                        }
                    result.confirm()
                    return true
                }
            }
            card.addView(web, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT))
        }

        fun load(data: String?) = web.loadData(lang.String.format(FORMAT, data),
                "text/HTML", "utf-8")
    }

    fun setContent(content: String) {
        list.clear()
        for (item in content split Regex.fromLiteral("`-?"))
            list add item.replace("``?", "`").replace("`??", "?")
        this setAdapter object : RecyclerView.Adapter<ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder?
                    = ViewHolder(getContext())

            override fun getItemCount(): Int = list.size()

            override fun onBindViewHolder(holder: ViewHolder?, position: Int)
                    = holder?.load(list[position])
        }
    }
}
