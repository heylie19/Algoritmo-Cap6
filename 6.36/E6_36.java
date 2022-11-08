import java.util.*;
import java.util.Scanner;
public class E6_36 
{
    

    public static void main(String []args){

        String numShares = "";
        String precio = "";
        capGain cg = new capGain();
        Scanner input = new Scanner (System.in);

        printMenu();

        System.out.println("Ingrese el comando: ");
        String command = input.nextLine();

        while(!command.equals("4")){
            switch(command){
                case "1": System.out.println("Cuantas acciones comprar: ");
                    numShares = input.nextLine();

                    System.out.println("A que precio ");
                    precio = input.nextLine();

                    cg.totalShares+=Integer.parseInt(numShares);
                    Trade t1 = new Trade(Integer.parseInt(numShares), Double.parseDouble(precio));
                    cg.q.add(t1);
                    break;

                case "2":
                    System.out.println("Cuantas acciones vender: ");
                    numShares = input.nextLine();
                    System.out.println("A que precio ");
                    precio = input.nextLine();
                    cg.sellShares(Integer.parseInt(numShares), Double.parseDouble(precio));
                    break;

                case "3": System.out.println("Ganancias/pérdidas de capital: " + cg.totCapGain);
                    break;
                case "0":  printMenu();
                    break;
            }
            System.out.println("Igrese comando: ");
            command = input.nextLine();
        }
        System.out.println("Saliendo del programa");
    }

    public static void printMenu(){
        System.out.println("1: Comprar");
        System.out.println("2: Vender");
        System.out.println("3: Imprimir Ganancia de Capital");
        System.out.println("4: Salir");
        System.out.println("0: Ayuda");
    }
}

class capGain{
    int totalShares = 0;
    double totCapGain = 0;
    Queue<Trade> q = new LinkedList<>();

    int getTotalSahres(){
        return totalShares;
    }

    void setTotalShares(int totalShares){
        this.totalShares = totalShares;
    }

    double getTotCapGain(){
        return totCapGain;
    }

    void setTotCapGain(double totCapGain){
        this.totCapGain = totCapGain;
    }

    void sellShares(int toSell, double currentPrice){
        int selling = toSell;
        // Si hay suficientes acciones para vender
        if(totalShares >= selling) 
        {
            // Mientras haya acciones que necesitan ser vendidas
            while (selling > 0) 
            {
                // Si hay menos acciones en este objeto comercial que la cantidad que aún debe venderse, se realiza el cálculo apropiado y el objeto comercial se borra de la cola))
                if (q.peek().getShares() <= selling) {
                    totCapGain += q.peek().getShares() * (currentPrice - q.peek().getPrice());
                    totalShares -= q.peek().getShares();
                    selling -= q.remove().getShares();
                // De lo contrario, la cantidad necesaria para vender es menor que la cantidad en el objeto comercial actual, el número de acciones que quedan en el comercio se reducen
                } else {
                    totCapGain += selling * (currentPrice - q.peek().getPrice());
                    q.peek().setShares(q.peek().getShares() - selling);
                    totalShares-=selling;
                    selling = 0;
                    // elimina los recursos compartidos de la cola, establece ShareLeft en 0
                }
            }
        }
        // Si no hay suficientes acciones para vender
        else {
            System.out.println("No hay suficientes acciones - Total de acciones disponibles: " + totalShares);
        }
    }
}

// El objeto comercial se agrega a la cola para contener información sobre cada comercio, se eliminan del quque o se reducen según sea necesario// Trade object gets added to the queue to hold information about each trade, they are removed from the quque or decremented as needed
class Trade{
    int numShares;
    double price;

    Trade(int numShares, double price){
        this.numShares = numShares;
        this.price = price;
    }

    void setShares(int numShares){
        this.numShares = numShares;
    }

    void setPrice(double price){
        this.price = price;
    }

    int getShares(){
        return numShares;
    }

    double getPrice() {
        return price;
    }
}


