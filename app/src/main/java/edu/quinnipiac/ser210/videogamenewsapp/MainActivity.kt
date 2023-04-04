package edu.quinnipiac.ser210.videogamenewsapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textview.MaterialTextView

/**
 * @author Michael Ruocco, Omar Rosario
 * @date 4/3/2023
 *
 * MainActivity class that sets the functionalities throughout the entire application.
 * Enables the toolbar to handle navigation between fragments, changes the theme of the
 * application to dark mode, displays a pop-up describing how to use the app, and creates
 * a "Share Link" option in the material card view in the Article Fragment.
 */

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Finds the Navigation Controller and sets the toolbar to navigate between fragments.
         **/
        val materialToolbar = findViewById<MaterialToolbar>(R.id.materialToolbar)
        setSupportActionBar(materialToolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        findViewById<Toolbar>(R.id.materialToolbar).setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId)
        {
            /**
             * Changes the theme of the app to dark mode when the "Eye" icon is pressed.
             **/
            R.id.displayMode -> {
                val nightModeFlags = when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                    Configuration.UI_MODE_NIGHT_YES -> AppCompatDelegate.MODE_NIGHT_NO
                    Configuration.UI_MODE_NIGHT_NO -> AppCompatDelegate.MODE_NIGHT_YES
                    else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                }
                AppCompatDelegate.setDefaultNightMode(nightModeFlags)
                recreate()

                return true
            }

            /**
             * Displays a pop-up describing how to use the app when the "?" is pressed.
             **/
            R.id.helpPopup -> {
                val popupView = layoutInflater.inflate(R.layout.popup_help, null)
                val popupWindow = PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true)
                popupWindow.showAtLocation(findViewById(R.id.materialToolbar), Gravity.CENTER, 0, 0)

                popupView.setOnTouchListener { _, _ ->
                    popupWindow.dismiss()
                    true
                }
            }

            /**
             * Enables the user to copy the article's source link to share or search on a browser.
             **/
            R.id.shareLink -> {
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("link", findViewById<MaterialTextView>(R.id.link).text.toString() as CharSequence)
                clipboard.setPrimaryClip(clip)

                val snackbar = Snackbar.make(
                    findViewById(android.R.id.content),
                    "Link copied",
                    Snackbar.LENGTH_SHORT
                )

                snackbar.show()

                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }
}