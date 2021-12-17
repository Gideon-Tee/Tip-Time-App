package eu.tutorials.tip

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eu.tutorials.tip.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
// The binding view helps to make reference to views easily without making any extra variables in the case of findViewById
    /* To use the binding view, include buildFeatures in build.gradle(app)...like this,
     * buildFeatures {
     *      viewBinding true
     * }

     */
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener{ calculateTip()}
    }

    private fun calculateTip() {
        val stringInTextField = binding.inputServiceCost.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        // The null is to help the application not to crush if the user presses the button without an input in the editText
        if (cost == null) {
            return
        }

        val tipPercentage = when(binding.radioGroup.checkedRadioButtonId) {
            R.id.tip_amazing -> 0.20
            R.id.tip_good -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost

        if(binding.switchRoundUp.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance()

        binding.tvResult.text = "Tip Amount: $${tip}"

    }
}