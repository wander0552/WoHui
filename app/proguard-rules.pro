# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\asset\adt-bundle-windows-x86_64-20131030\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
# 混淆时不使用大小写混合，混淆后的类名为小写
-dontusemixedcaseclassnames
# 指定不去忽略非公共的库的类
-dontskipnonpubliclibraryclasses
# 指定不去忽略非公共的库的类的成员
-dontskipnonpubliclibraryclassmembers
# 有了verbose这句话，混淆后就会生成映射文件

# 包含有类名->混淆后类名的映射关系

# 然后使用printmapping指定映射文件的名称

-verbose

-printmapping proguardMapping.txt

# 代码混淆压缩比，在0和7之间，默认为5，一般不需要改

-optimizationpasses 5

# 不做预校验，preverify是proguard的4个步骤之一

# Android不需要preverify，去掉这一步可加快混淆速度

-dontpreverify

# 指定混淆时采用的算法，后面的参数是一个过滤器

# 这个过滤器是谷歌推荐的算法，一般不改变

-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

# 避免混淆泛型，这在JSON实体映射时非常重要，比如fastJson
-keepattributes Exceptions,InnerClasses,Signature
# 保护代码中的Annotation不被混淆，这在JSON实体映射时非常重要，比如fastJson
-keepattributes *Annotation*
#抛出异常时保留代码行号，在第6章异常分析中我们提到过
-keepattributes SourceFile,LineNumberTable

# 保留所有的本地native方法不被混淆

-keepclasseswithmembernames class * {

    native <methods>;

}

# 保留了继承自Activity、Application这些类的子类

# 因为这些子类，都有可能被外部调用

# 比如说，第一行就保证了所有Activity的子类不要被混淆

-keep public class * extends android.app.Activity

-keep public class * extends android.app.Application

-keep public class * extends android.app.Service

-keep public class * extends android.content.BroadcastReceiver

-keep public class * extends android.content.ContentProvider

-keep public class * extends android.app.backup.BackupAgentHelper

-keep public class * extends android.preference.Preference

-keep public class * extends android.view.View

-keep public class com.android.vending.licensing.ILicensingService

# 保留JS方法不被混淆

-keepclassmembers class com.example.youngheart.MainActivity$JSInterface1 {

    <methods>;

}
-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**


-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**

-keep public class com.umeng.socialize.* {*;}
-keep public class javax.**
-keep public class android.webkit.**

-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**
-keep class com.umeng.socialize.handler.**
-keep class com.umeng.socialize.handler.*
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}



-keep class com.tencent.** {*;}
-dontwarn com.tencent.**
-keep public class com.umeng.soexample.R$*{
    public static final int *;
}
-keep public class com.umeng.soexample.R$*{
    public static final int *;
}
-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}

-keep class com.sina.** {*;}
-dontwarn com.sina.**
-keep class  com.alipay.share.sdk.** {
   *;
}
-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}
-keep class com.linkedin.** { *; }
-keepattributes Signature

-dontwarn com.squareup.okhttp.**
-dontwarn com.tencent.connect.avatar.**
-dontwarn android.support.v4.**
-dontwarn com.coremedia.iso.**
-dontwarn com.coremedia.iso.gui.**
-dontwarn org.jdesktop.application.**
-dontwarn nu.xom.**
-dontwarn com.tencent.weibo.utils.**
-dontwarn org.w3c.dom.**
-dontwarn com.alipay.**

#    3D 地图
    -keep   class com.amap.api.mapcore.**{*;}
    -keep   class com.amap.api.maps.**{*;}
    -keep   class com.autonavi.amap.mapcore.*{*;}
#    定位
   -keep class com.amap.api.location.**{*;}
   -keep class com.amap.api.fence.**{*;}
   -keep class com.autonavi.aps.amapapi.model.**{*;}

#    搜索
    -keep   class com.amap.api.services.**{*;}
#    2D地图
    -keep class com.amap.api.maps2d.**{*;}
    -keep class com.amap.api.mapcore2d.**{*;}
#    导航
    -keep class com.amap.api.navi.**{*;}
    -keep class com.autonavi.**{*;}