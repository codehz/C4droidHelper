package codehz.c4droidhelper

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import codehz.c4droidhelper.view.ContentDataView

public class ContentActivity : AppCompatActivity() {
    var content: ContentDataView? = null
    var collapsingToolbarLayout: CollapsingToolbarLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this setContentView R.layout.activity_content
        content = (this findViewById R.id.content) as ContentDataView?
        collapsingToolbarLayout = (this findViewById R.id.collapsingToolbarLayout)
                as CollapsingToolbarLayout?
        val intent = getIntent()
        content?.setContent(intent getStringExtra "content")
        collapsingToolbarLayout?.setTitle(intent getStringExtra "title")
    }
}