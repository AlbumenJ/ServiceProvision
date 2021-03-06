package com.albumen.auth.filter;

import com.albumen.auth.constant.SecurityConstant;
import com.albumen.auth.handler.GrantedAuthorityImpl;
import com.albumen.auth.properties.SecurityProperties;
import com.albumen.auth.source.IDataStore;
import com.albumen.auth.util.JwtUtil;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Albumen
 */
public class CustomAuthenticationFilter extends BasicAuthenticationFilter {

    private final static Logger logger = LoggerFactory.getLogger(CustomAuthenticationFilter.class);
    private JwtUtil jwtUtil;
    private IDataStore dataStore;
    private SecurityProperties securityProperties;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, IDataStore dataStore, JwtUtil jwtUtil, SecurityProperties securityProperties) {
        super(authenticationManager);
        this.dataStore = dataStore;
        this.jwtUtil = jwtUtil;
        this.securityProperties = securityProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String headerToken = request.getHeader(SecurityConstant.AUTHORIZATION_HEADER);
        ;
        // ??????????????????Session??????Token
        if (StringUtils.isEmpty(headerToken) && securityProperties.isEnableSession()) {
            headerToken = (String) request.getSession().getAttribute(SecurityConstant.AUTHORIZATION_HEADER);
        }

        // ??????Token????????????
        if (!StringUtils.isEmpty(headerToken) && headerToken.startsWith(SecurityConstant.STATE_PREFIX)) {
            String jwtToken = headerToken.substring(SecurityConstant.STATE_PREFIX.length());
            String token = jwtUtil.getSubject(jwtToken);

            if (Objects.nonNull(token)) {
                String userId = dataStore.get(SecurityConstant.TOKEN_PREFIX + token);
                System.out.println(userId);
                if (!StringUtils.isEmpty(userId)) {
                    //????????????
                    ArrayList<GrantedAuthority> authorities = new ArrayList<>();

                    String data = dataStore.get(SecurityConstant.INFORMATION_PREFIX + userId);
                    String permissionStore = dataStore.get(SecurityConstant.PERMISSION_PREFIX + userId);
                    List<String> permissions = JSONArray.parseArray(permissionStore, String.class);
                    if (Objects.nonNull(permissions) && permissions.size() > 0) {
                        for (String permission : permissions) {
                            authorities.add(new GrantedAuthorityImpl(permission));
                        }
                    }

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userId, null, authorities);
                    authentication.setDetails(data);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    chain.doFilter(request, response);
                    return;
                }
            }

        }

        //?????????????????????????????????
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new GrantedAuthorityImpl("ROLE_ANONYMOUS"));
        AnonymousAuthenticationToken anonymousAuthenticationToken =
                new AnonymousAuthenticationToken("Anonymous", "Anonymous", authorities);
        SecurityContextHolder.getContext().setAuthentication(anonymousAuthenticationToken);
        chain.doFilter(request, response);
    }
}
