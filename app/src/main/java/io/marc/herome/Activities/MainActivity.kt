package io.marc.herome.Activities

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.marc.herome.Fragments.BackStoryFragment
import io.marc.herome.Fragments.MainFragment
import io.marc.herome.Fragments.PowerPickerFragment
import io.marc.herome.R
import java.util.*
import android.content.Intent
import android.system.OsConstants.EXIT_SUCCESS
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity(),
        MainFragment.mainFragmentInteractionListener,
        PowerPickerFragment.PowerPickerFragmentListener,
        BackStoryFragment.OnBackStoryFragmentListener
{
    private val primaryStrings: ArrayList<Int> = arrayListOf(
            R.string.aherome_accidentBtn,
            R.string.aherome_geneticBtn,
            R.string.aherome_bornBtn
    )
    private val primaryDrawables: ArrayList<Int> = arrayListOf(
            R.drawable.atomic,
            R.drawable.rocket,
            R.drawable.lightning
    )
    private val secondaryDrawables: ArrayList<Int> = arrayListOf(
            R.drawable.turtlepower,
            R.drawable.thorshammer,
            R.drawable.supermancrest,
            R.drawable.spiderweb,
            R.drawable.laservision,
            R.drawable.superstrength
    )
    private val secondaryStrings: ArrayList<Int> = arrayListOf(
            R.string.aherome_turtle,
            R.string.aherome_lightning,
            R.string.aherome_superman,
            R.string.aherome_spiderman,
            R.string.aherome_cyclop,
            R.string.aherome_hulk
    )
    private val heroNames = arrayListOf(
            "teenager mutant ninja turtle",
            "thor",
            "superman",
            "spiderman",
            "cyclop",
            "hulk"
    )

    enum class Fragments {
        MAIN,
        POWERPICKER,
        BACKSTORY
    }
    private val fragments = arrayListOf("mainFragment", "powerPickerFragment", "backStoryFragment")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, MainFragment.newInstance(), null)
                    .addToBackStack(fragments[Fragments.MAIN.ordinal])
                    .commit()
        }
    }

    fun loadPowerPickerFragment(mainPower: Int) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, PowerPickerFragment.newInstance(mainPower), null)
                .addToBackStack(fragments[Fragments.POWERPICKER.ordinal])
                .commit()
    }

    fun loadBackStoryFragment(mainPower: Int, secondPower: Int) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, BackStoryFragment.newInstance(mainPower, secondPower), null)
                .addToBackStack(fragments[Fragments.BACKSTORY.ordinal])
                .commit()
    }

    fun getPrimaryPowerIds() = primaryDrawables
    fun getSecondaryPowersIds() = secondaryDrawables
    fun getPrimaryPowerStrings() = primaryStrings
    fun getSecondaryPowerStrings() = secondaryStrings
    fun getHeroNames() = heroNames

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.isNotEmpty() && supportFragmentManager.fragments.last() is BackStoryFragment) {
            supportFragmentManager
                    .popBackStackImmediate(fragments[Fragments.MAIN.ordinal], 0)
        } else if (supportFragmentManager.fragments.size > 1) {
            super.onBackPressed()
        } else {
            finishAndRemoveTask()
        }
    }

    override fun onPowerPickerFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMainFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackStoryFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
