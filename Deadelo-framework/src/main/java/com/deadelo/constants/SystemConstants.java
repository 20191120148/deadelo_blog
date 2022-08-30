package com.deadelo.constants;

public class SystemConstants
{
    /**
     *  文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     *  文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;
    /**
     * 类别是正常状态
     */
    public static final String CATEGORY_STATUS_NORMAL = "0";
    /**
     * 友链审核通过
     */
    public static final String LINK_STATUS_NORMAL = "0";
    /**
     * 友链审核失败
     */
    public static final String LINK_STATUS_FALL = "1";
    /**
     * 友链正在审核
     */
    public static final String LINK_STATUS_WAIT = "2";

    /**
     * redis 前台登录id前缀
     */
    public static final String BLOG_LOGIN_PREFIX = "blog_login:";

    /**
     * redis 后台登录id前缀
     */
    public static final String ADMIN_LOGIN_PREFIX = "admin_login:";

    /**
     * 该评论为根评论
     */
    public static final Long ROOT_COMMENT_ID = -1L;

    /**
     * 评论类型：文章评论
     */
    public static final String ARTICLE_COMMMENT = "0";
    /**
     * 评论类型：友链评论
     */
    public static final String LINK_COMMMENT = "1";
    /**
     * redis 存储文章viewCount前缀
     */
    public static final String REDIS_VIEWCOUNT = "article:viewCount";
    public static final String MENU = "C";
    public static final String BUTTON = "F";
    public static final int STATUS_NORMAL = 0;
}