package pl.edu.pwr.labx.i251937.sensorexplorer

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text.text = "No sensor selected."

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        var sensorList: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL).toList()
        var listViewItems = arrayOfNulls<String>(sensorList.size)

        for (i in sensorList.indices) {
            listViewItems[i] = sensorList[i].name
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listViewItems)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as String
            var sensorTextValues: String = ""

            if (sensorList[position] != null){
                if ( sensorList[position].name != null ){
                    sensorTextValues += "Name: " + sensorList[position].name.toString() + "\n"
                }
                if (sensorList[position].vendor != null)
                    sensorTextValues += "Vendor: " + sensorList[position].vendor.toString() + " "
            }
            text.text = sensorTextValues
        }


    }
}
