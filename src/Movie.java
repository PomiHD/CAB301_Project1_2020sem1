import java.util.Scanner;

public class Movie implements Comparable<Movie> {

    public enum Classification {G, PG, M15, MA15}

    public enum Genre {Drama, Adventure, Family, Action, SciFi, Comedy, Thriller, Other}

    private String movietitle;
    private String starring;
    private String director;
    private Classification _classification;
    private int duration;
    private Genre _genre;
    private int releasedate;
    private int copies;
    private int rentTimes = 0;
    private Scanner kb;

    @Override
    public int compareTo(Movie other) {
        if (other.getRentTimes() > this.getRentTimes()) return 1;
        else if (other.getRentTimes() < this.getRentTimes()) return -1;
        return 0;
    }

    public int getRentTimes() {
        return rentTimes;
    }

    public void setRentTimes(int rentTimes) {
        this.rentTimes = rentTimes;
    }

    public String getMovietitle() {
        return movietitle;
    }

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Classification get_classification() {
        return _classification;
    }

    public void set_classification(Classification _classification) {
        this._classification = _classification;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Genre get_genre() {
        return _genre;
    }

    public void set_genre(Genre _genre) {
        this._genre = _genre;
    }

    public int getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(int releasedate) {
        this.releasedate = releasedate;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int newCopies) {
        copies = newCopies;
    }


    public String toString() {
        return "\n\nTitle: " + getMovietitle() +
                "\nDirector:" + getDirector() +
                "\nStarring: " + getStarring() +
                "\nClassification: " + get_classification() +
                "\nDuration: " + getDuration() +
                "\nGenre: " + get_genre() +
                "\nRelease Date:" + getReleasedate() +
                "\nRemaining Copies:" + getCopies() +
                "\nRent times: " + getRentTimes();
    }

    public void add() {
        System.out.println("Please enter the starring name:");
        starring = kb.nextLine();
        setStarring(starring);
        System.out.println("Please enter the director name:");
        director = kb.nextLine();
        setDirector(director);

        String choices_genre;
        boolean isValid_Input;
        do {
            System.out.println("\nSelect the genre: \n1. Drama\n2. Adventure\n3. Family\n4. Action\n5. Sci-Fi\n6. Comedy\n7. Thrille\n8. Other\nMake Selection(1-8):");
            kb = new Scanner(System.in);
            choices_genre = kb.nextLine();
            if (!choices_genre.matches("[1-8]")) {
                isValid_Input = false;
                System.out.println("Illegal selection,please enter the correct number!");
            } else
                isValid_Input = true;

        } while (!isValid_Input);

        switch (choices_genre) {
            case "1" -> {
                _genre = Genre.Drama;
                set_genre(_genre);
            }
            case "2" -> {
                _genre = Genre.Adventure;
                set_genre(_genre);
            }
            case "3" -> {
                _genre = Genre.Family;
                set_genre(_genre);
            }
            case "4" -> {
                _genre = Genre.Action;
                set_genre(_genre);
            }
            case "5" -> {
                _genre = Genre.SciFi;
                set_genre(_genre);
            }
            case "6" -> {
                _genre = Genre.Comedy;
                set_genre(_genre);
            }
            case "7" -> {
                _genre = Genre.Thriller;
                set_genre(_genre);
            }
            case "8" -> {
                _genre = Genre.Other;
                set_genre(_genre);
            }
            default -> {
                System.out.println("Invalid input, please enter again!!!");
            }
        }
        System.out.println("Please set up the duration time of the movie");
        duration = kb.nextInt();
        setDuration(duration);

        String choices_classification;
        boolean isValid_Input_;
        do {
            System.out.println(
                    "\nSelect the classification: " +
                            "\n1. General (G)" +
                            "\n2. Parental Guidance (PG)" +
                            "\n3. Mature (M15+)" +
                            "\n4. Mature Accompanied (MA15+)" +
                            "\nMake Selection(1-4): \n");

            kb = new Scanner(System.in);
            choices_classification = kb.nextLine();
            if (!choices_classification.matches("[1-4]")) {
                isValid_Input_ = false;
                System.out.println("Illegal selection,please enter the correct number!");
            } else
                isValid_Input_ = true;

        } while (!isValid_Input_);

        switch (choices_classification) {
            case "1" -> {
                _classification = Classification.G;
                set_classification(_classification);
            }
            case "2" -> {
                _classification = Classification.PG;
                set_classification(_classification);
            }
            case "3" -> {
                _classification = Classification.M15;
                set_classification(_classification);
            }
            case "4" -> {
                _classification = Classification.MA15;
                set_classification(_classification);
            }
            default -> System.out.println("Invalid input, please enter again!!!");
        }

        System.out.println("Please set up the release date:");
        releasedate = kb.nextInt();
        setReleasedate(releasedate);
        System.out.println("Please set up the copies of movie DVD");
        copies = kb.nextInt();
        setCopies(copies);
    }

    public Movie(String title) {
        kb = new Scanner(System.in);
        movietitle = title;
        add();
    }
}
