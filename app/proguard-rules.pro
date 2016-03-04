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


-dontwarn com.squareup.okhttp.**
-dontwarn com.mob.tools.gui.**
-dontwarn com.tencent.connect.avatar.**
-dontwarn android.support.v4.**
-dontwarn com.coremedia.iso.**
-dontwarn com.coremedia.iso.gui.**
-dontwarn org.jdesktop.application.**
-dontwarn nu.xom.**
-dontwarn com.googlecode.mp4parser.isoviewer.**
-dontwarn com.tencent.weibo.utils.**
-dontwarn org.w3c.dom.**
-dontwarn com.alipay.**
-dontwarn cn.sharesdk.**
-dontwarn cn,sharesdk.tencent.qq.**

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