package BuzzFunds;

public class Student {


    private final String name;
    private int gtid;
    private int classYear;
    private double gpa;
    private double diningDollar;
    private double buzzfund;


    public Student(String name, int gtid, int classYear, double gpa, double diningDollar, double buzzfund) {
        this(name, gtid, classYear);

        if (gpa < 0.0 || gpa > 4.0) {
            this.gpa = 4.0;
        } else {
            this.gpa = gpa;
        }

        if (diningDollar < 0) {
            this.diningDollar = 0.0;
        } else {
            this.diningDollar = diningDollar;
        }

        if (buzzfund < 0) {
            this.buzzfund = 0.0;
        } else {
            this.buzzfund = buzzfund;
        }

    }

    public Student(String name, int gtid, int classYear) {
        this(name);
        this.gtid = gtid;
        if (classYear > 4 || classYear <= 0) {
            this.classYear = 1;
        } else {
            this.classYear = classYear;
        }
        this.diningDollar = 0.0;
        this.buzzfund = 0.0;
        this.gpa = 4.0;
    }

    public Student(String name, double diningDollar, double buzzfund) {
        this(name, -1, 1);
        this.diningDollar = diningDollar;
        this.buzzfund = buzzfund;
    }

    public Student(String name) {
        this.name = name;
        this.gtid = -1;
        this.classYear = 1;
        this.gpa = 4.0;
        this.diningDollar = 0.0;
        this.buzzfund = 0.0;

    }


    public void purchaseMeal(double cost) {
        boolean sufficientFunds;

        if (this.diningDollar >= cost) {
            this.diningDollar = this.diningDollar - cost;
            sufficientFunds = true;
        } else if (this.diningDollar < cost && this.buzzfund + this.diningDollar >= cost) {
            this.buzzfund = this.buzzfund - (cost - this.diningDollar);
            this.diningDollar = 0;
            sufficientFunds = true;
        } else {
            this.diningDollar = this.diningDollar;
            this.buzzfund = this.buzzfund;
            sufficientFunds = false;
        }

        if (sufficientFunds) {
            System.out.println(name + " has successfully purchased the meal");
        } else {
            System.out.println(name + " does not have enough funds purchase the meal");
        }

    }

    public void addDiningDollars(double diningDollarVal) {
        this.diningDollar = this.diningDollar + diningDollarVal;
    }

    public void addBuzzfund(double buzzfundVal) {
        this.buzzfund = this.buzzfund + buzzfundVal;

    }

    public void increaseClassYear() {
        if (this.classYear == 4) {
            this.classYear = 4;
        } else {
            this.classYear = this.classYear + 1;
        }
    }

    public String toString() {
        return "Name: " + name + ", GTID: " + gtid + ", GPA: " + gpa + ", Class Year: "
                + classYear + ",Funds: " + (buzzfund + diningDollar);
    }

    public boolean equals(Student other) {
        if (this.gpa == other.gpa && this.classYear == other.classYear && this.name == other.name) {
            return true;


        }
        return false;

    }
}
