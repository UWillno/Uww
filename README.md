# Uww

## 简介/Introduction

`Android`文件夹隐藏，根据状态栏开关控制。几分钟随便乱写的软件。原理就是`Linux`下的文件夹隐藏，文件夹前面加个`.`。格式是目录之间用英文`;`隔开，不建议直接用于会自动创建的目录。

`Android` folder is hidden and controlled according to the status bar switch. A software that can be written randomly for a few minutes. The principle is to hide the folder under `Linux` and add a `.` in front of the folder. The format is separated by English `;` between directories and is not recommended to be used directly in directories that will be automatically created.

## 应用场景/Application scenarios

可能某人上班经常要发图片，偶尔摸鱼期间存了些二次元图，而通常媒体选择器是按照时间进行排序的，容易误选发送，导致社死？或者有些媒体不适合出现在相册中……？状态栏开关控制这些文件夹就比较方便。~~保护隐私？~~

Maybe someone often has to post pictures at work, and occasionally saves some two-dimensional pictures during the fishing period. Usually the media selector is sorted by time, which is easy to miss the selection and send, resulting in social death? Or are some media not suitable for appearing in photo albums…? It is more convenient to control these folders with the status bar switch.  ~~Privacy protection?~~ 

## 测试/Test

即使微信或QQ中最近照片中没有及时更新缩略图显示了该照片也发不出去，微信会显示`所选项目中没有可发送内容`，QQ中会显示`null文件不存在`。最近加了个扫描，可能可以更新MediaStore，顺便把/sdcard/换成/storage/emulated/0/，但我懒得编译，因为我不太习惯写Kotlin了。

Even if the thumbnail is not updated in time in the recent photos on WeChat or QQ, the thumbnail cannot be sent out. WeChat will show `No content to be sent in the selected item`, and the null file does not exist in QQ. I recently added a scan and may update MediaStore and change /sdcard/ to /storage/emulated/0/ by the way, but I am too lazy to compile because I am not used to writing Kotlin anymore. 

## 其它方案/Other solutions

1. .nomedia，几乎没效果。.nomedia, almost no effect.
2. 可能可以xposed来淦MediaProvider，环境检测频繁，我也不想折腾了，现在手机难解bl锁。Maybe xposed to MediaProvider. The environment is frequently detected, and I don’t want to make trouble anymore. Now the mobile phone is difficult to unlock the BL lock.
3. 如果文件系统支持且有权限，或许可以建立或删除软链接来实现？If the file system supports it and has permissions, maybe it can be created or deleted to implement it?
4. 不给存储权限直接分享，限制好像是9张？ If you don’t give storage permissions, it seems that the limit is 9? 
5. 应用双开（一个给权限，一个不给，通信之后手动转发）、手机分身、虚拟机。如果你的设备性能足够。Applications are double-opened (one gives permissions, the other does not, and they are forwarded manually after communication), mobile phone clone, and virtual machine. If your device performs enough.
6. 安卓14以上可以选择图片是否能够访问，有100张限制，且需要应用支持。Android 14 or above can choose whether the pictures can be accessed. There is a limit of 100 pictures and requires application support.
7. 换IOS/换工作/换世界……？Change IOS/change job/change world…?

## Uww2

Uww2只是拿Qt重写改进了下文件夹合并的逻辑以及加锁，实现方法无变化，依旧懒得写选择器和处理contenturi，建议手动输入。可能存在遗留代码，只用于测试前台服务或者其它保活方案，过于繁琐，功能并没有加入。

Uww2 just rewrites Qt to improve the logic and locking of folders. The implementation method has not changed. I am still too lazy to write selectors and handle contenturi. It is recommended to enter manually.There may be legacy code, which is only used to test ForegroundService or other keep-alive solutions. It is too cumbersome and the functions have not been added.
