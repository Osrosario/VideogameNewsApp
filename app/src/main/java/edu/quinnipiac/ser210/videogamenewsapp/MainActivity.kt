package edu.quinnipiac.ser210.videogamenewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.graph = navController.navInflater.inflate(R.navigation.nav_graph)

        setSupportActionBar(findViewById(R.id.materialToolbar))
        val config = AppBarConfiguration(navController.graph)
        findViewById<Toolbar>(R.id.materialToolbar).setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        return super.onCreateOptionsMenu(menu)
    }
}
