package com.albumen.auth.source.impl;

import com.albumen.auth.source.IPermissionPath;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Albumen
 */
@Service
public class IPermissionPathImpl implements IPermissionPath {

    @Override
    public List<String> permitPath() {
        List<String> permitPath = new LinkedList<>();
        permitPath.add("/static/**");
        permitPath.add("/api/user/login");
        permitPath.add("/api/user/logout");
        permitPath.add("/error**");
        permitPath.add("/api/repair/getRepair");
        return permitPath;
    }

    @Override
    public List<String> ignorePath() {
        return null;
    }

    @Override
    public List<String> authenticatedPath() {
        List<String> authenticatedPath = new LinkedList<>();
        authenticatedPath.add("/**");
        return authenticatedPath;
    }
}
