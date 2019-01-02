package com.qihao.beans;

import java.io.Serializable;

import androidx.annotation.NonNull;

/**
 * Created by Ivan on 2016/12/30.
 */

public class AppInfo implements Serializable {


    /**
     * adType : 0
     * ads : 0
     * apkSize : 611780185
     * appendSize : 0
     * briefShow : 唯美和风 奇幻之旅
     * briefUseIntro : false
     * displayName : 阴阳师
     * icon : AppStore/088d184422af941b529836c4c0cf9504810ab9446
     * id : 395801
     * level1CategoryId : 15
     * level1CategoryName : 游戏
     * level2CategoryId : 19
     * packageName : com.netease.onmyoji.mi
     * position : 0
     * publisherName : 杭州网易雷火科技有限公司
     * rId : 0
     * ratingScore : 3.5
     * releaseKeyHash : ef931f7785b34fd2da077eaaf42542d9
     * screenshot : AppStore/0e8f5355aa8b04e330e1f2e0c44def4169aabfcb4,AppStore/0c8f5355aa8b04e340e1fbe0c48def4569aa1fcb4,AppStore/058885d6033f83464ff3478801734e310a442253e,AppStore/068f5355aa8804e3a0e1f4e0c4cdef4569aaffcb4,AppStore/08361c479d4824757150e47737e752af67590aa18
     * source :
     * suitableType : 2
     * updateTime : 1482830816276
     * versionCode : 14
     * versionName : 1.0.14
     * videoId : 678
     */

    private int adType;
    private int ads;
    private long apkSize;
    private int appendSize;
    private String briefShow;
    private boolean briefUseIntro;
    private String displayName;
    private String icon;
    private int id;
    private int level1CategoryId;
    private String level1CategoryName;
    private int level2CategoryId;
    private String packageName;
    private int position;
    private String publisherName;
    private int rId;
    private double ratingScore;
    private String releaseKeyHash;
    private String screenshot;
    private String source;
    private int suitableType;
    private long updateTime;
    private int versionCode;
    private String versionName;
    private int videoId;

    public int getAdType() {
        return adType;
    }

    public void setAdType(int adType) {
        this.adType = adType;
    }

    public int getAds() {
        return ads;
    }

    public void setAds(int ads) {
        this.ads = ads;
    }

    public long getApkSize() {
        return apkSize;
    }

    public void setApkSize(long apkSize) {
        this.apkSize = apkSize;
    }

    public int getAppendSize() {
        return appendSize;
    }

    public void setAppendSize(int appendSize) {
        this.appendSize = appendSize;
    }

    public String getBriefShow() {
        return briefShow;
    }

    public void setBriefShow(String briefShow) {
        this.briefShow = briefShow;
    }

    public boolean isBriefUseIntro() {
        return briefUseIntro;
    }

    public void setBriefUseIntro(boolean briefUseIntro) {
        this.briefUseIntro = briefUseIntro;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel1CategoryId() {
        return level1CategoryId;
    }

    public void setLevel1CategoryId(int level1CategoryId) {
        this.level1CategoryId = level1CategoryId;
    }

    public String getLevel1CategoryName() {
        return level1CategoryName;
    }

    public void setLevel1CategoryName(String level1CategoryName) {
        this.level1CategoryName = level1CategoryName;
    }

    public int getLevel2CategoryId() {
        return level2CategoryId;
    }

    public void setLevel2CategoryId(int level2CategoryId) {
        this.level2CategoryId = level2CategoryId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public int getRId() {
        return rId;
    }

    public void setRId(int rId) {
        this.rId = rId;
    }

    public double getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(double ratingScore) {
        this.ratingScore = ratingScore;
    }

    public String getReleaseKeyHash() {
        return releaseKeyHash;
    }

    public void setReleaseKeyHash(String releaseKeyHash) {
        this.releaseKeyHash = releaseKeyHash;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getSuitableType() {
        return suitableType;
    }

    public void setSuitableType(int suitableType) {
        this.suitableType = suitableType;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    @NonNull
    @Override
    public String toString() {
        return "briefShow="+briefShow+",displayName="+displayName;
    }
}
