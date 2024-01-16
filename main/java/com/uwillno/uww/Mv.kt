package com.uwillno.uww

import java.io.File


fun addDotAfterLastSlash(input: String): String {
    val lastSlashIndex = input.lastIndexOf('/')

    return if (lastSlashIndex != -1 && lastSlashIndex < input.length - 1) {
        input.substring(0, lastSlashIndex + 1) + "." + input.substring(lastSlashIndex + 1)
    } else {
        // 如果没有斜杠或者斜杠已经在字符串末尾，则直接返回原始字符串
        input
    }
}


fun dotDir(dir: String): Boolean{
    val file = File(dir)
    val newFile = File(addDotAfterLastSlash(dir))
    if(file.isDirectory && file.exists())
        return file.renameTo(newFile)
    return false
}

fun noDotDir(dir: String): Boolean {
    val file = File(dir)
    val newFile = File(addDotAfterLastSlash(dir))
    if(newFile.isDirectory && newFile.exists())
        return newFile.renameTo(file)
    return false
}