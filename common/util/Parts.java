package org.firstinspires.ftc.teamcode.common.util;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class Parts {

    // declaring parts
    // TODO: get rid of and/or add any parts you need
    public static DcMotor FR, FL, BR, BL;
    public static DcMotor shooterOne, shooterTwo;


    public static IMU imu;
    IMU.Parameters myIMUparameters;

    /**
     * important variables
     */

    public static double driveMaxSpd = 1;
    public static double driveSlwSpd = 0.5;

    public Parts(HardwareMap hardwareMap) {


        // TODO: assign drive train names here
        FR = hardwareMap.get(DcMotor.class, "rightFront");
        FL = hardwareMap.get(DcMotor.class, "leftFront");
        BR = hardwareMap.get(DcMotor.class, "rightRear");
        BL = hardwareMap.get(DcMotor.class, "leftRear");

        // TODO: reverse any motors that go the opposite direction
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);

        // when setPower(0) -> motors brake
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        shooterOne = hardwareMap.get(DcMotor.class, "shooterOne");
        shooterTwo = hardwareMap.get(DcMotor.class, "shooterTwo");

        // TODO: reverse any motors you need
        // we had two motors for our pivot
        shooterOne.setDirection(DcMotorSimple.Direction.FORWARD);
        shooterTwo.setDirection(DcMotorSimple.Direction.REVERSE);

        // if you use encoders this is needed
        shooterOne.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooterTwo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        shooterOne.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooterTwo.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // when setPower(0) -> motors brake
        shooterOne.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooterTwo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // TODO: assign and set up IMU here
        imu = hardwareMap.get(IMU.class, "imu");
        // Reconfiguring IMU orientation
        myIMUparameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(
                        // TODO: this is what you'll need to change this is for the orientation of your control hub
                        RevHubOrientationOnRobot.LogoFacingDirection.UP, // what direction the logo is facing
                        RevHubOrientationOnRobot.UsbFacingDirection.RIGHT // what direction the USB is facing
                )
        );
        imu.initialize(myIMUparameters);
        imu.resetYaw();
    }
}