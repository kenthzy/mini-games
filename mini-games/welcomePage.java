import java.util.Scanner;

public class welcomePage {
    public static void welcome (){
        String green = "\u001B[32m";
        String reset = "\u001B[0m";
        String yellow = "\u001B[33m";
        String red = "\u001B[31m";
        String blue = "\u001B[34m";
        System.out.println(yellow);
        System.out.println("  ███    ███    ██    ███     ██    ██           ██████      █████     ███    ███    ███████    ███████");
        System.out.println("  ████  ████    ██    ████    ██    ██          ██         ██     ██   ████  ████    ██         ██");
        System.out.println("  ██ ████ ██    ██    ██  ██  ██    ██    ███   ██  ███    █████████   ██ ████ ██    █████      ███████");
        System.out.println("  ██  ██  ██    ██    ██   ██ ██    ██          ██   ██    ██     ██   ██  ██  ██    ██              ██");
        System.out.println("  ██      ██    ██    ██    ████    ██           █████     ██     ██   ██      ██    ███████    ███████");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(reset + red + "                            ███████ THIS SYSTEM ARE ALL CREATED WITH JAVA ███████ ");
        System.out.println(reset + green + " ");
        String song = "▇▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇<>▇▇";
        for (int i = 0; i < song.length(); i++) {
            System.out.print(song.charAt(i));


            try {
                // Add a delay of 50 milliseconds between each letter
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(" ");
        System.out.println(" " + reset + blue);
        System.out.println("                                ▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇");
        System.out.println("                                ▇▇                                        ▇▇");
        System.out.println("                                ▇▇      CHOOSE GAME YOU WANT TO PLAY      ▇▇");
        System.out.println("                                ▇▇                                        ▇▇");
        System.out.println("                                ▇▇          TYPE 1:  SNAKE                ▇▇");
        System.out.println("                                ▇▇          TYPE 2:  TIC-TAC-TOE          ▇▇");
        System.out.println("                                ▇▇          TYPE 3:  PING-PONG            ▇▇");
        System.out.println("                                ▇▇          TYPE 4:  TETRIS               ▇▇");
        System.out.println("                                ▇▇                                        ▇▇");
        System.out.println("                                ▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇▇");
        System.out.print("                                                TYPE HERE: "+reset+red);
    }
}
