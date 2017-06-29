package com.easyhealth365.nutritionprescription.beans;

/**
 * Created by lingxiao-Ching on 2017/6/29.
 */

public class User {

    /**
     * status : 103
     * message : 登录成功
     * results : {"access_token":"6MJ1BKL1536Nf7076924-a2ae-4727-a098-331b64ef877eX5T8WE171S3A5Z2BC44RIE0HD30TQW2EE820OG138","realname":"开发霄","telephone":"15645454545","gender":true,"email":"qlx7117@sina.com","birthday":"1991-3-14","idcard":"","step":"75","weight":"75","height":"","Mbtelephone":"18915014545","Mbemail":"123@qq.com"}
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

    public static class ResultsBean {
        /**
         * access_token : 6MJ1BKL1536Nf7076924-a2ae-4727-a098-331b64ef877eX5T8WE171S3A5Z2BC44RIE0HD30TQW2EE820OG138
         * realname : 开发霄
         * telephone : 15645454545
         * gender : true
         * email : qlx7117@sina.com
         * birthday : 1991-3-14
         * idcard :
         * step : 75
         * weight : 75
         * height :
         * Mbtelephone : 18915014545
         * Mbemail : 123@qq.com
         */

        private String access_token;
        private String realname;
        private String telephone;
        private boolean gender;
        private String email;
        private String birthday;
        private String idcard;
        private String step;
        private String weight;
        private String height;
        private String Mbtelephone;
        private String Mbemail;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
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

        public String getMbtelephone() {
            return Mbtelephone;
        }

        public void setMbtelephone(String Mbtelephone) {
            this.Mbtelephone = Mbtelephone;
        }

        public String getMbemail() {
            return Mbemail;
        }

        public void setMbemail(String Mbemail) {
            this.Mbemail = Mbemail;
        }
    }
}
