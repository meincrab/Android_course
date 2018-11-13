package fi.jamk.k8760.harkka

import android.app.Activity
import android.content.Context
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.viewpagerindicator.CirclePageIndicator
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val myImageList = arrayListOf<String>("https://pp.userapi.com/c852124/v852124053/e4c3/5HmuzL7Tv-8.jpg", "https://pp.userapi.com/c852124/v852124053/e4ca/ciuoC9PJ8Ag.jpg", "https://pp.userapi.com/c852124/v852124053/e4d1/rbN8K7IlILA.jpg")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        download.setOnClickListener()
        {
            SaveImage(this).execute(myImageList[currentPage])
            var toast: Toast = Toast(this)
            toast.createToast(this, "Image is donwloaded!")
        }

    }

    private fun init() {

        mPager = findViewById(R.id.pager) as ViewPager
        mPager!!.adapter = SlideAdapter(this@MainActivity, myImageList!!)

        val indicator = findViewById(R.id.indicator) as CirclePageIndicator
        indicator.setViewPager(mPager)

        val density = resources.displayMetrics.density
        //Set circle indicator radius
        indicator.setRadius(5 * density)

        NUM_PAGES = myImageList!!.size

        // Pager listener over indicator
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                currentPage = position
            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {
            }

            override fun onPageScrollStateChanged(pos: Int) {
            }
        })

    }

    fun Toast.createToast(context: Context, message:String){
        val inflater:LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        /*first parameter is the layout you made
        second parameter is the root view in that xml
         */
        val layout = inflater.inflate(R.layout.toast_layout, (context as Activity).findViewById<ViewGroup>(R.id.custom_toast_container))

        layout.findViewById<TextView>(R.id.text).text = message
        setGravity(Gravity.CENTER_VERTICAL, 0, 0)
        setDuration(Toast.LENGTH_SHORT);
        setView(layout);
        show()
    }

    companion object {

        private var mPager: ViewPager? = null
        private var currentPage = 0
        private var NUM_PAGES = 0
    }

}
