package com.android.beans;

/**
 * (核心功能)：
 *
 * @author qihao
 * @date on 2018/12/20 13:56
 */
public class WorkInfo {
    private String job = "财务";
    private int jobYear = 5;
    private String jobSite = "北京";

    public String getInfo(){
        return "工作："+job+", 工龄："+jobYear+", 工作地点："+jobSite;
    }
}
