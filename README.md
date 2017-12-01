
ConstraintLayout，让布局更优雅。
### 一、为什么要用ConstraintLayout
![image.jpg](https://note.youdao.com/yws/public/resource/3631121d9411925634e11c66f1e8f601/xmlnote/C3F2CE1DACBD405BB47638C811E32C48/11854)

上图是网易100分的选课首页，在Banner图的下部是推荐类目模块，其中数学、语言、小低和小高分别是推荐类目Item。可见每个类目的子类目个数是不确定的，根据个数的不同，子类目的排列方式也不一样。

现在我们来实现Item的布局。如果用LinearLayout、RelativeLayout和FrameLayout去实现Item布局，我目前想到的最低也需要两层布局。如下所示：

```
<Relative>  
    <ImageView />
    <TextView />
    <LinearLayout>
        <TextView />
        <TextView />
        <TextView />
    </LinearLayout>
    <LinearLayout>
        <TextView />
        <TextView />
    </LinearLayout>
</Relative>
```
可以发现没有一种布局容器是可以单靠自己搞定这个布局的，需要嵌套不同布局。这样布局层级增加，布局计算时间也加长了。这些都是传统布局存在的问题，概括起来有以下三点：

+ 复杂布局能力差，需要不同布局嵌套使用。 
+ 布局嵌套层级高。不同布局的嵌套使用，导致布局的嵌套层级偏高。
+ 页面性能低。较高的嵌套层级，需要更多的计算布局时间，降低了页面性能。

正是由于目前布局容器存在的问题，我们需要寻找一种可以解决这些问题的布局容器。正好，ConstraintLayout可以。

### 二、ConstraintLayout是什么
ConstraintLayout，中文称约束布局，在2016年Google I/O大会时提出，2017年2月发布正式版，目前稳定版本为1.0.2。约束布局作为Google今后主推的布局样式，可以完全替代其他布局，降低页面布局层级，提升页面渲染性能。

### 三、怎么用ConstraintLayout
#### 3.1 环境搭建
ConstraintLayout支持最低Android Studio版本是2.2，但是有些属性在2.2的布局编辑器上不支持编辑，如比例和baseline等约束。所以推荐使用2.3的版本，当然3.0的版本那就更好了。要使用ConstraintLayout，需要在项目中进行如下配置：

+ 在项目外层定义google maven仓库

```
repositories {
    maven {
        url 'https://maven.google.com'
    }
}
```

+ 在要使用ConstraintLayout的module的build.gradle文件中引入约束布局库

```
dependencies {
    compile 'com.android.support.constraint:constraint-layout:1.0.2'
}

```

#### 3.2 布局引入
按照上述配置好环境后，我们就可以在项目中使用ConstraintLayout了。有两种方式使用：
 
1. layout转换的方式使用
	+ 首先，打开一个非ConstraintLayout的布局文件，切换到`Design Tab`
	
	+ 在Component Tree窗口，选中要转换的layout文件根布局，点击右键，然后选择**Convert layout to ConstraintLayout**

2. 直接新建一个layout文件使用 

通过如下方式引入约束布局:

```
<android.support.constraint.ConstraintLayout

/>
```

#### 3.3 属性介绍
ConstraintLayout的布局属性，乍一看有很多，其实可以分为8个部分，下面一一介绍。
##### 3.3.1 相对位置
```
layout_constraintLeft_toLeftOf
layout_constraintLeft_toRightOf
layout_constraintRight_toLeftOf
layout_constraintRight_toRightOf
layout_constraintTop_toTopOf
layout_constraintTop_toBottomOf
layout_constraintBottom_toTopOf
layout_constraintBottom_toBottomOf
layout_constraintBaseline_toBaselineOf
layout_constraintStart_toEndOf
layout_constraintStart_toStartOf
layout_constraintEnd_toStartOf
layout_constraintEnd_toEndOf
```

以上这些属性，用于设置一个控件相对于其他控件、Guideline或者父容器的位置。以`layout_constraintLeft_toLeftOf`为例，其中`layout_`部分是固定格式，主要的信息包含在下面两部分：

+ constraintXXX：指定当前控件需要设置约束的属性部分。如`constraintLeft`表示对当前控件的**左边**进行约束设置。
+ toXXXOf：其指定的内容是作为当前控件设置约束需要依赖的控件或父容器（可以理解为设置约束的参照物）。并通过`XXX`指定被依赖对象用于参考的属性。如`toLeftOf="parent"` ：表示当前控件相对于父容器的左边进行约束设置。 

ConstraintLayout的相对位置布局比较灵活，相比于RelativeLayout，ConstraintLayout可以通过`layout_constraintBaseline_toBaselineOf`设置两个控件之间的文字相对于baseline对齐。一个布局效果的例子，如下所示：

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/activity_relative_position"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="zr.com.constraintdemo.normal.RelativePositionActivity">

    <Button
        android:id="@+id/btn_A"
        android:text="A"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        />

    <Button
        android:text="在A下方,与A左对齐"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toBottomOf="@id/btn_A"
        app:layout_constraintLeft_toLeftOf="@id/btn_A"
        android:layout_marginTop="32dp"
        />

    <Button
        android:text="在A上方,与A居中对齐"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toTopOf="@id/btn_A"
        app:layout_constraintLeft_toLeftOf="@id/btn_A"
        app:layout_constraintRight_toRightOf="@id/btn_A"
        android:layout_marginBottom="32dp"
        />

    <Button
        android:text="baseline对齐"
        android:layout_width="wrap_content"
        android:layout_height="80dp"
        app:layout_constraintBaseline_toBaselineOf="@id/btn_A"
        app:layout_constraintLeft_toLeftOf="parent"
        android:layout_marginLeft="8dp"
        android:gravity="bottom"
        />

    <Button
        android:text="水平居中对齐"
        android:layout_width="wrap_content"
        android:layout_height="80dp"
        android:gravity="bottom"
        app:layout_constraintTop_toTopOf="@id/btn_A"
        app:layout_constraintBottom_toBottomOf="@id/btn_A"
        app:layout_constraintLeft_toRightOf="@id/btn_A"
        android:layout_marginLeft="16dp"
        />

</android.support.constraint.ConstraintLayout>
```

![相对布局例子图](https://note.youdao.com/yws/public/resource/3631121d9411925634e11c66f1e8f601/xmlnote/79E0096195E2478290801796FDD17477/12340)

##### 3.3.2 边距
在ConstraintLayout中，控件除了可以设置普通的边距属性，还可以设置当控件依赖的控件GONE之后的边距属性。即我们可以理解可以根据被依赖控件是否GONE的状态，设置两种边距值。分别通过如下属性进行设置：

+ 普通边距属性

```
android:layout_marginStart
android:layout_marginEnd
android:layout_marginLeft
android:layout_marginTop
android:layout_marginRight
android:layout_marginBottom
```

+ 被依赖控件GONE之后的边距属性

```
layout_goneMarginStart
layout_goneMarginEnd
layout_goneMarginLeft
layout_goneMarginTop
layout_goneMarginRight
layout_goneMarginBottom
```

这种特性，可以比较方便实现一些特定的需求，且无需代码中进行额外设置。如B控件依赖A，A距离父容器左边20dp，B在A右边，距离A为20dp。需求当A设置为GONE之后，B距离父容器左边60dp。这在ConstraintLayout中实现起来就很简单，对B同时设置如下属性即可：

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/activity_margin"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="zr.com.constraintdemo.normal.MarginActivity">
    <Button
        android:id="@+id/btn_a"
        android:text="A"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_marginTop="100dp"
        />

    <Button
        android:text="B"
        android:textAllCaps="false"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintLeft_toRightOf="@id/btn_a"
        app:layout_constraintTop_toTopOf="parent"
        android:layout_marginLeft="20dp"
        app:layout_goneMarginLeft="60dp"
        android:layout_marginTop="100dp"
        />

</android.support.constraint.ConstraintLayout>
```

##### 3.3.3 居中
+ 水平居中：相对一个控件或者父容器左右对齐

```
app:layout_constraintLeft_toLeftOf="parent"
app:layout_constraintRight_toRightOf="parent
```

+ 垂直居中：相对一个控件或者父容器左右对齐

```
app:layout_constraintTop_toTopOf="parent"
app:layout_constraintBottom_toBottomOf="parent"
```
例子：

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/activity_center_position"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="zr.com.constraintdemo.normal.CenterPositionActivity">

    <Button
        android:text="水平居中"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        />

    <Button
        android:text="垂直居中"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        />

    <Button
        android:text="水平垂直居中"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        />

</android.support.constraint.ConstraintLayout>
```

![实现效果截图](https://note.youdao.com/yws/public/resource/3631121d9411925634e11c66f1e8f601/xmlnote/9A040A863DF842FDBA401C3003F29C2D/12342)

##### 3.3.4 偏移
在设置控件的居中属性之后，通过偏移属性可以设置让控件更偏向于依赖控件的某一方，偏移设置为0～1之间的值。相应属性：

```
layout_constraintHorizontal_bias // 水平偏移
layout_constraintVertical_bias   // 垂直偏移
```
例子：

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/activity_bias"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="zr.com.constraintdemo.normal.BiasActivity">

    <Button
        android:text="水平偏移30%"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintHorizontal_bias="0.3"
        />

    <Button
        android:text="垂直偏移30%"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintVertical_bias="0.3"
        />

    <Button
        android:text="水平垂直偏移70%"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintVertical_bias="0.7"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintHorizontal_bias="0.7"
        />

</android.support.constraint.ConstraintLayout>
```

![偏移截图](https://note.youdao.com/yws/public/resource/3631121d9411925634e11c66f1e8f601/xmlnote/9B9FACCBE4454D1A946EA46AB0816359/12345)

##### 3.3.5 可见性
可见性这个属性大家应该很熟悉，但是约束布局的可见性属性和其它布局相比，存在以下区别：

+ 当控件设为GONE时，被认为尺寸为0。可以理解为布局上的一个点。

+ 若GONE的控件对其它控件有约束，则约束保留并生效，但所有的边距（margin）会清零。

##### 3.3.6 尺寸
###### 几种设置方式：

+ 设置固定尺寸，如123dp
+ 使用`wrap_content`，根据内容计算合适大小
+ `match_parent`，填充满父布局，此时设置的约束都不生效了。（早之前的约束布局版本貌似不允许在其子view中使用match_parent属性，但是我写文章的时候发现也是可以用上去的）
+ 设置0dp，相当于MATCH_CONSTRAINT属性，基于约束最终确定大小

###### MATH_CONSTRAINT

+ `layout_constraintWidth_min`和`layout_constraintHeight_min`：设置最小值
+ `layout_constraintWidth_max`和`layout_constraintHeight_max`：设置最大值
+ `layout_constraintWidth_percent`和`layout_constraintHeight_percent`：设置控件相对于父容器的百分比大小（1.1.0开始支持）。使用之前需要先设置为百分比模式，然后设置设置宽高值为0～1之间。
	
	设置为百分比模式的属性：
	
    ```
app:layout_constraintWidth_default="percent" 
	app:layout_constraintHeight_default="percent" 
	```

+ 强制约束  
当一个控件设为wrap_content时，再添加约束尺寸是不起效果的。如需生效，需要设置如下属性为true:

```	
app:layout_constrainedWidth=”true|false”	 
app:layout_constrainedHeight=”true|false”
```
	 
看个具体例子：

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/activity_dimen"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="zr.com.constraintdemo.normal.DimenActivity">

    <Button
        android:id="@+id/btn_1"
        android:text="minWidth设置为200dp"
        android:textAllCaps="false"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        android:minWidth="200dp"
        />

    <Button
        android:id="@+id/btn_2"
        android:text="设置为MATCH_CONSTRAINT"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toBottomOf="@id/btn_1"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        />

    <Button
        android:id="@+id/btn_3"
        android:textAllCaps="false"
        android:text="layout_constrainedWidth开启"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toBottomOf="@id/btn_2"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constrainedWidth="true"
        app:layout_constraintWidth_min="300dp"
        />
    <Button
        android:id="@+id/btn_4"
        android:textAllCaps="false"
        android:text="layout_constrainedWidth关闭"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toBottomOf="@id/btn_3"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintWidth_min="300dp"
        />
    <Button
        android:id="@+id/btn_5"
        android:textAllCaps="false"
        android:text="宽50%高30%布局"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintTop_toBottomOf="@id/btn_4"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintWidth_default="percent"
        app:layout_constraintHeight_default="percent"
        app:layout_constraintWidth_percent="0.5"
        app:layout_constraintHeight_percent="0.3"
        />

</android.support.constraint.ConstraintLayout>
```

![dimen截图](https://note.youdao.com/yws/public/resource/3631121d9411925634e11c66f1e8f601/xmlnote/EFA55FC84CCB4EDA9839268B388AD66F/12347)

##### 3.3.7 比例
控件可以定义两个尺寸之间的比例，目前支持宽高比。
前提条件是至少有一个尺寸设置为0dp，然后通过`layout_constraintDimentionRatio`属性设置宽高比。设置方式有以下几种：

+ 直接设置一个float值，表示宽高比
+ 以” width：height”形式设置
+ 通过设置前缀W或H，指定一边相对于另一边的尺寸，如”H, 16:9”，高比宽为16:9

如果宽高都设置为0dp，也可以用ratio设置。这种情况下控件会在满足比例
约束的条件下，尽可能填满父布局。

下面看个例子：

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="zr.com.constraintdemo.normal.RatioActivity">

    <Button
        android:id="@+id/btn_1"
        android:text="宽高比设置为2:1"
        android:layout_width="wrap_content"
        android:layout_height="0dp"
        app:layout_constraintDimensionRatio="1:1"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        />

    <Button
        android:id="@+id/btn_2"
        android:text="宽高都设置为0dp，高宽比是16:9"
        android:textAllCaps="false"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintDimensionRatio="H,16:9"
        app:layout_constraintTop_toBottomOf="@id/btn_1" />

</android.support.constraint.ConstraintLayout>
```

![实现截图](https://note.youdao.com/yws/public/resource/3631121d9411925634e11c66f1e8f601/xmlnote/252B40A7A16B4DA68F7F4AA74C1362A9/12349)

##### 3.3.8 链
链这个概念是约束布局新提出的，它提供了在一个维度（水平或者垂直），管理一组控件的方式。

###### 创建一个链
多个view在同一个方向上双向引用。如下图所示：水平方向A、B、C，A位于B左边，B位于A右边，他们就是一对双向引用。同理B和C也是。

![](https://note.youdao.com/yws/public/resource/3631121d9411925634e11c66f1e8f601/xmlnote/21B73D286A0444E7BBC16931B08EB4B9/11919)

双向引用布局代码如下所示。A通过`app:layout_constraintRight_toLeftOf="@+id/btn_2"`引用右边的B，B通过`app:layout_constraintLeft_toRightOf="@+id/btn_1"`引用A。

```
<Button
    android:id="@+id/btn_1"
    android:text="A"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:layout_constraintTop_toBottomOf="@id/tv_spread"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toLeftOf="@+id/btn_2"
    app:layout_constraintHorizontal_chainStyle="spread_inside"
    />

<Button
    android:id="@+id/btn_2"
    android:text="B"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:layout_constraintTop_toBottomOf="@id/tv_spread"
    app:layout_constraintLeft_toRightOf="@+id/btn_1"
    app:layout_constraintRight_toLeftOf="@+id/btn_3"
    />
    
    ...
```



###### 链头
最左边或最上面的控件，链的属性由链头控制。

###### 设置
通过`layout_constraintHorizontal_chainStyle`和`layout_constraintVertical_chainStyle`在链的第一个元素上设置。默认spread样式。如上所示，A作为链头，设置了chainStyle：`app:layout_constraintHorizontal_chainStyle="spread_inside"`。

几种链的样式如下图所示：

![链的展示图](https://note.youdao.com/yws/public/resource/3631121d9411925634e11c66f1e8f601/xmlnote/E36A1DFFD2AA45B196674CB70ABF2427/11924)

链的布局代码比较多，大家可以看demo。主要是通过修改**链头**的chainStyle样式改变链的类型。

#### 3.4 Guideline
可以理解为布局辅助线，用于布局辅助，不在设备上显示。

有垂直和水平两个方向（android:orientation=“vertical/horizontal”）

+ 垂直：宽度为0，高度等于父容器
+ 水平：高度为0，宽度等于父容器

有三种放置Guideline的方式：

+ 给定距离左边或顶部一个固定距离（`layout_constraintGuide_begin`）
+ 给定距离右边或底部一个固定距离（`layout_constraintGuide_end`）
+ 给定宽高一个百分比距离（`layout_constraintGuide_percent`）

看例子：

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_guideline"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="zr.com.constraintdemo.GuidelineActivity">

    <!-- 垂直Guideline -->
    <android.support.constraint.Guideline
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/guideline"
        app:layout_constraintGuide_percent="0.5"
        android:orientation="vertical"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:text="GuideLine左边"
        android:textAllCaps="false"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/btn_1"
        app:layout_constraintRight_toLeftOf="@+id/guideline"
        android:layout_marginTop="16dp"
        app:layout_constraintTop_toTopOf="parent"
        />

    <Button
        android:text="GuideLine右边"
        android:textAllCaps="false"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/btn_2"
        app:layout_constraintLeft_toRightOf="@+id/guideline"
        android:layout_marginTop="16dp"
        app:layout_constraintTop_toTopOf="parent"
        />

    <!-- 水平Guideline -->
    <android.support.constraint.Guideline
        android:id="@+id/h_guideline"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintGuide_begin="200dp"
        />

    <Button
        android:text="Guideline上面"
        android:textAllCaps="false"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toTopOf="@id/h_guideline"
        app:layout_constraintLeft_toLeftOf="parent"
        />

    <Button
        android:text="Guideline下面"
        android:textAllCaps="false"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toBottomOf="@id/h_guideline"
        app:layout_constraintLeft_toLeftOf="parent"
        />

</android.support.constraint.ConstraintLayout>

```

![](https://note.youdao.com/yws/public/resource/3631121d9411925634e11c66f1e8f601/xmlnote/6C9B1F383B6B45A196567AE398FA8525/12353)

#### 3.5 代码中设置约束
通过ConstraintSet，允许在代码中进行约束设置，进行布局变换。（API 19及以上支持trasmition动画）

创建ConstraintSet对象的几种方式：

+ 手动

```
c = new ConstraintSet(); 
c.connect(....);
```

+ 通过一个R.layout.xxx对象

```
c.clone(context, R.layout.layout1);
```

+ 通过一个ConstraintLayout对象

```
c.clone(clayout);
```

布局变化开启平滑动画的方式：

```
TransitionManager.beginDelayedTransition(constraintLayout);
```
其中参数constraintLayout表示动画作用的约束布局对象。

看个例子：

```
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        // 4.4以上开启布局切换动画
        TransitionManager.beginDelayedTransition(constraintLayout);

// 清空margin
        applyConstraintSet.setMargin(R.id.btn_1, ConstraintSet.START, 0);
        applyConstraintSet.setMargin(R.id.btn_1, ConstraintSet.END, 0);
        applyConstraintSet.setMargin(R.id.btn_2, ConstraintSet.START, 0);
        applyConstraintSet.setMargin(R.id.btn_2, ConstraintSet.END, 0);
        applyConstraintSet.setMargin(R.id.btn_3, ConstraintSet.START, 0);
        applyConstraintSet.setMargin(R.id.btn_3, ConstraintSet.END, 0);

// 全部相对于父容器居中
        applyConstraintSet.centerHorizontally(R.id.btn_1, R.id.activity_constraint_set);
        applyConstraintSet.centerHorizontally(R.id.btn_2, R.id.activity_constraint_set);
        applyConstraintSet.centerHorizontally(R.id.btn_3, R.id.activity_constraint_set);
        applyConstraintSet.applyTo(constraintLayout);
    }
```
看下在4.4系统以上动画的一个效果：

![4.4以上和以下切换的gif](http://upload-images.jianshu.io/upload_images/703392-3dbd8ac4cbd46e95.gif?imageMogr2/auto-orient/strip)


更多ConstraintSet例子，推荐看[这篇文章](http://www.jianshu.com/p/575829baa39d)

### 四、开始实践
说了这么多，那么约束布局用起来到底怎么样呢？下面我们来实践下：
#### 前面类目的Item布局具体实现  

我们先来分析下类目Item，可以将类目Item分为两个部分：父类目和子类目两部分。父类目包括图片icon和文字描述。子类目包含根据个数布局可变的按钮。很明显，父类目通过约束布局的相对位置约束设置可以实现。子类目中的子控件，可以以父布局中的某个控件和子类目中其他子控件为参照物（依赖参照对象）实现布局。总共放置两排的按钮，第一排3个，第二排2个，宽度设置为MATH_CONSTRAINT。然后在代码中根据子类目的个数，设置相应按钮的可见性即可实现Item根据子类目个数展示不同布局的效果。

**布局XML：**
   
```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:paddingTop="7.5dp"
    android:paddingBottom="7.5dp"
    android:paddingLeft="12.5dp"
    android:paddingRight="12.5dp"
    android:clipToPadding="false"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <ImageView
        android:id="@+id/img_icon"
        android:layout_width="20dp"
        android:layout_height="20dp"
        android:layout_marginLeft="5dp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:src="@mipmap/ic_launcher"
        />

    <TextView
        android:id="@+id/tv_parent_category_name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="5dp"
        android:text="少儿编程"
        android:textSize="15sp"
        android:textColor="#333333"
        app:layout_constraintBottom_toBottomOf="@id/img_icon"
        app:layout_constraintLeft_toRightOf="@id/img_icon"
        app:layout_constraintTop_toTopOf="@id/img_icon" />

    <!-- 子类目布局开始 -->
    <Button
        android:id="@+id/btn_one"
        android:layout_width="0dp"
        android:layout_height="40dp"
        android:layout_marginTop="10dp"
        android:text="A"
        app:layout_constraintTop_toBottomOf="@id/img_icon"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toLeftOf="@+id/btn_two" />

    <Button
        android:id="@+id/btn_two"
        android:layout_width="0dp"
        android:layout_height="40dp"
        android:text="B"
        app:layout_constraintLeft_toRightOf="@id/btn_one"
        app:layout_constraintRight_toLeftOf="@+id/btn_three"
        app:layout_constraintTop_toTopOf="@id/btn_one" />

    <Button
        android:id="@+id/btn_three"
        android:layout_width="0dp"
        android:layout_height="40dp"
        android:text="C"
        app:layout_constraintLeft_toRightOf="@id/btn_two"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="@id/btn_two" />

    <!-- 第二排 -->
    <Button
        android:id="@+id/btn_four"
        android:layout_width="0dp"
        android:layout_height="40dp"
        android:layout_marginTop="10dp"
        android:text="D"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toLeftOf="@+id/btn_five"
        app:layout_constraintTop_toBottomOf="@id/btn_one" />

    <Button
        android:id="@+id/btn_five"
        android:layout_width="0dp"
        android:layout_height="40dp"
        android:text="E"
        app:layout_constraintLeft_toRightOf="@+id/btn_four"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="@id/btn_four" />


</android.support.constraint.ConstraintLayout>
```
**实现效果：**  
![实现类目效果截图](https://note.youdao.com/yws/public/resource/3631121d9411925634e11c66f1e8f601/xmlnote/WEBRESOURCE324b30e2b024250f989452277d256fb8/12485)


#### 下一道练习
![课程列表](https://note.youdao.com/yws/public/resource/3631121d9411925634e11c66f1e8f601/xmlnote/WEBRESOURCE5ce65b1551c73201796b07309e45a2ae/12355)

要求：图片宽高比16:9，图片宽度固定110dp。

分析：宽高比16:9，需要比例布局；其他都是一些位置关系，用约束布局相对位置的一些约束可以实现。

具体实现：

**布局XML：**

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:paddingLeft="15dp"
    android:paddingTop="12dp">

    <ImageView
        android:id="@+id/iv_course"
        android:layout_width="110dp"
        android:layout_height="0dp"
        android:scaleType="fitXY"
        android:src="@mipmap/test"
        app:layout_constraintDimensionRatio="16:9"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/tv_course_name"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginLeft="10dp"
        android:layout_marginRight="15dp"
        android:ellipsize="end"
        android:maxLines="2"
        android:textColor="#333333"
        android:textSize="15sp"
        app:layout_constraintLeft_toRightOf="@id/iv_course"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="@id/iv_course"
        tools:text="六年级单元过关检测六年级单元过关检测六年级单元过关检测" />

    <TextView
        android:id="@+id/tv_signature"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginRight="15dp"
        android:layout_marginTop="5dp"
        android:ellipsize="end"
        android:maxLines="1"
        android:textColor="#666666"
        android:textSize="12sp"
        app:layout_constraintLeft_toLeftOf="@id/tv_course_name"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@id/tv_course_name"
        tools:text="签名" />

    <TextView
        android:id="@+id/tv_content"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginRight="15dp"
        android:layout_marginTop="5dp"
        android:ellipsize="end"
        android:maxLines="1"
        android:textColor="#666666"
        android:textSize="12sp"
        app:layout_constraintLeft_toLeftOf="@id/tv_course_name"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@id/tv_signature"
        tools:text="内容内容内容内容内容内容内容内容内容内容" />

    <TextView
        android:id="@+id/tv_current_price"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="5dp"
        android:maxLines="1"
        android:textColor="#f6454a"
        android:textSize="15sp"
        app:layout_constraintLeft_toLeftOf="@id/tv_course_name"
        app:layout_constraintTop_toBottomOf="@id/tv_content"
        tools:text="¥ 480" />

    <TextView
        android:id="@+id/tv_origin_price"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="5dp"
        android:maxLines="1"
        android:textColor="#999999"
        android:textSize="12sp"
        app:layout_constraintBottom_toBottomOf="@id/tv_current_price"
        app:layout_constraintLeft_toRightOf="@id/tv_current_price"
        tools:text="¥ 1480" />

</android.support.constraint.ConstraintLayout>
```

实现截图：

![实现截图](https://note.youdao.com/yws/public/resource/3631121d9411925634e11c66f1e8f601/xmlnote/WEBRESOURCEba3146128194986d8aebcb43071cf0dc/12487)

#### 复杂度升级
要求：图片宽度占整个布局30%，宽高比16:9。

分析：看到30%，首先考虑的是百分比布局，但是图片右边的view较多，每个都是设置一边百分比，实在是麻烦。因此，可以考虑使用Guideline，设置Guideline垂直，并距离父容器左边30%的距离，之后布局通过Guideline设置约束即可。

**布局XML：**

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:paddingLeft="15dp"
    android:paddingTop="12dp">

    <android.support.constraint.Guideline
        android:id="@+id/guideline"
        android:orientation="vertical"
        app:layout_constraintGuide_percent="0.3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

    <ImageView
        android:id="@+id/iv_course"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:scaleType="fitXY"
        android:src="@mipmap/test"
        app:layout_constraintDimensionRatio="16:9"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="@id/guideline"
        app:layout_constraintTop_toTopOf="parent" />
    ...
</android.support.constraint.ConstraintLayout>
```

#### 复杂度再升级
要求：在之前基础上，底部加一根横线用于分隔，要求线与上面最近的控件距离是15dp。

分析：由于文字内容是可变的，当文字内容多的时候，线可能距离文字近；若文字不多，线也可能距离图片近。这个时候，基于当前最新1.0.2稳定版本的约束布局已经不能满足我们实现一层布局了，还是需要将图片和文字整体放入一个布局容器中，然后横线依赖这个布局容器设置约束实现，嵌套好像在所难免了。然而，当约束布局1.1.0稳定版本发布时，这问题也可以得到解决。我们先来看看在1.1.0上是怎么实现的：

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:paddingLeft="15dp"
    android:paddingTop="12dp">

    ...

    <android.support.constraint.Barrier
        android:id="@+id/barrier"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:barrierDirection="bottom"
        app:constraint_referenced_ids="iv_course, tv_origin_price"
        />

    <View
        android:layout_width="match_parent"
        android:layout_height="1px"
        android:background="#d8d8d8"
        android:layout_marginLeft="15dp"
        android:layout_marginTop="12dp"
        app:layout_constraintTop_toBottomOf="@id/barrier"
        />

</android.support.constraint.ConstraintLayout>
```
原来可以通过Barrier实现，那么Barrier是什么？请往下看。

### 五、性能怎么样？

本文主要介绍ConstraintLayout的使用，因此也不大篇幅讲述性能相关内容。

+ 直观可见的一点是，同样一种复杂布局，相对于传统布局方式，ConstraintLayout的布局层级减少了。
+ 具体一些性能的对比，如渲染速度和计算次数等，可以看这篇文章[《了解使用 ConstraintLayout 的性能优势》](http://developers.googleblog.cn/2017/09/constraintlayout.html)。通过结论可知使用了ConstraintLayout，布局计算次数降低了，渲染速度也相应提升了。

### 六、布局编辑器
从Android studio 2.2版本开始，布局编辑器支持拖拽的方式进行约束布局。但是在2.2上布局编辑器还不是很完善，部分约束不能设置，只能通过xml输入方式实现。因此推荐用版本为2.3或者更高的Android studio。

限于篇幅，这里就不展开介绍布局编辑器了。在这里推荐两篇文章，分别是[ConstraintLayout 终极秘籍（下）](http://blog.chengyunfeng.com/?p=1031)和[ Android新特性介绍，ConstraintLayout完全解析](http://blog.csdn.net/guolin_blog/article/details/53122387)。看完这两篇，大家应该对布局编辑器就会有比较深入的了解了。

### 七、ConstraintLayout使用小结
在使用约束布局的过程中，有一些需要强调的点和碰到的一些坑分享给大家。

#### 7.1 margin只能设置正值或者0，负值无效
我们之前实现重叠布局时，会通过设置负的margin值实现。但是在约束布局中，负的margin值不会生效，只能设置0或者大于0的值，小于0也当作0处理。

#### 7.2 链的书写方式注意
一般布局我们都是遵守**先定义，后使用**原则，但是约束布局实现链时，这个原则就遵守不了了。这个时候如果还是按照常规的`@id/btn_2`的方式指定依赖控件（这个控件在当前控件之后声明的），就会报**Error:(23, 46) No resource found that matches the given name**错误。解决方案其实很简单，只需要修改指定方式如下：`@+id/btn_2`即可。

#### 7.3 ConstraintSet动画Api支持等级
在代码中设置控件约束，可以通过ConstraintSet实现。约束变了之后，布局肯定会跟着变。`TransitionManager.beginDelayedTransition`提供了平滑动画变换布局的能力，但是只支持Api 19及以上的版本。

#### 7.4 自定义guideLine
对Guideline设置相对位置属性是不生效的，因此当我们想要一个相对于某个view的Guideline时，约束布局是不能满足我们的要求的。
看Guideline源码：

```
public class Guideline extends View {
    public Guideline(Context context) {
        super(context);
        super.setVisibility(8);
    }
    ...    
}
```
发现Guideline是一个不可见的view，那么我们可以布局时放置一个不可见的view来作为Guideline的替代品，实现一些特殊布局要求。如布局重叠：

```
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/activity_bias"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="zr.com.constraintdemo.normal.BiasActivity">

    <Button
        android:id="@+id/btn_a"
        android:text="A"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:background="@color/colorAccent"
        />

    <View
        android:id="@+id/view"
        android:layout_width="match_parent"
        android:layout_height="1px"
        app:layout_constraintBottom_toBottomOf="@id/btn_a"
        android:layout_marginBottom="40dp"
        />

    <Button
        android:text="B"
        android:background="@color/colorPrimary"
        android:layout_width="wrap_content"
        android:layout_height="200dp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@id/view"
        />
</android.support.constraint.ConstraintLayout>
```
![](https://note.youdao.com/yws/public/resource/3631121d9411925634e11c66f1e8f601/xmlnote/WEBRESOURCEf476f674330a1ca0a1b18925cb23a2b3/12441)

这种方式可以弥补margin不能设置为负值的不足，而且并没有增加布局层级。

#### 7.5 区分0dp、`match_parent`和`MATCH_CONSTRAINT`
+ 0dp等价于MATCH_CONSTRAINT，对控件设置其它尺寸相关约束会生效。如`app:layout_constraintWidth_min`等约束。
+ match_parent，填充满父布局，之后设置约束属性无效。

#### 7.6 使用布局编辑器多出了一些属性

```
layout_optimizationLevel
layout_editor_absoluteX
layout_editor_absoluteY
layout_constraintBaseline_creator
layout_constraintTop_creator
layout_constraintRight_creator
layout_constraintLeft_creator
layout_constraintBottom_creator
```
这几个属性是 UI 编辑器所使用的，用了辅助拖拽布局的，在实际使用过程中，可以不用关心这些属性。

### 八、即将到来的一些有意思的特性
最新的约束布局beta版本，已经出到了1.1.0-beta3。在将来约束布局1.1.0版本发布后，其中会包含一下一些有意思的特性，让人看了充满期待。我们先来一睹为快：

  + Barrier  
  [Barrier](https://developer.android.com/reference/android/support/constraint/Barrier.html)是一个虚拟的辅助控件，它可以阻止一个或者多个控件越过自己，就像一个屏障一样。当某个控件要越过自己的时候，Barrier会自动移动，避免自己被覆盖。
  
  + Group  
  [Group](https://developer.android.com/reference/android/support/constraint/Group.html)帮助你对一组控件进行设置。最常见的情况是控制一组控件的visibility。你只需把控件的id添加到Group，就能同时对里面的所有控件进行操作。
  
  + Circular positioning   
  可以相对另一个控件，以角度和距离定义当前控件的位置，即提供了在圆上定义控件位置的能力。如图所示：
  
  ![](https://note.youdao.com/yws/public/resource/3631121d9411925634e11c66f1e8f601/xmlnote/WEBRESOURCE1209fee70f88bd12d9ef105948fafc14/12481)
  
  + Placeholder  
  [Placeholder](https://developer.android.com/reference/android/support/constraint/Placeholder.html)顾名思义，就是用来一个占位的东西，它可以把自己的内容设置为ConstraintLayout内的其它view。因此它用来写布局的模版，也可以用来动态修改UI的内容。
  
  + 百分比布局  
  允许设置控件占据可用空间的百分比，大大增加布局灵活度和适配性。

总而言之，约束布局的能力正在变得越来越强大。


### 九、最后
曾几何时，对于复杂布局，很多时候不是一种布局就可以解决。这时需要考虑布局嵌套，又或者需要在代码中动态设置控件宽高比，无形中增加了开发的复杂性和布局的嵌套层级，进而影响了页面性能。随着google推出了ContraintLayout，上述的问题大部分都可以得到有效的解决。

总的来说，ConstraintLayout优势如下：

+ 布局高效
+ 轻松应对复杂布局
+ 嵌套层级少
+ 适配性好

本人通过在项目中的实践，真切体会到了ConstraintLayout应对复杂布局和自适应页面的强大能力，不但降低了布局难度，而且提升了开发效率。开发过程中基本没怎么踩**深坑**，因此也很推荐大家在项目中去使用ConstraintLayout布局。

附上demo的链接[https://github.com/yushiwo/ConstraintDemo](https://github.com/yushiwo/ConstraintDemo)，当然更建议大家自己去写一遍，可以加深印象。


### 参考文献
1. [ConstraintLayout 终极秘籍（上）](http://blog.chengyunfeng.com/?p=1030)
2. [ConstraintLayout 终极秘籍（下）](http://blog.chengyunfeng.com/?p=1031)
3. [了解使用 ConstraintLayout 的性能优势](http://developers.googleblog.cn/2017/09/constraintlayout.html)
4. [[译]Constraint Layout 动画 |动态 Constraint |用 Java 实现的 UI（这到底是什么）[第三部分]](http://www.jianshu.com/p/575829baa39d)
5. [Constraint Layout 1.1.x带来了哪些新东西？](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/1019/8618.html)
6. [当然还有官方文档啦]()
