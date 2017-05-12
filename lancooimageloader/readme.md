## Lancoo-Image-Loader简介

* Lancoo-Image-Loader 是自由学习片区推出的一款图片加载框架，它目前拥有以下功能:
  - 支持设置错误/加载图片.
  - 支持gif.
  - 支持回收被view持有, 但被Mem Cache移除的图片, 减少页面回退时的闪烁.


## 使用

### Step 1

在build.gradle中添加依赖.
```javascript
compile project(':lancooimagelodaer')
```

### Step 2

在Manifest中添加必须的权限.
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```
### Step 3

初始化.
```java
// 在application的onCreate中初始化
@Override
public void onCreate() {
    super.onCreate();
    LancooImageLoader.init(this);
    ...
}
```

### Step 4

使用.
```java
LancooImageLoader.bind(imageView, url);
LancooImageLoader.bind(imageView, url, imageOptions);
// assets file
LancooImageLoader.bind(imageView, "assets://test.gif", imageOptions);

// local file
LancooImageLoader.bind(imageView, new File("/sdcard/test.gif").toURI().toString(), imageOptions);
LancooImageLoader.bind(imageView, "/sdcard/test.gif", imageOptions);
LancooImageLoader.bind(imageView, "file:///sdcard/test.gif", imageOptions);
LancooImageLoader.bind(imageView, "file:/sdcard/test.gif", imageOptions);
```

## 关于作者


* 片区：自由学习
* 姓名：罗驰正