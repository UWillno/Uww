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

fun mergeDirectories(src: File, dest: File) {
    if (!src.isDirectory) return

    src.listFiles()?.forEach { file ->
        val destFile = File(dest, file.name)
        if (file.isDirectory) {
            if (!destFile.exists()) destFile.mkdirs()
            mergeDirectories(file, destFile) // 递归合并子目录
        } else {
            // 文件：覆盖复制
            file.copyTo(destFile, overwrite = true)
        }
    }
}
fun dotDirWithMergeAndOverwrite(dirPath: String): Boolean {
    val dir = File(dirPath)
    if (!dir.exists() || !dir.isDirectory) return false
    val dottedDir = File(dir.parent, ".${dir.name}")
    // .dir存在
    if (dottedDir.exists() && dottedDir.isDirectory) {
        // 合并 .dir 到 dir，文件覆盖，目录递归合并
        mergeDirectories(dottedDir, dir)
        dottedDir.deleteRecursively()
    }
    // 重命名 dir -> .dir
    return dir.renameTo(dottedDir)
}

// 还原dot
fun restoreFromDotDir(dirPath: String): Boolean {
    val dir = File(dirPath)
    val dottedDir = File(dir.parent, ".${dir.name}")
    // 不存在
    if (!dottedDir.exists() || !dottedDir.isDirectory) return false

    // 如果 dir 存在，先用把dir里的移动到.dir里
    if (dir.exists() && dir.isDirectory) {
        mergeDirectories(dir, dottedDir) // 和前面同样的合并逻辑
        dir.deleteRecursively()
    }
    // 将 .dir 重命名为 dir
    return dottedDir.renameTo(dir)
}
