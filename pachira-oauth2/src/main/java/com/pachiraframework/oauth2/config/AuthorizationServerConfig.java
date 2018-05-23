package com.pachiraframework.oauth2.config;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.pachiraframework.oauth2.service.DefaultClientDetailsService;
import com.pachiraframework.oauth2.service.DefaultUserDetailsService;

/**
 * @author KevinWang
 *
 */
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private DefaultUserDetailsService userDetailsService;
	@Autowired
	private DefaultClientDetailsService clientDetailsService;

	@Override
	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.allowFormAuthenticationForClients();
		oauthServer.passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientDetailsService);
	}

	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.tokenStore(tokenStore());
        endpoints.tokenEnhancer(accessTokenConverter());
        endpoints.userDetailsService(userDetailsService);

        // 配置TokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(false);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30)); // 30天
        endpoints.tokenServices(tokenServices);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();;
		
		return encoder;
	}
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter() {
			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
				String grantType = authentication.getOAuth2Request().getGrantType();
				//只有如下两种模式才能获取到当前用户信息
				if("authorization_code".equals(grantType) || "password".equals(grantType)) {
					String userName = authentication.getUserAuthentication().getName();
					final Map<String, Object> additionalInformation = new HashMap<>();
					additionalInformation.put("user_name", userName);
					((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
				}
				OAuth2AccessToken token = super.enhance(accessToken, authentication);
				return token;
			}
		};
		KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("kevin_key.jks"), "123456".toCharArray())
				.getKeyPair("kevin_key");
		converter.setKeyPair(keyPair);
		return converter;
	}
}
