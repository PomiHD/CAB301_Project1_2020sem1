import java.util.*;

public class MovieCollection {
    public static Node root;
    private List<Map.Entry<String, Integer>> topTenList;
    private static Map<String, Integer> topTenMap;

    public MovieCollection() {
        this.root = null;
        topTenMap = new HashMap<>();
    }

    public boolean find(String title) {
        Node current = root;
        while (current != null) {
            if (current.movie.getMovietitle().equals(title)) {
                return true;
            } else if (current.movie.getMovietitle().compareTo(title) > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    // method to search for an element in a tree
    public Node searchMoive(String title) {
        Node current = root;
        while (current != null) {
            if (current.movie.getMovietitle().compareTo(title) > 0) {//
                current = current.left;
            } else if (current.movie.getMovietitle().compareTo(title) < 0) {//
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }

    public boolean delete(String title) {
        Node parent = root;
        Node current = root;
        boolean isLeftChild = false;
        while (!current.movie.getMovietitle().equals(title)) {
            parent = current;
            if (current.movie.getMovietitle().compareTo(title) > 0) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null) {
                return false;
            }
        }
        //if i am here that means we have found the node
        //Case 1: if node to be deleted has no children
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild == true) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        //Case 2 : if node to be deleted has only one child
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.left != null && current.right != null) {

            //now we have found the minimum element in the right sub tree
            Node successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }


    public Node getSuccessor(Node deleleNode) {
        Node successsor = null;
        Node successsorParent = null;
        Node current = deleleNode.right;
        while (current != null) {
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }
        //check if successor has the right child, it cannot have left child for sure
        // if it does have the right child, add it to the left of successorParent.
//		successsorParent
        if (successsor != deleleNode.right) {
            successsorParent.left = successsor.right;
            successsor.right = deleleNode.right;
        }
        return successsor;
    }

    public void insert() {
        System.out.println("Please enter the Moive title:");
        Scanner kb = new Scanner(System.in);
        String title = kb.nextLine();
        Node newNode;

        if (root == null) {
            newNode = new Node(new Movie(title));
            root = newNode;
            System.out.println("root is newNode Now!!!");
            return;
        }

        boolean isExist = find(title);
        if (!isExist) { // tittle is not existed
            System.out.println(title + " is not found!Adding this new movie to library\n");
            newNode = new Node(new Movie(title));
            Node current = root;
            Node parent = null;
            while (true) {
                parent = current;
                if (current.movie.getMovietitle().compareTo(title) > 0) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        } else if (isExist) { // title is existed
            System.out.println("How many copies do you want to add?");
            Scanner kb1 = new Scanner(System.in);
            int addCopies = kb1.nextInt();

            int oldCopies = searchMoive(title).movie.getCopies();
            int newCopies = oldCopies + addCopies;
            searchMoive(title).movie.setCopies(newCopies);
            Node updatedNode = searchMoive(title);
            delete(title);
            newNode = updatedNode;
            if (root == null) {
                root = newNode;
                System.out.println("root is newNode Now!!!");
                return;
            }
            Node current = root;
            Node parent = null;
            while (true) {
                parent = current;
                if (current.movie.getMovietitle().compareTo(title) > 0) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    public void display(Node root) {
        if (root != null) {
            display(root.left);
            System.out.print(" " + root.movie.toString());
            display(root.right);
        }
    }

    /**
     *
     */
    public void borrow(String title) {

        Movie oldMovie = searchMoive(title).movie;
        Node newNode = new Node(oldMovie);
        boolean isAlreadyExist = find(title);

        if (isAlreadyExist) {
            int oldCopies = searchMoive(title).movie.getCopies();
            newNode.movie.setCopies(oldCopies - 1);
            delete(title);//
            newNode.movie.setRentTimes(newNode.movie.getRentTimes() + 1);//
            if (root == null) {
                root = newNode;
                return;
            }
            Node current = root;
            Node parent = null;
            while (true) {
                parent = current;
                if (title.compareTo(current.movie.getMovietitle()) < 0) {
                    current = current.left;
                    if (current == null) {

                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        } else
            System.out.println("\nSorry, we don't have this movie in this library."
                    + "\nPlease ask admin to add this movie to our library.");
    }

    public void remove(String title) {

        Movie oldMovie = searchMoive(title).movie;
        Node newNode = new Node(oldMovie);
        boolean isAlreadyExist = find(title);
        if (isAlreadyExist) {
            System.out.println("How many copies do you want to remove?");
            Scanner kb1 = new Scanner(System.in);
            int removeCopies = kb1.nextInt();
            int oldCopies = searchMoive(title).movie.getCopies();
            int newCopies = oldCopies - removeCopies;

            newNode.movie.setCopies(newCopies);
            delete(oldMovie.getMovietitle());//

            if (root == null) {
                root = newNode;
                return;
            }
            Node current = root;
            Node parent = null;
            while (true) {
                parent = current;
                if (title.compareTo(current.movie.getMovietitle()) < 0) {
                    current = current.left;
                    if (current == null) {

                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        } else
            System.out.println("\nSorry, we don't have this movie in this library."
                    + "\nPlease ask admin to add this movie to our library.");
    }

    public void setTopTen(Node root) {
        if (root != null) {
            setTopTen(root.left);
            topTenMap.put(root.movie.getMovietitle(), root.movie.getRentTimes());
            setTopTen(root.right);
        }
    }

    public void listTopTen() {

        topTenList = new ArrayList<>(topTenMap.entrySet());
        Collections.sort(topTenList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        int edge;
        if (topTenList.size() < 10) {
            edge = topTenList.size();
            for (int i = 0; i < edge; i++) {
                System.out.println("Top: " +
                        (i + 1) +
                        " " +
                        topTenList.get(i).getKey() +
                        ". Rent Times: " +
                        topTenList.get(i).getValue());
            }
        } else {
            edge = 10;
            for (int i = 0; i < edge; i++) {
                System.out.println("Top: " +
                        (i + 1) +
                        " " +
                        topTenList.get(i).getKey() +
                        ". Rent Times: " +
                        topTenList.get(i).getValue());
            }

        }
    }

    class Node {
        Movie movie;
        Node left;
        Node right;

        public Node(Movie movie) {
            this.movie = movie;
            left = null;
            right = null;
        }
    }
}
