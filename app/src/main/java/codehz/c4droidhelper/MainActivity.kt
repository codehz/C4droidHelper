package codehz.c4droidhelper

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.widget.Toolbar

public class MainActivity : BaseActivity() {
    private var toolbar:Toolbar? = null
    private var tabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar) as Toolbar?
        tabLayout = findViewById(R.id.tabLayout) as TabLayout?
        setSupportActionBar(toolbar)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true)
        tabLayout?.addTab(tabLayout?.newTab()?.setText(R.string.homepage))
        tabLayout?.addTab(tabLayout?.newTab()?.setText(R.string.explore))
        tabLayout?.addTab(tabLayout?.newTab()?.setText(R.string.category))
        tabLayout?.addTab(tabLayout?.newTab()?.setText(R.string.message))
    }
}