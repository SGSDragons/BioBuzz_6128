package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Operate Only")
public class OperateOnly extends LinearOpMode {
    public void runOpMode() {
        final Operate operate = new Operate(gamepad1, hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            operate.update();
            operate.reconfigure();
        }
    }
}
