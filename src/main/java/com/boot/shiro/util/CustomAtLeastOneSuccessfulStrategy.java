package com.boot.shiro.util;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.util.CollectionUtils;

/**
 * @Author zxx
 * @Description
 * @Date Created on 2017/11/11
 */
public class CustomAtLeastOneSuccessfulStrategy extends AtLeastOneSuccessfulStrategy {

    public AuthenticationInfo afterAllAttempts(AuthenticationToken token,
            AuthenticationInfo aggregate) throws AuthenticationException {
        //we know if one or more were able to succesfully authenticate if the aggregated account object does not
        //contain null or empty data:
        if (aggregate == null || CollectionUtils.isEmpty(aggregate.getPrincipals())) {
            if (token instanceof UsernamePasswordToken) {
                throw new IncorrectCredentialsException();
            } else {
                throw new AuthenticationException(
                        "Authentication token of type [" + token.getClass() + "] " +
                                "could not be authenticated by any configured realms.  Please ensure that at least one realm can "
                                +
                                "authenticate these tokens.");
            }
        }
        return aggregate;
    }
}
