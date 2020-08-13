package domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @Description
 * @Author wzl
 * @Date 2020/7/6 17:28
 */
public class User implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private List<GrantedAuthority> grantedAuthorityList;

    public User(String username, String password, List<GrantedAuthority> grantedAuthorityList) {
        this.username = username;
        this.password = password;
        this.grantedAuthorityList = grantedAuthorityList;
    }

    public User(Long id, String username, String password, List<GrantedAuthority> grantedAuthorityList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.grantedAuthorityList = grantedAuthorityList;
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User() {

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<GrantedAuthority> getGrantedAuthorityList() {
        return grantedAuthorityList;
    }

    public void setGrantedAuthorityList(List<GrantedAuthority> grantedAuthorityList) {
        this.grantedAuthorityList = grantedAuthorityList;
    }
}
