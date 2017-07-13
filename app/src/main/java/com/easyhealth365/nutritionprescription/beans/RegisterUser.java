package com.easyhealth365.nutritionprescription.beans;

/**
 * Created by lingxiao-Ching on 2017/7/13.
 */

public class RegisterUser extends BaseBean {
    private static final long serialVersionUID = 7963972682717082387L;
    private String userid = "";
    private String username = "";// :用户名
    private String password = ""; // ;//密码 （必填）
    private String telephone = ""; // :手机号码 （必填）
    private String realname = "";// :姓名 （必填）
    private boolean gender = true;// :性别 // true 男， false 女
    private String email = "";// :邮箱
    private String birthday = "";// :出生年月日
    private String idcard = "";// :身份证号码
    private String step = "";// :步幅
    private String weight = "";// :体重
    private String height = "";// :身高

    private String Mbtelephone;
    private String Mbemail;

    private String access_token = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getMbtelephone() {
        return Mbtelephone;
    }

    public void setMbtelephone(String mbtelephone) {
        Mbtelephone = mbtelephone;
    }

    public String getMbemail() {
        return Mbemail;
    }

    public void setMbemail(String mbemail) {
        Mbemail = mbemail;
    }
}
