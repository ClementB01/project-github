package fr.appsolute.template.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import fr.appsolute.template.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolBar()
    }

    override fun onNavigateUp(): Boolean {
        // We just say to the activity that its back stack will manage by the NavController
        return findNavController(R.id.main_fragment_container).navigateUp()
    }

    /**
     * Init the ToolBar
     */
    private fun initToolBar() {
        setSupportActionBar(main_tool_bar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        main_tool_bar.setNavigationOnClickListener { onNavigateUp() }
    }
}
