package org.ilong.yuekeyun.bean.dto;

import org.apache.commons.lang3.StringUtils;
import org.ilong.yuekeyun.utils.BeanUtil;

/**
 * 课程Dto
 *
 * @author long
 * @date 2020-10-14 17:00
 */
public class CourseDto {
    private String sortField;
    private String sortDirection = "DESC";
    private Integer start=0;//limit开始
    private Integer count;//数量
    private Integer end;//limit结束
    /**
     *课程名称
     **/
    private String name;
    /**
     *课程类型
     **/
    private String type;

    /**
     *课程分类
     **/
    private String classify;

    /**
     *课程二级分类
     **/
    private String subClassify;
    /**
     *课程方向
     **/
    private String direction;
    /**
     *课程级别：1-初级，2-中级，3-高级
     **/
    private Integer level;
    /**
     *是否免费：0-否，1-是
     **/
    private Integer free;
    /**
     *未上架（0）、上架（1）
     **/
    private Integer onsale;

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public Integer getCount() {
        return count;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "sortField='" + sortField + '\'' +
                ", sortDirection='" + sortDirection + '\'' +
                ", start=" + start +
                ", count=" + count +
                ", end=" + end +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", classify='" + classify + '\'' +
                ", subClassify='" + subClassify + '\'' +
                ", direction='" + direction + '\'' +
                ", level=" + level +
                ", free=" + free +
                ", onsale=" + onsale +
                '}';
    }

    public String getSortField() {
        return sortField;
    }

    /**
     * 按照sortField升序
     * @param sortField：指java bean中的属性
     */
    public void ascSortField(String sortField) {
        if(StringUtils.isNotEmpty(sortField)){
            this.sortField = BeanUtil.fieldToColumn(sortField);
            this.sortDirection = " ASC ";
        }
    }

    /**
     * 按照sortField降序
     * @param sortField ：指java bean中的属性
     */
    public void descSortField(String sortField) {
        if(StringUtils.isNotEmpty(sortField)){
            this.sortField = BeanUtil.fieldToColumn(sortField);
            this.sortDirection = " DESC ";
        }
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getEnd() {
        if(null != this.count){
            if(null == this.start){
                this.start = 0;
            }
            this.end = this.start + this.count;
        }
        return end;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getSubClassify() {
        return subClassify;
    }

    public void setSubClassify(String subClassify) {
        this.subClassify = subClassify;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }

    public Integer getOnsale() {
        return onsale;
    }

    public void setOnsale(Integer onsale) {
        this.onsale = onsale;
    }
}
