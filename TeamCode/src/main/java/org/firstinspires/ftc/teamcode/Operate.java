package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;

@Config

public class Operate {

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
        this.shooters.get(0).setDirection(DcMotorSimple.Direction.REVERSE);
        this.intake.setDirection(DcMotorSimple.Direction.REVERSE);
        this.output.setDirection(DcMotorSimple.Direction.REVERSE);

        for (DcMotorEx s : shooters) {
            s.setVelocityPIDFCoefficients(20, 0, 0, 0);
        }
    }

    public void update() {

        for (DcMotorEx s : shooters) {
            if (gamepad.b) {
                shoot(Mode.NECTAR);
            } else if (gamepad.a) {
                shoot(Mode.POLLEN);

            } else {
                s.setPower(0);
            }
        }

        // Pull in with positive power. Eject with negative
        intake.setPower(gamepad.left_trigger);
        if (gamepad.left_bumper){
            intake.setPower(-intakePower);
        }

        output.setPower(gamepad.right_trigger);
        if (gamepad.right_bumper){
            output.setPower(-outputPower);
        }
    }

    public enum Mode {
        OFF(0),
        POLLEN(2350),
        NECTAR(3550);

        final int speed;
        Mode(int speed) { this.speed = speed; }
    }

    public void shoot(Mode mode) {
        for (DcMotorEx s : shooters) {
            switch (mode) {
                case POLLEN: s.setVelocity(2350); break;
                case NECTAR: s.setVelocity(3550); break;
                default: s.setPower(0.0);
            }
        }
    }
    public void ballDrive(double ison){
        if (ison==1){
            output.setPower(1);
        } else if (ison==0){
            output.setPower(0);
        }
    }
}
