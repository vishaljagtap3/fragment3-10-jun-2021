package `in`.bitcode.fragment3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class FragmentUser : Fragment() {

    lateinit var edtUsername : EditText
    lateinit var btnSendData : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_user_layout, null)
        edtUsername = view.findViewById(R.id.edtUsername)
        btnSendData = view.findViewById(R.id.btnSendData)

        btnSendData.setOnClickListener(BtnSendDataOnClickListener())

        return view
    }

    inner private class BtnSendDataOnClickListener : View.OnClickListener {
        override fun onClick(v: View?) {

            var fragmentUserDetails = FragmentUserDetails()

            //set up then input
            var params = Bundle()
            params.putString("username", edtUsername.text.toString())
            fragmentUserDetails.arguments = params

            //set up the listener
            fragmentUserDetails.onDataListener = MyOnDataListener()

            parentFragmentManager.beginTransaction()
                .add(R.id.mainContainer, fragmentUserDetails, null)
                .addToBackStack(null)
                .commit()

        }
    }

    inner class MyOnDataListener : FragmentUserDetails.OnDataListener {
        override fun onDataSet(data: String) {
            edtUsername.setText(data)
        }
    }
}