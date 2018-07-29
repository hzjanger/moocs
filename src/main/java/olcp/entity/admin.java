package olcp.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 管理员用户
 */
@Entity
@Table(name = "admin")
public class admin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private int state;
//    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
//    private List<SysRole> roles;

    public admin(Integer id, String name, String password, Integer state) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.state = state;
    }

    public admin() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

//    public List<SysRole> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<SysRole> roles) {
//        this.roles = roles;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> auths = new ArrayList<>();
//        List<SysRole> roles = this.getRoles();
//        for (SysRole role : roles) {
//            auths.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return auths;
//    }
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }


}
