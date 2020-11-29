package org.ilong.yuekeyun.Enum;

/**
 * 课程分类枚举
 *
 * @author long
 * @date 2020-10-15 9:43
 */
public enum  ClassifyEnum {

    Classify_houduan("be"),
    Classify_qianduan("fe"),
    Classify_yunjisuanAnddashuju("cb"),
    Classify_sub_java("java"),
    Classify_sub_html5("html5"),
    Classify_sub_python("python"),
    Classify_sub_yunjisuanAnddashuju("云计算&大数据"),
    Classify_sub_js("JS"),
    Classify_sub_c("c"),
    Classify_sub_php("php"),
    Classify_sub_jquery("jquery")
    ;


    private String name;

    ClassifyEnum(String name){
        this.name=name;
    }
    public String value() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }}
