package com.arpaul.samplefilter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.navigation.fragment.findNavController
import com.arpaul.samplefilter.dummy.DummyContent

/**
 * A fragment representing a list of Items.
 */
class AccountFragment : Fragment() {

    private var columnCount = 1
    private var adapterAccountDet: MyAccountDetRecyclerViewAdapter ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootview = inflater.inflate(R.layout.fragment_account_list, container, false)

        val view = rootview.findViewById<RecyclerView>(R.id.list)
        // Set the adapter
        if (view is RecyclerView) {
            adapterAccountDet = MyAccountDetRecyclerViewAdapter(DummyContent.ITEMS)
            with(view) {
                val layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                view.adapter = adapterAccountDet
            }
        }
        if(adapterAccountDet === null)
            Toast.makeText(context, "adapter is null", Toast.LENGTH_SHORT).show()
        return rootview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<AppCompatImageButton>(R.id.imgBtnBack).setOnClickListener {
            findNavController().navigate(R.id.filter_dashboard_frag)
        }

        view.findViewById<Button>(R.id.btnFilter).setOnClickListener {
            adapterAccountDet.let {
                adapterAccountDet!!.refresh(DummyContent.ITEMS.filter { it.accountName.split(" ")[1].toInt() % 2 !== 0 })
            }
        }
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}