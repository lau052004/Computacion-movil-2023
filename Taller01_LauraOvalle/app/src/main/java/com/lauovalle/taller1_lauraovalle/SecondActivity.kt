package com.lauovalle.taller1_lauraovalle

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lauovalle.taller1_lauraovalle.adapter.PaisesAdapter
import com.lauovalle.taller1_lauraovalle.databinding.ActivitySecondBinding
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    companion object {
        private const val COUNTRIES_FILE = "paises.json"
    }

    lateinit var countryList: ListView
    lateinit var countries: JSONArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.item_paises)
        //Se recibe el nombre de la región
        // Obtiene el parámetro de la actividad principal
        val regionName = intent.getStringExtra("region") ?: ""

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countriesArray: MutableList<paises> = ArrayList()

        try {
            val jsonFile = loadCountriesByJson()
            val countries = jsonFile.getJSONArray("Countries")

            // Itera a través de cada país en el array JSON "countries"
            for (i in 0 until countries.length()) {
                val country = countries.getJSONObject(i)
                val countryRegion = country.getString("Region")

                // Verifica si el país pertenece a la región seleccionada
                if (countryRegion == regionName) {
                    val countryName = country.getString("Name")
                    val countryAlpha2Code = country.getString("Alpha2Code")
                    val Alpha3Code = country.getString("Alpha3Code")
                    val NativeName = country.getString("NativeName")
                    val Region = country.getString("Region")
                    val SubRegion = country.getString("SubRegion")
                    val Latitude = country.getString("Latitude")
                    val Longitude = country.getString("Longitude")
                    val areaString = country.getString("Area")
                    val Area: Int = if (areaString != "null") areaString.toInt() else 0
                    val numericCodeString = country.getString("NumericCode")
                    val NumericCode: Int = if (numericCodeString != "null") numericCodeString.toInt() else 0
                    val NativeLanguage = country.getString("NativeLanguage")
                    val CurrencyCode = country.getString("CurrencyCode")
                    val CurrencyName = country.getString("CurrencyName")
                    val CurrencySymbol = country.getString("CurrencySymbol")
                    val Flag = country.getString("Flag")
                    val FlagPng = country.getString("FlagPng")

                    val nuevoPais = paises(
                        Name = countryName,
                        Alpha2Code = countryAlpha2Code,
                        Alpha3Code = Alpha3Code,
                        NativeName = NativeName,
                        Region = Region,
                        SubRegion = SubRegion,
                        Latitude = Latitude,
                        Longitude = Longitude,
                        Area = Area,
                        NumericCode = NumericCode,
                        NativeLanguage = NativeLanguage,
                        CurrencyCode = CurrencyCode,
                        CurrencyName = CurrencyName,
                        CurrencySymbol = CurrencySymbol,
                        Flag = Flag,
                        FlagPng = FlagPng
                    )
                    countriesArray.add(nuevoPais)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        initRecyclerView(countriesArray)
    }

    fun loadJSONFromAsset(assetName: String): String {
        val json: String
        json = try {
            val inputStream = this.assets.open(assetName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, charset("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            ""
        }
        return json
    }

    @Throws(JSONException::class)
    fun loadCountriesByJson(): JSONObject {
        return JSONObject(loadJSONFromAsset(COUNTRIES_FILE))
    }

    private fun initRecyclerView(countriesArray:List<paises>){
        binding.recyclerPaises.layoutManager = LinearLayoutManager(this)
        binding.recyclerPaises.adapter = PaisesAdapter(countriesArray)
    }
}