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
    TouXiang(0),//"头像路径" ; ;

    LunBoTu(1),//"轮播图存储路径",

    KeChengTu(2),//"课程图存储路径"

    KeChengVideo(3);//课程视频存储路径

    private Integer value;
    public byte ItoByte(){
        return  this.value.byteValue();
    }
    ImgDir(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }}