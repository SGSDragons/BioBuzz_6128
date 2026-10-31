package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MotorDrive extends LinearOpMode {
    public void runOpMode() {

        DcMotor leftFront  = hardwareMap.get(DcMotor.class, "port0");
        DcMotor rightFront = hardwareMap.get(DcMotor.class, "port1");
        DcMotor leftBack= hardwareMap.get(DcMotor.class, "port2");
        DcMotor rightBack = hardwareMap.get(DcMotor.class, "port3");

        waitForStart();

        while (opModeIsActive()) {
            final double forwardPower = gamepad1.left_stick_y;
            leftFront.setPower(forwardPower);
            leftBack.setPower(forwardPower);
            rightFront.setPower(forwardPower);
            rightBack.setPower(forwardPower);
        }

    }
}
