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

import kotlin.ResultKt;

@Config

public class Operate {

    public static double flyWheelSpeed;
    public static double flyWheelSpeed2;
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
        this.shooters.get(1).setDirection(DcMotorSimple.Direction.REVERSE);
        this.intake.setDirection(DcMotorSimple.Direction.FORWARD);
        this.output.setDirection(DcMotorSimple.Direction.FORWARD);

    }


    public void update() {

        intake.setPower(-gamepad.left_trigger);
        output.setPower(-gamepad.right_trigger);

        for (DcMotorEx s : shooters) {
            if (gamepad.b) {
                s.setVelocityPIDFCoefficients(20, 0, 0, 0);
                s.setVelocity(-flyWheelSpeed);
            } else if (gamepad.a) {
                s.setVelocityPIDFCoefficients(20, 0, 0, 0);
                s.setVelocity(-flyWheelSpeed2);

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
        TelemetryPacket p1=new TelemetryPacket();
        TelemetryPacket p2=new TelemetryPacket();
        p1.put("shooter 0", shooters.get(0).getVelocity());
        p2.put("shooter 1", shooters.get(1).getVelocity());
        FtcDashboard.getInstance().sendTelemetryPacket(p1);
        FtcDashboard.getInstance().sendTelemetryPacket(p2);

    }

    public void shoot(double mode) {
        for (DcMotorEx s : shooters) {
            s.setVelocityPIDFCoefficients(20, 0, 0, 0);
            if (mode == 1) {
                s.setVelocity(-2350);
            }
            else if (mode == 2){
                s.setVelocity(-2650);
            }
            else {
                s.setPower(0);
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