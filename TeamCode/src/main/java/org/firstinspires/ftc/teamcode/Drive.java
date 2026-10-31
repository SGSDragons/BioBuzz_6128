package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drive {

    final Gamepad gamepad;
    final DcMotor frontLeftDrive;
    final DcMotor backLeftDrive;
    final DcMotor frontRightDrive;
    final DcMotor backRightDrive;

    public Drive(Gamepad gamepad, HardwareMap hardwareMap) {
        this.gamepad = gamepad;
        this.frontLeftDrive = hardwareMap.get(DcMotor.class, "port0");
        this.backLeftDrive = hardwareMap.get(DcMotor.class, "port2");
        this.frontRightDrive = hardwareMap.get(DcMotor.class, "port1");
        this.backRightDrive = hardwareMap.get(DcMotor.class, "port3");
        this.frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        this.backRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        this.frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        this.backLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void update() {
        drive(
            -gamepad.left_stick_y,  // Note: pushing stick forward gives negative value
            gamepad.left_stick_x,
            gamepad.right_stick_x
        );
    }

    public void drive(double forward, double strafe, double turn) {

        // Combine the joystick requests for each axis-motion to determine each wheel's power.
        // Set up a variable for each drive wheel to save the power level for telemetry.
        double frontLeftPower  = forward + strafe + turn;
        double frontRightPower = forward - strafe - turn;
        double backLeftPower   = forward - strafe + turn;
        double backRightPower  = forward + strafe - turn;

        // Normalize the values so no wheel power exceeds 100%
        // This ensures that the robot maintains the desired motion.
        double max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
        max = Math.max(max, Math.abs(backLeftPower));
        max = Math.max(max, Math.abs(backRightPower));


        frontLeftPower  *= 0.7;
        frontRightPower *= 0.7;
        backLeftPower   *= 0.7;
        backRightPower  *= 0.7;


        // Send calculated power to wheels
        frontLeftDrive.setPower(frontLeftPower);
        frontRightDrive.setPower(frontRightPower);
        backLeftDrive.setPower(backLeftPower);
        backRightDrive.setPower(backRightPower);
    }
}
