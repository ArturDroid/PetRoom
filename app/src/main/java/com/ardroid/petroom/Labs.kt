package com.ardroid.petroom

import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import java.io.File
import java.io.FileWriter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.math.abs
import kotlin.system.exitProcess

class Labs {
    private val externalFile = File(Environment.getExternalStorageDirectory().path, "/labs/")

    private fun writeFileOnExternalStorage(labNum: Int, sBody: String): File? {
        val root = externalFile
        val outputFile = File(root.path + "/" + "output$labNum.txt")
        if (!root.exists()) {
            root.mkdirs()
        }
        return try {
            outputFile.createNewFile()
            val writer = FileWriter(outputFile)
            writer.append(sBody)
            writer.flush()
            writer.close()
            outputFile
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


    fun lab16(): File? {
        val inputFile = File(externalFile.path + "/" + "input16.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            var sum = 0
            val size = scanner.nextInt()
            for (i in 0 until size) {
                sum += scanner.nextInt()
            }
            stringBuilder.appendln(sum)
        }
        return writeFileOnExternalStorage(16, stringBuilder.toString())
    }

    fun lab17(): File? {
        val inputFile = File(externalFile.path + "/" + "input17.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val evenList = mutableListOf<Int>()
            val oddList = mutableListOf<Int>()
            val size = scanner.nextInt()
            for (i in 0 until size) {
                val number = scanner.nextInt()
                if (number % 2 == 0) evenList.add(number)
                else oddList.add(number)
            }
            stringBuilder.appendln(evenList.joinToString(" ") +
                    " " + oddList.joinToString(" "))
        }

        return writeFileOnExternalStorage(17, stringBuilder.toString())
    }

    fun lab18(): File? {
        val inputFile = File(externalFile.path + "/" + "input18.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val list = mutableListOf<Int>()
            val size = scanner.nextInt()
            for (i in 0 until size) {
                list.add(scanner.nextInt())
            }
            for (i in 0 until size / 2) {
                val temp = list[i]
                list[i] = list[size - i - 1]
                list[size - i - 1] = temp
            }
            stringBuilder.appendln(list.joinToString(" "))
        }

        return writeFileOnExternalStorage(18, stringBuilder.toString())
    }

    fun lab19(): File? {
        val inputFile = File(externalFile.path + "/" + "input19.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextLine()) {
            val str = scanner.nextLine()
            val list = str.map {
                when (it) {
                    'w' -> {
                        'z'
                    }
                    'z' -> {
                        'w'
                    }
                    else -> it
                }
            }
            stringBuilder.appendln(list.joinToString(separator = ""))

        }
        return writeFileOnExternalStorage(19, stringBuilder.toString())

    }

    fun lab20(): File? {
        val inputFile = File(externalFile.path + "/" + "input20.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextLine()) {
            val str = scanner.nextLine()
            var space = 0
            str.map {
                if (it == ' ') {
                    space++
                }

            }
            stringBuilder.appendln(space)

        }
        return writeFileOnExternalStorage(20, stringBuilder.toString())

    }

    fun lab21(): File? {
        val inputFile = File(externalFile.path + "/" + "input21.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val x = scanner.nextInt()
            val y = scanner.nextInt()
            val matrix = mutableListOf<MutableList<Int>>()
            for (i in 0 until x) {
                matrix.add(mutableListOf())
                for (j in 0 until y) {
                    matrix[i].add(scanner.nextInt())
                }
            }
            val n = scanner.nextInt()
            val m = scanner.nextInt()
            val temp = matrix[n - 1]
            matrix[n - 1] = matrix[m - 1]
            matrix[m - 1] = temp

            for (i in matrix) {
                stringBuilder.appendln(i)
            }

        }


        return writeFileOnExternalStorage(21, stringBuilder.toString())
    }

    fun lab22(): File? {
        val inputFile = File(externalFile.path + "/" + "input22.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val n = scanner.nextInt()
            val matrix = mutableListOf<MutableList<Int>>()
            for (i in 0 until n) {
                matrix.add(mutableListOf())
                for (j in 0 until n) {
                    if (!scanner.hasNextInt()) scanner.next()
                    matrix[i].add(scanner.nextInt())
                }
            }
            for (d in 0 until matrix[0].size) {
                var sum = 0
                for (j in matrix) {
                    sum += j[d]

                }
                stringBuilder.appendln(sum / matrix.size)
            }


        }
        return writeFileOnExternalStorage(22, stringBuilder.toString())


    }

    fun lab23(): File? {
        val inputFile = File(externalFile.path + "/" + "input23.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val quantityLines = scanner.nextInt()
            val lineDouble = scanner.nextDouble() / 2
            val arr = mutableListOf<MutableList<Double>>()
            for (i in 0 until quantityLines) {
                val tripleList = mutableListOf<Double>()
                var isCorrect = true
                for (j in 0..2) {
                    val next = scanner.nextDouble()
                    tripleList.add(next)
                    if (lineDouble < abs(next)) {
                        isCorrect = false
                    }
                }
                if (isCorrect) arr.add(tripleList)
            }
            for (i in arr) {
                stringBuilder.appendln(i.joinToString(separator = " "))
            }

        }
        return writeFileOnExternalStorage(23, stringBuilder.toString())
    }

//    fun lab24(): File? {
//        val inputFile = File(externalFile.path + "/" + "input24.txt")
//        val scanner = Scanner(inputFile)
//        val stringBuilder = StringBuilder()
//        while (scanner.hasNextInt()) {
//            val sizeLine1 = scanner.nextInt()
//            val arr = mutableListOf<Int>()
//            for (i in 0 until sizeLine1) arr.add(scanner.nextInt())
//            val sizeLine2 = scanner.nextInt()
//            for (i in 0 until sizeLine2) arr.add(scanner.nextInt())
//            for (j in 0..arr.size - 2) {
//                var temp = 0
//                for (i in 0..arr.size - 2) {
//                    if (arr[i] > arr[i + 1]) {
//                        temp = arr[i]
//                        arr[i] = arr[i + 1]
//                        arr[i + 1] = temp
//                    }
//                }
//            }
//            for (i in arr) {
//                stringBuilder.append("$i ")
//            }
//
//
//        }
//        return writeFileOnExternalStorage(24, stringBuilder.toString())
//    }

    fun lab24(): File? {
        val inputFile = File(externalFile.path + "/" + "input24.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        val arr = mutableListOf<Int>()
        while (scanner.hasNextInt()) {
            val sizeLine = scanner.nextInt()
            for (i in 0 until sizeLine) arr.add(scanner.nextInt())
        }
        arr.sort()
        for (i in arr) {
            stringBuilder.append("$i ")
        }
        return writeFileOnExternalStorage(24, stringBuilder.toString())
    }

    fun lab25(): File? {
        val inputFile = File(externalFile.path + "/" + "input25.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        val listInt = mutableListOf<Int>()
        loop@ while (scanner.hasNextLine()) {
            val s = scanner.nextLine()
            when {
                "push" in s -> {
                    listInt.add(s.filter { it.isDigit() }.toInt())
                    stringBuilder.appendln("ok")
                }
                "back" in s -> {
                    stringBuilder.appendln(listInt.last())
                }
                "size" in s -> {
                    stringBuilder.appendln(listInt.size)
                }
                "pop" in s -> {
                    stringBuilder.appendln(listInt.last())
                    listInt.remove(listInt.last())
                }
                "clear" in s -> {
                    stringBuilder.appendln("ok")
                    listInt.clear()
                }
                "exit" in s -> {
                    stringBuilder.appendln("bye")
                    break@loop

                }

            }


        }
        return writeFileOnExternalStorage(25, stringBuilder.toString())
    }

    fun lab1(): File? {
        val inputFile = File(externalFile.path + "/" + "input1.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()

        while (scanner.hasNextInt()) {
            var res = 0
            var number = abs(scanner.nextInt())
            while (number > 0) {
                res += number % 10
                number /= 10
            }
        }


        return writeFileOnExternalStorage(1, stringBuilder.toString())
    }


    fun lab2(): File? {
        val inputFile = File(externalFile.path + "/" + "input2.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextLine()) {
            var hour = scanner.nextInt()
            var min = scanner.nextInt()
            val sec = scanner.nextInt()
            when {
                59 > min -> {
                    min++
                    stringBuilder.appendln(stringBuilder.append(hour, " ", min, " ", sec))
                }
                59 == min && hour < 12 -> {
                    min = 0
                    hour++
                    stringBuilder.appendln(stringBuilder.append(hour, " ", min, " ", sec))
                }
                59 == min && hour == 12 -> {
                    hour = 1
                    min = 0
                    stringBuilder.appendln(stringBuilder.append(hour, " ", min, " ", sec))
                }
            }
        }

        return writeFileOnExternalStorage(2, stringBuilder.toString())
    }

    fun lab3(): File? {
        val inputFile = File(externalFile.path + "/" + "input3.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val child = scanner.nextInt()
            val apples = scanner.nextInt()
            stringBuilder.appendln(child - (apples % child))
        }
        return writeFileOnExternalStorage(3, stringBuilder.toString())


    }

    fun lab4(): File? {
        val inputFile = File(externalFile.path + "/" + "input4.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val number = scanner.nextLine()
            var result = 0
            for (i in 1..5) {
                when {
                    number.take(i).toInt() == 0 -> {
                        result++
                    }
                }
            }
            stringBuilder.appendln(result)
        }

        return writeFileOnExternalStorage(4, stringBuilder.toString())


    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun lab5(): File? {
        val inputFile = File(externalFile.path + "/" + "input5.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextLine()) {
            val dd = scanner.nextInt()
            val mm = scanner.nextInt()
            val yyyy = scanner.nextInt()
            val checkDate = LocalDate.of(yyyy, mm, dd)
            val centuryStart = LocalDate.of(1999, 12, 31)
            val centuryEnd = LocalDate.of(2100, 1, 1)
            // if (checkDate < centuryEnd && checkDate > centuryStart)
            if (checkDate.isAfter(centuryStart) && checkDate.isBefore(centuryEnd)) {
                stringBuilder.appendln("YES")
            } else stringBuilder.appendln("NO")
        }
        return writeFileOnExternalStorage(5, stringBuilder.toString())
    }

    fun lab6(): File? {
        val inputFile = File(externalFile.path + "/" + "input6.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()

        return writeFileOnExternalStorage(6, stringBuilder.toString())
    }

    fun lab7(): File? {
        val inputFile = File(externalFile.path + "/" + "input7.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextLine()) {
            val number = scanner.nextLine()
            if (number.toInt() < 0) {
                stringBuilder.appendln("YES")
                break
            }
            if (number.take(number.length).toInt() != 0 && number.take(number.length - 1).toInt() == 0) {
                stringBuilder.appendln("YES")
                break
            }
            if (number.toInt() % 3 != 0 && number.toInt() % 5 != 0) {
                stringBuilder.appendln("YES")
                break
            } else stringBuilder.appendln("NO")
        }
        return writeFileOnExternalStorage(7, stringBuilder.toString())
    }

    fun lab8(): File? {
        val inputFile = File(externalFile.path + "/" + "input8.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        val price = scanner.nextInt() * 100 + scanner.nextInt()
        val cash = scanner.nextInt() * 100 + scanner.nextInt()
        val countBuy = cash / price
        val cashOrder = cash - (price * countBuy)
        stringBuilder.append(cashOrder / 100, " ", cashOrder - (cashOrder / 100 * 100))

        return writeFileOnExternalStorage(8, stringBuilder.toString())
    }

    fun lab9(): File? {
        val inputFile = File(externalFile.path + "/" + "input9.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()

        return writeFileOnExternalStorage(9, stringBuilder.toString())
    }

    fun lab10(): File? {
        val inputFile = File(externalFile.path + "/" + "input10.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val value = scanner.nextInt()
            val arrFibonacci = mutableListOf<Int>()
            var result = 0
            arrFibonacci.add(0)
            arrFibonacci.add(1)
            if (value > 1) {
                for (i in 2..value) {
                    arrFibonacci.add(arrFibonacci[i - 1] + arrFibonacci[i - 2])
                }
            }
            if (value != 0) {
                for (i in arrFibonacci) {
                    result += arrFibonacci[i]
                }
                stringBuilder.appendln(result)
            } else stringBuilder.appendln("0")
        }
        return writeFileOnExternalStorage(10, stringBuilder.toString())
    }

    fun lab11(): File? {
        val inputFile = File(externalFile.path + "/" + "input11.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()

        return writeFileOnExternalStorage(11, stringBuilder.toString())
    }

    fun lab12(): File? {
        val inputFile = File(externalFile.path + "/" + "input12.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        val arrInt = mutableListOf<Int>()
        while (scanner.hasNextInt()) {
            val tempScanner = scanner.nextInt()
            if (tempScanner == 0) break
            arrInt.add(tempScanner)
        }
        for (j in 0 until arrInt.size - 1) {
            for (i in 0 until arrInt.size - 1) {
                if (arrInt[i] > arrInt[i + 1]) {
                    val temp = arrInt[i]
                    arrInt[i] = arrInt[i + 1]
                    arrInt[i + 1] = temp
                }
            }
        }
        stringBuilder.appendln(arrInt[arrInt.size - 2])

        return writeFileOnExternalStorage(12, stringBuilder.toString())
    }

    fun lab13(): File? {
        val inputFile = File(externalFile.path + "/" + "input13.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        val arrInt = mutableListOf<Int>()
        while (scanner.hasNextInt()) arrInt.add(scanner.nextInt())
        var maxSequence = 0
        var sequence = 1
        var current = arrInt.first()
        for (i in 1 until arrInt.size) {
            if (current == arrInt[i]) {
                sequence++
            } else {
                if (sequence > maxSequence) maxSequence = sequence
                sequence = 1
                current = arrInt[i]
            }
        }
        stringBuilder.appendln(maxSequence)
        return writeFileOnExternalStorage(13, stringBuilder.toString())
    }

    fun lab14(): File? {
        val inputFile = File(externalFile.path + "/" + "input14.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            var sum = 0
            val size = scanner.nextInt()
            var max = Pair(0, 0)
            var min = Pair(0, 0)
            for (i in 0 until size) {
                val number = scanner.nextInt()
                sum += number
                if (number >= max.first) max = Pair(number, i + 1)
                if (number < min.first) min = Pair(number, i + 1)
            }
            stringBuilder.appendln("$sum\n${max.first} ${max.second}\n${min.first} ${min.second} ")

        }
        return writeFileOnExternalStorage(14, stringBuilder.toString())
    }

    fun lab15(): File? {
        val inputFile = File(externalFile.path + "/" + "input15.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()) {
            val arr = mutableListOf<Int>()
            val size: Int = scanner.nextInt()
            for (i in 0 until size) arr.add(scanner.nextInt())
            var count: Int = arr.size
            for (i in 0 until arr.size - 1) {
                for (j in i + 1 until arr.size) {
                    if (arr[i] == arr[j] && i != j) {
                        count--
                        break
                    }
                }
            }
            stringBuilder.appendln(count)
        }

        return writeFileOnExternalStorage(15, stringBuilder.toString())
    }

    fun doAllLabs(): List<File?> {
        return listOf(
//                lab1(),
                lab12(),
                lab13(),
                lab14(),
                lab15()
//                lab16(),
//                lab17(),
//                lab18(),
//                lab19(),
//                lab20(),
//                lab21(),
//                lab22(),
//                lab23(),
//                lab24(),
//                lab25()


        )
    }
}

