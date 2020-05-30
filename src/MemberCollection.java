import java.util.*;

public class MemberCollection {

    private String phone;
    private static Map<String, Set<String>> borrowedDVDmap;
    private static List<Member> memberList;// NB:As assignment requirement, it should use an Array to store member!!!
    private static Scanner kb;
    private static Set<String> nameSet;

    public Map<String, Set<String>> getMap() {
        return borrowedDVDmap;
    }

    public void setMap(String DVD_title, String name) {
        for (int i = 0; i < memberList.size(); i++) {
            if (name.equals(memberList.get(i).getName())) {
                memberList.get(i).setDVD_title(DVD_title);
                borrowedDVDmap.put(name, memberList.get(i).getDVD_title());
            }
        }
    }

    public static List<Member> getMemberList() {
        return memberList;
    }

    public Member adminUser() {
        return memberList.get(0);
    }

    public void register() {
        Member member = new Member();
        if (nameSet.add(member.getName()))
            memberList.add(member);
        else
            System.out.println("Register failed!!! This member has been registered!!!");
    }

    public void getPhone() {
        System.out.println("Please enter the name");
        String name = kb.nextLine();
        for (Member m : memberList) {
            if (name.compareTo(m.getName()) == 0) {
                phone = m.getContactNumber();
                System.out.println("The Phone NO. is: " + phone);
            }
        }
    }

    public void listBorrowed(String name) {
        String titles_DVD = "";
        Set<String> key = null;
        key = borrowedDVDmap.get(name);

        if (key != null) {
            for (String s : key) {
                titles_DVD += " <<" + s + ">> ";
            }
            System.out.println(name + " has borrowed moives: " + titles_DVD);
        } else
            System.out.println(name + " haven't borrow any movie.");

    }

    public MemberCollection() {
        kb = new Scanner(System.in);
        borrowedDVDmap = new HashMap<>();
        memberList = new ArrayList<Member>();
        nameSet = new HashSet<>();
        memberList.add(new Member("staff", "today123"));
    }
}
