package com.example.opeyemiabdulsalam.utils

import android.util.Log
import com.example.opeyemiabdulsalam.data.CarOwner
import com.example.opeyemiabdulsalam.data.Filter
import java.io.*

private const val TAG = "Utils"
private const val DEFAULT_SEPARATOR = ','
private const val DEFAULT_QUOTE = '"'
private const val FILE_PATH = "/storage/emulated/0/Venten/car_owners_data.csv"

fun filterCarOwners(filter: Filter, carOwners: List<CarOwner>): List<CarOwner> {

    return carOwners.filter { owner ->

        val containCountry = if (filter.countries.isNotEmpty()) {
            owner.country in filter.countries
        } else {
            true
        }

        val containsColor = if (filter.colors.isNotEmpty()) {
            owner.carColor in filter.colors
        } else {
            true
        }

        val containStartYear = if (filter.startYear != 0) {
            owner.carModelYear >= filter.startYear
        } else {
            true
        }

        val containEndYear = if (filter.endYear != 0) {
            owner.carModelYear <= filter.endYear
        } else {
            true
        }

        val containsGender = if (filter.gender.isNotEmpty()) {
            owner.gender.equals(filter.gender, true)
        } else {
            true
        }
        containCountry && containsColor && containStartYear && containEndYear && containsGender
    }
}

fun readDataFromRaw(): ArrayList<CarOwner> {
    val carOwners = ArrayList<CarOwner>()
    try {
        val file = File(FILE_PATH)
        val reader = FileReader(file)
        val bufferedReader = BufferedReader(reader)
        var line: String?
        bufferedReader.readLine() //Step over the header
        line = bufferedReader.readLine()
        while (line != null) {
            val carTokens = parseLine(line, DEFAULT_SEPARATOR, DEFAULT_QUOTE)
            if (carTokens.isNotEmpty()) {
                val carOwner = CarOwner(
                    carTokens[0].toInt(),
                    carTokens[1],
                    carTokens[2],
                    carTokens[3],
                    carTokens[4],
                    carTokens[5],
                    carTokens[6].toInt(),
                    carTokens[7],
                    carTokens[8],
                    carTokens[9],
                    carTokens[10]
                )
                carOwners.add(carOwner)
            }
            line = bufferedReader.readLine()
        }
    } catch (e: IOException) {
        Log.e(TAG, e.message, e)
        throw e
    }
    return carOwners
}

fun parseLine(
    cvsLine: String,
    separators: Char,
    customQuote: Char
): List<String> {
    val result: MutableList<String> = ArrayList()

    if (cvsLine.isEmpty()) {
        return result
    }

    var curVal = StringBuffer()
    var inQuotes = false
    var startCollectChar = false
    var doubleQuotesInColumn = false
    val chars = cvsLine.toCharArray()
    for (ch in chars) {
        if (inQuotes) {
            startCollectChar = true
            if (ch == customQuote) {
                inQuotes = false
                doubleQuotesInColumn = false
            } else { //Fixed : allow "" in custom quote enclosed
                if (ch == '\"') {
                    if (!doubleQuotesInColumn) {
                        curVal.append(ch)
                        doubleQuotesInColumn = true
                    }
                } else {
                    curVal.append(ch)
                }
            }
        } else {
            if (ch == customQuote) {
                inQuotes = true
                //Fixed : allow "" in empty quote enclosed
                if (chars[0] != '"' && customQuote == '\"') {
                    curVal.append('"')
                }
                //double quotes in column will hit this!
                if (startCollectChar) {
                    curVal.append('"')
                }
            } else if (ch == separators) {
                result.add(curVal.toString())
                curVal = StringBuffer()
                startCollectChar = false
            } else if (ch == '\r') { //ignore LF characters
                continue
            } else if (ch == '\n') { //the end, break!
                break
            } else {
                curVal.append(ch)
            }
        }
    }
    result.add(curVal.toString())
    return result
}

