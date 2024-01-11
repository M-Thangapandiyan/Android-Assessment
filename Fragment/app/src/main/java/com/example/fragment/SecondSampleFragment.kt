import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fragment.DataListener
import com.example.fragment.R
import com.example.fragment.SampleFragment

class SecondSampleFragment : Fragment(), SampleFragment.DataPassListener {

    private lateinit var editText: EditText
    private lateinit var textView: TextView
    private var dataPassListener: DataListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second_sample, container, false)
        editText = view.findViewById(R.id.etEdit)
        textView = view.findViewById(R.id.tvText)

        val btnPassData = view.findViewById<Button>(R.id.btnButton)
        btnPassData.setOnClickListener {
            val data = editText.text.toString()
            dataPassListener?.onListener(data)
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPassListener = context as DataListener
    }

    override fun firstFragmentListener(data: String) {
        editText.setText(data)
    }
}
