package org.ilong.yuekeyun.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * 系统用户(分系统用户和普通用户）
 *
 * @author long
 * @date 2020-10-10 14:44
 */
public class AuthUser extends BaseEntity implements UserDetails {

    private Integer id;
    /**
     *登录用户名
     **/
    private String realname;

    /**
     *真实姓名
     **/
    private String username;

    /**
     *密码
     **/
    private String password;

    /**
     *性别
     **/
    private Integer gender;

    /**
     *头像
     **/
    private String header;

    /**
     *手机号码
     **/
    private String mobile;
    /**
     * 用户类型：默认为1（0:系统管理员;1:普通用户;2:教师用户;3:会员用户）
     */
    private Integer type;
    /**
     *状态：待审核（0），审核通过（1），默认（2），审核未通过（3），禁用（5）
     **/
    private Integer status;

    /**
     *生日
     **/
    private Date birthday;

    /**
     *学历：大专、本科、硕士、博士、博士后
     **/
    private String education;

    /**
     * 大学编号
     */
    private String collegeCode;

    /**
     * 大学名称
     */
    private String collegeName;

    /**
     *资格证书编号
     **/
    private String certNo;

    /**
     *头衔
     **/
    private String title;

    /**
     *签名
     **/
    private String sign;

    /**
     *微信公众号openid
     **/
    private String openId;

    /**
     *微信号
     **/
    private String wechatId;

    /**
     *qq号
     **/
    private String qq;

    /**
     *最后一次登录时间
     **/
    private Date loginTime;

    /**
     *最后一次登录IP
     **/
    private String ip;

    /**
     *所在省份
     **/
    private String province;

    /**
     *所在城市
     **/
    private String city;

    /**
     *所在地区
     **/
    private String district;

    /**
     * 推荐权重
     */
    private Integer weight;

    /**
     * 扩展字段：微信昵称
     */
    private String nickname;


    //是否被禁用,禁用的用户不能身份验证
    @Override
    public boolean isEnabled() {
        System.out.println(this.status);
        return this.status==5?false:true;
    }
    //用户的权限集， 默认需要添加ROLE_ 前缀
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {
        return mobile;
    }
    //账户是否过期,过期无法验证
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //指示是否已过期的用户的凭据(密码),过期的凭据防止认证
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

