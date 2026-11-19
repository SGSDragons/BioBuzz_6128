package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;

@Config

public class Operate {

    public static double pollenSpeed = 2650;
    public static double nectarSpeed = 3850;
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
                shoot(Mode.OFF);
            }
        }

        // Pull in with positive power. Eject with negative
        if (gamepad.left_trigger > 0.5) {
            intake.setPower(intakePower);
        } else if (gamepad.left_bumper){
            intake.setPower(-intakePower);
        } else {
            intake.setPower(0.0);
        }

        if (gamepad.right_trigger > 0.5) {
            output.setPower(outputPower);
        } else if (gamepad.right_bumper){
            output.setPower(-outputPower);
        } else {
            output.setPower(0.0);
        }

    }

    public enum Mode {
        POLLEN,
        NECTAR,
        OFF;
    }

    public void shoot(Mode mode) {
        final double target;
        switch (mode) {
            case POLLEN: target = pollenSpeed; break;
            case NECTAR: target = nectarSpeed; break;
            default: target = 0.0;
        }

        for (DcMotorEx s : shooters) {
            if (target > 0.0) {
                s.setVelocity(target);
            } else {
                s.setPower(0.0);
            }
        }

        TelemetryPacket p = new TelemetryPacket();
        p.put("shooter 0", shooters.get(0).getVelocity());
        p.put("shooter 1", shooters.get(1).getVelocity());
        p.put("target", target);
        p.put("outputPower", output.getPower());
        p.put("intakePower", intake.getPower());
        FtcDashboard.getInstance().sendTelemetryPacket(p);

    }
    public void ballDrive(double ison){
        if (ison==1){
            output.setPower(1);
        } else if (ison==0){
            output.setPower(0);
        }
    }
}
