package com.example.opeyemiabdulsalam.utils

import com.example.opeyemiabdulsalam.data.CarOwner
import com.example.opeyemiabdulsalam.data.Filter
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UtilsKtTest {
    var carOwners: List<CarOwner> = listOf()

    @Before
    fun fillCarOwnersListWithData(){
        carOwners = listOf<CarOwner>(
                CarOwner(100, "Jayeola", "Olayemi","jayeola@gmail.com",
                    "Nigeria", "Nissan", 2015,  "Blue",
                    "Male","Software developer", "An awesome human being"),

                CarOwner(65449,"Timmy","Orniz", "tornizcg@xinhuanet.com",
                    "Peru", "Honda", 2003, "Aquamarine",
                    "Female", "Human Resources Manager",
                    "Nulla justo. Aliquam quis turpis eget elit sodales scelerisque. " +
                            "Mauris sit amet eros." ),

                CarOwner(65375,"Heindrick","Dillway",
                    "hdillwayae@state.tx.us","China","Toyota",2001,
                    "Pink","Male","Geological Engineer",
                    "Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus" +
                            " vestibulum. Proin eu mi.")
            )
    }

    @Test
    fun filterCarOwners_genderSupplied_returnsCarOwnersWithTheGenderSupplied(){

        val filter = Filter(0, 0, 0, "female",
            listOf(), listOf())

        val result = filterCarOwners(filter, carOwners)

        assertEquals(1, result.size)
    }

    @Test
    fun filterCarOwners_countriesSupplied_returnsCarOwnersWithTheCountriesSupplied(){

        val filter = Filter(0, 0, 0, "",
            listOf("Nigeria", "Ghana", "USA", "China"), listOf())
        val filter2 = Filter(0, 0, 0, "",
            listOf("South Africa", "Madagascar", "Algeria", "Congo"), listOf())
        val filter3 = Filter(0, 0, 0, "",
            listOf("Nigeria", "Ghana", "USA", "China", "Peru", "Guinea"), listOf())

        val result = filterCarOwners(filter, carOwners)
        val result2 = filterCarOwners(filter2, carOwners)
        val result3 = filterCarOwners(filter3, carOwners)

        assertEquals(2, result.size) //Nigeria China
        assertEquals(0, result2.size) //No countries present
        assertEquals(3, result3.size) //Nigeria China Peru
    }

    @Test
    fun filterCarOwners_colorsSupplied_returnsCarOwnersWithTheColorsSupplied(){

        val filter = Filter(0, 0, 0, "",
            listOf(), listOf("Yellow", "Green", "Aquamarine", "Red"))
        val filter2 = Filter(0, 0, 0, "",
            listOf(), listOf("Blue", "Pink", "Indigo", "Cyan"))
        val filter3 = Filter(0, 0, 0, "",
            listOf(), listOf("Orange", "Brown", "Violet", "White"))

        val result = filterCarOwners(filter, carOwners)
        val result2 = filterCarOwners(filter2, carOwners)
        val result3 = filterCarOwners(filter3, carOwners)

        assertEquals(1, result.size) //Aquamarine
        assertEquals(2, result2.size) //Blue Pink
        assertEquals(0, result3.size) //No colors present
    }

    @Test
    fun filterCarOwners_yearsSupplied_returnsCarOwnersWithTheYearsSupplied(){
        val filter = Filter(0, 2001, 0, "",
            listOf(), listOf())
        val filter2 = Filter(0, 2003, 2015, "",
            listOf(), listOf())
        val filter3 = Filter(0, 0, 1999, "",
            listOf(), listOf())
        val result = filterCarOwners(filter, carOwners)
        val result2 = filterCarOwners(filter2, carOwners)
        val result3 = filterCarOwners(filter3, carOwners)

        assertEquals(3, result.size) // 2001 2003 2015
        assertEquals(2, result2.size) // 2003 2015
        assertEquals(0, result3.size) // no year present
    }

}
