package pers.czj.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 创建在 2020/7/10 15:42
 * 用户数据库类
 */
@Data
public class User implements Serializable {
    /**
     * 自增唯一主键
     */
    private long id;

    /**
     * 账户名
     */
    private String account;

    /**
     * 密码
     */
    private String password;


    /**
     * 用户名字
     */
    private String username;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户头像
     */
    private String img;

    /**
     * 关注数
     */
    private long followNum;


    /**
     * 粉丝数
     */
    private long fansNum;

    /**
     * 个性签名
     */
    private String description;

    /**
     * 用户等级
     */
    private long grade;

    /**
     * 硬币数
     */
    private long coinNum;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}