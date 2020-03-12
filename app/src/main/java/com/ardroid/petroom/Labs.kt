package com.ardroid.petroom

import android.os.Environment
import java.io.File
import java.io.FileWriter
import java.util.*

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

    fun doAllLabs(): List<File?> {
        return listOf(
                lab1(),
                lab2(),
                lab18(),
                lab19(),
                lab20(),
                lab21(),
                lab22()

        )
    }


    fun lab1(): File? {
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
        return writeFileOnExternalStorage(1, stringBuilder.toString())
    }

    fun lab2(): File? {
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
}


