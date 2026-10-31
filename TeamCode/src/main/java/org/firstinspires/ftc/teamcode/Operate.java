package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;

@Config
public class Operate {

    public static double flyWheelSpeed;
    public static double kF;
    public static double kP;
    public static double kI;
    public static double kD;

    public static double intakePower = 1.0;
    public static double outputPower = 1.0;

    final Gamepad gamepad;
    final List<DcMotorEx> shooters;
    final DcMotorEx intake;
    final DcMotorEx output;

    public Operate(Gamepad gamepad, HardwareMap hardwareMap) {
        this.gamepad = gamepad;
        this.intake = hardwareMap.get(DcMotorEx.class, "exp0");
        this.output = hardwareMap.get(DcMotorEx.class, "exp1");
        this.shooters = List.of(
                hardwareMap.get(DcMotorEx.class, "exp2"),
                hardwareMap.get(DcMotorEx.class, "exp3"));
    }

    public void update() {

        intake.setPower(-gamepad.left_trigger);
        output.setPower(-gamepad.right_trigger);

        for (DcMotorEx s : shooters) {
            if (gamepad.b) {
                s.setVelocityPIDFCoefficients(kP, kI, kD, kF);
                s.setVelocity(flyWheelSpeed);
            } else {
                s.setPower(0);
            }
        }

        if (gamepad.left_bumper){
            intake.setPower(intakePower);
        }
        if (gamepad.right_bumper){
            output.setPower(outputPower);
        }

    }
}
