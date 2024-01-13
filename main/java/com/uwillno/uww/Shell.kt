package com.uwillno.uww

import java.io.BufferedReader
import java.io.InputStreamReader

    fun String.execute(): Process {
        val runtime = Runtime.getRuntime()
        return runtime.exec(this)
    }


    fun Process.text(): String {
        // 输出 Shell 执行结果
        val inputStream = this.inputStream
        val insReader = InputStreamReader(inputStream)
        val bufReader = BufferedReader(insReader)

        var output = ""
        var line: String? = ""
        while (null != line) {
            // 逐行读取shell输出，并保存到变量output
            line = bufReader.readLine()
            output += line + "\n"
        }
        return output
    }


    fun addDotAfterLastSlash(input: String): String {
        val lastSlashIndex = input.lastIndexOf('/')

        return if (lastSlashIndex != -1 && lastSlashIndex < input.length - 1) {
            input.substring(0, lastSlashIndex + 1) + "." + input.substring(lastSlashIndex + 1)
        } else {
            // 如果没有斜杠或者斜杠已经在字符串末尾，则直接返回原始字符串
            input
        }
    }

    fun dotDir(dir: String): Int {

        val dir1 = addDotAfterLastSlash(dir);
        val process = ("mv  $dir $dir1").execute()
        //    val text = process.text()
        return process.waitFor();
    }

fun noDotDir(dir: String): Int {
    val dir1 = addDotAfterLastSlash(dir);
    val process = ("mv $dir1 $dir").execute()
    return process.waitFor();
}