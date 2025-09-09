package org.firstinspires.ftc.teamcode.opmode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import dev.nextftc.ftc.NextFTCOpMode;

@TeleOp (name = "Next OpMode Test", group = "Decode")
public class nextftcoptest extends NextFTCOpMode {
    @Override
    public void onInit() {
        telemetry.addData("Init called!", "yes");
        telemetry.update();
    }

    @Override
    public void onWaitForStart() {
        telemetry.addData("WaitForStart called!", "yes");
        telemetry.update();
    }

    @Override
    public void onStartButtonPressed() {
        telemetry.addData("StartButtonPressed called!", "yes");
        telemetry.update();
    }

    @Override
    public void onUpdate() {
        telemetry.addData("Update called!", "");
        telemetry.update();
    }

    @Override
    public void onStop() {
        telemetry.addData("Stop called!", "");
        telemetry.update();
    }

}