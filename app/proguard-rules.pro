# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\sdk/tools/proguard/proguard-android.txt
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


#####################################################################################
# Start on 2017/5/16 15:59
# Update on 2018/10/30 17:53
# Email：sin2t@sina.com
# by:qihao    http://blog.csdn.net/gfg156196
#####################################################################################

    # 指定代码的压缩级别
    -optimizationpasses 5
    # 包名不混合大小写
    -dontusemixedcaseclassnames
    # 不去忽略非公共的库类
    -dontskipnonpubliclibraryclasses
    # 优化  不优化输入的类文件
    -dontoptimize
    # 混淆时是否做预校验
    -dontpreverify
    # 混淆时是否记录日志
    -verbose
    # 混淆时所采用的算法
    -optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
    # 保护注解
    -keepattributes *Annotation*
    # 如果引用了v4或者v7包
    -dontwarn android.support.**
    # 保持 native 方法不被混淆
    -keepclasseswithmembernames class * {
        native <methods>;
    }
    # 保持自定义控件类不被混淆
    -keepclasseswithmembers class * {
        public <init>(android.content.Context, android.util.AttributeSet);
    }
    # 保持自定义控件类不被混淆
    -keepclassmembers class * extends android.app.Activity {
       public void *(android.view.View);
    }
    # 保持自定义控件类不被混淆
    -keepclasseswithmembers class * {
        public <init>(android.content.Context, android.util.AttributeSet);
    }
    # 保持自定义控件类不被混淆
    -keepclasseswithmembers class * {
        public <init>(android.content.Context, android.util.AttributeSet, int);
    }
    # 保持 Parcelable 不被混淆
    -keep class * implements android.os.Parcelable {
      public static final android.os.Parcelable$Creator *;
    }
    # 保持 Serializable 不被混淆
    -keepnames class * implements java.io.Serializable
    # 保持 Serializable 不被混淆并且enum 类也不被混淆
    -keepclassmembers class * implements java.io.Serializable {
        static final long serialVersionUID;
        private static final java.io.ObjectStreamField[] serialPersistentFields;
        !static !transient <fields>;
        !private <fields>;
        !private <methods>;
        private void writeObject(java.io.ObjectOutputStream);
        private void readObject(java.io.ObjectInputStream);
        java.lang.Object writeReplace();
        java.lang.Object readResolve();
    }
    # 保持枚举 enum 类不被混淆 如果混淆报错，建议直接使用上面的 -keepclassmembers class * implements java.io.Serializable即可
    # -keepclassmembers enum * {
    #  public static **[] values();
    #  public static ** valueOf(java.lang.String);
    # }

    -keepclassmembers class * {
        public void *ButtonClicked(android.view.View);
    }
    # 不混淆资源类
    -keepclassmembers class **.R$* {
        public static <fields>;
    }
    # 避免混淆泛型 如果混淆报错建议关掉
    #–keepattributes Signature

    # 关闭所有日志 log, java.io.Print, printStackTrace
    -assumenosideeffects class android.util.Log {
        public static *** e(...);
        public static *** w(...);
        public static *** i(...);
        public static *** d(...);
        public static *** v(...);
    }
    -assumenosideeffects class java.io.PrintStream {
        public *** print(...);
        public *** println(...);
    }
    -assumenosideeffects class java.lang.Throwable {
        public *** printStackTrace(...);
    }

    ################ android eventbus start ###############
    -keep class org.simple.** { *; }
    -keep interface org.simple.** { *; }
    -keepclassmembers class * {
        @org.simple.eventbus.Subscriber <methods>;
    }
    -keepattributes *Annotation*
    ################ android eventbus end ###############

    ## bugly
    -dontwarn com.tencent.bugly.**
    -keep public class com.tencent.bugly.**{*;}
    # Gson specific classes
    -keep class sun.misc.Unsafe { *; }
    #youmeng
    -keep class com.umeng.analytics.**{*;}
    -keep class com.umeng.analytics.**
    -keep class u.aly.** {*;}
    -keep class u.aly.**
    #pinyin4j
    -keep class com.hp.hpl.sparta..** { *; }
    -keep class com.hp.hpl.sparta..**
    -keep class demo..** { *; }
    -keep class demo..**
    -keep class net.sourceforge.pinyin4j..** { *; }
    -keep class net.sourceforge.pinyin4j..**
    #retrofit
    -dontnote retrofit2.Platform
    #okhttp
    -dontwarn com.squareup.okhttp3.**
    -keep class com.squareup.okhttp3.** { *;}
    -dontwarn okhttp3.logging.**
    -keep class okhttp3.internal.**{*;}
    -dontwarn okio.**
    # OkHttp3
    -dontwarn com.squareup.okhttp3.**
    -keep class com.squareup.okhttp3.** { *;}
    -dontwarn okio.**
    # Glide
    -keep public class * implements com.bumptech.glide.module.GlideModule
    -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
      **[] $VALUES;
      public *;
    }
    #极光推送
    -dontoptimize
    -dontpreverify
    -dontwarn cn.jpush.**
    -keep class cn.jpush.** { *; }
    -dontwarn cn.jiguang.**
    -keep class cn.jiguang.** { *; }
    #retrofit
    -dontnote retrofit2.Platform
    -dontnote retrofit2.Platform$IOS$MainThreadExecutor
    -dontwarn retrofit2.Platform$Java8
    -keepattributes Signature
    -dontwarn retrofit2.**
    -keep class retrofit2.** { *; }
    -keepattributes Signature
    -keepattributes Exceptions
    # RxJava RxAndroid
    -dontwarn sun.misc.**
    -keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
       long producerIndex;
       long consumerIndex;
    }
    -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
        rx.internal.util.atomic.LinkedQueueNode producerNode;
    }
    -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
        rx.internal.util.atomic.LinkedQueueNode consumerNode;
    }
    #Gson
    -keepattributes Signature
    -keepattributes *Annotation*
    -keep class sun.misc.Unsafe { *; }
    -keep class com.google.gson.stream.** { *; }
    # Application classes that will be serialized/deserialized over Gson 下面替换成自己的实体类
    -keep class com.example.bean.** { *; }

    # 蒲公英
    -dontwarn com.pgyersdk.**
    -keep class com.pgyersdk.** { *; }
    # 极光推送
    -dontoptimize
    -dontpreverify
    -dontwarn cn.jpush.**
    -keep class cn.jpush.** { *; }
    -keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }
    -dontwarn cn.jiguang.**
    -keep class cn.jiguang.** { *; }
    #==================gson && protobuf==========================
    -dontwarn com.google.**
    -keep class com.google.gson.** {*;}
    -keep class com.google.protobuf.** {*;}
    ### greenDAO 3
    -keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
    public static java.lang.String TABLENAME;
    }
    -keep class **$Properties
    -dontwarn org.greenrobot.greendao.database.**
    -dontwarn rx.**
    -keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
    public static java.lang.String TABLENAME;
    }
    -keep class **$Properties
    # butterknife
    -keep class butterknife.** { *; }
    -dontwarn butterknife.internal.**
    -keep class **$$ViewBinder { *; }
    -keepclasseswithmembernames class * {
        @butterknife.* <fields>;
    }
    -keepclasseswithmembernames class * {
        @butterknife.* <methods>;
    }
    # 友盟统计
    -keepclassmembers class * {
      public <init> (org.json.JSONObject);
    }
    -keep public class [您的应用包名].R$*{
      public static final int *;
    }
    -keepclassmembers enum * {
      public static **[] values(); public static ** valueOf(java.lang.String);
    }
    # 忽略警告
    -ignorewarning
    -keepattributes Signature
    -keepattributes Annotation
    -keep class okhttp3.**{*;}
    -keep interface okhttp3.**{*;}
     # greenDao 升级
    -keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
        public static void dropTable(org.greenrobot.greendao.database.Database, boolean);
        public static void createTable(org.greenrobot.greendao.database.Database, boolean);
    }


#####################################################################################
# End on 2017/5/16 15:59
# Update on 2018/10/30 17:53
# Email：sin2t@sina.com
# by:qihao    http://blog.csdn.net/gfg156196
#####################################################################################