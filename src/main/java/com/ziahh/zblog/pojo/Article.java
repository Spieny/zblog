package com.ziahh.zblog.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 文章表，用于存储发布的文章
 * </p>
 *
 * @author Ziahh
 * @since 2024-09-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("article")
@ApiModel(value="Article对象", description="文章表，用于存储发布的文章")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章唯一标识")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "文章标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "文章别名")
    @TableField("alias")
    private String alias;

    @ApiModelProperty(value = "文章内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "分类ID，关联到分类表")
    @TableField("category_id")
    private Long categoryId;

    @ApiModelProperty(value = "作者ID，关联到用户表")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "文章字数")
    @TableField("word_count")
    private Integer wordCount;

    @ApiModelProperty(value = "文章是否加密")
    @TableField("encrypted")
    private Boolean encrypted;

    @ApiModelProperty(value = "加密文章的密码，如果文章加密则需要")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "发布时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "文章状态：草稿、已发布或已归档")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "文章浏览次数")
    @TableField("views")
    private Integer views;

    @ApiModelProperty(value = "文章点赞次数")
    @TableField("likes")
    private Integer likes;


}
