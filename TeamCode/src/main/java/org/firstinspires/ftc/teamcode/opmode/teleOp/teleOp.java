package org.firstinspires.ftc.teamcode.opmode.teleOp;

import dev.nextftc.ftc.NextFTCOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import org.firstinspires.ftc.teamcode.common.pedroPathing.Constants;


import org.firstinspires.ftc.teamcode.common.util.Parts;
import org.firstinspires.ftc.teamcode.common.util.Robot;

@TeleOp (name = "teleOp", group = "TELEOP")
public class teleOp extends NextFTCOpMode {
    private Follower follower;
    private final Pose startingpose = new Pose(0,0,Math.toRadians(0));

    public void onInit() {
        Parts config = new Parts(hardwareMap); // configure robot
        Robot robot = new Robot(); // configure robot

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
}
