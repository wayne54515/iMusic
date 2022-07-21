# iMusic
iTunes music search

# APP畫面
![](https://i.imgur.com/zZzinLo.png)

# 目標功能
1. 查詢歌曲or作者
2. 顯示查詢後結果
3. 撥放歌曲

# require
* minSdkVersion 26
* Manifest
	```
	<uses-permission android:name="android.permission.INTERNET" />
	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
	```

# implement
* implementation 'com.android.volley:volley:1.2.1'
* implementation 'androidx.cardview:cardview:1.0.0'
* implementation 'androidx.recyclerview:recyclerview:1.2.1'
