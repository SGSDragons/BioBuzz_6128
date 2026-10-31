package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple

// Kotlin version of the previously used "MotorTester.java"

@TeleOp(name = "Drive Motor/Odometry Tester")
class DriveMotorTester : LinearOpMode() {
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        val frontLeft = hardwareMap.get(DcMotorEx::class.java, "exp2")
        val frontRight = hardwareMap.get(DcMotorEx::class.java, "exp3")
        val backLeft = hardwareMap.get(DcMotorEx::class.java, "exp1")
        val backRight = hardwareMap.get(DcMotorEx::class.java, "exp0")
        val rightOdomWheel = hardwareMap.get(DcMotorEx::class.java, "port3")

        frontRight.direction = DcMotorSimple.Direction.FORWARD
        backRight.direction = DcMotorSimple.Direction.FORWARD
        frontLeft.direction = DcMotorSimple.Direction.REVERSE
        frontRight.direction = DcMotorSimple.Direction.FORWARD

        waitForStart()
        while (opModeIsActive()) {
            if (gamepad1.left_bumper) {
                frontLeft.power = 0.5
            } else {
                frontLeft.power = 0.0
            }

            if (gamepad1.right_bumper) {
                frontRight.power = 0.5
            } else {
                frontRight.power = 0.0
            }

            backRight.power = gamepad1.right_trigger.toDouble()/2

            backLeft.power = gamepad1.left_trigger.toDouble()/2
        }
    }
}