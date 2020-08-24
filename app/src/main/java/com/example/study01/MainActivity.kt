package com.example.study01

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ivBack: ImageView = findViewById(R.id.ivBack)
        val tvTemp: TextView = findViewById(R.id.tvTemp)
        val tvHumidity: TextView = findViewById(R.id.tvHumid)
        val tvWind: TextView = findViewById(R.id.tvWind)
        val tvClouds: TextView = findViewById(R.id.tvClouds)

        val requestQueue: RequestQueue = Volley.newRequestQueue(this)

        val url =
            "https://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=a3d42e970ae9acb9fccfdd1cf03d3d35&units=metric"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    val weatherId: Int =
                        response.getJSONArray("weather").getJSONObject(0).getInt("id")
                    val temp: Double = response.getJSONObject("main").getDouble("temp")
                    val humidity: Int = response.getJSONObject("main").getInt("humidity")
                    val windSpeed: Double = response.getJSONObject("wind").getDouble("speed")
                    val clouds: Int = response.getJSONObject("clouds").getInt("all")

                    tvTemp.text = temp.toInt().toString() + "°C"
                    tvHumidity.text = humidity.toString() + "%"
                    tvWind.text = windSpeed.toInt().toString() + "m/s"
                    tvClouds.text = clouds.toString() + "%"

                    if(weatherId > 199 && weatherId < 300) {
                        ivBack.setImageResource(R.drawable.stomy01)
                    }
                    else if(weatherId >= 300 && weatherId < 600) {
                        ivBack.setImageResource(R.drawable.rainy02)
                    }
                    else if(weatherId >= 600 && weatherId < 700) {
                        ivBack.setImageResource(R.drawable.snowy01)
                    }
                    else if(weatherId >= 700 && weatherId < 800) {
                        ivBack.setImageResource(R.drawable.cloudy02)
                    }
                    else if(weatherId == 800) {
                        ivBack.setImageResource(R.drawable.sunny01)
                    }
                    else {
                        ivBack.setImageResource(R.drawable.cloudy01)
                    }

                } catch (e: JSONException) {
                    Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }, Response.ErrorListener { error ->
                Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        )

        requestQueue.add(request)

    }
}