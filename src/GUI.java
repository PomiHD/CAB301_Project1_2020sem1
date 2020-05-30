import java.util.Scanner;

public class GUI {
    private static Scanner kb;
    private static MovieCollection movieCollection;
    private static MemberCollection memberCollection;
    private static boolean isValid_member;
    private static boolean isBorrowed;
    private static String name_member;

    GUI() {
        kb = new Scanner(System.in);
        movieCollection = new MovieCollection();
        memberCollection = new MemberCollection();
    }


    public static void mainmenu() {
        System.out.println("\n\nWelcome to the Community Library");
        System.out.println("===========Main Menu============");
        System.out.println(" 1. Staff Login");
        System.out.println(" 2. Member Login");
        System.out.println(" 0. Exit");
        System.out.println("================================");
        String choices_mainmenu;
        boolean isValid_Input;
        do {
            System.out.println("\nPlease make a selection (1-2, or 0 to exit): ");
            choices_mainmenu = kb.nextLine();
            if (!choices_mainmenu.matches("[0-2]")) {
                isValid_Input = false;
                System.out.println("Illegal selection,please enter the correct number!");
            } else
                isValid_Input = true;

        } while (!isValid_Input);

        switch (choices_mainmenu) {
            case "0":
                System.out.println("exit the program");
                break;
            case "1":
                boolean isName_staff;
                boolean isPassword_staff;
                do {
                    System.out.println("Please enter your staff name:");
                    String admin_name = kb.nextLine();
                    if (memberCollection.adminUser().getName().compareTo(admin_name) != 0) {
                        isName_staff = false;
                        System.out.println("Wrong staff name,please enter the correct staff name!");
                    } else
                        isName_staff = true;
                } while (!isName_staff);

                do {
                    System.out.println("Please enter your password");
                    String admin_pwd = kb.nextLine();
                    if (memberCollection.adminUser().getPassword().compareTo(admin_pwd) != 0) {
                        isPassword_staff = false;
                        System.out.println("Wrong staff PWD,please enter the correct staff PWD!");
                    } else
                        isPassword_staff = true;
                } while (!isPassword_staff);
                staffMenu();
                break;
            case "2":
                System.out.println("Please enter your member name(one word):[given name+surname]");
                name_member = kb.nextLine();
                System.out.println("Please enter your password:[4 digits]");
                String member_pwd = kb.nextLine();
                isValid_member = false;

                for (Member m : MemberCollection.getMemberList()) {
                    if (m.getName().compareTo(name_member) == 0 && m.getPassword().compareTo(member_pwd) == 0)
                        isValid_member = !isValid_member;
                }
                if (isValid_member)
                    memberMenu();
                else {
                    System.out.println("Sorry, you are not our member." +
                            "\nPlease enter the correct member name and password!" +
                            "\nOr contact our official staff to add you to our memberlist." +
                            "\nReturning to main menu.");
                    mainmenu();
                }
                break;
            default:
                System.out.println("Invalid!!!");
                break;
        }
    }

    public static void staffMenu() {
        System.out.println();
        System.out.println("\n\n===========Staff Menu============");
        System.out.println("1. Add a new movie DVD");
        System.out.println("2. Remove a movie DVD");
        System.out.println("3. Register a new member");
        System.out.println("4. Find a registered member's phone number");
        System.out.println("0. Return to main menu");
        System.out.println("=====================================");

        String choices_staffmenu;
        boolean isValid_Input;
        do {
            System.out.println("\nPlease make a selection (1-4, or 0 to exit to main menu): ");
            choices_staffmenu = kb.nextLine();
            if (!choices_staffmenu.matches("[0-4]")) {
                isValid_Input = false;
                System.out.println("Illegal selection,please enter the correct number!");
            } else
                isValid_Input = true;
        } while (!isValid_Input);

        switch (choices_staffmenu) {
            case "1" -> {
                movieCollection.insert();
                staffMenu();
            }
            case "2" -> {
                String title_toRemove;
                boolean isExist_title;
                do {
                    System.out.println("Please enter the movie title:");
                    title_toRemove = kb.nextLine();
                    if (!movieCollection.find(title_toRemove)) {
                        isExist_title = false;
                        System.out.println("Sorry, we don't have this movie in our library!" +
                                " Please try to search other movies.");
                    } else
                        isExist_title = true;
                } while (!isExist_title);
                movieCollection.remove(title_toRemove);
                staffMenu();
            }
            case "0" -> mainmenu();
            case "3" -> {
                memberCollection.register();
                staffMenu();
            }
            case "4" -> {
                memberCollection.getPhone();
                staffMenu();
            }
            default -> System.out.println("invalid");
        }
    }

    public static void memberMenu() {
        System.out.println("\n\n===========Member Menu===========");
        System.out.println("1. Display all movies");
        System.out.println("2. Borrow a movie DVD");
        System.out.println("3. Return a movie DVD");
        System.out.println("4. List current borrowed movie DVDs");
        System.out.println("5. Display top 10 most popular movies");//
        System.out.println("0. Return to main menu");

        String choices_membermenu;
        boolean isValid_Input;
        do {
            System.out.println("\nPlease make a selection (1-5, or 0 to exit to main menu): ");
            choices_membermenu = kb.nextLine();
            if (!choices_membermenu.matches("[0-5]")) {
                isValid_Input = false;
                System.out.println("Illegal selection,please enter the correct number!");
            } else
                isValid_Input = true;
        } while (!isValid_Input);

        switch (choices_membermenu) {
            case "1" -> {
                if (movieCollection.root == null)
                    System.out.println("Opps, the movie library is empty now!");
                else
                    movieCollection.display(movieCollection.root);
                memberMenu();
            }
            case "2" -> {
                System.out.println("Please enter the movie title:");
                String title_BorrowedDVD = kb.nextLine();
                isBorrowed = false;
                if (memberCollection.getMap().get(name_member) != null) {
                    for (String str : memberCollection.getMap().get(name_member)) {
                        if (str.equals(title_BorrowedDVD))
                            isBorrowed = true;
                    }
                }
                if (!isBorrowed && movieCollection.find(title_BorrowedDVD)) {
                    movieCollection.borrow(title_BorrowedDVD);
                    memberCollection.setMap(title_BorrowedDVD, name_member);
                } else {
                    System.out.println("Sorry, each member can only borrow same movie each time!" +
                            "Or can't borrowed the movie that is not exist in the library!");
                }
                memberMenu();
            }
            case "3" -> {
                String title;
                System.out.println("Please enter the movie name: ");
                title = kb.nextLine();
                if (!movieCollection.find(title)) {
                    System.out.println("Sorry, we don't have this movie in the library.");
                } else {
                    Movie returnedMovie = movieCollection.searchMoive(title).movie;
                    System.out.println(returnedMovie.toString());
                }
                memberMenu();
            }
            case "4" -> {
                memberCollection.listBorrowed(name_member);
                memberMenu();
            }
            case "5" -> {

                movieCollection.setTopTen(movieCollection.root);
                movieCollection.listTopTen();
                memberMenu();
            }
            case "0" -> mainmenu();
            default -> System.out.println("Invalid!!! Please try again: ");
        }
    }

    public static void main(String[] args) {
        GUI runMenu = new GUI();
        runMenu.mainmenu();
    }
}

