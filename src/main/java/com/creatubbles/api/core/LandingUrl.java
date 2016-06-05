package com.creatubbles.api.core;

import lombok.Value;
import lombok.experimental.NonFinal;

/**
 * Created by Jevgeni on 10.03.2016.
 */
@Value
@NonFinal
public class LandingUrl {

    LandingUrlType type;
    String url;

    public enum LandingUrlType {

        CTB_ABOUT_US("ctb-about_us"),
        CTB_TERMS_OF_USE("ctb-terms_of_use"),
        CTB_PRIVACY_POLICY("ctb-privacy_policy"),
        CTB_REGISTRATION("ctb-registration"),
        CTB_FORGOT_PASSWORD("ctb-forgot_password"),
        CTB_USER_PROFILE("ctb-user_profile"),
        CTB_EXPLORE("ctb-explore");

        private String type;

        public String getType() {
            return type;
        }

        LandingUrlType(String type) {
            this.type = type;
        }

        public static LandingUrlType from(String type) {
            for (LandingUrlType e : LandingUrlType.values()) {
                if (e.getType().equals(type)) {
                    return e;
                }
            }
            throw new IllegalStateException();
        }

        @Override
        public String toString() {
            return type;
        }
    }
}
