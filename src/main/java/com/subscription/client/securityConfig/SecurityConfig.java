package com.subscription.client.securityConfig;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.subscription.client.security.JwtAutenticationTokenFilter;
import com.subscription.client.security.JwtAuthenticationEntryPoint;
import com.subscription.client.security.JwtAuthenticationProvider;
import com.subscription.client.security.JwtUsernamePasswordAuthenticationFilter;
import com.subscription.client.security.handler.JwtAuthenticationFailureHandler;
import com.subscription.client.security.handler.JwtAuthenticationSuccessHandler;
import com.subscription.client.services.serviceImpl.ClientDetailsSeviceImpl;








@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	
	@Autowired
	private ClientDetailsSeviceImpl clientDetailsServiceImpl;
	
	@Autowired
	JwtAuthenticationProvider authenticationProvider;
	
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Bean BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		System.out.println("Inside RepositoryConfiguration bCryptPasswordEncoder()");
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		
		return encoder;
	}
	public JwtUsernamePasswordAuthenticationFilter jwtUsernamePasswordAuthenticationFilter()
	{
		JwtUsernamePasswordAuthenticationFilter userPwdFilter=null;
		try
		{
			System.out.println("Inside WebSecurityConfig jwtUsernamePasswordAuthenticationFilter() ");
			userPwdFilter=new JwtUsernamePasswordAuthenticationFilter(authenticationManager() );
			//This can also be invoked if we want to set method of the url we want to invoke
			userPwdFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
			userPwdFilter.setAuthenticationFailureHandler(new JwtAuthenticationFailureHandler());
			userPwdFilter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());
		}
		catch(Exception e)
		{
			System.out.println("Exception in WebSecurityConfig jwtUsernamePasswordAuthenticationFilter()");
			e.printStackTrace();
		}
		return userPwdFilter;
	}
	
	@Bean
	public JwtAutenticationTokenFilter jwtAutenticationTokenFilter()
	{
		System.out.println("Inside WebSecurityConfigurer jwtAuthenticationTokenFilter() initialized");
		JwtAutenticationTokenFilter filter=new JwtAutenticationTokenFilter();
		filter.setAuthenticationManager(authenticationManagerToken());
		filter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());
		return filter;
	}
	
	//Bean for AuthenticationManager for Authorization of JWT
	@Bean
	public AuthenticationManager authenticationManagerToken()
	{
		System.out.println("Inside WebSecurityConfigurer authenticationManager() initialized");
		return new ProviderManager(Collections.singletonList(authenticationProvider));
	}
		
	@Override
	protected void configure(HttpSecurity http)
	{
		System.out.println("Inside WebSecurityConfigurer configure(HttpSecurity http) method");
		try
		{
			http
				.authorizeRequests()
				 	.antMatchers("/").permitAll()
				 	.antMatchers("/register", "/login", "/user/loginFailed").permitAll()
				 	.antMatchers("/forgotPassword").permitAll()	
				 	.antMatchers("/forgot").permitAll()
				 	.antMatchers("/view").permitAll()
				 	.antMatchers("//updatePassword").permitAll()
					.antMatchers("/rest/**").authenticated()
				.and()
					.csrf().disable()
					.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.and()
					.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
			http.addFilterBefore(jwtAutenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
			http.addFilter(jwtUsernamePasswordAuthenticationFilter());
			
			//headers() expalnation
			/*Adds the Security headers to the response. This is activated by default when using
		  		{@link WebSecurityConfigurerAdapter}'s default constructor*/
			
			http.headers().cacheControl();
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in WebSecurityConfigurer configure(HttpSecurity http) ");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
	{			// MY CODE STARTS FROM HERE //
		try
		{
			System.out.println("Inside WebSecurityConfigurer configure(AuthenticationManagerBuilder auth)");
			
			auth.userDetailsService(clientDetailsServiceImpl)
				.passwordEncoder(bcryptPasswordEncoder);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in WebSecurityConfigurer configure(AuthenticationManagerBuilder auth)");
			e.printStackTrace();
		}
	}
}


