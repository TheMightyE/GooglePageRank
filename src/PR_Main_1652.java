/* Syed Khalid      cs435 1652 mp */

public class PR_Main_1652 {

    public static void main(String[] args) {
        /* Command line processing */
        int iterations = 0;
        double initialValue = 0;
        String filename = "";

        if (args.length != 3) {
            System.err.println("Incorrect number of arguments provided (expected 3).");
            System.err.println("Program terminated.");
            System.err.println("Command format:");
            System.err.println("java PR_Main_1652 iterations initial_value filename");
            System.exit(1);
        } else {
            try {
                iterations = Integer.parseInt(args[0]);
                initialValue = Double.parseDouble(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Argument 1 must be an integer and argument 2 must be a floating point value.");
                System.err.println("Program terminated.");
                System.exit(1);
            }

            filename = args[2];
        }

        PageRank_1652 rank = new PageRank_1652(iterations, initialValue, filename);
        rank.rank();

    }
}
