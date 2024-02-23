
package com.crud.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.crud.springsecurity.filter.JwtAuthenticationFilter;
import com.crud.springsecurity.service.EmployeeServiceimpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


	private final EmployeeServiceimpl employeeServiceimpl;

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	public SecurityConfig(EmployeeServiceimpl employeeServiceimpl, JwtAuthenticationFilter jwtAuthenticationFilter) {
		this.employeeServiceimpl = employeeServiceimpl;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}

	

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(t -> t.disable()).authorizeHttpRequests((req) -> req
				.requestMatchers("/api/auth/admin").hasAuthority("ADMIN")
				.requestMatchers("/api/auth/users").hasAnyAuthority("ADMIN","USER")
				.requestMatchers("/","/register","/signin").permitAll().anyRequest().permitAll())
		.userDetailsService(employeeServiceimpl)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.formLogin(form -> form
						.loginPage("/signin"))
				.logout(logout -> logout.permitAll());
		return http.build();
	}
	
	
	/*
	 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
	 * Exception { http.csrf(t -> t.disable()).authorizeHttpRequests((authorize) ->
	 * authorize // .requestMatchers("/users").hasAnyAuthority("ADMIN","USER") // //
	 * .requestMatchers("/admin").hasAuthority("ADMIN")
	 * .requestMatchers("/users").hasAnyAuthority(RoleConstant.ROLE_ADMIN,
	 * RoleConstant.ROLE_USER)
	 * .requestMatchers("/admin").hasAuthority(RoleConstant.ROLE_ADMIN)
	 * .requestMatchers("/register").permitAll().requestMatchers("/")
	 * .permitAll().anyRequest().permitAll()) .formLogin(form ->
	 * form.permitAll().successHandler(new CustomAuthenticationSuccessHandler()))
	 * .logout(logout -> logout.logoutRequestMatcher(new
	 * AntPathRequestMatcher("/logout")).permitAll()); return http.build(); }
	 */

	/*
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 * throws Exception { http.authorizeHttpRequests(authorizeRequests ->
	 * authorizeRequests.requestMatchers("/home/register").permitAll()
	 * .requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/user/**").
	 * hasRole("USER").anyRequest() .authenticated())
	 * 
	 * .formLogin( // Customizer.withDefaults() formLogin -> formLogin //
	 * .loginPage("/customLogin") // .loginProcessingUrl("/authenticateTheUser")
	 * .permitAll().successHandler(new CustomAuthenticationSuccessHandler()))
	 * .logout(logout -> logout.permitAll()); return http.build(); }
	 */

	// This is Use for InMemoryUserDetails
	/*
	 * @Bean public InMemoryUserDetailsManager
	 * userDetailManager(@Value("${security.users.admin2.name}") String
	 * adminUsername,
	 * 
	 * @Value("${security.users.admin2.password}") String adminPassword,
	 * 
	 * @Value("${security.users.admin2.roles}") String adminRoles,
	 * 
	 * @Value("${security.users.user2.name}") String userUsername,
	 * 
	 * @Value("${security.users.user2.password}") String userPassword,
	 * 
	 * @Value("${security.users.user2.roles}") String userRoles) {
	 * 
	 * UserDetails admin =
	 * User.withUsername(adminUsername).password(passwordEncoder().encode(
	 * adminPassword)) .roles(adminRoles.split(",")).build();
	 * 
	 * UserDetails user =
	 * User.withUsername(userUsername).password(passwordEncoder().encode(
	 * userPassword)) .roles(userRoles).build(); System.out.println(admin);
	 * System.out.println(user); return new InMemoryUserDetailsManager(admin, user);
	 * }
	 */

	// This is Use for InMemoryUserDetails
	/*
	 * @Bean public UserDetailsService userDetailsService(SecurityConfigConstants
	 * securityConfigConstants) { UserDetails user =
	 * User.withUsername(securityConfigConstants.getUsername())
	 * .password(passwordEncoder().encode(securityConfigConstants.getPassword()))
	 * .roles(securityConfigConstants.getRole()).build();
	 * 
	 * UserDetails admin =
	 * User.withUsername("Admin").password(passwordEncoder().encode("Pass")).roles(
	 * "ADMIN") .build();
	 * 
	 * return new InMemoryUserDetailsManager(user, admin); }
	 */

	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception {
	 * auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()
	 * ); }
	 */
}
