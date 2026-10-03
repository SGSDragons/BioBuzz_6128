package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotorSimple

// Kotlin version of the previously used "MotorTester.java"

@TeleOp(name = "Drive Motor Tester")
class DriveMotorTester : LinearOpMode() {
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        val frontLeft = hardwareMap.get<DcMotorSimple>(DcMotorSimple::class.java, "port2")
        val frontRight = hardwareMap.get<DcMotorSimple>(DcMotorSimple::class.java, "port1")
        val backLeft = hardwareMap.get<DcMotorSimple>(DcMotorSimple::class.java, "port3")
        val backRight = hardwareMap.get<DcMotorSimple>(DcMotorSimple::class.java, "port0")

        frontRight.direction = DcMotorSimple.Direction.REVERSE
        backRight.direction = DcMotorSimple.Direction.REVERSE

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

            backRight.power = gamepad1.right_trigger.toDouble()

            backLeft.power = gamepad1.left_trigger.toDouble()
        }
    }
}