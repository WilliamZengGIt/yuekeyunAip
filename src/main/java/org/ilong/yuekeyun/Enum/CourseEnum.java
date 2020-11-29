package org.ilong.yuekeyun.Enum;

/**
 * 课程使用的枚举
 */
public enum CourseEnum {

	FREE(1), //免费
	FREE_NOT(0), //收费
	
	ONSALE(1), //上架
	ONSALE_NOT(0), //下架

	Level_Lingjichu(0),//零基础
	Level_Chuji(1),//初级
	Level_Zhongji(2),//中级
	Level_Gaoji(3),//高级
	Level_TiSheng(4),//提升
	COLLECTION_CLASSIFY_COURSE(1);//课程收藏




	
	private Integer value;
	private CourseEnum(Integer value) {
		this.value = value;
	}
	
	public Integer value(){
		return value;
	}
}
