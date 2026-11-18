package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Drive Only")
public class DriveOnly extends LinearOpMode {
    public void runOpMode() {
        final Drive drive = new Drive(gamepad1, hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            drive.update();
        }
    }
}
