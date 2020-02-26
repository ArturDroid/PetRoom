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
                lab1()
        )
    }



    fun lab1(): File? {
        val inputFile = File(externalFile.path + "/" + "input1.txt")
        val scanner = Scanner(inputFile)
        val stringBuilder = StringBuilder()
        while (scanner.hasNextInt()){
            var sum = 0
            val size = scanner.nextInt()
            for (i in 0 until size){
                sum += scanner.nextInt()
            }
            stringBuilder.appendln(sum)
        }
        return writeFileOnExternalStorage(1, stringBuilder.toString())
    }

}