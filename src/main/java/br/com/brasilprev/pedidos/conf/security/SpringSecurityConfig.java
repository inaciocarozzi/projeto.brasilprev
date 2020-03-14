package br.com.brasilprev.pedidos.conf.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}brasilprev").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/v1/categoria/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/v1/categoria/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/v1/categoria/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/v1/categoria/***").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/v1/categoria/***").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/v1/cliente/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/v1/cliente/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/v1/cliente/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/v1/cliente/***").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/v1/cliente/***").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/v1/endereco/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/v1/endereco/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/v1/endereco/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/v1/endereco/***").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/v1/endereco/***").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/v1/pedido/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/v1/pedido/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/v1/pedido/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/v1/pedido/***").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/v1/pedido/***").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/v1/pedido/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/v1/pedido/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/v1/pedido/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/v1/pedido/***").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/v1/pedido/***").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/v1/produto/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/v1/produto/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/v1/produto/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/v1/produto/***").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/v1/produto/***").hasRole("USER")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}