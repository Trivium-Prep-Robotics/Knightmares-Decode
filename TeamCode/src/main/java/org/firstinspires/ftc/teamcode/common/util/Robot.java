package org.firstinspires.ftc.teamcode.common.util;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * example class for a robot that implements the interfaces
 * All methods must be here, but if you don't want to use them simply leave them blank
 */

public class Robot implements Drive {
    /**
     * SHOOTER SECTION
     */





    /**
     * DRIVE SECTION
     */

    // for teleOp movement (field centric)

    public void feildCentric(Gamepad gamepad) { // yeah just put this in your teleOp
        double botHeading = Parts.imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double vertical = -gamepad.left_stick_y * Parts.driveMaxSpd;
        double horizontal = gamepad.left_stick_x * Parts.driveMaxSpd;
        double pivot = gamepad.right_stick_x * Parts.driveMaxSpd;
        double denominator = Math.max(Math.abs(vertical) + Math.abs(horizontal) + Math.abs(pivot), 1);

        if (gamepad.right_trigger > 0) {
            vertical = -gamepad.left_stick_y * Parts.driveSlwSpd;
            horizontal = gamepad.left_stick_x * Parts.driveSlwSpd;
            pivot = gamepad.right_stick_x * (Parts.driveSlwSpd + 0.1);
        }

        // Kinematics (Counter-acting angle of robot's heading)
        double newVertical = horizontal * Math.sin(-botHeading) + vertical * Math.cos(-botHeading);
        double newHorizontal = horizontal * Math.cos(-botHeading) - vertical * Math.sin(-botHeading);

        // Setting Field Centric Drive
        Parts.FL.setPower((newVertical + newHorizontal + pivot) / denominator);
        Parts.FR.setPower((newVertical - newHorizontal - pivot) / denominator);
        Parts.BL.setPower((newVertical - newHorizontal + pivot) / denominator);
        Parts.BR.setPower((newVertical + newHorizontal - pivot) / denominator);
    }

}
