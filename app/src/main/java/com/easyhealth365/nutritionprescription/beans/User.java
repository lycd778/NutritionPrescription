package com.easyhealth365.nutritionprescription.beans;

/**
 * Created by lingxiao-Ching on 2017/6/29.
 */

public class User extends BaseBean {

    private static final long serialVersionUID = 8427255712080006337L;
    /**
     * status : 103
     * message : 登录成功
     * results : {"access_token":"8MJ3BKL1736N26e54be2-e565-43f2-a0f9-b3f30309ac93X8T5WE201S3A5Z6BC35RIE0HD77TQW8EE960OG952","userid":"26e54be2-e565-43f2-a0f9-b3f30309ac93","realname":"开发霄Test","telephone":"15647474747","gender":true,"birthday":"2006-7-22","Mbtelephone":"15647474747","hospitalBaseUrl":"http://testnew.xzkf365.com/"}
     */

    private int status;
    private String message;
    private ResultsBean results;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public static class ResultsBean extends BaseBean{

        private static final long serialVersionUID = -8809061807184774285L;
        /**
         * access_token : 8MJ3BKL1736N26e54be2-e565-43f2-a0f9-b3f30309ac93X8T5WE201S3A5Z6BC35RIE0HD77TQW8EE960OG952
         * userid : 26e54be2-e565-43f2-a0f9-b3f30309ac93
         * realname : 开发霄Test
         * telephone : 15647474747
         * gender : true
         * birthday : 2006-7-22
         * Mbtelephone : 15647474747
         * hospitalBaseUrl : http://testnew.xzkf365.com/
         */

        private String access_token;
        private String userid;
        private String realname;
        private String telephone;
        private boolean gender;
        private String birthday;
        private String Mbtelephone;
        private String hospitalBaseUrl;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public boolean isGender() {
            return gender;
        }

        public void setGender(boolean gender) {
            this.gender = gender;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getMbtelephone() {
            return Mbtelephone;
        }

        public void setMbtelephone(String Mbtelephone) {
            this.Mbtelephone = Mbtelephone;
        }

        public String getHospitalBaseUrl() {
            return hospitalBaseUrl;
        }

        public void setHospitalBaseUrl(String hospitalBaseUrl) {
            this.hospitalBaseUrl = hospitalBaseUrl;
        }
    }
}
