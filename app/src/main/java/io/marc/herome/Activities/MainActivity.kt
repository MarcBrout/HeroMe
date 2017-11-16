package io.marc.herome.Activities

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.marc.herome.Fragments.BackStoryFragment
import io.marc.herome.Fragments.MainFragment
import io.marc.herome.Fragments.PowerPickerFragment
import io.marc.herome.R

class MainActivity : AppCompatActivity(),
        MainFragment.mainFragmentInteractionListener,
        PowerPickerFragment.PowerPickerFragmentListener,
        BackStoryFragment.OnBackStoryFragmentListener
{
    private val primaryStrings: HashMap<Int, Int> = hashMapOf(
            Pair(0, R.string.aherome_accidentBtn),
            Pair(1, R.string.aherome_geneticBtn),
            Pair(2, R.string.aherome_bornBtn)
    )
    private val primaryDrawables: HashMap<Int, Int> = hashMapOf(
            Pair(0, R.drawable.atomic),
            Pair(1, R.drawable.rocket),
            Pair(2, R.drawable.lightning)
    )
    private val secondaryDrawables: HashMap<Int, Int> = hashMapOf(
            Pair(0, R.drawable.turtlepower),
            Pair(1, R.drawable.thorshammer),
            Pair(2, R.drawable.supermancrest),
            Pair(3, R.drawable.spiderweb),
            Pair(4, R.drawable.laservision),
            Pair(5, R.drawable.superstrength)
    )
    private val secondaryStrings: HashMap<Int, Int> = hashMapOf(
            Pair(0, R.string.aherome_turtle),
            Pair(1, R.string.aherome_lightning),
            Pair(2, R.string.aherome_superman),
            Pair(3, R.string.aherome_spiderman),
            Pair(4, R.string.aherome_cyclop),
            Pair(5, R.string.aherome_hulk)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, MainFragment.newInstance(), "mainFragment")
                    .commit()
        }
    }

    fun loadPowerPickerFragment(mainPower: Int) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, PowerPickerFragment.newInstance(mainPower), "powerPickFragment")
                .addToBackStack(null)
                .commit()
    }

    fun loadBackStoryFragment(mainPower: Int, secondPower: Int) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, BackStoryFragment.newInstance(mainPower, secondPower), "backStoryFragment")
                .addToBackStack(null)
                .commit()
    }

    fun getPrimaryPowerIds() = primaryDrawables
    fun getSecondaryPowersIds() = secondaryDrawables
    fun getPrimaryPowerStrings() = primaryStrings
    fun getSecondaryPowerStrings() = secondaryStrings

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
