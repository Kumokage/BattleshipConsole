package battleship;

import org.jetbrains.annotations.Nullable;
import java.util.Scanner;

/**
 * A {@code BattleshipGame} represent a class,
 * that have main method and do all communication
 * with user and game start and end
 */
public class BattleshipGame {

    /**
     * Method clean console
     */
    private  static void cleanConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println();
    }

    /**
     * Method do communication with user and call initialize classes and
     * use methods we need for game
     * @return if we should finish game or not
     */
    private static boolean game(){
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        do{
            ocean.print();
            try{
                System.out.print("Row = ");
                Integer row = reading();
                if(row == null)
                    return false;
                System.out.print("Column = ");
                Integer column = reading();
                if(column == null)
                    return false;
                cleanConsole();
                if(ocean.shootAt(row, column)){
                    if(ocean.getShips()[row][column].isSunk())
                        System.out.print("Congratulations! ");
                    System.out.println("Hit!\n");
                }
                else System.out.println("Miss!\n");
            }
            catch(IndexOutOfBoundsException e){
                cleanConsole();
                System.out.println("You should enter coordinates in the range 0 - 9");
            }
            catch(NumberFormatException e){
                cleanConsole();
                System.out.println("You should enter coordinates in the range 0 - 9");
            }
        }while(!ocean.isGameOver());
        ocean.print();
        System.out.println("Game is over! I've done " + ocean.getShotsFired() + " shoots.");
        System.out.println("If you want to finish game print exit else print again or something else.");
        Scanner scanner = new Scanner(System.in);
        return !scanner.next().equalsIgnoreCase("exit");
    }

    /**
     * Method read number from console
     * @return null if you will print exit and number if he is able to parse it
     * @throws NumberFormatException
     */
    @Nullable
    private static Integer reading(){
        Integer n = null;
        Scanner scanner = new Scanner(System.in);
        String buf = scanner.next();
        if (buf.equalsIgnoreCase("exit"))
            return null;
        n = Integer.parseInt(buf);
        return n;
    }

    /**
     * Main class has nothing special, only start game and control
     * when it finishes
     * @param args
     */
    public static void main(String[] args){
        cleanConsole();
        System.out.println("\nYou are welcome to play Battleship game! \n" +
                "Enter first coordinate than second to shoot! If you want\n" +
                "exit game, you can write 'exit' at any time! Sulk all ships!!\n" +
                " \t Enjoy the game! Good luck!\n");
        try{
            while(game()) ;
        }
        catch(Exception e){
            System.out.println("Something go wrong!" + e.getMessage());
        }
        System.out.println("Thank you for game! By!");
    }
}
