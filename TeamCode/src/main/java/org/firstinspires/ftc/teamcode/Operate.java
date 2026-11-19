package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

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

    public static double pollenSpeed = 2350;
    public static double nectarSpeed = 3550;
    public static double kF;
    public static double kP;
    public static double kI;
    public static double kD;

    public static double tolerance = 100;

    public Mode currentMode=Mode.OFF;

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

        intake.setPower(-gamepad.left_trigger);
        output.setPower(-gamepad.right_trigger);

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

        TelemetryPacket p1=new TelemetryPacket();
        TelemetryPacket p2=new TelemetryPacket();
        p1.put("shooter 0", shooters.get(0).getVelocity());
        p2.put("shooter 1", shooters.get(1).getVelocity());
        FtcDashboard.getInstance().sendTelemetryPacket(p1);
        FtcDashboard.getInstance().sendTelemetryPacket(p2);

    }

    public boolean ReadyToShoot() {
        if (this.currentMode==Mode.OFF){
            return true;
        }
        double avespeed=0;
        for (DcMotorEx shooter : shooters){
            avespeed+=shooter.getVelocity();
        }

        avespeed/=shooters.size();

        if (this.currentMode==Mode.POLLEN){
            return (Math.abs(avespeed-pollenSpeed)<=tolerance);
        }

        if (this.currentMode==Mode.NECTAR){
            return (Math.abs(avespeed-nectarSpeed)<=tolerance);
        }
        return false;
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
                case POLLEN: s.setVelocity(pollenSpeed); break;
                case NECTAR: s.setVelocity(nectarSpeed); break;
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
