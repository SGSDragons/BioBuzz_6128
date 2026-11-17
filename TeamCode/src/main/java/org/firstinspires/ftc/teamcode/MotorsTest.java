package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name="Operator Motor Testing")
public class MotorsTest extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Operate operate = new Operate(gamepad1, hardwareMap);
        Drive drive = new Drive(gamepad1, hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

            run("shooter 0", gamepad1.a, operate.shooters.get(0));
            run("shooter 1", gamepad1.b, operate.shooters.get(1));
            run("intake", gamepad1.x, operate.intake);
            run("output", gamepad1.y, operate.output);
        }
    }

    public void run(String name, boolean button, DcMotor motor) {
        if (button) {
            motor.setPower(0.8);
            telemetry.addData("Running shooter 0", "true");
        } else {
            motor.setPower(0.0);
        }
    }
}
