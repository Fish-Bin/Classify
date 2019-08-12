# 一.配置方式
## 1.环境：studio版本3.4.1
   Gradle plugin version: classpath 'com.android.tools.build:gradle:3.4.1'  
   Gradle Version: 5.1.1
## 2.project 下的 build.gradle 配置
   classpath 'com.novoda:bintray-release:0.9'    // novada plugin 快速配置工具
## 3.library 下的 build.gradle 配置
   apply plugin: 'com.novoda.bintray-release'
## 4.上传github命令
   gradlew clean build bintrayUpload -PbintrayUser=fish -PbintrayKey=86dc41aa041a5a9e00295650f013910e343a88ae -PdryRun=false

# 二.版本说明
## 1.compile 'com.fish.bin:classifyview:1.0.0'
   第一次测试上传
## 2.compile 'com.fish.bin:classifyview:1.1.0'
   加入SeekBar
## 3.compile 'com.fish.bin:classifyview:1.2.0'
   将项目配置到Fish-Bin,并加入忽略文件
   添加kotlin支持
   加入CrashView
   
# 三.功能说明  
   弧形（ArcView)   
   时间轴（TimeLine）   
   波浪（WaveView）  
   图片加框（BorderImageView）  
   文字高亮（LightTextView）  
   时间钟（ClockTimeView）  
   百分比选择（SeekBar）  
   柱状线性图（CarshView）  
# 四.依赖方式  
  ```
  <dependency>
  	<groupId>com.fish.bin</groupId>
  	<artifactId>classifyview</artifactId>
  	<version>1.2.0</version>
  	<type>pom</type>
  </dependency>
  ```
  详情见：https://bintray.com/beta/#/fish/hui/classifyview?tab=overview
