package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.geometry.BezierLine;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.common.pedroPathing.Constants;

import org.firstinspires.ftc.teamcode.common.util.Parts;
import org.firstinspires.ftc.teamcode.common.util.Robot;
import org.firstinspires.ftc.teamcode.common.util.Shooter;


@Autonomous (name = "ParkSideFar", group = "Decode")
public class ParkSideFar extends LinearOpMode {
    private Follower follower;
    private PathChain parkingpath;
    private PathChain humanplayers;
    private PathChain score;
    private PathChain humanplayers2;
    private PathChain score2;
    public static final Pose startingpose = new Pose(30.66, 130.05, Math.toRadians(0));
    public static final Pose scorepose = new Pose(28.14, 120.52, Math.toRadians(0));
    public static final Pose humanplayer = new Pose(12.63, 11.52, Math.toRadians(0));
    public void runOpMode() throws InterruptedException {
        Parts config = new Parts(hardwareMap); // configure robot
        Robot robot = new Robot(); // configure robot

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startingpose);
        buildPaths(); // path builder for park

        waitForStart();

        // go park which is going after the line and free 3 points!!!!!!!!
        follower.followPath(parkingpath);
        while (follower.isBusy()) {
            follower.update();
        }

        Shooter.shootauto();

        follower.followPath(humanplayers);
        while (follower.isBusy()) {
            follower.update();
        }

        follower.followPath(score);
        while (follower.isBusy()) {
            follower.update();
        }

        Shooter.shootauto();

        follower.followPath(humanplayers2);
        while (follower.isBusy()) {
            follower.update();
        }

        follower.followPath(score2);
        while (follower.isBusy()) {
            follower.update();
        }

        Shooter.shootauto();
    }

    public void buildPaths() {
        // basic parking path
        //new Path(new BezierLine(startingpose, leavingline));
        parkingpath = follower.pathBuilder()
                .addPath(new BezierLine(startingpose, scorepose))
                .setConstantHeadingInterpolation(startingpose.getHeading())
                .build();

        humanplayers = follower.pathBuilder()
                .addPath(new BezierLine(scorepose, humanplayer))
                .setConstantHeadingInterpolation(scorepose.getHeading())
                .build();

        score = follower.pathBuilder()
                .addPath(new BezierLine(humanplayer, scorepose))
                .setConstantHeadingInterpolation(humanplayer.getHeading())
                .build();

        humanplayers2 = follower.pathBuilder()
                .addPath(new BezierLine(scorepose, humanplayer))
                .setConstantHeadingInterpolation(scorepose.getHeading())
                .build();

        score2 = follower.pathBuilder()
                .addPath(new BezierLine(humanplayer, scorepose))
                .setConstantHeadingInterpolation(humanplayer.getHeading())
                .build();
    }
}