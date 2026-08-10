package com.work.bench.security;

import com.work.bench.enums.DeleteStatus;
import com.work.bench.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Spring security 用户对象
 *
 * @author 洁心未眠
 * @Package com.work.bench.security
 * @date 2026/8/10 16:42
 */
@Data
@AllArgsConstructor
public class LoginUser implements UserDetails {
    // 数据库用户对象
    private User user;

    /**
     * 返回权限
     * 后续做 RBAC 权限时修改
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    /**
     * 获取密码
     *
     * @return
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 获取用户名
     * <p>
     * 这里不是登录账号
     * 是Security内部唯一标识 为什么？因为你的登录方式可能不同，userName、email、phone，所以需要一个统一标识符来也就是ID
     *
     * @return
     */
    @Override
    public String getUsername() {
        return String.valueOf(user.getId());
    }


    /**
     * 账号是否过期
     * 会抛出 AccountExpiredException 错误
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否锁定
     * 会抛出 LockedException 错误
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        // TODO 这里暂时用账号逻辑删除来确定是否锁定
        return user.getDeleted()
                .equals(DeleteStatus.UNDELETED.getCode());
    }

    /**
     * 密码是否过期
     * 会抛出 CredentialsExpiredException 错误
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否启用或禁用
     * 会抛出 DisabledException 错误
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
