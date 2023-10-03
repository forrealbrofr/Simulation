package org.example.actions;

import org.example.Map;

import java.util.Scanner;

public class CycleActions extends Actions{
    private final static Scanner scanner = new Scanner(System.in);
    private final static String CONTINUE_KEY = "1";
    private final static String STOP_KEY = "2";
    private final static String QUIT_KEY = "3";
    public CycleActions(Map map) {
        super(map);
    }
    private final IterateActions iterateActions = new IterateActions(map);

    @Override
    public void perform() {
        startCycle();
    }

    private void startCycle() {
        String inputKey = CONTINUE_KEY;
        while (!inputKey.equals(QUIT_KEY)) {
            while (!inputKey.equals(STOP_KEY) && !inputKey.equals(QUIT_KEY)) {
                inputKey = loopOneTime();
            }
            if (inputKey.equals(QUIT_KEY))
                break;
            inputKey = scanner.nextLine();
            switch (inputKey) {
                case CONTINUE_KEY -> inputKey = CONTINUE_KEY;
                case QUIT_KEY -> inputKey = QUIT_KEY;
            }
        }
    }
    public String loopOneTime()
    {
        String inputKeyInsideLoop;
        try
        {
            iterateActions.perform();
            showOptions();
            Thread.sleep(1000);
            if (System.in.available() > 0)
            {
                inputKeyInsideLoop = scanner.nextLine();
                return switch (inputKeyInsideLoop) {
                    case "3" -> QUIT_KEY;
                    case "2" -> STOP_KEY;
                    default -> CONTINUE_KEY;
                };
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return CONTINUE_KEY;
    }


    private static void showOptions()
    {
        System.out.print("1 - continue the cycle\t");
        System.out.print("2 - stop the cycle\t");
        System.out.println("3 - quit the cycle");
    }

}
