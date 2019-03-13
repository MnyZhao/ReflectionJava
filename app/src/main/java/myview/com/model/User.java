package myview.com.model;

/**
 * Crate by E470PD on 2019/3/11
 */
public class User extends UserGroup {
    private String name="宝塔镇河妖";
    private String email;
    public String publicName;

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    private void setName(String name) {
        System.out.println("User.setName" + name);
        this.name = name;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getPublicName() {
        return this.publicName;
    }
}
