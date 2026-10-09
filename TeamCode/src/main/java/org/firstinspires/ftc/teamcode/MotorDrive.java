package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp(name="Robot Driving")
public class MotorDrive extends LinearOpMode {
    @Override
    public void runOpMode() {
        Follower follower = Constants.create(hardwareMap);

        waitForStart();
        while (opModeIsActive()) {
            double forward = -gamepad1.left_stick_y;
            double lateral = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;
            follower.manual(forward, lateral, turn);
            follower.update();
        }
    }

}
