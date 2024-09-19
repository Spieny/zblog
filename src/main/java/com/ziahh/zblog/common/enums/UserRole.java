package com.ziahh.zblog.common.enums;

import lombok.Getter;

/**
 * @Title: UserRole
 * @Author Ziahh
 * @Package com.ziahh.enums
 * @Date 2024/5/8 下午3:47
 * @description: 用户角色
 */
@Getter
public enum UserRole {
    USER("user","用户"),
    ADMIN("admin","管理员"),
    BAN("ban","已封禁");

    private String value;
    private String desc;

    UserRole(String value, String desc) {
        this.desc = desc;
        this.value = value;
    }

    /**
     * 通过user_role值获取对应的role
     * @param value
     * @return
     */
    public static UserRole getUserRoleByValue(String value) {
        try{
            if(value == null || value.isEmpty()) {return null;}
            for(UserRole role : values()){
                if(role.value.equals(value)){
                    return role;
                }
            }
        } catch (Exception e){
            return null;
        }
        return null;
    }
}
