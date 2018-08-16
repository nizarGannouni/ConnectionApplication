package com.gannouni.nizar.recyclerWithMVVM.Model

import java.io.Serializable

/**
 * Created by Nizar on 21/02/2018.
 */
class Country(val numCoutry: Int, var labelCountry: String, var capitalCountry: String, var areaCountry: Float, var population: Long) : Serializable {

    override fun toString(): String {
        return "Country(numCoutry=$numCoutry, labelCountry='$labelCountry', capitalCountry='$capitalCountry', areaCountry=$areaCountry)"
    }

}
