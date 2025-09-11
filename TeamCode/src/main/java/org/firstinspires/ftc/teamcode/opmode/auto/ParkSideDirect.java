package org.firstinspires.ftc.teamcode.opmode.auto;

import com.pedropathing.paths.Path;
import dev.nextftc.ftc.NextFTCOpMode;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.geometry.BezierLine;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.common.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.common.util.Shooter;

import org.firstinspires.ftc.teamcode.common.util.Parts;
import org.firstinspires.ftc.teamcode.common.util.Robot;


@Autonomous (name = "ParkSideDirect", group = "Decode")
public class ParkSideDirect extends NextFTCOpMode {
    private Follower follower;
    private PathChain scoring;
    private PathChain goingtohuman;
    private PathChain scoring2;
    private PathChain goingtohuman2;
    private PathChain scoring3;
    public static final Pose startingpose = new Pose(56, 8, Math.toRadians(0));
    public static final Pose scorepose = new Pose(28.14, 120.52, Math.toRadians(0));
    public static final Pose humanplayer = new Pose(12.63, 11.52, Math.toRadians(0));
    @Override
    public void onInit() throws InterruptedException {
        Parts config = new Parts(hardwareMap); // configure robot
        Robot robot = new Robot(); // configure robot

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(startingpose);
        buildPaths(); // path builder for park

        // go park which is going after the line and free 3 points!!!!!!!!
        follower.followPath(scoring);
        while (follower.isBusy()) {
            follower.update();
        }

        Shooter.shootauto();

        follower.followPath(goingtohuman);
        while (follower.isBusy()) {
            follower.update();
        }

        follower.followPath(scoring2);
        while (follower.isBusy()) {
            follower.update();
        }

        Shooter.shootauto();

        follower.followPath(goingtohuman2);
        while (follower.isBusy()) {
            follower.update();
        }

        follower.followPath(scoring3);
        while (follower.isBusy()) {
            follower.update();
        }

        Shooter.shootauto();
    }

    public void buildPaths() {
        // basic parking path
        //new Path(new BezierLine(killingmyself67, leavingline));
        scoring = follower.pathBuilder()
                .addPath(new BezierLine(startingpose, scorepose))
                .setConstantHeadingInterpolation(startingpose.getHeading())
                .build();

        goingtohuman = follower.pathBuilder()
                .addPath(new BezierLine(scorepose, humanplayer))
                .setConstantHeadingInterpolation(scorepose.getHeading())
                .build();

        scoring2 = follower.pathBuilder()
                .addPath(new BezierLine(humanplayer, scorepose))
                .setConstantHeadingInterpolation(humanplayer.getHeading())
                .build();

        goingtohuman2 = follower.pathBuilder()
                .addPath(new BezierLine(scorepose, humanplayer))
                .setConstantHeadingInterpolation(scorepose.getHeading())
                .build();

        scoring3 = follower.pathBuilder()
                .addPath(new BezierLine(humanplayer, scorepose))
                .setConstantHeadingInterpolation(humanplayer.getHeading())
                .build();
    }
}
