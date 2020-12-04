package company;


public class Drink{

    //Attributes
    private int id;
    private String name;
    private double price;
    private double size;
    private int alcoholic;
    private int stock;

    //Constructor without ID
    public Drink(String n, double p, double s, int a, int st)  {
        this.name = n;
        this.price = p;
        this.size = s;
        this.alcoholic = a;
        this.stock = st;

    }

    //Constructor with ID
    public Drink(int id, String n, double p, double s, int a, int st)  {
        this.id = id;
        this.name = n;
        this.price = p;
        this.size = s;
        this.alcoholic = a;
        this.stock = st;
    }

    //Constructor with ID and name
    public Drink(int id, String n)  {
        this.id = id;
        this.name = n;
    }


    //Get Methods
    //ID
    public int getId() {
        return id;
    }
    //Name
    public String getName() {
        return name;
    }
    //Price
    public double getPrice() {
        return price;
    }
    //Size
    public double getSize() {
        return size;
    }
    //Alcoholic
    public int getAlcoholic() {
        return alcoholic;
    }
    //Stock
    public int getStock() {
        return stock;
    }


    //Set Methods
    //ID
    public void setId(int id){
        this.id = id;
    }
    //Name
    public void setName(String name) {
        this.name = name;
    }
    //Price
    public void setPrice(double price) {
        this.price = price;
    }
    //Size
    public void setSize(double size) {
        this.size = size;
    }
    //Alcoholic
    public void setAlcoholic(int alcoholic) {
        this.alcoholic = alcoholic;
    }
    //Stock
    public void setStock(int stock) {
        this.stock = stock;
    }
    //Date



    @Override
    public String toString() {
        return  "Drink ID:" + id + " " +
                "Name: " + name  +
                ", Price: " + price +
                ", Size: " + size +
                ", Alcoholic: " + alcoholic +
                ", Stock: " + stock;
    }
}
