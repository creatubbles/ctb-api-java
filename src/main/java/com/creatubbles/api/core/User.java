package com.creatubbles.api.core;

public class User {
    public String id;
    public String username, email, country, role;
    public boolean is_teacher, is_loggable, is_site_admin, newsletter;
    public int signed_up_as;
    public String access_token;
    public String password;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id.hashCode();
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        if (id == null && other.id != null) {
            return false;
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (username == null && other.username != null) {
            return false;
        } else if (!username.equals(other.username)) {
            return false;
        }
        return true;
    }
}
