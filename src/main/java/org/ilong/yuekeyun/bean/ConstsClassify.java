package org.ilong.yuekeyun.bean;
/**
 *
 *课程分类
 * @author long
 * @date 2020-10-14 14:24
 */
public class ConstsClassify extends BaseEntity{
    private Integer id;
    /**
     *名称
     **/
    private String name;

    /**
     *编码
     **/
    private String code;

    /**
     *父级别id
     **/
    private String parentCode;

    /**
     *排序
     **/
    private Long sort;

    @Override
    public String toString() {
        return "ConstsClassify{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", sort=" + sort +
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }
}
