package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Competition driving")
public class CompetitionMode extends OpMode {
    private Drive drive;
    private Operate operate;

    @Override
    public void init() {
        drive = new Drive(gamepad1, hardwareMap);
        operate = new Operate(gamepad2, hardwareMap);
    }

    @Override
    public void loop() {
        drive.update();
        operate.update();
    }
}
