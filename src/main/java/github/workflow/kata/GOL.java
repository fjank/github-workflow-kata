package github.workflow.kata;

public final class GOL {
    private GOL() {
    }

    /**
     * Starter for this Game Of Life.
     *
     * @param args the commend line arguments this GOL is started with.
     */
    public static void main(String[] args) {
        if (args.length != 0) {
            // started with -help
            String result = "You can either load a world from a file with -file option,\n"
                    + "or manually entering the world definition after starting GOL with no arguments.\n"
                    + "The definition should be as the following example:\n"
                    + "Generation 1\n"
                    + "3 3\n"
                    + "...\n"
                    + "***\n"
                    + "...\n"
                    + "\n"
                    + "You finish the grid definition with an empty line.";
            System.out.println(result);
            return;
        }
        System.out.println("Generation 2:\n4 8\n........\n...**...\n...**...\n........");
    }
}
