package org.ilong.yuekeyun.Enum;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.naming.Name;

/**
 * 图片路径枚举类
 * 通过ItoByte()方法，指定fastdfs 文件存储的stroage 相当于变相指定存储路径
 *
 * @author long
 * @date 2020-10-13 21:18
 */
public enum ImgDir {
    TouXiang(0, "头像路径"),
    LunBoTu(1,"轮播图存储路径"),
    KeChengTu(2,"课程图存储路径"),
    KeChengVideo(3,"课程视频存储路径");

    private Integer code;
    private String name;

    ImgDir(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
    public byte ItoByte(){
        return this.code.byteValue();
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }}