package com.haipiao.registration.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotEmpty;

@Component
@Validated
@ConfigurationProperties
@PropertySource("classpath:/resources/sms.properties")
public class SMSProperties {

    private SMS sms;

    public SMS getSms() {
        return sms;
    }

    public void setSms(SMS sms) {
        this.sms = sms;
    }

    @Override
    public String toString() {
        return "SMSProperties{" +
                "sms=" + sms +
                '}';
    }

    public static class SMS {

        @NotEmpty
        private String testing;

        @NotEmpty
        private String domain;

        @NotEmpty
        private String region;

        @NotEmpty
        private String accessKeyId;

        @NotEmpty
        private String accessKeySecret;

        @NotEmpty
        private String signature;

        @NotEmpty
        private String template;

        public String getTesting() {
            return testing;
        }

        public void setTesting(String testing) {
            this.testing = testing;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getAccessKeyId() {
            return accessKeyId;
        }

        public void setAccessKeyId(String accessKeyId) {
            this.accessKeyId = accessKeyId;
        }

        public String getAccessKeySecret() {
            return accessKeySecret;
        }

        public void setAccessKeySecret(String accessKeySecret) {
            this.accessKeySecret = accessKeySecret;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        @Override
        public String toString() {
            return "SMS{" +
                    "testing=" + testing +
                    ", domain='" + domain + '\'' +
                    ", region='" + region + '\'' +
                    ", accessKeyId='" + accessKeyId + '\'' +
                    ", accessKeySecret='" + accessKeySecret + '\'' +
                    ", signature='" + signature + '\'' +
                    ", template='" + template + '\'' +
                    '}';
        }
    }
}
