package org.firstinspires.ftc.teamcode.opmode.teleOp;

import dev.nextftc.ftc.NextFTCOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.common.pedroPathing.Constants;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;


import org.firstinspires.ftc.teamcode.common.util.Parts;
import org.firstinspires.ftc.teamcode.common.util.Robot;
import org.firstinspires.ftc.teamcode.common.util.AprilTagDetectionPipeline;


/**
 * This is a template for a teleOp
 * It should allow you to move field centric and has the base structure of teleOp along with configuring the robot
 */
@TeleOp (name = "teleOp", group = "TELEOP")
public class teleOp extends NextFTCOpMode {
    OpenCvCamera camera;
    private Follower follower;
    private final Pose startingpose = new Pose(0,0,Math.toRadians(0));
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    // calibration for C920 webcam if you have different camera then uhh change it ig??
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    public void onInit() {
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
                telemetry.addData("Camera Error: ", errorCode);
                telemetry.update();
            }
        });

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startingpose);
        follower.update();
        follower.startTeleopDrive();


    }

    @Override
    public void onStartButtonPressed() {
        follower.setTeleOpDrive(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, false); // idk for isrobotcentric so I'm thinking no???
        //follower.update();
    }
    @Override
    public void onUpdate() {
        telemetry.addData("X", follower.getPose().getX());
        telemetry.addData("Y", follower.getPose().getY());
        telemetry.addData("Heading in Degrees", Math.toDegrees(follower.getPose().getHeading()));
        telemetry.update();
        follower.update(); // maybe??
    }

    public void tagToTelemetry(AprilTagDetection detection) {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        //telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x * FEET_PER_METER));
        //telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y * FEET_PER_METER));
        //telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z * FEET_PER_METER));
        //telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", Math.toDegrees(detection.pose.yaw)));
        //telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", Math.toDegrees(detection.pose.pitch)));
        //telemetry.addLine(String.format("Rotation Roll: %.2f degrees", Math.toDegrees(detection.pose.roll)));
    }
}
