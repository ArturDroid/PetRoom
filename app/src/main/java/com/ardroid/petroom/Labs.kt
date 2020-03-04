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
                lab18()

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
            for (i in 0 until size/2 ) {
                val temp = list[i]
                list[i] = list[size - i - 1]
                list[size - i - 1] = temp
            }
            stringBuilder.appendln(list.joinToString(" "))
        }

        return writeFileOnExternalStorage(18, stringBuilder.toString())
    }

}

