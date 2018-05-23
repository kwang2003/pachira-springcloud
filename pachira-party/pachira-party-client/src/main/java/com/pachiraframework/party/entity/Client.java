package com.pachiraframework.party.entity;

import com.pachiraframework.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 接入的客户端应用
 * @author KevinWang
 *
 */
@Setter
@Getter
@ToString(callSuper=true)
public class Client extends BaseEntity<Long>{
	private static final long serialVersionUID = 6386375772897527620L;
	/**
	 * 客户端唯一编号
	 */
	private String clientId;
	/**
	 * 客户端应用图标
	 */
	private String imageUrl;
	/**
	 * 可以访问的资源ID
	 */
	private String resourceIds;
	/**
	 * 客户端接入密钥
	 */
	private String clientSecret;
	/**
	 * 范围
	 */
	private String scope;
	
	/**
	 * 认证成功后重定向url地址
	 */
	private String webRedirectUri;
	/**
	 * 权限
	 */
	private String authorities;
	/**
	 * 认证类型
	 */
	private String authorizedGrantTypes;
	/**
	 * 是否自动授权
	 * @see AutoApproveEnum
	 */
	private String autoApprove;
	/**
	 * 额外信息
	 */
	private String additionalInformation;
	private Long accessTokenValidity;
	private Long refreshTokenValidity;
	
	@Getter
	public static enum AutoApproveEnum{
		YES,NO;
	}
}
