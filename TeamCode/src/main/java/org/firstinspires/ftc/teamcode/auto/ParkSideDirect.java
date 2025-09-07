package org.firstinspires.ftc.teamcode.auto;

import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.geometry.BezierLine;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import org.firstinspires.ftc.teamcode.util.Parts;
import org.firstinspires.ftc.teamcode.util.Robot;


@Autonomous (name = "ParkSideDirect", group = "Decode")
public class ParkSideDirect extends LinearOpMode {
    private Follower follower;
    private PathChain wtvthefuckitis;
    public static final Pose killingmyself67 = new Pose(63.64, 2.62, Math.toRadians(0));
    public static final Pose leavingline = new Pose(64.23, 37.05, Math.toRadians(0));
    public void runOpMode() throws InterruptedException {
        Parts config = new Parts(hardwareMap); // configure robot
        Robot robot = new Robot(); // configure robot

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(killingmyself67);
        buildPaths(); // path builder for park

        waitForStart();

        // go park which is going after the line and free 3 points!!!!!!!!
        follower.followPath(wtvthefuckitis);
        while (follower.isBusy()) {
            follower.update();
        }
    }

    public void buildPaths() {
        // basic parking path
        //new Path(new BezierLine(killingmyself67, leavingline));
        wtvthefuckitis = follower.pathBuilder()
                .addPath(new BezierLine(killingmyself67, leavingline))
                .setConstantHeadingInterpolation(killingmyself67.getHeading())
                .build();
    }
}
