//package com.pachiraframework.oauth2.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//
//import com.pachiraframework.oauth2.service.DefaultClientDetailsService;
//import com.pachiraframework.oauth2.service.DefaultUserDetailsService;
//
///**
// * @author KevinWang
// *
// */
//@Configuration
//public class SimpleAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	@Autowired
//	private DefaultUserDetailsService userDetailsService;
//	@Autowired
//	private DefaultClientDetailsService clientDetailsService;
//
//	@Override
//	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//		oauthServer.allowFormAuthenticationForClients();
//	}
//
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.withClientDetails(clientDetailsService);
//	}
//
//	@Override
//	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		// @formatter:off
//		endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
//		// @formatter:on
//	}
//}
