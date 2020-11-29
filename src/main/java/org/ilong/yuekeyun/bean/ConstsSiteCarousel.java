package org.ilong.yuekeyun.bean;

/**
 * 轮播图实体类
 *
 * @author long
 * @date 2020-10-11 20:49
 */
public class ConstsSiteCarousel extends BaseEntity {
    /**
     * id
     */
    private Integer id;
    /**
     *名称
     **/
    private String name;

    /**
     *图片
     **/
    private String picture;

    /**
     *链接
     **/
    private String url;

    /**
     *权重
     **/
    private Integer weight;

    /**
     * 是否可用 1可用 0禁用
     */
    private Integer enable;

    @Override
    public String toString() {
        return "ConstsSiteCarousel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", url='" + url + '\'' +
                ", weight=" + weight +
                ", enable=" + enable +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

}
