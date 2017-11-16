package io.marc.herome.Fragments

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.Fragment
import android.widget.Button
import io.marc.herome.Activities.MainActivity


import io.marc.herome.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MainFragment.mainFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null


    enum class Buttons {
        GENETIC,
        BORN,
        ACCIDENT
    }

    private var leftDrawables: ArrayList<Int> = arrayListOf(R.drawable.atomic, R.drawable.rocket, R.drawable.lightning)
    private var ids: ArrayList<Int> = arrayListOf(R.id.main_geneticBtn, R.id.main_bornBtn, R.id.main_accidentBtn)
    private var buttons: ArrayList<Button> = arrayListOf()
    private var chooseBtn: Button? = null

    private var mListener: mainFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments.getString(ARG_PARAM1)
            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater?.inflate(R.layout.fragment_main, container, false) ?: return null

        buttons.clear()
        for (id in Buttons.values()) {
            println(id)
            buttons.add(view.findViewById(ids[id.ordinal]))
            buttons[id.ordinal].setOnClickListener { v ->
                onClick(v as Button, id.ordinal)
            }
        }

        chooseBtn = view.findViewById(R.id.main_choosePowerBtn)
        chooseBtn?.setOnClickListener { v ->
            (activity as MainActivity).loadPowerPickerFragment()
        }

        chooseBtn?.isEnabled = false
        chooseBtn?.background?.alpha = 128

        return view
    }

    fun onClick(button: Button, id: Int) {
        println(id)
        for (type in Buttons.values()) {
            buttons[type.ordinal].setCompoundDrawablesWithIntrinsicBounds(leftDrawables[type.ordinal], 0, 0, 0)
        }

        button.setCompoundDrawablesWithIntrinsicBounds(leftDrawables[id], 0, R.drawable.itemselected, 0)

        chooseBtn?.isEnabled = true
        chooseBtn?.background?.alpha = 255
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onMainFragmentInteraction(uri)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is mainFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement mainFragmentInteractionListener")
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
    interface mainFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onMainFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): MainFragment {
            val fragment = MainFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
