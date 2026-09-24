package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Motor tester")
public class MotorTester extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor leftFront  = hardwareMap.get(DcMotor.class, "port3");
        DcMotor rightFront = hardwareMap.get(DcMotor.class, "port1");
        DcMotor leftBack= hardwareMap.get(DcMotor.class, "port2");
        DcMotor rightBack = hardwareMap.get(DcMotor.class, "port0");

        rightFront.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.left_bumper) {
                leftFront.setPower(.5);
            } else {
                leftFront.setPower(0);
            }

            if (gamepad1.right_bumper)  {
                rightFront.setPower(.5);
            } else {
                rightFront.setPower(0);
            }

            rightBack.setPower(gamepad1.right_trigger);

            leftBack.setPower(gamepad1.left_trigger);
        }

    }
}
