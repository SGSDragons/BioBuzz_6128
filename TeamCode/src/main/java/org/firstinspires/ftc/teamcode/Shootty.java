package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name="shoot thing testing")
public class Shootty extends OpMode {

    public static double flyWheelSpeed = 0;
    DcMotorEx intake;
    DcMotorEx output;
    DcMotorEx shooter1;
    DcMotorEx shooter2;

    @Override
    public void init() {
        intake = hardwareMap.get(DcMotorEx.class, "exp0");
        output = hardwareMap.get(DcMotorEx.class, "exp1");
        shooter1 = hardwareMap.get(DcMotorEx.class, "exp2");
        shooter2 = hardwareMap.get(DcMotorEx.class, "exp3");

    }

    @Override
    public void loop() {
        intake.setPower(-gamepad2.left_trigger);
        output.setPower(-gamepad2.right_trigger);

        if (gamepad2.b) {
            shooter1.setVelocity(flyWheelSpeed);
            shooter2.setVelocity(flyWheelSpeed);
        } else {
            shooter1.setPower(0);
            shooter2.setPower(0);
        }
        if (gamepad2.left_bumper){
            intake.setPower(1);
        }
        if (gamepad2.right_bumper){
            output.setPower(1);
        }
    }
}