package org.firstinspires.ftc.teamcode.common.util;

public class Shooter {
    public static void shootmanual(boolean move) {
        if (move) {
            Parts.shooterOne.setPower(1);
            Parts.shooterTwo.setPower(1);
        }
    }

    public void shootauto() throws InterruptedException { // idk bro this is skunked asf
        Parts.shooterOne.setPower(1);
        Parts.shooterTwo.setPower(1);
        Thread.sleep(100);
        Parts.shooterOne.setPower(0);
        Parts.shooterTwo.setPower(0);
    }
}

