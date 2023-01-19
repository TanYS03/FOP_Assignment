public class exercise {
    public static void main(String[] args) {

        Burger first = new Burger(234);
        Burger second = new Burger(345);
        first.getNumBurger();
//        Burger.showNumBurger;
    }
}

class Burger{
    private int numBurger;
    private int totalnumBurger;

    public Burger(int amount){
        numBurger += amount;
        sold(amount);
    }

    public int getNumBurger(){
        return numBurger;
    }

    public void showNumBurger(){
        System.out.println(totalnumBurger);
    }

    void sold(int amount){
        totalnumBurger += amount;
    }

}
