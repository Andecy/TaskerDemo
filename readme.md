## Tasker简介

* Tasker 是自由学习片区推出的一款作业作答/展示框架，它目前拥有以下功能:
  - 上下分屏大题/小题区域，支持作答、展示作答内容以及展示标准答案和解析.
  - 多音频列表播放，音频被放置于大题区域.
  - 大小题选择列表，可以展示作答进度和当前所在题目.
  - 大题题干、小题题目支持富文本，也可个性化定制小题展示页.

----
## 使用

### Step 1

在build.gradle中添加依赖.
```javascript
compile project(':tasker')
```

### Step 2

在Manifest中添加必须的权限.
```xml
<uses-permission android:name="android.permission.INTERNET" />
```
### Step 3

初始化.
```java
// 在application的onCreate中初始化
@Override
public void onCreate() {
    super.onCreate();
    Tasker.init(this);
    ...
}
```

### Step 4

在布局中添加Tasker.
```xml
    <com.lancoo.tasker.TaskView
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

### 代码中使用的一些参考

```java

TaskView mTaskView = (TaskView) findViewById(R.id.tasker_demo);

SimpleItemAdapter mSimpleItemAdapter = new SimpleItemAdapter(getSupportFragmentManager(),
                new KSTaskTimu(), new KSTaskAnswer());

        mSimpleItemAdapter.setAnswerable(switch_answerable.isChecked());
        mSimpleItemAdapter.setAnalysisable(switch_standardable.isChecked());

        mTaskView.setItemAdapter(mSimpleItemAdapter);

        mTaskView.setTaskTitleName("(试卷总分：5.0分)");

        mTaskView.setTaskListener(new TaskView.TaskListener() {

            @Override
            public void onTimuChanged(int topicPosition, int itemPosition) {
                Log.w("TAG", "topicPos-->" + topicPosition + ",itemPos-->" + itemPosition);
            }

            @Override
            public void onAudioPlayError(MediaPlayer mp, int what, int extra) {
                ToastUtils.showLongToast("播放出错");
            }

        });

        mTaskView.setOnItemSwitchClickListener(new TaskView.ItemSwitchListener() {
            @Override
            public void onItemSwitcherClick(View view) {
                mTaskView.showItemSelectView();
                ToastUtils.showLongToast("点击小题切换啦");
            }
        });

```

----
## 关于作者


* 片区：自由学习
* 姓名：罗驰正