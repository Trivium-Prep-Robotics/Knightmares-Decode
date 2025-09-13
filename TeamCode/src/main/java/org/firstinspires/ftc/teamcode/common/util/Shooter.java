package org.firstinspires.ftc.teamcode.common.util;

import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.utility.LambdaCommand;

public class Shooter {


   /*
    Command shootmanuel = new LambdaCommand()
            .setStart(() -> {
                Parts.shooterOne.setPower(1);
                Parts.shooterTwo.setPower(1);
            });*/
    public static void shootmanual(boolean move) {
        if (move) {
            Parts.shooterOne.setPower(1);
            Parts.shooterTwo.setPower(1);
        }
    }

    public static void shootauto() throws InterruptedException  { // if this doesn't work then I'm not surprised lmao

        Parts.shooterOne.setPower(1);
        Parts.shooterTwo.setPower(1);
        Thread.sleep(100);
        Parts.shooterOne.setPower(0);
        Parts.shooterTwo.setPower(0);
        Thread.sleep(100);
        Parts.shooterOne.setPower(1);
        Parts.shooterTwo.setPower(1);
        Thread.sleep(100);
        Parts.shooterOne.setPower(0);
        Parts.shooterTwo.setPower(0);
        Thread.sleep(100);
        Parts.shooterOne.setPower(1);
        Parts.shooterTwo.setPower(1);
        Thread.sleep(100);
        Parts.shooterOne.setPower(0);
        Parts.shooterTwo.setPower(0);
    }
}

