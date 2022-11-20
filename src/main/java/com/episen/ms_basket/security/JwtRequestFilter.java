package com.episen.ms_basket.security;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException,RuntimeException {
    	 List<String> privileges=null;
        final String requestTokenHeader = request.getHeader("Authorization");
        System.out.println("Current token is ==> "+requestTokenHeader);
        String username = null;
        String jwtToken = null;
        // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
       if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                privileges=(List<String>) jwtTokenUtil.getAllClaimsFromToken(jwtToken).get("Privileges");
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }
       if(privileges==null)
       {
    	   throw new RuntimeException("Invalid JwToken!");
       }
       else
       {
    	   if(privileges.contains("admin"))
    	   {
				jwtTokenUtil.update_jwt_prop("admin");
    	    }
    	   else
    	   {
   				jwtTokenUtil.update_jwt_prop("notAdmin");
    	   }
       }
        chain.doFilter(request, response);
    }

}
