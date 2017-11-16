package io.marc.herome.Activities

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.marc.herome.Fragments.MainFragment
import io.marc.herome.Fragments.PowerPickerFragment
import io.marc.herome.R

class MainActivity : AppCompatActivity(),
        MainFragment.mainFragmentInteractionListener,
        PowerPickerFragment.PowerPickerFragmentListener
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, MainFragment.newInstance("1", "2"), "mainFragment")
                    .commit()
        }
    }

    fun loadPowerPickerFragment() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, PowerPickerFragment.newInstance("1", "2"), "powerPickFragment")
                .addToBackStack(null)
                .commit()
    }

    override fun onPowerPickerFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMainFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
