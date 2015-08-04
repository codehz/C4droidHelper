package codehz.c4droidhelper

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import codehz.c4droidhelper.model.Kii.RepoSource
import codehz.c4droidhelper.view.BaseDataView

public class MainActivity : BaseActivity() {
    private var toolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private var homeView: View? = null
    private var findView: View? = null
    private var categoryView: View? = null
    private var messageView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this setContentView R.layout.activity_main

        setupFields()
        setupSupportActionBar()
        setupViewPager()
        setupTabLayout()
    }

    private fun setupFields() {
        toolbar = findViewById(R.id.toolbar) as Toolbar?
        tabLayout = findViewById(R.id.tabLayout) as TabLayout?
        viewPager = findViewById(R.id.viewPager) as ViewPager?
        homeView = BaseDataView(this, RepoSource)
        RepoSource.flush()
        findView = TextView(this)
        findView as TextView setText "ts1\n\n\n\n\n\n\n\n\n\nts2"
        categoryView = TextView(this)
        messageView = TextView(this)
    }

    private fun setupSupportActionBar() {
        this setSupportActionBar toolbar
        getSupportActionBar() setDisplayHomeAsUpEnabled true
    }

    private fun setupViewPager() {
        viewPager!! setAdapter object : PagerAdapter() {
            init {
                for (i in 0..3) {
                    getView(i) setLayoutParams
                            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT)
                    viewPager!! addView getView(i)
                }
            }

            fun getView(position: Int): View = when (position) {
                0 -> homeView
                1 -> findView
                2 -> categoryView
                3 -> messageView
                else -> null
            } ?: throw IllegalArgumentException()

            override fun instantiateItem(container: ViewGroup?, position: Int): View?
                    = getView(position)

            override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?)
                    = Unit

            override fun isViewFromObject(view: View?, `object`: Any?): Boolean
                    = view == `object`

            override fun getCount(): Int = 4
        }
    }

    private fun setupTabLayout() {
        tabLayout!! setupWithViewPager viewPager
        for (i in 0..3)
            tabLayout!! getTabAt i setIcon when (i) {
                0 -> R.drawable.home
                1 -> R.drawable.idea
                2 -> R.drawable.categorize
                3 -> R.drawable.message
                else -> 0
            }
    }
}