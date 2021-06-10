package `in`.bitcode.fragment3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

open class FragmentUserDetails : Fragment() {
    lateinit var txtUsername: TextView
    lateinit var btnSetAndFinish: Button
    var username: String? = null

    open interface OnDataListener {
        fun onDataSet(data: String)
    }

    var onDataListener: OnDataListener? = null
        get() = field
        set(value) {
            field = value
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_user_details_layout, null)
        txtUsername = view.findViewById(R.id.txtUsername)
        btnSetAndFinish = view.findViewById(R.id.btnSetAndFinish)

        btnSetAndFinish.setOnClickListener(BtnSetAndFinishClickListener())

        view.setOnClickListener(View.OnClickListener { })

        //get arguments
        username = arguments?.getString("username")
        txtUsername.text = username

        return view
    }

    inner private class BtnSetAndFinishClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            if( onDataListener != null) {
                onDataListener?.onDataSet( "$username ${System.currentTimeMillis()}")
            }
            parentFragmentManager.beginTransaction()
                .remove(this@FragmentUserDetails)
                .commit()
        }
    }
}