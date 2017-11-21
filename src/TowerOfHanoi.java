import java.util.Scanner;

public class TowerOfHanoi {

    public static int[][] towers;
    public static int poles = 3;
    public static int discs;
    public static int moves = 0;

    public static void main(String[] args)
    {
        System.out.print("Enter number of discs: ");
        Scanner scanner = new Scanner(System.in);

        discs = scanner.nextInt();
        towers = new int[discs][poles];

        // place the discs on the left-most pole (column)
        for (int disc = 0; disc < discs; disc++)
        {
            towers[disc][0] = (disc * 2) + 1;
        }

        printTowers();
        solveTowers(discs, 0, 2);
        scanner.close();
        System.out.println("Number of moves: " + moves);
    }

    // Use recursive method to solve the towers
    public static void solveTowers(int numDiscs, int startPole, int endPole)
    {
        if (numDiscs != 0)
        {
            int pole = 3 - endPole - startPole;
            solveTowers(numDiscs - 1, startPole, pole);
            move(startPole, endPole);
            solveTowers(numDiscs - 1, pole, endPole);
        }
    }

    // Moves a disc
    public static void move(int fromPole, int toPole)
    {
        moves++;
        int disc = 0;
        while (disc < discs && towers[disc][fromPole] == 0)
        {
            disc++;
        }

        int temp = towers[disc][fromPole];
        towers[disc][fromPole] = 0;

        int newDisc = 0;
        while (newDisc < discs && towers[newDisc][toPole] == 0)
        {
            newDisc++;
        }
        towers[--newDisc][toPole] = temp;
        printTowers();
    }

    public static void printTowers()
    {
        System.out.println();
        for (int disc = 0; disc < discs; disc++)
        {
            for (int pole = 0; pole < poles; pole++)
            {
                System.out.print(prettyPrint(towers[disc][pole]));
            }
            System.out.println();
        }
        System.out.println();
    }

    // Pads the width of the disc for printing
    public static String prettyPrint(int disc)
    {
        // prettyPrint string with spaces
        int columnWidth = (discs * 2) + 2;


        String output = "";
        if (disc == 0)
        {
            output = "|";
        } else for (int i = 0; i < disc; i++)
        {
            output += "*";
        }

        // if length is odd prettyPrint end to even length
        if (output.length() % 2 == 1)
        {
            output += " ";
        }
        // justify prettyPrint to center in cell
        while (output.length() < columnWidth)
        {
            output = " " + output + " ";
        }
        return output;
    }
}