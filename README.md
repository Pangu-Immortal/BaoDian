

# BaoDian

<div align="center">

![萌萌计数器](https://count.getloli.com/get/@BaoDian?theme=rule34)

</div>

<p align="center">
  <b>🌟 如果觉得有帮助，请点击 <a href="https://github.com/Pangu-Immortal/BaoDian/stargazers">Star</a> 支持一下，关注不迷路！🌟</b>
</p>

这是一个App框架（功能完善可以快速开发自己的App）今后只维护 MVP分支，此页面将会看不到最近更新日期。

![image](https://github.com/yugu88/BaoDian/blob/master/doc/device-2018-12-06-153552.gif)

# Android Studio 3.4.1 + gradle-5.1.1

## 基于MVP+Dagger2+Retrofit2.0+Rxjava2开发
#### 具体原理： [http://blog.csdn.net/gfg156196/article/details/52996133](http://blog.csdn.net/gfg156196/article/details/52996133)

#### gradle-wrapper.properties
    distributionUrl=https\://services.gradle.org/distributions/gradle-5.1.1-all.zip
    classpath 'com.android.tools.build:gradle:3.4.1'

```
    compileOptions {
            sourceCompatibility JavaVersion.VERSION_1_8
            targetCompatibility JavaVersion.VERSION_1_8
    }

```

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

![image](https://github.com/yugu88/BaoDian/blob/master/app/src/main/res/mipmap-xxhdpi/main_gg.jpeg)

---

## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=Pangu-Immortal/BaoDian&type=Date)](https://star-history.com/#Pangu-Immortal/BaoDian&Date)

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request
## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
