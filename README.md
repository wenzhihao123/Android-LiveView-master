# Android-LiveView-master
视频直播的浮层送礼物特效，类似印客直播
# 简介
年关将至，给大家拜个早年，祝大家鸡年大吉吧 ~ 哈哈 有污的同学面壁去吧！闲话不多说，今天实现的效果是直播的时候浮层View，不知道直播的sdk是不是都提供UI这方面的东西。先看一张效果图：

![实现效果图](http://wx4.sinaimg.cn/mw690/935ca1ddly1fbp1wdgpnzg20b30jq1l7.gif)

主要实现效果：
（当然直播的时候视频是主要的，这里不关心视频，重点是View，这里只是随意播放了一个视频。）
1. 首先是顶部横线滑动的ListView显示房间的成员；
2. ListView 自动滚动到底部；
3. 自定义实现刷礼物的View，过段时间自动消失；
4. 送礼物的时候弹出礼物的DialogFragment+ViewPager+GridView;
5. 点击评论的时候监听弹出键盘的事件；
6. 直播底部显示送心的特效（这里用了第三方的 tyrant:heartlayout）；
7. VedioView播放视频；

详情请见： [简书博客](https://github.com/wenzhihao123/Android-rxjava-retrofit-okhttp-app)  