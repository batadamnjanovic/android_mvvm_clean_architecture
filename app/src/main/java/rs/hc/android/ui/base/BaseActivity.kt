package rs.hc.android.ui.base

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import rs.hc.android.R


/**
 * Base for all activities.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Handle progress bar visibility
        getViewModel().progressBarVisibilityObservable.observe(this, Observer { visible ->
            val progressBar = findViewById<FrameLayout>(R.id.progressBar)
            progressBar?.let {
                it.visibility = if (visible) View.VISIBLE else View.GONE
            }
        })

        // Show error
        getViewModel().errorObservable.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    override fun setContentView(layoutResID: Int) {
        // Inflate content into the base view
        val baseView = View.inflate(this, R.layout.z__layout_base, null)
        val contentView = View.inflate(this, layoutResID, null)
        baseView.findViewById<FrameLayout>(R.id.content).addView(contentView)

        super.setContentView(baseView)
    }

    abstract fun getViewModel(): IBaseViewModel

}