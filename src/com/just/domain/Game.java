package com.just.domain;

import java.io.Serializable;

public class Game implements Serializable {

    private static final long serialVersionUID = 3996224339979944805L;

    int[][] imageList;
    int total_count;
    String filepath;

    public Game() {
    }

    public Game(int[][] imageList, int total_count, String filepath) {
        this.imageList = imageList;
        this.total_count = total_count;
        this.filepath = filepath;
    }

    /**
     * 获取
     * @return imageList
     */
    public int[][] getImageList() {
        return imageList;
    }

    /**
     * 设置
     * @param imageList
     */
    public void setImageList(int[][] imageList) {
        this.imageList = imageList;
    }

    /**
     * 获取
     * @return total_count
     */
    public int getTotal_count() {
        return total_count;
    }

    /**
     * 设置
     * @param total_count
     */
    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    /**
     * 获取
     * @return filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * 设置
     * @param filepath
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String toString() {
        return "Game{imageList = " + imageList + ", total_count = " + total_count + ", filepath = " + filepath + "}";
    }

}
