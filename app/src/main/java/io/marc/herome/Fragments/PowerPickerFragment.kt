package io.marc.herome.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import io.marc.herome.Activities.MainActivity

import io.marc.herome.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PowerPickerFragment.PowerPickerFragmentListener] interface
 * to handle interaction events.
 * Use the [PowerPickerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PowerPickerFragment : Fragment() {
    enum class Buttons {
        TURTLE,
        THOR,
        SUPERMAN,
        SPIDERMAN,
        CYCLOP,
        HULK
    }

    private var leftDrawables: ArrayList<Int> = arrayListOf(
            R.drawable.turtlepower,
            R.drawable.thorshammer,
            R.drawable.supermancrest,
            R.drawable.spiderweb,
            R.drawable.laservision,
            R.drawable.superstrength
            )
    private var ids: ArrayList<Int> = arrayListOf(
            R.id.ppicker_turtleBtn,
            R.id.ppicker_thorBtn,
            R.id.ppicker_supermanBtn,
            R.id.ppicker_spidermanBtn,
            R.id.ppicker_cyclopBtn,
            R.id.ppicker_hulkBtn
            )
    private var buttons: ArrayList<Button> = arrayListOf()
    private var backstoryBtn: Button? = null
    
    private var mListener: PowerPickerFragmentListener? = null
    private var mainPower: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mainPower = arguments.getInt(PowerPickerFragment.MAIN_POWER)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_power_picker, container, false) ?: return null

        buttons.clear()
        for (id in Buttons.values()) {
            buttons.add(view.findViewById(ids[id.ordinal]))
            buttons[id.ordinal].setOnClickListener { v ->
                onClick(v as Button, id.ordinal)
            }
        }

        backstoryBtn = view.findViewById(R.id.ppicker_showbStory)
        backstoryBtn?.setOnClickListener { v ->
            (activity as MainActivity).loadPowerPickerFragment(mainPower)
        }

        backstoryBtn?.isEnabled = false
        backstoryBtn?.background?.alpha = 128
        
        return view
    }

    fun onClick(button: Button, id: Int) {
        for (type in Buttons.values()) {
            buttons[type.ordinal].setCompoundDrawablesWithIntrinsicBounds(leftDrawables[type.ordinal], 0, 0, 0)
        }

        button.setCompoundDrawablesWithIntrinsicBounds(leftDrawables[id], 0, R.drawable.itemselected, 0)

        backstoryBtn?.isEnabled = true
        backstoryBtn?.background?.alpha = 255
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onPowerPickerFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is PowerPickerFragmentListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement PowerPickerFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface PowerPickerFragmentListener {
        // TODO: Update argument type and name
        fun onPowerPickerFragmentInteraction(uri: Uri)
    }

    companion object {
        private val MAIN_POWER = "mainpower"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment PowerPickerFragment.
         */
        fun newInstance(mainPower: Int): PowerPickerFragment {
            val fragment = PowerPickerFragment()
            val args = Bundle()
            args.putInt(PowerPickerFragment.MAIN_POWER, mainPower)
            fragment.arguments = args
            return fragment
        }
    }
}
