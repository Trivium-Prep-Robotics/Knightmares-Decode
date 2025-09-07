package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;


import org.firstinspires.ftc.teamcode.util.Parts;
import org.firstinspires.ftc.teamcode.util.Robot;
import org.firstinspires.ftc.teamcode.util.AprilTagDetectionPipeline;


/**
 * This is a template for a teleOp
 * It should allow you to move field centric and has the base structure of teleOp along with configuring the robot
 */
@TeleOp (name = "teleOp", group = "TELEOP")
public class teleOpTemplate extends LinearOpMode {
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    // calibration for C920 webcam if you have different camera then uhh change it ig??
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    public void runOpMode() throws InterruptedException {
        Parts config = new Parts(hardwareMap); // configure robot
        Robot robot = new Robot(); // configure robot
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {
                telemetry.addData("camera error: ", errorCode);
                telemetry.update();
            }
        });

        waitForStart(); // initialize

        // set the power used for arm motors to 1
        robot.armPower(1); // set arm power
        robot.extendPower(1); // set extend power

        robot.openClosePose(0.4, 0.8); // set claw positions
        robot.sampSpecPose(0.075, 0.6); // set wrist positions

        while (opModeIsActive()) {

            // arm controls
            robot.up(gamepad2.dpad_up);
            robot.down(gamepad2.dpad_down);
            // I put this in my code so that it knew when to stop moving
//            arm.armStop(!(gamepad2.dpad_up || gamepad2.dpad_down));

            // extend controls
            robot.extend(gamepad2.right_trigger);
            robot.retract(gamepad2.left_trigger);
            // I put this in my code so that it knew when to stop moving
//            arm.slideStop((gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0 && !gamepad2.dpad_up && !gamepad2.dpad_down));

            // claw controls
            robot.grabs(gamepad2.right_bumper);
            robot.drops(gamepad2.left_bumper);

            // wrist controls
            robot.specimen(gamepad2.y);
            robot.sample(gamepad2.a);

            // sets field centric drive, if you would like robot centric or tank uncomment which one you want but make sure no more than one is uncommented
            robot.feildCentric(gamepad1);
//            robot.robotCentric(gamepad1);
//            robot.tankDrive(gamepad1);
        }
    }
}
