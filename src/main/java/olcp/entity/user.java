package olcp.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user")
public class user implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column
    private Integer userid;
    @Column
    private String userrealname;
    @Column
    private String userpassword;
    @Column
    private String usereducationlevel;
    @Column
    private String userpicture;
    @Column
    private String usersex;
    @Column
    private String userphone;
    @Column
    private String usernickname;
    @Column
    private String usersignature;
    @Column
    private String useremail;
    @Column
    private String useruniversity;
    @Column
    private String usercity;
    @Column
    private String userjob;
    @Column
    private Integer userstate;
    @Column
    private String useraddress;
    public user(Integer userid, String userrealname, String userpassword, String usereducationlevel,String userpicture,String usersex,String userphone,String usernickname,String usersignature,String useremail,String useruniversity,String usercity,String userjob,Integer userstate,String useraddress) {
        this.userid = userid;
        this.userrealname = userrealname;
        this.userpassword = userpassword;
        this.usereducationlevel = usereducationlevel;
        this.userpicture=userpicture;
        this.usersex=usersex;
        this.userphone=userphone;
        this.usernickname=usernickname;
        this.usersignature=usersignature;
        this.useremail=useremail;
        this.useruniversity=useruniversity;
        this.usercity=usercity;
        this.userjob=userjob;
        this.userstate=userstate;
        this.useraddress=useraddress;
    }

    public user() {
        super();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUserrealname() {
        return userrealname;
    }

    public void setUserrealname(String userrealname) {
        this.userrealname = userrealname == null ? null : userrealname.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null :userpassword.trim();
    }

    public String getUsereducationlevel() {
        return usereducationlevel;
    }

    public void setUsereducationlevel(String usereducationlevel) {
        this.usereducationlevel = usereducationlevel == null ? null : usereducationlevel.trim();
    }

    public String getUserpicture() {
        return userpicture;
    }

    public void setUserpicture(String userpicture) {
        this.userpicture = userpicture == null ? null : userpicture.trim();
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex == null ? null : usersex.trim();
    }
    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone== null ? null : userphone.trim();
    }

    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname == null ? null : usernickname.trim();
    }
    public String getUsersignature() {
        return usersignature;
    }

    public void setUsersignature(String usersignature) {
        this.usersignature = usersignature;
    }
    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUseruniversity() {
        return useruniversity;
    }

    public void setUseruniversity(String useruniversity) {
        this.useruniversity =useruniversity;
    }

    public String getUsercity() {
        return usercity;
    }

    public void setUsercity(String usercity) {
        this.usercity = usercity;
    }

    public String getUserjob() {
        return userjob;
    }

    public void setUserjob(String userjob) {
        this.userjob = userjob;
    }

    public Integer getUserstate() {
        return userstate;
    }

    public void setUserstate(Integer userstate) {
        this.userstate = userstate;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }
}
