# BaoDian
这是一个demo


![image](https://github.com/yugu88/BaoDian/blob/master/demo.gif)

#### 实验了slidingMenu侧滑的沉浸式效果，demo已完成。。
#### 初始化时，获取布局文件，动态设置padding值为空，因为include加载布局的方法初始化无法进入内存，
#### 此举也是复杂布局节约内存的方式,所以如果初始化获取布局并设置padding，必然为空对象，修复此处问题后，沉浸式可以正常使用。。
#### volley以源码的方式集成到了项目里
#### 具体集成的详细步骤见博客：http://blog.csdn.net/gfg156196/article/details/52996133


#### gradle-wrapper.properties
    distributionUrl=https\://services.gradle.org/distributions/gradle-3.3-all.zip

#### 关于退出和栈中Activity实时管理
```
    /**
     * 作为良心开发者，不能因为用户多安装一个app而增加一丝卡顿，app退出时必须调用此方法。
     * 彻底退出应用，清空相关的所有进程和堆栈内存
     * 注意：可用内存查看工具查看app退出后是否在堆栈中被彻底清除
     */
        public void AppExit();
    // 栈中移除其他Activity
        ActivityUtil.getInstance().popOtherActivity(MainActivity.class);
    // 把当前Activity添加进管理集合
        ActivityUtil.getInstance().addActivity(this);


   /**
    * 并非每次从栈中移除都节省CPU和内存的消耗，根据跳转逻辑斟酌使用。
    */
```