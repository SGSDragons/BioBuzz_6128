package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.drivetrain.Drivetrain;
import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Localizer;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.ThreeWheelConfig;
import com.pedropathing.revhub.localizers.ThreeWheelLocalizer;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {
    public static Follower create(HardwareMap h) {
        return new Follower(getLocalizer(h), getDriveTrain(h), getForesight());//Foresight);
        // return null;
    }

    public static Drivetrain getDriveTrain (HardwareMap h) {
        return new Mecanum(h, drivetrainConfig);
    }

    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("port2");
        c.frontRightName.set("port1");
        c.backLeftName.set("port3");
        c.backRightName.set("port0");
        c.frontLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.frontRightDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backRightDirection.set(DcMotorSimple.Direction.REVERSE);
    });
    public static Localizer getLocalizer (HardwareMap h) {
        return new ThreeWheelLocalizer(h, new ThreeWheelConfig(c -> {})); //TODO tune localizer
    }
    public static Foresight getForesight () {
        return new Foresight(new ForesightConfig(c -> {})); //TODO tune foresight algorithm
    }
}