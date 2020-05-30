import java.util.*;

class Member {
    private String name;
    private String password;
    private String contactNumber;
    private Set<String> DVD_title;
    private static Scanner kb;

    public void add() {
        kb = new Scanner(System.in);
        System.out.println("Please enter the member name(one word):[given name+surname]");
        name = kb.nextLine();
        setName(name);

        boolean isFour;
        do {
            System.out.println("Please enter the member password:[4 digits]");
            password = kb.nextLine();
            if (password.length() < 4) {
                isFour = false;
                System.out.println("Warning!!! Please enter 4 digits number");
            } else if (password.length() > 4) {
                isFour = false;
                System.out.println("Warning!!! Please enter 4 digits number");
            } else
                isFour = true;
        } while (!isFour);
        setPassword(password);

        System.out.println("Please enter a Phone number of member :");
        contactNumber = kb.nextLine();
        setContactNumber(contactNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Set<String> getDVD_title() {
        return DVD_title;
    }

    public void setDVD_title(String DVD_title) {
        this.DVD_title.add(DVD_title);
    }

    public Member() {
        add();
        DVD_title = new HashSet<>();
    }

    public Member(String adminName, String adminPWD) {
        name = adminName;
        password = adminPWD;
    }
}
